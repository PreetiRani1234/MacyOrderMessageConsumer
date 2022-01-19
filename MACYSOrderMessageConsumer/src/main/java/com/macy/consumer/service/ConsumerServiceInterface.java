package com.macy.consumer.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.macy.consumer.dto.json.ConsumerJson;
import com.macy.consumer.dto.xml.FulfillmentOrder;

public interface ConsumerServiceInterface {
	
	ResponseEntity<List<FulfillmentOrder>> getconsumerXmlMessage();

    ResponseEntity<List<ConsumerJson>> getconsumerJsonMessage();


}
