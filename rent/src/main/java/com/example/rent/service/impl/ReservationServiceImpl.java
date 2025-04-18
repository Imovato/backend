package com.example.rent.service.impl;

import com.example.rent.dto.ReservationDto;
import com.example.rent.entities.Accommodation;
import com.example.rent.entities.ParticipanteReserva;
import com.example.rent.entities.Reservation;
import com.example.rent.entities.User;
import com.example.rent.enums.StatusAccommodation;
import com.example.rent.enums.StatusReservation;
import com.example.rent.repository.AccommodationRepository;
import com.example.rent.repository.ReservationRepository;
import com.example.rent.repository.UserRepository;
import com.example.rent.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    AccommodationRepository accommodationRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ReservationRepository reservationRepository;

    @Override
    public Reservation criarReserva(ReservationDto request) {

        Accommodation accommodation = accommodationRepository.findById(request.accommodationId())
                .orElseThrow(() -> new RuntimeException("Imóvel não encontrado"));

        Reservation reservation = new Reservation();
        reservation.setAccommodation(accommodation);
        reservation.setStatusReservation(StatusReservation.WAITING);
        reservation.setCreationDate(LocalDateTime.now());
        reservation.setExpiresDate(LocalDateTime.now().plusDays(3));

        List<ParticipanteReserva> guests = request.guestIds().stream().map(id -> {
            User guest = userRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Estudante não encontrado"));

            ParticipanteReserva guestReservation = new ParticipanteReserva();
            guestReservation.setGuest(guest);
            guestReservation.setReservation(reservation);
            guestReservation.setPaid(false);

            accommodation.setStatus(StatusAccommodation.BOOKING);
            
            return guestReservation;
        }).toList();

        reservation.setGuests(guests);
        reservationRepository.save(reservation);

        return reservation;
    }
}
