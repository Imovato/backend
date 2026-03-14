package com.example.rent.service.impl;

import com.example.rent.client.AccommodationClient;
import com.example.rent.config.security.SecurityUtil;
import com.example.rent.dto.BookingDto;
import com.example.rent.dto.ReservedPropertyDto;
import com.example.rent.dto.BookingInviteRequestDto;
import com.example.rent.dto.BookingInviteResponseDto;
import com.example.rent.dto.AccommodationDetailsDto;
import com.example.rent.dto.InvitePendingResponseDto;
import com.example.rent.dto.InviteRespondRequestDto;
import com.example.rent.dto.InviteRespondResponseDto;
import com.example.rent.entities.Accommodation;
import com.example.rent.entities.GuestBooking;
import com.example.rent.entities.Booking;
import com.example.rent.entities.User;
import com.example.rent.entities.BookingInvite;
import com.example.rent.enums.StatusAccommodation;
import com.example.rent.enums.StatusReservation;
import com.example.rent.enums.InviteStatus;
import com.example.rent.exceptions.BusinessException;
import com.example.rent.exceptions.InviteBadRequestException;
import com.example.rent.exceptions.InviteNotFoundException;
import com.example.rent.exceptions.InviteConflictException;
import com.example.rent.mapper.BookingMapper;
import com.example.rent.repository.AccommodationRepository;
import com.example.rent.repository.BookingRepository;
import com.example.rent.repository.UserRepository;
import com.example.rent.repository.BookingInviteRepository;
import com.example.rent.service.BookingService;
import com.example.rent.service.UserService;
import com.example.rent.service.InviteService;
import com.example.rent.sender.AccommodationStatusSender;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
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

    @Autowired
    AccommodationClient accommodationClient;

    @Autowired
    AccommodationStatusSender accommodationStatusSender;

    @Autowired
    BookingInviteRepository bookingInviteRepository;

    @Override
    public Booking createBooking(BookingDto request) {
        validateRentalTime(request);
        var accommodation = findAccommodation(request);
        validateAccommodationCapacity(request, accommodation);
        verifyAccommodationStats(accommodation);
        var guests = findGuests(request);

        var guestsBooking = buildGuestsForBooking(guests);
        var booking = buildBooking(accommodation, request, guestsBooking);

        Booking savedBooking = bookingRepository.save(booking);
        accommodationStatusSender.sendStatusUpdate(
                savedBooking.getAccommodation().getId(),
                StatusAccommodation.UNAVAILABLE
        );

        return savedBooking;
    }

    protected void validateRentalTime(BookingDto request) {
        Integer months = request.rentalMonths();
        if (months == null || months < MIN_MONTHS || months > MAX_MONTHS) {
            throw new BusinessException(RENTAL_TIME_ERROR);
        }

    }

    protected Accommodation findAccommodation(BookingDto request) {
        Accommodation accommodation = accommodationRepository.findById(request.accommodationId())
                .orElseThrow(() -> new RuntimeException(ACCOMMODATION_NOT_FOUND));

        if (accommodation.getGuestCapacity() <= 0) {
            var details = accommodationClient.getAccommodationById(accommodation.getId());
            if (details != null && details.maxOccupancy() != null && details.maxOccupancy() > 0) {
                accommodation.setGuestCapacity(details.maxOccupancy());
                accommodationRepository.save(accommodation);
            }
        }

        return accommodation;
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
        Booking savedBooking = bookingRepository.save(booking);
        accommodationStatusSender.sendStatusUpdate(
                savedBooking.getAccommodation().getId(),
                StatusAccommodation.AVAILABLE
        );
        return savedBooking;
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

        boolean allGuestsPaid = booking.getGuests().stream().allMatch(GuestBooking::isPaid);
        if (booking.getGuests().size() == 1 || allGuestsPaid) {
            booking.setStatusReservation(StatusReservation.CONFIRMED);
        }

        Booking updatedBooking = updateBooking(booking);

        return BookingMapper.toDto(updatedBooking);
    }

    @Override
    public List<ReservedPropertyDto> getReservedPropertiesByUser(String userId) throws Exception {
        if (!SecurityUtil.isOwnerOrAdmin(userId)) {
            throw new Exception("Acesso negado para consultar reservas de outro usuário.");
        }

        if (userService.findById(userId).isEmpty()) {
            throw new Exception(USER_NOT_FOUND);
        }

        return bookingRepository
                .findDistinctByGuests_Guest_IdAndStatusReservationNot(userId, StatusReservation.CANCELED)
                .stream()
                .map(booking -> BookingMapper.toReservedPropertyDto(
                        booking,
                        accommodationClient.getAccommodationById(booking.getAccommodation().getId())
                ))
                .toList();
    }

    @Override
    public BookingInviteResponseDto createBookingInvite(Long bookingId, BookingInviteRequestDto request) throws Exception {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new InviteNotFoundException("Reserva não encontrada"));

        AccommodationDetailsDto details = accommodationClient.getAccommodationById(booking.getAccommodation().getId());
        if (!isColiving(details)) {
            throw new InviteBadRequestException("Reservas compartilhadas só são permitidas em imóveis do tipo coliving");
        }

        int capacity = resolveCapacity(booking.getAccommodation(), details);
        int currentGuests = booking.getGuests() == null ? 0 : booking.getGuests().size();
        boolean alreadyInBooking = booking.getGuests() != null
                && booking.getGuests().stream().anyMatch(guest -> guest.getGuest().getId().equals(request.guestId()));

        if (capacity > 0 && currentGuests + (alreadyInBooking ? 0 : 1) > capacity) {
            throw new InviteBadRequestException("Número máximo de participantes atingido para este imóvel");
        }

        User guest = userRepository.findById(request.guestId())
                .orElseThrow(() -> new InviteBadRequestException(USER_NOT_FOUND));

        if (!alreadyInBooking) {
            GuestBooking guestBooking = new GuestBooking();
            guestBooking.setGuest(guest);
            guestBooking.setPaid(false);
            guestBooking.setReservation(booking);

            List<GuestBooking> updatedGuests = booking.getGuests() == null
                    ? new ArrayList<>()
                    : new ArrayList<>(booking.getGuests());
            updatedGuests.add(guestBooking);
            booking.setGuests(updatedGuests);
            bookingRepository.save(booking);
        }

        int totalGuests = booking.getGuests() == null ? 0 : booking.getGuests().size();
        BigDecimal shareAmount = calculateShareAmount(booking.getAccommodation(), totalGuests);

        BookingInvite invite = new BookingInvite();
        invite.setBooking(booking);
        invite.setGuest(guest);
        invite.setStatus(InviteStatus.PENDING);
        invite.setShareAmount(shareAmount);
        invite.setDeadline(LocalDateTime.now().plusDays(DAYS_FOR_EXPIRES));
        invite.setCreatedAt(LocalDateTime.now());

        BookingInvite savedInvite = bookingInviteRepository.save(invite);

        return new BookingInviteResponseDto(
                String.valueOf(savedInvite.getId()),
                String.valueOf(booking.getId()),
                guest.getId(),
                savedInvite.getStatus(),
                savedInvite.getShareAmount(),
                savedInvite.getDeadline()
        );
    }

    private boolean isColiving(AccommodationDetailsDto details) {
        if (details == null) {
            return false;
        }
        if (Boolean.TRUE.equals(details.isSharedHosting())) {
            return true;
        }
        return details.accommodationType() != null
                && details.accommodationType().equalsIgnoreCase("COLIVING");
    }

    private int resolveCapacity(Accommodation accommodation, AccommodationDetailsDto details) {
        if (details != null && details.maxOccupancy() != null && details.maxOccupancy() > 0) {
            return details.maxOccupancy();
        }
        if (accommodation != null && accommodation.getGuestCapacity() > 0) {
            return accommodation.getGuestCapacity();
        }
        return 0;
    }

    private BigDecimal calculateShareAmount(Accommodation accommodation, int totalGuests) {
        if (accommodation == null || accommodation.getPrice() == null || totalGuests <= 0) {
            return BigDecimal.ZERO;
        }
        return BigDecimal.valueOf(accommodation.getPrice())
                .divide(BigDecimal.valueOf(totalGuests), 2, RoundingMode.HALF_UP);
    }
}

