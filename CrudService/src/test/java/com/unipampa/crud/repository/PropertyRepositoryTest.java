package com.unipampa.crud.repository;

import com.unipampa.crud.model.Apartment;
import com.unipampa.crud.model.Ground;
import com.unipampa.crud.model.House;
import com.unipampa.crud.model.Property;
import com.unipampa.util.ApartmentCreator;
import com.unipampa.util.GroundCreator;
import com.unipampa.util.HouseCreator;
import com.unipampa.util.PropertyCreator;
import lombok.extern.log4j.Log4j2;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@DisplayName("Tests for Contact Repository")
@Log4j2
class PropertyRepositoryTest {
    @Autowired
    private PropertyRepository propertyRepository;

    @Test
    @DisplayName("Save creates property when Successful")
    void save_PersistProperty_WhenSuccessful(){
        Property propertyToBeSaved = PropertyCreator.createPropertyToSaved();
        Property propertySaved = this.propertyRepository.save(propertyToBeSaved);
        Assertions.assertThat(propertySaved).isNotNull();
        Assertions.assertThat(propertySaved.getId()).isNotNull();
        Assertions.assertThat(propertySaved.getName()).isEqualTo(propertyToBeSaved.getName());
    }

    @Test
    @DisplayName("Save updates property when Successful")
    void save_UpdateProperty_WhenSuccessful(){
        Property propertyToBeSaved = PropertyCreator.createPropertyToSaved();
        Property propertySaved = this.propertyRepository.save(propertyToBeSaved);

        propertySaved.setNumber(529L);
        Property propertyUpdated = this.propertyRepository.save(propertySaved);
        log.info(propertyUpdated.getNumber());
        Assertions.assertThat(propertySaved).isNotNull();
        Assertions.assertThat(propertySaved.getId()).isNotNull();
        Assertions.assertThat(propertyUpdated.getName()).isEqualTo(propertySaved.getName());
    }
    @Test
    @DisplayName("Deletes removes property when Successful")
    void delete_RemovesProperty_WhenSuccessful(){
        Property propertyToBeSaved = PropertyCreator.createPropertyToSaved();
        Property propertySaved = this.propertyRepository.save(propertyToBeSaved);

        this.propertyRepository.delete(propertySaved);
        Optional<Property> propertyOptional = this.propertyRepository.findById(propertySaved.getId());
        Assertions.assertThat(propertyOptional).isEmpty();

    }

    @Test
    @DisplayName("Find by property when Successful")
    void find_ByProperty_WhenSuccessful(){
        Property propertyToBeSaved = PropertyCreator.createPropertyToSaved();
        Property propertySaved = this.propertyRepository.save(propertyToBeSaved);

        Property propertyFind = this.propertyRepository.findPropertyById(propertySaved.getId());

        Assertions.assertThat(propertyFind).isNotNull();
        Assertions.assertThat(propertyFind.getId()).isNotNull();
    }
    @Test
    @DisplayName("Find by apartment when Successful")
    void find_ByApartment_WhenSuccessful(){
        Apartment apartmentToBeSaved = ApartmentCreator.createApartmentToSaved();
        Apartment apartmentSaved = this.propertyRepository.save(apartmentToBeSaved);

        Apartment apartmentFind = this.propertyRepository.findApartmentById(apartmentSaved.getId());

        Assertions.assertThat(apartmentFind).isNotNull();
        Assertions.assertThat(apartmentFind.getId()).isNotNull();
    }

    @Test
    @DisplayName("Find by ground when Successful")
    void find_ByGround_WhenSuccessful(){
        Ground groundToBeSaved = GroundCreator.createGroundToSaved();
        Ground groundSaved = this.propertyRepository.save(groundToBeSaved);

        Ground groundFind = this.propertyRepository.findGroundById(groundSaved.getId());

        Assertions.assertThat(groundFind).isNotNull();
        Assertions.assertThat(groundFind.getId()).isNotNull();
    }

    @Test
    @DisplayName("Find by house when Successful")
    void find_ByHouse_WhenSuccessful(){
        House houseToBeSaved = HouseCreator.createHouseToSaved();
        House houseSaved = this.propertyRepository.save(houseToBeSaved);

        House houseFind = this.propertyRepository.findHouseById(houseSaved.getId());

        Assertions.assertThat(houseFind).isNotNull();
        Assertions.assertThat(houseFind.getId()).isNotNull();
    }

    @Test
    @DisplayName("Find by property return null when no property not found")
    void findByProperty_ReturnsNull_WhenContactIsNotFound() {
        Property propertyToBeSaved = PropertyCreator.createPropertyToSaved();
        Apartment apartmentFind = this.propertyRepository.findApartmentById(propertyToBeSaved.getId());

        Assertions.assertThat(apartmentFind).isNull();
    }

    @Test
    @DisplayName("Save throw ConstraintViolidationException when address is empty")
    void save_ConstraintViolidationException_WhenAddressIsEmpty(){
        Property property = new Property();
        Assertions.assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(() -> this.propertyRepository.save(property))
                .withMessageContaining("The address cannot be empty");
    }

    @Test
    @DisplayName("Find All By the type property on list when Successful")
    void save_FindAllTypePropertyOnList_WhenSuccessful(){
        Property propertyToBeSaved = PropertyCreator.createPropertyToSaved();
        Apartment apartmentToBeSaved = ApartmentCreator.createApartmentToSaved();
        House houseToBeSaved = HouseCreator.createHouseToSaved();
        Ground groundToBeSaved = GroundCreator.createGroundToSaved();
        this.propertyRepository.save(propertyToBeSaved);
        this.propertyRepository.save(apartmentToBeSaved);
        this.propertyRepository.save(houseToBeSaved);
        this.propertyRepository.save(groundToBeSaved);

        List<Property> properties = this.propertyRepository.findAllByDtype("Apartment");


        Assertions.assertThat(properties).isNotNull();
        Assertions.assertThat(properties.get(0).getDtype()).isEqualTo("Apartment");
    }


}