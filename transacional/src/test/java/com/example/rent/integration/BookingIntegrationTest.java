package com.example.rent.integration;

import com.example.rent.dto.AccommodationDto;
import com.example.rent.entities.Accommodation;
import com.example.rent.entities.Booking;
import com.example.rent.entities.GuestBooking;
import com.example.rent.enums.StatusAccommodation;
import com.example.rent.enums.StatusReservation;
import com.example.rent.receiver.AccommodationReceiver;
import com.example.rent.repository.AccommodationRepository;
import com.example.rent.repository.BookingRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Teste de integração para o fluxo completo de Booking.
 * Testa a integração com MySQL e RabbitMQ usando containers reais.
 */
@AutoConfigureMockMvc
class BookingIntegrationTest extends BaseIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private AccommodationRepository accommodationRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private AccommodationReceiver accommodationReceiver;

    @Value("${crud.rabbitmq.queues.accommodationQueue:accommodation.queue}")
    private String accommodationQueue;

    private Accommodation testAccommodation;
    private Booking testBooking;

    @BeforeEach
    void setUp() {
        // Limpa os bancos antes de cada teste
        bookingRepository.deleteAll();
        accommodationRepository.deleteAll();

        // Cria uma acomodação de teste
        testAccommodation = Accommodation.builder()
                .id("acc-123")
                .price(250.0)
                .status(StatusAccommodation.AVAILABLE)
                .guestCapacity(4)
                .build();
        accommodationRepository.save(testAccommodation);

        // Cria uma reserva de teste
        testBooking = new Booking();
        testBooking.setAccommodation(testAccommodation);
        testBooking.setStatusReservation(StatusReservation.WAITING_PAYMENT);
        testBooking.setInitialDate(LocalDate.now().plusDays(7));
        testBooking.setEndDate(LocalDate.now().plusDays(10));
        testBooking.setCreationDate(LocalDateTime.now());
        testBooking.setExpiresDate(LocalDateTime.now().plusDays(1));
        testBooking.setGuests(new ArrayList<>());
    }

    @AfterEach
    void tearDown() {
        bookingRepository.deleteAll();
        accommodationRepository.deleteAll();
    }

    @Test
    void testMySQLConnection() {
        // Testa se o MySQL está funcionando
        assertThat(mysqlContainer.isRunning()).isTrue();
    }

    @Test
    void testRabbitMQConnection() {
        // Testa se o RabbitMQ está funcionando
        assertThat(rabbitMQContainer.isRunning()).isTrue();
    }

    @Test
    void shouldSaveBookingToMySQL() {
        // Given
        Booking saved = bookingRepository.save(testBooking);

        // When
        Booking found = bookingRepository.findById(saved.getId()).orElse(null);

        // Then
        assertThat(found).isNotNull();
        assertThat(found.getStatusReservation()).isEqualTo(StatusReservation.WAITING_PAYMENT);
        assertThat(found.getAccommodation().getId()).isEqualTo("acc-123");
        assertThat(found.getInitialDate()).isEqualTo(testBooking.getInitialDate());
        assertThat(found.getEndDate()).isEqualTo(testBooking.getEndDate());
    }

    @Test
    void shouldFindAllBookings() {
        // Given
        bookingRepository.save(testBooking);

        Booking anotherBooking = new Booking();
        anotherBooking.setAccommodation(testAccommodation);
        anotherBooking.setStatusReservation(StatusReservation.CONFIRMED);
        anotherBooking.setInitialDate(LocalDate.now().plusDays(14));
        anotherBooking.setEndDate(LocalDate.now().plusDays(17));
        anotherBooking.setCreationDate(LocalDateTime.now());
        anotherBooking.setExpiresDate(LocalDateTime.now().plusDays(1));
        anotherBooking.setGuests(new ArrayList<>());
        bookingRepository.save(anotherBooking);

        // When
        List<Booking> bookings = bookingRepository.findAll();

        // Then
        assertThat(bookings).hasSize(2);
        assertThat(bookings)
                .extracting(Booking::getStatusReservation)
                .containsExactlyInAnyOrder(StatusReservation.WAITING_PAYMENT, StatusReservation.CONFIRMED);
    }

    @Test
    void shouldUpdateBookingStatus() {
        // Given
        Booking saved = bookingRepository.save(testBooking);

        // When
        saved.setStatusReservation(StatusReservation.CONFIRMED);
        bookingRepository.save(saved);

        // Then
        Booking updated = bookingRepository.findById(saved.getId()).orElse(null);
        assertThat(updated).isNotNull();
        assertThat(updated.getStatusReservation()).isEqualTo(StatusReservation.CONFIRMED);
    }

    @Test
    void shouldDeleteBooking() {
        // Given
        Booking saved = bookingRepository.save(testBooking);
        Long bookingId = saved.getId();

        // When
        bookingRepository.deleteById(bookingId);

        // Then
        assertThat(bookingRepository.findById(bookingId)).isEmpty();
        assertThat(bookingRepository.count()).isZero();
    }

    @Test
    void shouldReceiveAccommodationMessageFromRabbitMQ() {
        // Given
        String newAccommodationId = "acc-rabbitmq-456";
        AccommodationDto accommodationDto = new AccommodationDto(
                newAccommodationId,
                300.0,
                6,
                StatusAccommodation.AVAILABLE
        );

        // When - Publica mensagem no RabbitMQ
        rabbitTemplate.convertAndSend(accommodationQueue, accommodationDto);

        // Then - Aguarda o processamento assíncrono e verifica se foi salvo
        await().atMost(5, TimeUnit.SECONDS).untilAsserted(() -> {
            Accommodation savedAccommodation = accommodationRepository.findById(newAccommodationId).orElse(null);
            assertThat(savedAccommodation).isNotNull();
            assertThat(savedAccommodation.getPrice()).isEqualTo(300.0);
            assertThat(savedAccommodation.getGuestCapacity()).isEqualTo(6);
        });
    }

    @Test
    void shouldHandleMultipleBookingsForSameAccommodation() {
        // Given
        Booking firstBooking = bookingRepository.save(testBooking);

        Booking secondBooking = new Booking();
        secondBooking.setAccommodation(testAccommodation);
        secondBooking.setStatusReservation(StatusReservation.CONFIRMED);
        secondBooking.setInitialDate(LocalDate.now().plusDays(20));
        secondBooking.setEndDate(LocalDate.now().plusDays(23));
        secondBooking.setCreationDate(LocalDateTime.now());
        secondBooking.setExpiresDate(LocalDateTime.now().plusDays(1));
        secondBooking.setGuests(new ArrayList<>());
        bookingRepository.save(secondBooking);

        // When
        List<Booking> allBookings = bookingRepository.findAll();

        // Then
        assertThat(allBookings).hasSize(2);
        assertThat(allBookings)
                .allMatch(booking -> booking.getAccommodation().getId().equals(testAccommodation.getId()));
    }

    @Test
    void shouldVerifyBookingDatesConstraints() {
        // Given
        LocalDate initialDate = LocalDate.now().plusDays(7);
        LocalDate endDate = LocalDate.now().plusDays(10);
        
        testBooking.setInitialDate(initialDate);
        testBooking.setEndDate(endDate);

        // When
        Booking saved = bookingRepository.save(testBooking);
        Booking retrieved = bookingRepository.findById(saved.getId()).orElse(null);

        // Then
        assertThat(retrieved).isNotNull();
        assertThat(retrieved.getInitialDate()).isEqualTo(initialDate);
        assertThat(retrieved.getEndDate()).isEqualTo(endDate);
        assertThat(retrieved.getInitialDate()).isBefore(retrieved.getEndDate());
    }

    @Test
    void shouldHandleBookingWithMultipleGuests() {
        // Given
        testBooking.setGuests(new ArrayList<>());
        Booking saved = bookingRepository.save(testBooking);
        
        GuestBooking guest1 = new GuestBooking();
        guest1.setReservation(saved);
        
        GuestBooking guest2 = new GuestBooking();
        guest2.setReservation(saved);
        
        saved.getGuests().add(guest1);
        saved.getGuests().add(guest2);

        // When
        Booking updated = bookingRepository.save(saved);
        bookingRepository.flush();

        // Then - Verifica que foram salvos 2 guests
        assertThat(updated.getGuests()).hasSize(2);
    }

    @Test
    void shouldVerifyAccommodationAvailableForBooking() {
        // Given
        Accommodation accommodation = accommodationRepository.findById(testAccommodation.getId()).orElse(null);
        
        // Then
        assertThat(accommodation).isNotNull();
        assertThat(accommodation.getStatus()).isEqualTo(StatusAccommodation.AVAILABLE);
        assertThat(accommodation.getGuestCapacity()).isGreaterThan(0);
    }

    @Test
    void shouldVerifyBookingDataIntegrity() {
        // Given & When
        Booking saved = bookingRepository.save(testBooking);
        Booking retrieved = bookingRepository.findById(saved.getId()).orElse(null);

        // Then - Verifica todos os campos importantes
        assertThat(retrieved).isNotNull();
        assertThat(retrieved.getStatusReservation()).isEqualTo(testBooking.getStatusReservation());
        assertThat(retrieved.getInitialDate()).isEqualTo(testBooking.getInitialDate());
        assertThat(retrieved.getEndDate()).isEqualTo(testBooking.getEndDate());
        assertThat(retrieved.getAccommodation()).isNotNull();
        assertThat(retrieved.getAccommodation().getId()).isEqualTo(testAccommodation.getId());
        assertThat(retrieved.getCreationDate()).isNotNull();
        assertThat(retrieved.getExpiresDate()).isNotNull();
    }

    @Test
    void shouldCascadeDeleteGuests() {
        // Given
        Booking saved = bookingRepository.save(testBooking);
        
        GuestBooking guest = new GuestBooking();
        guest.setReservation(saved);
        saved.setGuests(List.of(guest));
        bookingRepository.save(saved);

        Long bookingId = saved.getId();

        // When
        bookingRepository.deleteById(bookingId);

        // Then
        assertThat(bookingRepository.findById(bookingId)).isEmpty();
    }
}
