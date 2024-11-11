package com.example.rent.receiver;

import com.example.rent.dto.UserDTO;
import com.example.rent.entities.User;
import com.example.rent.repository.UserRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class UserReceiver {

	private UserRepository userRepository;
	
	@Autowired
	public UserReceiver(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@RabbitListener(queues = {"${crud.rabbitmq.queues.userQueue}"})
	public void receive(@Payload UserDTO dto) {
		User user = new User();
		user.setName(dto.getName());
		user.setEmail(dto.getEmail());
		user.setUserType(dto.getType());
		userRepository.save(user);
	}
	
}
