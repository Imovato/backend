package com.unipampa.scheduling.message;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.unipampa.scheduling.model.Customer;
import com.unipampa.scheduling.repository.CustomerRepository;

@Component
public class CustomerReceiveMessage {
	
	private CustomerRepository customerRepository;
	
	@Autowired
	public CustomerReceiveMessage(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}
	
	@RabbitListener(queues = {"${crud.rabbitmq.queue}"})
	public void receive(@Payload Customer customer) {
		customerRepository.save(customer);
	}

}
