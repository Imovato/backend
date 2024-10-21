package com.unipampa.crud.sender;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.unipampa.crud.model.User;

@Component
public class UserSender {

//	@Value("${crud.rabbitmq.exchange}")
	String exchange;

//	@Value("${crud.rabbitmq.routingkeyUser}")
//	String routingkey;

	public RabbitTemplate rabbitTemplate;

	@Autowired
	public UserSender(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}

	public void sendMessage(User user) {
		rabbitTemplate.convertAndSend(exchange, "", user);
	}

}
