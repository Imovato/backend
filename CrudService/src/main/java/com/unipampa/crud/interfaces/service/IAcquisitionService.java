package com.unipampa.crud.interfaces.service;

import java.util.List;

import com.unipampa.crud.model.Acquisition;

public interface IAcquisitionService {
	void saveAcquisition(Acquisition acquisition);
	Acquisition findAcquisitionById(Long id);
	List<Acquisition> findAllAcquisitions();
	void deleteAcquisition(Long id);
	Acquisition updateAcquisition(Acquisition acquisition);

}
