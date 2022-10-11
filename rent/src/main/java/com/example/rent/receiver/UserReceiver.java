package com.example.rent.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import com.example.rent.model.User;
import com.example.rent.repository.UserRepository;

@Component
public class UserReceiver {

	private UserRepository userRepository;
	
	@Autowired
	public UserReceiver(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@RabbitListener(queues = {"${crud.rabbitmq.queueUser}"})
	public void receive(@Payload User user) {
		userRepository.save(user);
	}
	
}
