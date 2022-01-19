package com.macy.consumer.utils;


import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

import com.macy.consumer.dto.json.ConsumerJson;
import com.macy.consumer.dto.xml.FulfillmentOrder;
import com.macy.consumer.entity.json.ConsumerJsonEntity;
import com.macy.consumer.entity.xml.FulfillmentOrderEntity;

public class EntityPojoConverterUtil {

    public static ConsumerJson jsonEntityToPojo(ModelMapper modelMapper, ConsumerJsonEntity orderMessageJsonEntity) {
        return modelMapper.map(orderMessageJsonEntity, ConsumerJson.class);
    }

    public static ConsumerJsonEntity jsonPojoToEntity(ModelMapper modelMapper, ConsumerJson orderMessageJson) {
        return modelMapper.map(orderMessageJson, ConsumerJsonEntity.class);
    }

    public static FulfillmentOrderEntity xmlPojoToEntity(ModelMapper modelMapper, FulfillmentOrder fulfillmentOrder) {
        return modelMapper.map(fulfillmentOrder, FulfillmentOrderEntity.class);
    }

    public static FulfillmentOrder xmlEntityToPojo(ModelMapper modelMapper, FulfillmentOrderEntity fulfillmentOrderEntity) {
        return modelMapper.map(fulfillmentOrderEntity, FulfillmentOrder.class);
    }
}
