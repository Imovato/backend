package com.example.rent.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Value("${crud.rabbitmq.exchanges.exchangeAccommodations}")
    private String exchangeAccommodations;

    @Value("${crud.rabbitmq.exchanges.exchangeUsers}")
    private String exchangeUsers;

    @Value("${crud.rabbitmq.queues.accommodationQueue}")
    private String accommodationQueue;

    @Value("${crud.rabbitmq.queues.userQueue}")
    private String userQueue;

    // Exchanges
    @Bean
    public FanoutExchange accommodationsExchange() {
        return new FanoutExchange(exchangeAccommodations, true, false);
    }

    @Bean
    public FanoutExchange usersExchange() {
        return new FanoutExchange(exchangeUsers, true, false);
    }

    // Queues
    @Bean
    public Queue accommodationQueue() {
        return new Queue(accommodationQueue, true);
    }

    @Bean
    public Queue userQueue() {
        return new Queue(userQueue, true);
    }

    // Bindings
    @Bean
    public Binding bindingAccommodation() {
        return BindingBuilder.bind(accommodationQueue()).to(accommodationsExchange());
    }

    @Bean
    public Binding bindingUser() {
        return BindingBuilder.bind(userQueue()).to(usersExchange());
    }
}

