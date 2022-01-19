package com.macy.consumer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.macy.consumer.dto.json.ConsumerJson;
import com.macy.consumer.dto.xml.FulfillmentOrder;
import com.macy.consumer.service.ConsumerServiceInterface;


@RestController
@RequestMapping("/macy/consumer")

public class ConsumerController {
	@Autowired
	ConsumerServiceInterface consumerServiceInterface;


    @GetMapping(value = "/xml",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    ResponseEntity<List<FulfillmentOrder>> getXmlMessage() {
        return consumerServiceInterface.getconsumerXmlMessage();
    }

    @GetMapping(value = "/json",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    ResponseEntity<List<ConsumerJson>> getJsonMessage() {
        return consumerServiceInterface.getconsumerJsonMessage();
    }

	
}
