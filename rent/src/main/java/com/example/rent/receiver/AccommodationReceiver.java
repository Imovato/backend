package com.example.rent.receiver;


import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import com.example.rent.entities.Accommodation;
import com.example.rent.repository.AccommodationRepository;


@Component
public class PropertyReceiver {
	
	private AccommodationRepository accommodationRepository;
	
	@Autowired
	public PropertyReceiver(AccommodationRepository accommodationRepository) {
		this.accommodationRepository = accommodationRepository;
	}
	
	@RabbitListener(queues = {"${crud.rabbitmq.queues.accommodationQueue}"})
	public void receive(@Payload Accommodation property) {
		accommodationRepository.save(property);
	}
}
