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
import com.example.rent.validations.DateValidations;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RentServiceImp implements IRentService {

    private final RentRepository rentRepository;
    private final AccommodationService accommodationService;
    private final UserService userService;

    @Autowired
    List<DateValidations> dateValidations;

    @Override
    @Transactional
    public Rent createNewRent(RentDto dto) {
        var accommodation = searchAccommodationForRent(dto);
        var user = searchUserForRent(dto);
        var rent = buildRent(accommodation, user, dto);
        dateValidations.forEach(e -> e.validate(dto));
        accommodationService.changeStatusForRented(accommodation);

        return rentRepository.save(rent);
    }

    private Accommodation searchAccommodationForRent(RentDto dto){
        return Optional.ofNullable(accommodationService.findAccommodationById(dto.idAccommodation()))
                .orElseThrow(() -> new IllegalArgumentException("Acomodação não encontrada"))
                .filter(accommodation -> accommodation.getStatus().equals(Status.AVAILABLE))
                .orElseThrow(() -> new IllegalStateException("Acomodação não está disponível para aluguel"));
    }

    private User searchUserForRent(RentDto dto) {
        return userService.findById(dto.idUser())
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
    }

    private Rent buildRent(Accommodation accommodation, User user, RentDto dto){
        var rent = Rent.builder()
                .accommodation(accommodation)
                .user(user)
                .price(accommodation.getPrice())
                .build();

        BeanUtils.copyProperties(dto, rent);

        accommodation.setStatus(Status.RENTED);
        rent.setDateRent(dto.startDateRent());

        return rent;
    }

    @Override
    public void update(RentDtoUpdate rentDtoUpdate) {
        Rent savedRent = findById(rentDtoUpdate.id());
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
