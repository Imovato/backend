package com.example.acquisition.receiver;


import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.example.acquisition.model.Property;
import com.example.acquisition.repository.PropertyRepository;




@Component
public class PropertyReceiver {
	
	private PropertyRepository propertyRepository;
	
	@Autowired
	public PropertyReceiver(PropertyRepository propertyRepository) {
		this.propertyRepository = propertyRepository;
	}
	
	@RabbitListener(queues = {"${crud.rabbitmq.queueProperty}"})
	public void receive(@Payload Property property) {
		propertyRepository.save(property);
	}
}
