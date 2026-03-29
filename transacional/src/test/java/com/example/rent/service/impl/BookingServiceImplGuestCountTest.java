package com.example.rent.service.impl;

import com.example.rent.entities.Booking;
import com.example.rent.repository.BookingRepository;
import com.example.rent.repository.GuestBookingRepository;
import com.example.rent.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookingServiceImplGuestCountTest {

    @Test
    void getGuestCountByReservationIdReturnsCount() throws Exception {
        UserService userService = Mockito.mock(UserService.class);
        BookingServiceImpl service = new BookingServiceImpl(userService);

        BookingRepository bookingRepository = Mockito.mock(BookingRepository.class);
        GuestBookingRepository guestBookingRepository = Mockito.mock(GuestBookingRepository.class);

        service.bookingRepository = bookingRepository;
        service.guestBookingRepository = guestBookingRepository;

        Booking booking = new Booking();
        booking.setId(15L);

        when(bookingRepository.findById(15L)).thenReturn(Optional.of(booking));
        when(guestBookingRepository.countByReservation_Id(15L)).thenReturn(2L);

        long count = service.getGuestCountByReservationId(15L);

        assertThat(count).isEqualTo(2L);
    }
}

