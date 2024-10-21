package com.unipampa.crud.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageConfig {

	@Value("${crud.rabbitmq.exchanges.exchangeAccommodations}")
	private String exchangeAccommodations;

	@Value("${crud.rabbitmq.exchanges.exchangeUsers}")
	private String exchangeUsers;

	@Value("${crud.rabbitmq.queues.accommodationQueue}")
	private String accommodationQueue;

	@Value("${crud.rabbitmq.queues.userQueue}")
	private String userQueue;

	@Bean
	public Exchange exchangeAccommodations() {
		return ExchangeBuilder.fanoutExchange(exchangeAccommodations).durable(true).build();
	}

	@Bean
	public Exchange exchangeUsers() {
		return ExchangeBuilder.fanoutExchange(exchangeUsers).durable(true).build();
	}

	@Bean
	public Queue propertyQueue() {
		return new Queue(accommodationQueue, true);
	}

	@Bean
	public Queue userQueue() {
		return new Queue(userQueue, true);
	}

	@Bean
	public Binding bindingAccomodationQueue(@Qualifier("exchangeAccommodations") FanoutExchange fanoutExchange, Queue propertyQueue) {
		return BindingBuilder.bind(propertyQueue).to(fanoutExchange);
	}

	@Bean
	public Binding bindingUserQueue(@Qualifier("exchangeUsers") FanoutExchange fanoutExchange, Queue userQueue) {
		return BindingBuilder.bind(userQueue).to(fanoutExchange);
	}

	@Bean
	public MessageConverter jsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}

}
