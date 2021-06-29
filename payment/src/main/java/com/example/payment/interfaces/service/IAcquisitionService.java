package com.example.payment.interfaces.service;

import java.util.List;

import com.example.payment.dto.AcquisitionDTO;
import com.example.payment.model.Acquisition;

public interface IAcquisitionService {

    void saveAcquisition(Acquisition acquisition);
    Acquisition findAcquisitionById(Long id);
    List<Acquisition> findAllAcquisitions();
    void deleteAcquisition(Long id);
    Acquisition updateAcquisition(Acquisition acquisition);
    public AcquisitionDTO createAcquisition(AcquisitionDTO acquisitionDTO);

}
