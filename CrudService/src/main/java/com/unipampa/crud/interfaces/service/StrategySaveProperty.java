package com.unipampa.crud.interfaces.service;

import com.unipampa.crud.dto.PropertyDTO;
import com.unipampa.crud.model.Property;

public interface StrategySaveProperty {

    Property save(PropertyDTO propertyDTO);
}
