package com.example.rent.receiver;


import com.example.rent.dto.AccommodationDto;
import com.example.rent.enums.StatusAccommodation;
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
	public void receive(@Payload AccommodationDto dto) {
		Accommodation accommodation = new Accommodation();
		accommodation.setId(dto.id());
		accommodation.setPrice(dto.price());
		accommodation.setStatus(dto.stats());
		accommodation.setGuestCapacity(dto.maxOccupancy());
		accommodationRepository.save(accommodation);
	}
}
