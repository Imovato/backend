package com.example.rent.receiver;

import com.example.rent.dto.UserDto;
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
	public void receive(@Payload UserDto dto) {
		User user = new User();
		user.setId(dto.id());
		user.setName(dto.name());
		user.setEmail(dto.email());
		user.setUserType(dto.type());
		userRepository.save(user);
	}
	
}
