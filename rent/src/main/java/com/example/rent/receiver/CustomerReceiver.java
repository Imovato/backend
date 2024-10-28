package com.example.rent.receiver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.rent.repository.CustomerRepository;

@Component
public class CustomerReceiver {

	private CustomerRepository customerRepository;
	
	@Autowired
	public CustomerReceiver(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}
	
//	@RabbitListener(queues = {"${crud.rabbitmq.queueUser}"})
//	public void receive(@Payload Customer customer) {
//		customerRepository.save(customer);
//	}
	
}
