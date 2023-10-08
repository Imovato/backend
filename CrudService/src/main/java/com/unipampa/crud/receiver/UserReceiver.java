package com.unipampa.crud.receiver;

import com.unipampa.crud.model.Guest;
import com.unipampa.crud.repository.UserRepository;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class UserReceiver {

  private UserRepository userRepository;

  @Autowired
  public UserReceiver(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

//  @RabbitListener(queues = "${crud.rabbitmq.signupQueue}")
//  public void receive(@Payload CompleteUserDTO user) {
//    Guest cust = new Guest();
//    cust.setAddress(user.getAddress());
//    cust.setCpf(user.getCpf());
//    cust.setName(user.getUsername());
//    cust.setEmail(user.getEmail());
//    cust.setPhone(user.getPhone());
//    userRepository.save(cust);
//  }

}
