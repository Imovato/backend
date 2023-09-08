package com.unipampa.crud.repository;

import com.unipampa.crud.model.Accommodation;
import com.unipampa.util.PropertyCreator;
import lombok.extern.log4j.Log4j2;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolationException;
import java.util.Optional;

@DataJpaTest
@DisplayName("Tests for Contact Repository")
@Log4j2
class PropertyRepositoryTest {
    @Autowired
    private AccommodationRepository propertyRepository;

//    @Test
//    @DisplayName("Save creates property when Successful")
//    void save_PersistProperty_WhenSuccessful(){
//        Accommodation hostingToBeSaved = PropertyCreator.createPropertyToSaved();
//        Accommodation hostingSaved = this.propertyRepository.save(hostingToBeSaved);
//        Assertions.assertThat(hostingSaved).isNotNull();
//        Assertions.assertThat(hostingSaved.getId()).isNotNull();
//        Assertions.assertThat(hostingSaved.getName()).isEqualTo(hostingToBeSaved.getName());
//    }
//
//    @Test
//    @DisplayName("Save updates property when Successful")
//    void save_UpdateProperty_WhenSuccessful(){
//        Accommodation hostingToBeSaved = PropertyCreator.createPropertyToSaved();
//        Accommodation hostingSaved = this.propertyRepository.save(hostingToBeSaved);
//
//        hostingSaved.setNumber(529L);
//        Accommodation hostingUpdated = this.propertyRepository.save(hostingSaved);
//        log.info(hostingUpdated.getNumber());
//        Assertions.assertThat(hostingSaved).isNotNull();
//        Assertions.assertThat(hostingSaved.getId()).isNotNull();
//        Assertions.assertThat(hostingUpdated.getName()).isEqualTo(hostingSaved.getName());
//    }
    @Test
    @DisplayName("Deletes removes property when Successful")
    void delete_RemovesProperty_WhenSuccessful(){
        Accommodation hostingToBeSaved = PropertyCreator.createPropertyToSaved();
        Accommodation hostingSaved = this.propertyRepository.save(hostingToBeSaved);

        this.propertyRepository.delete(hostingSaved);
        Optional<Accommodation> propertyOptional = this.propertyRepository.findById(hostingSaved.getId());
        Assertions.assertThat(propertyOptional).isEmpty();

    }

    @Test
    @DisplayName("Find by property when Successful")
    void find_ByProperty_WhenSuccessful(){
        Accommodation hostingToBeSaved = PropertyCreator.createPropertyToSaved();
        Accommodation hostingSaved = this.propertyRepository.save(hostingToBeSaved);

        Optional<Accommodation> hostingFind = this.propertyRepository.findById(hostingSaved.getId());

        Assertions.assertThat(hostingFind).isNotNull();
        Assertions.assertThat(hostingFind.get().getId()).isNotNull();
    }
//    @Test
//    @DisplayName("Find by apartment when Successful")
//    void find_ByApartment_WhenSuccessful(){
//        Apartment apartmentToBeSaved = ApartmentCreator.createApartmentToSaved();
//        Apartment apartmentSaved = this.propertyRepository.save(apartmentToBeSaved);
//
//        Apartment apartmentFind = this.propertyRepository.findApartmentById(apartmentSaved.getId());
//
//        Assertions.assertThat(apartmentFind).isNotNull();
//        Assertions.assertThat(apartmentFind.getId()).isNotNull();
//    }

//    @Test
//    @DisplayName("Find by ground when Successful")
//    void find_ByGround_WhenSuccessful(){
//        Ground groundToBeSaved = GroundCreator.createGroundToSaved();
//        Ground groundSaved = this.propertyRepository.save(groundToBeSaved);
//
//        Ground groundFind = this.propertyRepository.findGroundById(groundSaved.getId());
//
//        Assertions.assertThat(groundFind).isNotNull();
//        Assertions.assertThat(groundFind.getId()).isNotNull();
//    }

//    @Test
//    @DisplayName("Find by house when Successful")
//    void find_ByHouse_WhenSuccessful(){
//        House houseToBeSaved = HouseCreator.createHouseToSaved();
//        House houseSaved = this.propertyRepository.save(houseToBeSaved);
//
//        House houseFind = this.propertyRepository.findHouseById(houseSaved.getId());
//
//        Assertions.assertThat(houseFind).isNotNull();
//        Assertions.assertThat(houseFind.getId()).isNotNull();
//    }

//    @Test
//    @DisplayName("Find by property return null when no property not found")
//    void findByProperty_ReturnsNull_WhenContactIsNotFound() {
//        Accommodation hostingToBeSaved = PropertyCreator.createPropertyToSaved();
//        Apartment apartmentFind = this.propertyRepository.findApartmentById(hostingToBeSaved.getId());
//
//        Assertions.assertThat(apartmentFind).isNull();
//    }

    @Test
    @DisplayName("Save throw ConstraintViolidationException when address is empty")
    void save_ConstraintViolidationException_WhenAddressIsEmpty(){
        Accommodation hosting = new Accommodation();
        Assertions.assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(() -> this.propertyRepository.save(hosting))
                .withMessageContaining("The address cannot be empty");
    }

//    @Test
//    @DisplayName("Find All By the type property on list when Successful")
//    void save_FindAllTypePropertyOnList_WhenSuccessful(){
//        Hosting hostingToBeSaved = PropertyCreator.createPropertyToSaved();
//        Apartment apartmentToBeSaved = ApartmentCreator.createApartmentToSaved();
//        House houseToBeSaved = HouseCreator.createHouseToSaved();
//        Ground groundToBeSaved = GroundCreator.createGroundToSaved();
//        this.propertyRepository.save(hostingToBeSaved);
//        this.propertyRepository.save(apartmentToBeSaved);
//        this.propertyRepository.save(houseToBeSaved);
//        this.propertyRepository.save(groundToBeSaved);
//
//        List<Hosting> properties = this.propertyRepository.findAllByDtype("Apartment");
//
//
//        Assertions.assertThat(properties).isNotNull();
//        Assertions.assertThat(properties.get(0).getDtype()).isEqualTo("Apartment");
//    }


}