@Service
class InviteServiceImpl implements InviteService {

    private static final String DEFAULT_HOST_NAME = "Host não informado";

    private final BookingInviteRepository bookingInviteRepository;
    private final AccommodationClient accommodationClient;

    InviteServiceImpl(BookingInviteRepository bookingInviteRepository, AccommodationClient accommodationClient) {
        this.bookingInviteRepository = bookingInviteRepository;
        this.accommodationClient = accommodationClient;
    }

    @Override
    public InviteRespondResponseDto respondToInvite(Long inviteId, InviteRespondRequestDto request) {
        BookingInvite invite = bookingInviteRepository.findById(inviteId)
                .orElseThrow(() -> new InviteNotFoundException("Convite não encontrado"));

        if (invite.getStatus() != null && invite.getStatus() != InviteStatus.PENDING) {
            throw new InviteConflictException("Este convite já foi respondido");
        }

        InviteStatus action = request.action();
        if (action == null || action == InviteStatus.PENDING) {
            throw new InviteBadRequestException("Ação inválida para resposta do convite");
        }

        invite.setStatus(action);
        BookingInvite savedInvite = bookingInviteRepository.save(invite);

        return new InviteRespondResponseDto(String.valueOf(savedInvite.getId()), savedInvite.getStatus());
    }

