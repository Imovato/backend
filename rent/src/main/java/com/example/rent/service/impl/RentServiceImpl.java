package com.example.rent.service.impl;

import com.example.rent.dto.RentDto;
import com.example.rent.entities.Accommodation;
import com.example.rent.entities.Rent;
import com.example.rent.entities.User;
import com.example.rent.enums.Status;
import com.example.rent.enums.UserType;
import com.example.rent.repository.RentRepository;
import com.example.rent.response.RentResponse;
import com.example.rent.service.AccommodationService;
import com.example.rent.service.RentService;
import com.example.rent.service.UserService;
import com.example.rent.utils.ConverterResponse;
import com.example.rent.validations.DateValidations;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RentServiceImpl implements RentService {

    private final RentRepository rentRepository;
    private final AccommodationService accommodationService;
    private final UserService userService;
    private final List<DateValidations> dateValidations;
    private final ConverterResponse converterResponse;

    @Override
    public List<RentResponse> findByUserId(Long id) {
        List<RentDto> rents = rentRepository.findByUserId(id);
        return converterResponse.convertToRentResponseList(rents);
    }

    @Override
    @Transactional
    public RentResponse createNewRent(RentDto dto) {
        var accommodation = searchAccommodationForRent(dto);
        var user = searchUserForRent(dto);
        var rent = buildRent(accommodation, user, dto);
        dateValidations.forEach(e -> e.validate(dto));
        accommodationService.changeStatusForRented(accommodation);
        return converterResponse.convertToRentResponse(rentRepository.save(rent));

    }

    protected Accommodation searchAccommodationForRent(RentDto dto){
        return Optional.ofNullable(accommodationService.findAccommodationById(dto.accommodation().getId()))
                .orElseThrow(() -> new IllegalArgumentException("Acomodação não encontrada"))
                .filter(accommodation -> accommodation.getStatus().equals(Status.AVAILABLE))
                .orElseThrow(() -> new IllegalStateException("Acomodação não está disponível para aluguel"));
    }

    protected User searchUserForRent(RentDto dto) {
        return userService.findById(dto.user().getId())
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
    }

    protected Rent buildRent(Accommodation accommodation, User user, RentDto dto) {
        var rent = Rent.builder()
                .accommodation(accommodation)
                .user(user)
                .price(accommodation.getPrice())
                .build();

        BeanUtils.copyProperties(rent, dto);

        accommodation.setStatus(Status.RENTED);
        rent.setDateRent(dto.startDateRent());

        return rent;
    }
}
