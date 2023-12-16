package com.unipampa.crud.service;

import com.unipampa.crud.dto.AccommodationDTO;
import com.unipampa.crud.model.Accommodation;

public interface StrategySaveProperty {

    Accommodation save(AccommodationDTO propertyDTO);
}
