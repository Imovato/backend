package com.example.rent.service.impl;

import com.example.rent.entities.Accommodation;
import com.example.rent.entities.GuestBooking;
import com.example.rent.entities.Rent;
import com.example.rent.entities.Booking;
import com.example.rent.enums.StatusAccommodation;
import com.example.rent.repository.AccommodationRepository;
import com.example.rent.repository.RentRepository;
import com.example.rent.repository.BookingRepository;
import com.example.rent.response.RentResponse;
import com.example.rent.service.RentService;
import com.example.rent.utils.ConverterResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class RentServiceImpl implements RentService {

    public static final String PAYMENTS_HAS_SUCCESSFULLY = "Todos os participantes pagaram. Processando check-in...";
    public static final String BOOKING_NOT_FOUND = "Reserva Não Encontrada";
    public static final String GUESTS_HAS_NO_PAYED = "Participantes da reserva que ainda não pagaram";
    public static final String GUEST_PAYMENT_MISSING = "Falta o pagamento do participante : ";
    public static final String CHECKIN_NOT_ALLOWED_FOR_NOT_PAYING = "Checkin não permitido, existem participantes da reserva que ainda não efetuaram o pagamento.";
    public static final String ACCOMMODATION_NOT_FOUND = "Accomodação não encontrada";
    private final RentRepository rentRepository;
    private final BookingRepository bookingRepository;
    private final AccommodationRepository accommodationRepository;
    private final ConverterResponse converterResponse;

    @Override
    public RentResponse processCheckin(Long idReservation) {
        var booking = findBooking(idReservation);
        checkBookingPayment(booking);

        log.info(PAYMENTS_HAS_SUCCESSFULLY);

        var accommodation = findAccommodationInBooking(booking);
        var rent = buildRent(accommodation, booking);
        rentRepository.save(rent);

        return converterResponse.convertToRentResponse(rent);
    }

    protected Booking findBooking(Long idReservation) {
        return bookingRepository.findById(idReservation)
                .orElseThrow(() -> new RuntimeException(BOOKING_NOT_FOUND));
    }

    protected void checkBookingPayment(Booking reservation) {
        List<GuestBooking> guestsForBooking = reservation.getGuests();
        List<GuestBooking> notPayed = guestsForBooking.stream().filter(e -> !e.isPaid()).toList();

        if (!notPayed.isEmpty()) {
            System.out.println(GUESTS_HAS_NO_PAYED);
            notPayed.stream().forEach(e -> System.out.println(GUEST_PAYMENT_MISSING + e.getGuest().getName()));
            throw new RuntimeException(CHECKIN_NOT_ALLOWED_FOR_NOT_PAYING);
        }
    }

    protected Accommodation findAccommodationInBooking(Booking booking) {
        return accommodationRepository.findById(booking.getAccommodation().getId())
                .orElseThrow(() -> new RuntimeException(ACCOMMODATION_NOT_FOUND));
    }

    protected Rent buildRent(Accommodation accommodation, Booking reservation) {
        var rent = Rent.builder()
                .accommodation(accommodation)
                .numUsers(reservation.getGuests().size())
                .reservation(reservation)
                .price(accommodation.getPrice())
                .startDateRent(reservation.getInitialDate())
                .endDateRent(reservation.getEndDate())
                .build();
        accommodation.setStatus(StatusAccommodation.RENTED);

        return rent;
    }
}
