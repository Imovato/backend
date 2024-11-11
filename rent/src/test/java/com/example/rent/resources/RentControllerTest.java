//package com.example.rent.resources;
//
//import com.example.rent.dto.RentDto;
//import com.example.rent.dto.RentDtoUpdate;
//import com.example.rent.entities.Rent;
//import com.example.rent.service.impl.RentServiceImp;
//import com.example.rent.util.RentCreator;
//import com.example.rent.util.RentDtoCreator;
//import com.example.rent.util.RentDtoUpdateCreator;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.ArgumentMatchers;
//import org.mockito.BDDMockito;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.List;
//
//@ExtendWith(SpringExtension.class)
//class RentControllerTest {
//    @InjectMocks
//    private RentController rentController;
//    @Mock
//    private RentServiceImp rentServiceMock;
//
//    @BeforeEach
//    void setUp() {
////        PageImpl<Rent> rentPage = new PageImpl<>(List.of(RentCreator.createValidRent()));
////        BDDMockito.when(rentServiceMock.listAll(ArgumentMatchers.any()))
////                .thenReturn(rentPage);
//
//        BDDMockito.when(rentServiceMock.listAll())
//               .thenReturn(Arrays.asList(RentCreator.createValidRent()));
//
//        BDDMockito.when(rentServiceMock.findById(ArgumentMatchers.anyLong()))
//                .thenReturn(RentCreator.createValidRent());
//
//        BDDMockito.when(rentServiceMock.findRentsByCustomer_Id(ArgumentMatchers.anyLong()))
//                .thenReturn(Arrays.asList(RentCreator.createValidRent()));
//
//        BDDMockito.when(rentServiceMock.save(ArgumentMatchers.any(RentDto.class)))
//                .thenReturn(RentCreator.createValidRent());
//
//        BDDMockito.doNothing().when(rentServiceMock).update(ArgumentMatchers.any(RentDtoUpdate.class));
//
//        BDDMockito.doNothing().when(rentServiceMock).delete(ArgumentMatchers.anyLong());
//
//    }
//
//    @Test
//    @DisplayName("ListAll returns list of rent when successful")
//    void listAll_ReturnsListOfRents_WhenSuccessful() {
//        Double expectedValue = RentCreator.createValidRent().getValue();
//        List<Rent> rents = rentController.listAll().getBody();
//
//        Assertions.assertThat(rents)
//                .isNotNull()
//                .isNotEmpty()
//                .hasSize(1);
//
//        Assertions.assertThat(rents.get(0).getValue()).isEqualTo(expectedValue);
//    }
//
//    @Test
//    @DisplayName("findById returns rent when successful")
//    void findById_ReturnsRent_WhenSuccessful() {
//        Long expectedId = RentCreator.createValidRent().getId();
//        Rent rent = rentController.findById(1L).getBody();
//
//        Assertions.assertThat(rent).isNotNull();
//
//        Assertions.assertThat(rent.getId()).isEqualTo(expectedId);
//    }
//
//    @Test
//    @DisplayName("findByIdCustomer returns a list of rents when successful")
//    void findByIdCustomer_ReturnsListRents_WhenSuccessful() {
//        String expectedName = RentCreator.createValidRent().getCustomer().getPersonalInformation().getName();
//        List<Rent> rents = rentController.findRentsByCustomerId(1L).getBody();
//
//        Assertions.assertThat(rents)
//                .isNotNull()
//                .isNotEmpty()
//                .hasSize(1);
//
//        Assertions.assertThat(rents.get(0).getCustomer().getPersonalInformation().getName()).isEqualTo(expectedName);
//    }
//
//    @Test
//    @DisplayName("findByIdCustomer returns an empty list of rents when is not found")
//    void findByIdCustomer_ReturnsEmptyListOfRents_WhenIsNotFound() {
//        BDDMockito.when(rentServiceMock.findRentsByCustomer_Id(ArgumentMatchers.anyLong()))
//                .thenReturn(Collections.emptyList());
//
//        List<Rent> rents = rentController.findRentsByCustomerId(1L).getBody();
//
//        Assertions.assertThat(rents)
//                .isNotNull()
//                .isEmpty();
//    }
//
//    @Test
//    @DisplayName("save returns rent when successful")
//    void save_ReturnsRent_WhenSuccessful() {
//        Rent rent = rentController.save(RentDtoCreator.createRentDto()).getBody();
//
//        Assertions.assertThat(rent).isNotNull().isEqualTo(RentCreator.createValidRent());
//    }
//
//    @Test
//    @DisplayName("update rent when successful")
//    void update_UpdateRent_WhenSuccessful() {
//        Assertions.assertThatCode(()-> rentController.update(RentDtoUpdateCreator.createRentDtoUpdate()))
//                .doesNotThrowAnyException();
//        ResponseEntity<Void> entity = rentController.update(RentDtoUpdateCreator.createRentDtoUpdate());
//
//        Assertions.assertThat(entity).isNotNull();
//        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
//    }
//
//    @Test
//    @DisplayName("delete removes rent when successful")
//    void delete_RemovesRent_WhenSuccessful() {
//        Assertions.assertThatCode(()-> rentController.delete(1L))
//                .doesNotThrowAnyException();
//        ResponseEntity<Void> entity = rentController.delete(1L);
//
//        Assertions.assertThat(entity).isNotNull();
//        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
//    }
//}