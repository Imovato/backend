package com.unipampa.crud.sender;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.unipampa.crud.model.Hosting;

@Component
public class PropertySender {
	
	@Value("${crud.rabbitmq.exchange}")
	String exchange;
	
	@Value("${crud.rabbitmq.routingkeyProperty}")
	String routingkey;
	
	public RabbitTemplate rabbitTemplate;

	@Autowired
	public PropertySender(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}
	
	public void sendMessage(Hosting hosting) {
		rabbitTemplate.convertAndSend(exchange, routingkey, hosting);
	}
	
}
