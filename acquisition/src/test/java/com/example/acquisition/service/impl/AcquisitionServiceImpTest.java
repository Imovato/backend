package com.example.acquisition.service.impl;

import com.example.acquisition.model.Acquisition;
import com.example.acquisition.repository.AcquisitionRepository;
import com.example.acquisition.repository.UserRepository;
import com.example.acquisition.util.AcquisitionCreator;
import com.example.acquisition.util.AcquisitionPut;
import com.netflix.discovery.converters.Auto;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@Slf4j
class AcquisitionServiceImpTest {

    @InjectMocks
    private AcquisitionServiceImp acquisitionServiceImp;
    @Mock
    private AcquisitionRepository acquisitionRepository;

    @BeforeEach
    void setUp(){
        BDDMockito.when(acquisitionRepository.save(ArgumentMatchers.any(Acquisition.class)))
                .thenReturn(AcquisitionCreator.createValidUpdateAcquisition());

        BDDMockito.doNothing().when(acquisitionRepository).delete(ArgumentMatchers.any(Acquisition.class));
    }


    @Test
    @DisplayName("Save acquisition when successful")
    void save() {

        Assertions.assertThatCode(() -> acquisitionServiceImp.save(AcquisitionPut.createAcquisitionPutRequestBody()))
                .doesNotThrowAnyException();
    }

    @Test
    void findAcquisitionByProperty() {
        acquisitionRepository.save(AcquisitionCreator.createValidAcquisition());
        Long expectedId = AcquisitionCreator.createValidAcquisition().getId();

        Acquisition acquisition = acquisitionServiceImp.findAcquisitionById(AcquisitionCreator.createValidAcquisition().getId());
        Assertions.assertThat(acquisition)
                .isNotNull();
        Assertions.assertThat(acquisition.getId()).isNotNull().isEqualTo(expectedId);
    }
}