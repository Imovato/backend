package com.example.rent.receiver;


import com.example.rent.dto.AccommodationDTO;
import com.example.rent.enums.Status;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import com.example.rent.entities.Accommodation;
import com.example.rent.repository.AccommodationRepository;


@Component
public class AccommodationReceiver {
	
	private AccommodationRepository accommodationRepository;
	
	@Autowired
	public AccommodationReceiver(AccommodationRepository accommodationRepository) {
		this.accommodationRepository = accommodationRepository;
	}
	
	@RabbitListener(queues = {"${crud.rabbitmq.queues.accommodationQueue}"})
	public void receive(@Payload AccommodationDTO dto) {
		Accommodation accommodation = new Accommodation();
		accommodation.setPrice(dto.price());
		accommodation.setStatus(Status.AVAILABLE);
		accommodationRepository.save(accommodation);
	}
}
