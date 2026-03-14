package com.unipampa.crud.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitAccommodationStatusConfig {

    @Value("${crud.rabbitmq.exchanges.exchangeAccommodations}")
    private String exchangeAccommodations;

    @Value("${crud.rabbitmq.queues.accommodationStatusQueue}")
    private String accommodationStatusQueue;

    @Bean
    public FanoutExchange accommodationsExchange() {
        return new FanoutExchange(exchangeAccommodations, true, false);
    }

    @Bean
    public Queue accommodationStatusQueue() {
        return new Queue(accommodationStatusQueue, true);
    }

    @Bean
    public Binding bindingAccommodationStatusQueue() {
        return BindingBuilder.bind(accommodationStatusQueue()).to(accommodationsExchange());
    }
}

