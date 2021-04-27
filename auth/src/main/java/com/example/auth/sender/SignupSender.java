package com.example.auth.sender;

import com.example.auth.dto.CompleteUserDTO;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SignupSender {
  
  @Value("${auth.rabbitmq.exchange}")
  String exchange;

  @Value("${auth.rabbitmq.routingKeySignup}")
  String routingKey;

  public RabbitTemplate rabbitTemplate;

  @Autowired
  public SignupSender(RabbitTemplate rabbitTemplate) {
    this.rabbitTemplate = rabbitTemplate;
  }

  public void sendMessage(CompleteUserDTO user) {
    rabbitTemplate.convertAndSend(exchange, routingKey, user);
  }
}
