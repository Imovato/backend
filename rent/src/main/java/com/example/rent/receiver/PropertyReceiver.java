package com.example.rent.receiver;


import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import com.example.rent.entities.Property;
import com.example.rent.repository.PropertyRepository;


@Component
public class PropertyReceiver {
	
	private PropertyRepository propertyRepository;
	
	@Autowired
	public PropertyReceiver(PropertyRepository propertyRepository) {
		this.propertyRepository = propertyRepository;
	}
	
	@RabbitListener(queues = {"${crud.rabbitmq.queues.accommodationQueue}"})
	public void receive(@Payload Property property) {
		propertyRepository.save(property);
	}
}
