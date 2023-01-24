package com.example.rent.service.impl;

import com.example.rent.enums.Status;
import com.example.rent.exceptions.BadRequestException;
import com.example.rent.model.Customer;
import com.example.rent.model.Property;
import com.example.rent.model.Rent;
import com.example.rent.repository.CustomerRepository;
import com.example.rent.repository.PropertyRepository;
import com.example.rent.repository.RentRepository;
import com.example.rent.util.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.validation.ConstraintViolationException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;

@ExtendWith(SpringExtension.class)
class RentServiceImpTest {
    @InjectMocks
    private RentServiceImp rentService;
    @Mock
    private CustomerServiceImp customerService;
    @Mock
    private PropertyServiceImp propertyService;
    @Mock
    private RentRepository rentRepositoryMock;
    @Mock
    private CustomerRepository customerRepositoryMock;
    @Mock
    private PropertyRepository propertyRepositoryMock;

    @BeforeEach
    void setUp() {
//        PageImpl<Rent> rentPage = new PageImpl<>(List.of(RentCreator.createValidRent()));
//        BDDMockito.when(rentServiceMock.listAll(ArgumentMatchers.any()))
//                .thenReturn(rentPage);
        BDDMockito.when(rentRepositoryMock.findAll())
                .thenReturn(Arrays.asList(RentCreator.createValidRent()));

        BDDMockito.when(rentRepositoryMock.findById(anyLong()))
                .thenReturn(Optional.of(RentCreator.createValidRent()));

        BDDMockito.when(rentRepositoryMock.findByCustomerId(anyLong()))
                .thenReturn(Arrays.asList(RentCreator.createValidRent()));

        BDDMockito.when(rentRepositoryMock.save(ArgumentMatchers.any(Rent.class)))
                .thenReturn(RentCreator.createValidRent());

        BDDMockito.doNothing().when(rentRepositoryMock).delete(ArgumentMatchers.any(Rent.class));

        // mock of customer and property
        BDDMockito.when(customerRepositoryMock.save(ArgumentMatchers.any(Customer.class)))
                .thenReturn(RentDtoCreator.createCustomer());

        BDDMockito.when(propertyRepositoryMock.save(ArgumentMatchers.any(Property.class)))
                .thenReturn(RentDtoCreator.createProperty());

        BDDMockito.when(customerRepositoryMock.findCustomerById(ArgumentMatchers.anyLong()))
                .thenReturn(RentDtoCreator.createCustomer());

        BDDMockito.when(propertyRepositoryMock.findPropertyById(ArgumentMatchers.anyLong()))
                .thenReturn(RentDtoCreator.createProperty());
    }

    @Test
    @DisplayName("ListAll returns list of rent when successful")
    void listAll_ReturnsListOfRents_WhenSuccessful() {
        Double expectedValue = RentCreator.createValidRent().getValue();
        List<Rent> rents = rentService.listAll();

        Assertions.assertThat(rents)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(rents.get(0).getValue()).isEqualTo(expectedValue);
    }

    @Test
    @DisplayName("findById returns rent when successful")
    void findById_ReturnsRent_WhenSuccessful() {
        Long expectedId = RentCreator.createValidRent().getId();
        Rent rent = rentService.findById(1L);

        Assertions.assertThat(rent).isNotNull();

        Assertions.assertThat(rent.getId()).isEqualTo(expectedId);
    }

    @Test
    @DisplayName("findByIdCustomer returns a list of rents when successful")
    void findByIdCustomer_ReturnsListRents_WhenSuccessful() {
        String expectedName = RentCreator.createValidRent().getCustomer().getPersonalInformation().getName();
        List<Rent> rents = rentService.findRentsByCustomer_Id(1L);

        Assertions.assertThat(rents)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(rents.get(0).getCustomer().getPersonalInformation().getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("findByIdCustomer returns an empty list of rents when is not found")
    void findByIdCustomer_ReturnsEmptyListOfRents_WhenIsNotFound() {
        BDDMockito.when(rentRepositoryMock.findByCustomerId(anyLong()))
                .thenReturn(Collections.emptyList());

        List<Rent> rents = rentService.findRentsByCustomer_Id(1L);

        Assertions.assertThat(rents)
                .isNotNull()
                .isEmpty();
    }

    @Test
    @DisplayName("save returns rent when successful")
    void save_ReturnsRent_WhenSuccessful() {
        Customer savedCustomer = customerRepositoryMock.save(CustomerCreator.createCustomer());
        Property savedProperty = propertyRepositoryMock.save(PropertyCreator.createProperty());
        BDDMockito.when(customerService.findCustomerById(savedCustomer.getId())).thenReturn(savedCustomer);
        BDDMockito.when(propertyService.findPropertyById(savedProperty.getId())).thenReturn(savedProperty);

        Rent rent = rentService.save(RentDtoCreator.createRentDto());
        Assertions.assertThat(rent).isNotNull();
        Assertions.assertThat(rent.getValue()).isEqualTo(1250D);
        Assertions.assertThat(savedProperty.getStatus())
                .isNotNull()
                .isEqualTo(Status.RENTED);
    }

    @Test
    @DisplayName("update rent when successful")
    void update_UpdateRent_WhenSuccessful() {
        Assertions.assertThatCode(() -> rentService.update(RentDtoUpdateCreator.createRentDtoUpdate()))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("delete removes rent when successful")
    void delete_RemovesRent_WhenSuccessful() {
        Assertions.assertThatCode(() -> rentService.delete(1L))
                .doesNotThrowAnyException();
    }
    @Test
    @DisplayName("findById throw BadRequestException when rent is not found")
    void findById_ThrowBadRequestException_WhenRentIsNotFound() {
        BDDMockito.when(rentRepositoryMock.findById(anyLong()))
                .thenReturn(Optional.empty());

        Assertions.assertThatExceptionOfType(BadRequestException.class)
                .isThrownBy(()->this.rentService.findById(1L));
    }
}