package com.example.rent.service.impl;

import com.example.rent.dto.RentDto;
import com.example.rent.dto.RentDtoUpdate;
import com.example.rent.entities.User;
import com.example.rent.enums.Status;
import com.example.rent.exceptions.BadRequestException;
import com.example.rent.entities.Accommodation;
import com.example.rent.entities.Rent;
import com.example.rent.repository.RentRepository;
import com.example.rent.service.interfaces.UserService;
import com.example.rent.service.interfaces.AccommodationService;
import com.example.rent.service.interfaces.IRentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RentServiceImp implements IRentService {

    private final RentRepository rentRepository;
    private final AccommodationService propertyService;
    private final UserService userService;

    @Override
    @Transactional
    public Rent save(RentDto rentDto) {
        Optional<Accommodation> accommodation = Optional.ofNullable(propertyService.findAccommodationById(rentDto.getId_property()))
                .orElseThrow(() -> new IllegalArgumentException("Accommodation not found"));

        Optional<User> user = Optional.ofNullable(userService.findById(rentDto.getId_customer()))
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Rent rent = Rent.builder()
                .accommodation(accommodation.get())
                .user(user.get())
                .build();

        BeanUtils.copyProperties(rentDto, rent);

        accommodation.get().setStatus(Status.RENTED);
        propertyService.updateProperty(accommodation.get());

        return rentRepository.save(rent);
    }

    @Override
    public void update(RentDtoUpdate rentDtoUpdate) {
        Rent savedRent = findById(rentDtoUpdate.getId());
        BeanUtils.copyProperties(rentDtoUpdate, savedRent);
        rentRepository.save(savedRent);
    }

    @Override
    public void delete(Long id) {
        rentRepository.delete(findById(id));
    }

    @Override
    public Rent findById(Long id) {
        return rentRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Rent not found"));
    }

    @Override
    public List<Rent> listAll(){
        return rentRepository.findAll();
    }

//    @Override
//    public List<Rent> findRentsByCustomer_Id(Long id) {
//        return rentRepository.findByCustomerId(id);
//    }
}
