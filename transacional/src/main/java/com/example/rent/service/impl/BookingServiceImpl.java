package com.example.rent.service.impl;

import com.example.rent.dto.BookingDto;
import com.example.rent.entities.Accommodation;
import com.example.rent.entities.GuestBooking;
import com.example.rent.entities.Booking;
import com.example.rent.entities.User;
import com.example.rent.enums.StatusAccommodation;
import com.example.rent.enums.StatusReservation;
import com.example.rent.exceptions.BusinessException;
import com.example.rent.mapper.BookingMapper;
import com.example.rent.repository.AccommodationRepository;
import com.example.rent.repository.BookingRepository;
import com.example.rent.repository.UserRepository;
import com.example.rent.service.BookingService;
import com.example.rent.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BookingServiceImpl implements BookingService {

    public static final int MIN_MONTHS = 1;
    public static final int MAX_MONTHS  = 12;
    public static final int DAYS_FOR_EXPIRES = 3;
    public static final String RENTAL_TIME_ERROR = "Período de locação inválido. Use um valor de 1 a 12 meses.";
    public static final String ACCOMMODATION_NOT_AVAILABLE = "O imóvel não está disponível para reserva.";
    public static final String USER_NOT_FOUND = "Usuário não encontrado";
    public static final String ACCOMMODATION_NOT_FOUND = "Imóvel não encontrado";
    public static final String NUMBER_OF_GUESTS_MSG = "Número de convidados : ";
    public static final String ACCOMMODATION_CAPACITY_MSG = "\n O número máximo de participantes dessa reserva é : ";
    public static final String BOOKING_NOT_FOUND_MSG = "Reserva não encontrada com o id: ";
    @Autowired
    AccommodationRepository accommodationRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    private final UserService userService;

    @Override
    public Booking createBooking(BookingDto request) {
        validateRentalTime(request);
        var accommodation = findAccommodation(request);
        validateAccommodationCapacity(request, accommodation);
        verifyAccommodationStats(accommodation);
        var guests = findGuests(request);

        var guestsBooking = buildGuestsForBooking(guests);
        var booking = buildBooking(accommodation, request, guestsBooking);

        return bookingRepository.save(booking);
    }

    protected void validateRentalTime(BookingDto request) {
        Integer months = request.rentalMonths();
        if (months == null || months < MIN_MONTHS || months > MAX_MONTHS) {
            throw new BusinessException(RENTAL_TIME_ERROR);
        }

    }

    protected Accommodation findAccommodation(BookingDto request) {
        return accommodationRepository.findById(request.accommodationId())
                .orElseThrow(() -> new RuntimeException(ACCOMMODATION_NOT_FOUND));
    }

    protected void verifyAccommodationStats(Accommodation accommodation) {
        if (accommodation.getStatus().equals(StatusAccommodation.BOOKING)
                || accommodation.getStatus().equals(StatusAccommodation.RENTED)) {
            throw new RuntimeException(ACCOMMODATION_NOT_AVAILABLE);
        }
    }

    protected Booking buildBooking(Accommodation accommodation, BookingDto request, List<GuestBooking> guests) {
        LocalDate initialDate = LocalDate.now().plusDays(6);
        LocalDate endDate = initialDate.plusMonths(request.rentalMonths());
        Booking booking = new Booking();
        booking.setAccommodation(accommodation);
        booking.setStatusReservation(StatusReservation.WAITING_PAYMENT);
        booking.setCreationDate(LocalDateTime.now());
        booking.setExpiresDate(LocalDateTime.now().plusDays(DAYS_FOR_EXPIRES));
        booking.setInitialDate(initialDate);
        booking.setEndDate(endDate);
        guests.forEach(e -> e.setReservation(booking));
        booking.setGuests(guests);
        accommodation.setStatus(StatusAccommodation.BOOKING);

        return booking;
    }

    protected List<User> findGuests(BookingDto request) {
        return request.guestIds().stream().map(id -> userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(USER_NOT_FOUND))).toList();
    }

    protected List<GuestBooking> buildGuestsForBooking(List<User> guests) {
        return guests.stream().map(e -> {
            var guestBooking = new GuestBooking();
            guestBooking.setGuest(e);
            guestBooking.setPaid(false);
            return guestBooking;
        }).toList();
    }

    protected void validateAccommodationCapacity(BookingDto request, Accommodation accommodation) {
        if (request.guestIds().size() > accommodation.getGuestCapacity()) {
            throw new RuntimeException(NUMBER_OF_GUESTS_MSG + request.guestIds().size() +
                    ACCOMMODATION_CAPACITY_MSG + accommodation.getGuestCapacity());
        }
    }

    @Override
    public Booking getBookingById(Long id) throws Exception {
        return bookingRepository.findById(id).orElseThrow(() -> new Exception(BOOKING_NOT_FOUND_MSG + id));
    }

    @Override
    public Booking cancelBooking(Long id) throws Exception {
        Booking booking = getBookingById(id);
        booking.setStatusReservation(StatusReservation.CANCELED);
        return bookingRepository.save(booking);
    }

    @Override
    public Booking updateBooking(Booking request) throws Exception {
        if (request.getId() == null) {
            throw new IllegalArgumentException("O ID da reserva não pode ser nulo!");
        }

        Booking existingBooking = bookingRepository.findById(request.getId())
                .orElseThrow(() -> new Exception("Reserva não encontrada com o id: " + request.getId()));

        existingBooking.setGuests(request.getGuests());
        existingBooking.setStatusReservation(request.getStatusReservation());

        return bookingRepository.save(existingBooking);
    }

    @Override
    public BookingDto payBooking(Long bookingId, String userId) throws Exception {
        Booking booking = getBookingById(bookingId);

        if (booking.getStatusReservation() == StatusReservation.CANCELED
                || booking.getStatusReservation() == StatusReservation.CONFIRMED) {
            throw new Exception("Não é possível pagar uma reserva que está cancelada ou já confirmada.");
        }

        Optional<User> user = userService.findById(userId);
        if (user.isEmpty()) {
            throw new Exception("Usuário não encontrado.");
        }

        List<GuestBooking> guestBookings = booking.getGuests();

        Optional<GuestBooking> matchingGuestBooking = guestBookings.stream()
                .filter(guestBooking -> guestBooking.getGuest().getId().equals(userId))
                .findFirst();

        if (matchingGuestBooking.isEmpty()) {
            throw new Exception("Usuário não está entre os convidados da reserva.");
        }

        GuestBooking guestBooking = matchingGuestBooking.get();

        if (guestBooking.isPaid()) {
            throw new Exception("Usuário já efetuou o pagamento.");
        }

        guestBooking.setPaid(true);
        guestBooking.setPaymentDate(LocalDateTime.now());

        Booking updatedBooking = updateBooking(booking);

        return BookingMapper.toDto(updatedBooking);
    }

}
