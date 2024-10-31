package com.example.rent.receiver;

import com.example.rent.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserReceiver {

	private UserRepository userRepository;
	
	@Autowired
	public UserReceiver(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
//	@RabbitListener(queues = {"${crud.rabbitmq.queueUser}"})
//	public void receive(@Payload Customer customer) {
//		customerRepository.save(customer);
//	}
	
}
