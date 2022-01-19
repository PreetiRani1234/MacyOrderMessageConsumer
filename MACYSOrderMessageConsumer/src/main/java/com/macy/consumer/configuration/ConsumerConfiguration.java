package com.macy.consumer.configuration;

import com.macy.consumer.utils.AppConstant;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConsumerConfiguration {

    @Bean
    Queue jsonQueue() {
        return new Queue(AppConstant.JSON_QUEUE, true);
    }

    @Bean
    Queue xmlQueue() {
        return new Queue(AppConstant.XML_QUEUE, true);
    }

    @Bean
    DirectExchange exchange() {
        return new DirectExchange(AppConstant.EXCHANGE);
    }

    @Bean
    Binding binding1(DirectExchange exchange) {
        return BindingBuilder.bind(jsonQueue()).to(exchange).with(jsonQueue().getName());
    }

    @Bean
    Binding binding2(DirectExchange exchange) {
        return BindingBuilder.bind(xmlQueue()).to(exchange).with(xmlQueue().getName());
    }

    @Bean
    public AmqpTemplate jsonAmqpTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setRoutingKey(AppConstant.ROUTING_KEY);
        rabbitTemplate.setDefaultReceiveQueue(jsonQueue().getName());
        return rabbitTemplate;
    }

    @Bean
    public AmqpTemplate xmlAmqpTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setRoutingKey(AppConstant.ROUTING_KEY);
        rabbitTemplate.setDefaultReceiveQueue(xmlQueue().getName());
        return rabbitTemplate;
    }

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }
}