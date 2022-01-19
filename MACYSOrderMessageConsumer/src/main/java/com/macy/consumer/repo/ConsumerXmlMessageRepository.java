package com.macy.consumer.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.macy.consumer.entity.xml.FulfillmentOrderEntity;

public interface ConsumerXmlMessageRepository extends JpaRepository<FulfillmentOrderEntity, Integer> {
}