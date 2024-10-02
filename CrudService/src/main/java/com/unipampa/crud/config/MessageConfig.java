package com.unipampa.crud.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageConfig {

	@Value("${crud.rabbitmq.crudQueue}")
	private String queueCrud;

	@Value("${crud.rabbitmq.exchange}")
	private String exchange;

//	@Value("${crud.rabbitmq.authExchange}")
//	private String authExchange;

	@Bean
	public Exchange declareExchange() {
		return ExchangeBuilder.fanoutExchange(exchange).durable(true).build();
	}

//	@Bean
//	public Exchange declareExchangeAuth() {
//		return ExchangeBuilder.directExchange(authExchange).durable(true).build();
//	}

	@Bean
	public Queue queueOne() {
		return new Queue(queueCrud, true);
	}

	@Bean
	public Binding bindingQueueOne(FanoutExchange fanoutExchange, Queue queueOne) {
		return BindingBuilder.bind(queueOne).to(fanoutExchange);
	}

	@Bean
	public MessageConverter jsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}

}
