package com.unipampa.crud.receiver;

import com.unipampa.crud.dto.AccommodationStatusUpdateDto;
import com.unipampa.crud.entities.Accommodation;
import com.unipampa.crud.repository.AccommodationRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AccommodationStatusReceiver {

    private final AccommodationRepository accommodationRepository;

    public AccommodationStatusReceiver(AccommodationRepository accommodationRepository) {
        this.accommodationRepository = accommodationRepository;
    }

    @RabbitListener(queues = {"${crud.rabbitmq.queues.accommodationStatusQueue}"})
    public void receive(@Payload AccommodationStatusUpdateDto dto) {
        Optional<Accommodation> accommodationOptional = accommodationRepository.findById(dto.id());
        if (accommodationOptional.isEmpty()) {
            return;
        }

        Accommodation accommodation = accommodationOptional.get();
        accommodation.setStats(dto.stats());
        accommodationRepository.save(accommodation);
    }
}

