package com.example.rent.service.impl;

import com.example.rent.client.AccommodationClient;
import com.example.rent.dto.AccommodationDetailsDto;
import com.example.rent.dto.BookingInviteRequestDto;
import com.example.rent.dto.BookingInviteResponseDto;
import com.example.rent.dto.InviteRespondRequestDto;
import com.example.rent.dto.InviteRespondResponseDto;
import com.example.rent.entities.Accommodation;
import com.example.rent.entities.Booking;
import com.example.rent.entities.BookingInvite;
import com.example.rent.entities.GuestBooking;
import com.example.rent.entities.User;
import com.example.rent.enums.InviteStatus;
import com.example.rent.exceptions.InviteConflictException;
import com.example.rent.repository.BookingInviteRepository;
import com.example.rent.repository.BookingRepository;
import com.example.rent.repository.UserRepository;
import com.example.rent.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookingServiceImplInviteTest {

    @Test
    void createBookingInviteReturnsExpectedResponse() throws Exception {
        UserService userService = Mockito.mock(UserService.class);
        BookingServiceImpl service = new BookingServiceImpl(userService);

        BookingRepository bookingRepository = Mockito.mock(BookingRepository.class);
        UserRepository userRepository = Mockito.mock(UserRepository.class);
        BookingInviteRepository bookingInviteRepository = Mockito.mock(BookingInviteRepository.class);
        AccommodationClient accommodationClient = Mockito.mock(AccommodationClient.class);

        service.bookingRepository = bookingRepository;
        service.userRepository = userRepository;
        service.bookingInviteRepository = bookingInviteRepository;
        service.accommodationClient = accommodationClient;

        Accommodation accommodation = new Accommodation();
        accommodation.setId("acc-1");
        accommodation.setPrice(1000.0);
        accommodation.setGuestCapacity(3);

        Booking booking = new Booking();
        booking.setId(10L);
        booking.setAccommodation(accommodation);
        booking.setGuests(List.of(new GuestBooking()));

        User guest = new User();
        guest.setId("guest-1");

        AccommodationDetailsDto details = new AccommodationDetailsDto(
                "acc-1",
                "title",
                "neighborhood",
                "cod",
                "city",
                "description",
                "address",
                "state",
                BigDecimal.valueOf(1000.0),
                12,
                1,
                "COLIVING",
                3,
                1,
                1,
                true,
                true,
                List.of()
        );

        when(bookingRepository.findById(10L)).thenReturn(Optional.of(booking));
        when(userRepository.findById("guest-1")).thenReturn(Optional.of(guest));
        when(accommodationClient.getAccommodationById("acc-1")).thenReturn(details);

        BookingInvite savedInvite = new BookingInvite();
        savedInvite.setId(50L);
        savedInvite.setBooking(booking);
        savedInvite.setGuest(guest);
        savedInvite.setStatus(InviteStatus.PENDING);
        savedInvite.setShareAmount(BigDecimal.valueOf(500.00));
        savedInvite.setDeadline(LocalDateTime.now().plusDays(3));

        when(bookingInviteRepository.save(any(BookingInvite.class))).thenReturn(savedInvite);

        BookingInviteResponseDto response = service.createBookingInvite(10L, new BookingInviteRequestDto("guest-1"));

        assertThat(response.inviteId()).isEqualTo("50");
        assertThat(response.bookingId()).isEqualTo("10");
        assertThat(response.guestId()).isEqualTo("guest-1");
        assertThat(response.status()).isEqualTo(InviteStatus.PENDING);
    }

    @Test
    void respondToInviteAcceptsPendingInvite() {
        BookingInviteRepository bookingInviteRepository = Mockito.mock(BookingInviteRepository.class);
        InviteServiceImpl inviteService = new InviteServiceImpl(bookingInviteRepository);

        BookingInvite invite = new BookingInvite();
        invite.setId(99L);
        invite.setStatus(InviteStatus.PENDING);

        when(bookingInviteRepository.findById(99L)).thenReturn(Optional.of(invite));
        when(bookingInviteRepository.save(any(BookingInvite.class))).thenAnswer(invocation -> invocation.getArgument(0));

        InviteRespondResponseDto response = inviteService.respondToInvite(99L, new InviteRespondRequestDto(InviteStatus.ACCEPTED));

        assertThat(response.inviteId()).isEqualTo("99");
        assertThat(response.status()).isEqualTo(InviteStatus.ACCEPTED);
    }

    @Test
    void respondToInviteRejectsAlreadyAnsweredInvite() {
        BookingInviteRepository bookingInviteRepository = Mockito.mock(BookingInviteRepository.class);
        InviteServiceImpl inviteService = new InviteServiceImpl(bookingInviteRepository);

        BookingInvite invite = new BookingInvite();
        invite.setId(100L);
        invite.setStatus(InviteStatus.DECLINED);

        when(bookingInviteRepository.findById(100L)).thenReturn(Optional.of(invite));

        assertThatThrownBy(() -> inviteService.respondToInvite(100L, new InviteRespondRequestDto(InviteStatus.ACCEPTED)))
                .isInstanceOf(InviteConflictException.class)
                .hasMessage("Este convite já foi respondido");
    }
}

