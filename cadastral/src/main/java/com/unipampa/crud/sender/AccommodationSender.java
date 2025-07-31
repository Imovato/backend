package com.unipampa.crud.sender;

import com.unipampa.crud.dto.AccommodationMessageDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.unipampa.crud.entities.Accommodation;

@Component
public class AccommodationSender {
	
	@Value("${crud.rabbitmq.exchanges.exchangeAccommodations}")
	String exchange;

	public RabbitTemplate rabbitTemplate;

	@Autowired
	public AccommodationSender(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}
	
	public void sendMessage(Accommodation accommodation) {
		AccommodationMessageDTO dto = new AccommodationMessageDTO();
		BeanUtils.copyProperties(accommodation, dto);
		rabbitTemplate.convertAndSend(exchange, "", accommodation);
	}
	
}
