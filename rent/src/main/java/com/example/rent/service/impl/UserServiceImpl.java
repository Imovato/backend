package com.example.rent.service.impl;

import com.example.rent.entities.User;
import com.example.rent.repository.UserRepository;
import com.example.rent.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

//    private List<IValidationService> validations = Arrays.asList(new ValidateCpfServiceImp());


    @Override
    public User save(User customer) {
        return userRepository.save(customer);
    }

    public Optional<User> findById(Long id) {
      return userRepository.findById(id);
    }

//    @Override
//    public void validateCustomer(Customer customer, Accommodation property) {
//        this.validations.forEach(element->element.validate(customer, property));
//    }
}