    @Override
    public List<InvitePendingResponseDto> listPendingInvites() {
        String guestId = SecurityUtil.getAuthenticatedUserId();
        List<BookingInvite> invites = bookingInviteRepository.findByGuest_IdAndStatus(guestId, InviteStatus.PENDING);
        if (invites == null || invites.isEmpty()) {
            return Collections.emptyList();
        }
        return invites.stream().map(this::toPendingResponse).toList();
    }

    private InvitePendingResponseDto toPendingResponse(BookingInvite invite) {
        Booking booking = invite.getBooking();
        AccommodationDetailsDto details = accommodationClient.getAccommodationById(booking.getAccommodation().getId());

        String propertyTitle = details != null && details.title() != null ? details.title() : "";
        String propertyAddress = formatAddress(details);
        String hostName = DEFAULT_HOST_NAME;
        BigDecimal totalAmount = resolveTotalAmount(booking, details);
        Integer totalParticipants = booking.getGuests() == null ? 0 : booking.getGuests().size();
        LocalDateTime checkIn = booking.getInitialDate() != null ? booking.getInitialDate().atStartOfDay() : null;
        LocalDateTime checkOut = booking.getEndDate() != null ? booking.getEndDate().atStartOfDay() : null;

        return new InvitePendingResponseDto(
                String.valueOf(invite.getId()),
                String.valueOf(booking.getId()),
                propertyTitle,
                propertyAddress,
                hostName,
                invite.getShareAmount(),
                totalAmount,
                totalParticipants,
                checkIn,
                checkOut,
                invite.getDeadline(),
                invite.getStatus()
        );
    }

    private String formatAddress(AccommodationDetailsDto details) {
        if (details == null) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        if (details.address() != null) {
            builder.append(details.address());
        }
        if (details.streetNumber() != null) {
            if (!builder.isEmpty()) {
                builder.append(", ");
            }
            builder.append(details.streetNumber());
        }
        if (details.city() != null) {
            builder.append(" - ").append(details.city());
        }
        if (details.state() != null) {
            builder.append(" - ").append(details.state());
        }
        return builder.toString();
    }

    private BigDecimal resolveTotalAmount(Booking booking, AccommodationDetailsDto details) {
        if (details != null && details.price() != null) {
            return details.price();
        }
        if (booking != null && booking.getAccommodation() != null && booking.getAccommodation().getPrice() != null) {
            return BigDecimal.valueOf(booking.getAccommodation().getPrice());
        }
        return BigDecimal.ZERO;
    }
}
