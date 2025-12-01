package com.unipampa.crud.integration;

import com.unipampa.crud.dto.AccommodationDTO;
import com.unipampa.crud.entities.Accommodation;
import com.unipampa.crud.enums.AccommodationStats;
import com.unipampa.crud.enums.AccommodationType;
import com.unipampa.crud.repository.AccommodationRepository;
import com.unipampa.crud.sender.AccommodationSender;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc //permite fazer chamada HTTP sem subir um servidor real
class AccommodationIntegrationTest extends BaseIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AccommodationRepository accommodationRepository; //permite salvar e buscar no mongoDB

    @Autowired
    private RabbitAdmin rabbitAdmin; //gerencia filas

    @SpyBean //espião pra verificar se mensagens RabbitMQ foram enviadas
    private AccommodationSender accommodationSender;

    private Accommodation testAccommodation;

    @BeforeEach
    void setUp() {
        // Limpa o banco antes de cada teste
        accommodationRepository.deleteAll();

        // Cria uma acomodação de teste
        testAccommodation = Accommodation.builder()
                .title("Apartamento Cobertura")
                .description("Lindo apartamento com vista para o mar")
                .price(new BigDecimal("350.00"))
                .roomCount(3)
                .bathroomCount(2)
                .allowsPets(true)
                .allowsChildren(true)
                .isSharedHosting(false)
                .isAuthorizedAnnouncement(true)
                .type(AccommodationType.APARTMENT)
                .city("Porto Alegre")
                .neighborhood("Moinhos de Vento")
                .streetNumber(100)
                .zipCode("90570-020")
                .address("Rua Exemplo")
                .state("RS")
                .maxOccupancy(6)
                .hostId("host123")
                .stats(AccommodationStats.AVAILABLE)
                .imagesUrls(List.of())
                .build();
    }

    @AfterEach
    void tearDown() {
        accommodationRepository.deleteAll();
    }

    @Test
    void testMongoDBConnection() {
        // Testa se o MongoDB está funcionando
        assertThat(mongoDBContainer.isRunning()).isTrue();
    }

    @Test
    void testRabbitMQConnection() {
        // Testa se o RabbitMQ está funcionando
        assertThat(rabbitMQContainer.isRunning()).isTrue();
    }

    @Test
    void shouldSaveAccommodationToMongoDB() {
        // Given
        Accommodation saved = accommodationRepository.save(testAccommodation);

        // When
        Accommodation found = accommodationRepository.findById(saved.getId()).orElse(null);

        // Then
        assertThat(found).isNotNull();
        assertThat(found.getTitle()).isEqualTo("Apartamento Cobertura");
        assertThat(found.getPrice()).isEqualByComparingTo(new BigDecimal("350.00"));
        assertThat(found.getCity()).isEqualTo("Porto Alegre");
        assertThat(found.getStats()).isEqualTo(AccommodationStats.AVAILABLE);
    }

    @Test
    void shouldFindAllAccommodations() {
        // Given
        accommodationRepository.save(testAccommodation);
        
        Accommodation another = Accommodation.builder()
                .title("Casa de Campo")
                .description("Casa tranquila no interior")
                .price(new BigDecimal("200.00"))
                .roomCount(2)
                .bathroomCount(1)
                .allowsPets(false)
                .allowsChildren(true)
                .type(AccommodationType.HOUSE)
                .city("Gramado")
                .neighborhood("Centro")
                .streetNumber(50)
                .zipCode("95670-000")
                .address("Rua das Flores")
                .state("RS")
                .maxOccupancy(4)
                .hostId("host456")
                .stats(AccommodationStats.AVAILABLE)
                .imagesUrls(List.of())
                .build();
        accommodationRepository.save(another);

        // When
        List<Accommodation> accommodations = accommodationRepository.findAll();

        // Then
        assertThat(accommodations).hasSize(2);
        assertThat(accommodations)
                .extracting(Accommodation::getTitle)
                .containsExactlyInAnyOrder("Apartamento Cobertura", "Casa de Campo");
    }

    @Test
    @WithMockUser(roles = {"HOST"}) //simula um usuario autenticado
    void shouldRetrieveAllAccommodationsViaAPI() throws Exception {
        // Given
        accommodationRepository.save(testAccommodation);

        // When & Then
        mockMvc.perform(get("/accommodations"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].title").value("Apartamento Cobertura"));
    }

    @Test
    @WithMockUser(roles = {"HOST"})
    void shouldRetrieveAccommodationByIdViaAPI() throws Exception {
        // Given
        Accommodation saved = accommodationRepository.save(testAccommodation);

        // When & Then
        mockMvc.perform(get("/accommodations/" + saved.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(saved.getId()))
                .andExpect(jsonPath("$.title").value("Apartamento Cobertura"))
                .andExpect(jsonPath("$.city").value("Porto Alegre"));
    }

    @Test
    @WithMockUser(roles = {"HOST"})
    void shouldReturn404WhenAccommodationNotFound() throws Exception {
        // When & Then
        mockMvc.perform(get("/accommodations/nonexistent-id"))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldUpdateAccommodationStats() {
        // Given
        Accommodation saved = accommodationRepository.save(testAccommodation);
        
        // When
        saved.setStats(AccommodationStats.UNAVAILABLE);
        accommodationRepository.save(saved);
        
        // Then
        Accommodation updated = accommodationRepository.findById(saved.getId()).orElse(null);
        assertThat(updated).isNotNull();
        assertThat(updated.getStats()).isEqualTo(AccommodationStats.UNAVAILABLE);
    }

    @Test
    void shouldDeleteAccommodation() {
        // Given
        Accommodation saved = accommodationRepository.save(testAccommodation);
        String accommodationId = saved.getId();
        
        // When
        accommodationRepository.deleteById(accommodationId);
        
        // Then
        assertThat(accommodationRepository.findById(accommodationId)).isEmpty();
        assertThat(accommodationRepository.count()).isZero();
    }

    @Test
    void shouldHandleMultipleAccommodationsForSameHost() {
        // Given
        String hostId = "host-multi-123";
        
        testAccommodation.setHostId(hostId);
        accommodationRepository.save(testAccommodation);
        
        Accommodation second = Accommodation.builder()
                .title("Segunda Propriedade")
                .description("Outra propriedade do mesmo anfitrião")
                .price(new BigDecimal("400.00"))
                .roomCount(2)
                .bathroomCount(1)
                .type(AccommodationType.APARTMENT)
                .city("São Paulo")
                .neighborhood("Jardins")
                .streetNumber(200)
                .zipCode("01234-000")
                .address("Av Principal")
                .state("SP")
                .maxOccupancy(4)
                .hostId(hostId)
                .stats(AccommodationStats.AVAILABLE)
                .imagesUrls(List.of())
                .build();
        accommodationRepository.save(second);
        
        // When
        List<Accommodation> allAccommodations = accommodationRepository.findAll();
        
        // Then
        assertThat(allAccommodations).hasSize(2);
        assertThat(allAccommodations)
                .allMatch(acc -> acc.getHostId().equals(hostId));
    }

    @Test
    void shouldVerifyAccommodationDataIntegrity() {
        // Given & When
        Accommodation saved = accommodationRepository.save(testAccommodation);
        Accommodation retrieved = accommodationRepository.findById(saved.getId()).orElse(null);
        
        // Then - Verifica todos os campos importantes
        assertThat(retrieved).isNotNull();
        assertThat(retrieved.getTitle()).isEqualTo(testAccommodation.getTitle());
        assertThat(retrieved.getDescription()).isEqualTo(testAccommodation.getDescription());
        assertThat(retrieved.getPrice()).isEqualByComparingTo(testAccommodation.getPrice());
        assertThat(retrieved.getRoomCount()).isEqualTo(testAccommodation.getRoomCount());
        assertThat(retrieved.getBathroomCount()).isEqualTo(testAccommodation.getBathroomCount());
        assertThat(retrieved.isAllowsPets()).isEqualTo(testAccommodation.isAllowsPets());
        assertThat(retrieved.isAllowsChildren()).isEqualTo(testAccommodation.isAllowsChildren());
        assertThat(retrieved.getType()).isEqualTo(testAccommodation.getType());
        assertThat(retrieved.getCity()).isEqualTo(testAccommodation.getCity());
        assertThat(retrieved.getMaxOccupancy()).isEqualTo(testAccommodation.getMaxOccupancy());
        assertThat(retrieved.getStats()).isEqualTo(testAccommodation.getStats());
    }
}
