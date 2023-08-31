package com.unipampa.crud.service;

import com.unipampa.crud.dto.PropertyDTO;
import com.unipampa.crud.model.Hosting;

public interface StrategySaveProperty {

    Hosting save(PropertyDTO propertyDTO);
}
