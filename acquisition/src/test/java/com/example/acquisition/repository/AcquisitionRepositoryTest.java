package com.example.acquisition.repository;

import com.example.acquisition.model.Acquisition;
import com.example.acquisition.util.AcquisitionCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

class AcquisitionRepositoryTest {

    @Autowired
    private AcquisitionRepository acquisitionRepository;

    @Test
    void saveAcquisition(){
        Acquisition acquisitionToBeSaved = AcquisitionCreator.createAcquisitionToSaved();
        Acquisition acquisitionSaved = acquisitionRepository.save(acquisitionToBeSaved);

        Assertions.assertThat(acquisitionSaved).isNotNull();
        Assertions.assertThat(acquisitionSaved.getId()).isNotNull();

    }

    @Test
    @DisplayName("Save updates acquisition when Successful")
    void save_AcquisitionUser_WhenSuccessful(){
        Acquisition acquisitionToBeSaved = AcquisitionCreator.createAcquisitionToSaved();
        Acquisition acquisitionSaved = this.acquisitionRepository.save(acquisitionToBeSaved);

        Acquisition acquisitionUpdated = this.acquisitionRepository.save(acquisitionSaved);
        Assertions.assertThat(acquisitionSaved).isNotNull();
        Assertions.assertThat(acquisitionSaved.getId()).isNotNull();
        Assertions.assertThat(acquisitionUpdated.getId()).isEqualTo(acquisitionToBeSaved.getId());
    }

    @Test
    @DisplayName("Deletes removes acquisition when Successful")
    void delete_RemovesAcquisition_WhenSuccessful(){
        Acquisition acquisitionToBeSaved = AcquisitionCreator.createAcquisitionToSaved();
        Acquisition acquisitionSaved = this.acquisitionRepository.save(acquisitionToBeSaved);

        this.acquisitionRepository.delete(acquisitionSaved);
        Optional<Acquisition> userOptional = this.acquisitionRepository.findById(acquisitionSaved.getId());
        Assertions.assertThat(userOptional).isEmpty();

    }

    @Test
    @DisplayName("Find by acquisition when Successful")
    void find_ByAcquisition_WhenSuccessful(){
        Acquisition acquisitionToBeSaved = AcquisitionCreator.createAcquisitionToSaved();
        Acquisition acquisitionSaved = this.acquisitionRepository.save(acquisitionToBeSaved);
        Acquisition acquisitionFind = this.acquisitionRepository.findAcquisitionById(acquisitionSaved.getId());

        Assertions.assertThat(acquisitionFind).isNotNull();
        Assertions.assertThat(acquisitionFind.getId()).isNotNull();

    }

}