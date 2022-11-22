package com.unipampa.crud.controller;

import com.unipampa.crud.interfaces.service.IPropertyService;
import com.unipampa.crud.model.Property;
import com.unipampa.util.PropertyCreator;
import com.unipampa.util.PropertyDTO1;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class PropertyControllerTest {

    @InjectMocks
    private PropertyController propertyController;
    @Mock
    private IPropertyService iPropertyService;

    @BeforeEach
    void setUp(){
       // BDDMockito.when(iPropertyService.findAllProperties())
            //    .thenReturn(Arrays.asList(PropertyCreator.createValidProperty()));

//        BDDMockito.when(iPropertyService.getPropertyById(ArgumentMatchers.anyLong()))
            //    .thenReturn(PropertyCreator.createValidProperty());

        BDDMockito.doNothing().when(iPropertyService).saveProperty(ArgumentMatchers.any(Property.class));

//        BDDMockito.when(iPropertyService.updateProperty(ArgumentMatchers.any(Property.class)))
//                .thenReturn(PropertyCreator.createValidUpdateProperty());
//
//        BDDMockito.doNothing().when(iPropertyService).deleteProperty(ArgumentMatchers.anyLong());
    }

    @Test
    void saveProperty() {
        Assertions.assertThatCode(() -> propertyController.saveProperty(PropertyDTO1.createPropertyPostRequestBody()))
                .doesNotThrowAnyException();
        ResponseEntity<Void> entity = propertyController.saveProperty(PropertyDTO1.createPropertyPostRequestBody());
        Assertions.assertThat(entity).isNotNull();
        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    void getAllProperties() {
    }

    @Test
    void getPropertyById() {
    }

    @Test
    void deleteProperty() {
    }

    @Test
    void uploadImage() {
    }

    @Test
    void saveApartment() {
    }

    @Test
    void getAllApartments() {
    }

    @Test
    void getApartmentById() {
    }

    @Test
    void updateApartment() {
    }

    @Test
    void saveHouse() {
    }

    @Test
    void getAllHouses() {
    }

    @Test
    void getHouseById() {
    }

    @Test
    void updateHouse() {
    }

    @Test
    void saveGround() {
    }

    @Test
    void getAllGrounds() {
    }

    @Test
    void getGroundById() {
    }

    @Test
    void testUpdateApartment() {
    }
}