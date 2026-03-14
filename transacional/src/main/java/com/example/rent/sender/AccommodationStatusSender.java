package com.example.rent.sender;

import com.example.rent.dto.AccommodationStatusUpdateDto;
import com.example.rent.enums.StatusAccommodation;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AccommodationStatusSender {

    private final RabbitTemplate rabbitTemplate;

    @Value("${crud.rabbitmq.exchanges.exchangeAccommodations}")
    private String exchange;

    public AccommodationStatusSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendStatusUpdate(String accommodationId, StatusAccommodation status) {
        AccommodationStatusUpdateDto dto = new AccommodationStatusUpdateDto(accommodationId, status);
        rabbitTemplate.convertAndSend(exchange, "", dto);
    }
}

