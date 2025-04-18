package com.example.rent.service.impl;

import com.example.rent.entities.Accommodation;
import com.example.rent.entities.ParticipanteReserva;
import com.example.rent.entities.Rent;
import com.example.rent.entities.Reservation;
import com.example.rent.enums.StatusAccommodation;
import com.example.rent.repository.AccommodationRepository;
import com.example.rent.repository.RentRepository;
import com.example.rent.repository.ReservationRepository;
import com.example.rent.response.RentResponse;
import com.example.rent.service.AccommodationService;
import com.example.rent.service.RentService;
import com.example.rent.service.UserService;
import com.example.rent.utils.ConverterResponse;
import com.example.rent.validations.DateValidations;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RentServiceImpl implements RentService {

    private final RentRepository rentRepository;
    private final ReservationRepository reservationRepository;
    private final AccommodationRepository accommodationRepository;
    private final AccommodationService accommodationService;
    private final UserService userService;
    private final List<DateValidations> dateValidations;
    private final ConverterResponse converterResponse;


    @Override
    public List<RentResponse> findByUserId(Long id) {
        return null;
    }

    protected Rent buildRent(Accommodation accommodation, Reservation reservation) {
        var rent = Rent.builder()
                .accommodation(accommodation)
                .numUsers(reservation.getGuests().size())
                .reservation(reservation)
                .price(accommodation.getPrice())
                .build();

        accommodation.setStatus(StatusAccommodation.RENTED);

        return rent;
    }

    @Override
    public RentResponse processCheckin(Long idReservation) {
        //consultar a reserva
        Reservation reservation = reservationRepository.findById(idReservation)
                .orElseThrow(() -> new RuntimeException("Reserva Não Encontrada"));

        //percorrer todos os participantes daquela reserva para verificar um a um se foi pago
        List<ParticipanteReserva> participantesDaReserva = reservation.getGuests();

        //capturar os que nao pagaram e mostrar
        List<ParticipanteReserva> naoPagaram = participantesDaReserva.stream().filter(e -> !e.isPaid()).toList();

        if(!naoPagaram.isEmpty()) {
            System.out.println("Participantes da reserva que ainda não pagaram");
            naoPagaram.stream().forEach(e -> System.out.println("Falta o pagamento do participante : " + e.getGuest().getName()));
            throw new RuntimeException("Checkin não permitido, existem participantes da reserva que ainda não efetuaram o pagamento.");
        }

        //todos pagaram
        System.out.println("Todos os participantes pagaram. Processando check-in...");

        //se sim. deve processar
        //capturar a acomodação
        Accommodation accommodation = accommodationRepository.findById(reservation.getAccommodation().getId())
                .orElseThrow(() -> new RuntimeException("Accomodação não encontrada"));

        Rent rent = buildRent(accommodation, reservation);

        rentRepository.save(rent);

        return converterResponse.convertToRentResponse(rent);
    }
}
