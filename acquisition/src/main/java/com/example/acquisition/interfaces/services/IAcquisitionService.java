package com.example.acquisition.interfaces.services;

import java.util.List;

import com.example.acquisition.dto.AcquisitionDTO;
import com.example.acquisition.model.Acquisition;
import com.example.acquisition.model.Property;
import com.example.acquisition.model.User;

public interface IAcquisitionService{
	void saveAcquisition(Acquisition acquisition);

	List<Acquisition> findAllAcquisitionsByUser(User user);

    Acquisition findAcquisitionByProperty(Property property);

	public AcquisitionDTO createAcquisition(AcquisitionDTO acquisitionDTO);


}
