package com.macy.consumer.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.macy.consumer.entity.json.ConsumerJsonEntity;

public interface ConsumerJsonMessageRepository extends JpaRepository<ConsumerJsonEntity, Integer> {
}