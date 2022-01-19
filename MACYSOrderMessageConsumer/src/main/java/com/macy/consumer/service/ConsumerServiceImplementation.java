package com.macy.consumer.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.modelmapper.ModelMapper;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import com.macy.consumer.dto.json.ConsumerJson;
import com.macy.consumer.dto.xml.FulfillmentOrder;
import com.macy.consumer.entity.json.ConsumerJsonEntity;
import com.macy.consumer.entity.xml.FulfillmentOrderEntity;
import com.macy.consumer.exception.ErrorSavingDataToDatabaseException;
import com.macy.consumer.repo.ConsumerJsonMessageRepository;

import com.macy.consumer.repo.ConsumerXmlMessageRepository;
import com.macy.consumer.utils.AppConstant;
import com.macy.consumer.utils.EntityPojoConverterUtil;

public class ConsumerServiceImplementation implements ConsumerServiceInterface {

    @Autowired
    ConsumerXmlMessageRepository xmlMessageRepository;

    @Autowired
    ConsumerJsonMessageRepository jsonMessageRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    RabbitAdmin rabbitAdmin;

    @Autowired
    AmqpTemplate xmlAmqpTemplate;

    @Autowired
    AmqpTemplate jsonAmqpTemplate;

    @Override
    public ResponseEntity<List<FulfillmentOrder>> getconsumerXmlMessage() {
        List<FulfillmentOrder> fulfillmentOrderList = new ArrayList<>();
        Properties properties = rabbitAdmin.getQueueProperties(AppConstant.XML_QUEUE);
        int reqCount = (Integer) (properties != null ? properties.get(RabbitAdmin.QUEUE_MESSAGE_COUNT) : 0);
        for (int i = 0; i < reqCount; i++) {
            try {
                FulfillmentOrder fulfillmentOrder = new XmlMapper().readValue(new String((byte[]) xmlAmqpTemplate.receiveAndConvert()), FulfillmentOrder.class);
                FulfillmentOrderEntity entity = EntityPojoConverterUtil.xmlPojoToEntity(modelMapper, fulfillmentOrder);
                FulfillmentOrderEntity affectedEntity = null;
                try {
                    affectedEntity = xmlMessageRepository.save(entity);
                    fulfillmentOrderList.add(fulfillmentOrder);
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                } finally {
                    if (affectedEntity == null) {
                        throw new ErrorSavingDataToDatabaseException();
                    }
                }
            } catch (Exception e) {
                break;
            }
        }
        return new ResponseEntity<>(fulfillmentOrderList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ConsumerJson>> getconsumerJsonMessage() {
        List<ConsumerJson> orderMessageJsonList = new ArrayList<>();
        Properties properties = rabbitAdmin.getQueueProperties(AppConstant.JSON_QUEUE);
        int reqCount = (Integer) (properties != null ? properties.get(RabbitAdmin.QUEUE_MESSAGE_COUNT) : 0);
        for (int i = 0; i < reqCount; i++) {
            try {
            	ConsumerJson orderMessageJson = new ObjectMapper().readValue(new String((byte[]) jsonAmqpTemplate.receiveAndConvert()), ConsumerJson.class);
                ConsumerJsonEntity entity = EntityPojoConverterUtil.jsonPojoToEntity(modelMapper, orderMessageJson);
                ConsumerJsonEntity affectedEntity = null;
                try {
                    affectedEntity = jsonMessageRepository.save(entity);
                    orderMessageJsonList.add(orderMessageJson);
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                } finally {
                    if (affectedEntity == null) {
                        throw new ErrorSavingDataToDatabaseException();
                    }
                }
            } catch (Exception e) {
                break;
            }
        }
        return new ResponseEntity<>(orderMessageJsonList, HttpStatus.OK);
    }




}
