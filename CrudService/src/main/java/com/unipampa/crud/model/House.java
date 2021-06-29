package com.unipampa.crud.model;

import com.unipampa.crud.dto.PropertyDTO;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
public class House extends Property {

    public static House createHouse(PropertyDTO propertyDTO){
        return new ModelMapper().map(propertyDTO, House.class);
    }
}