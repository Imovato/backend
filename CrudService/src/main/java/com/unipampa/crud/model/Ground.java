package com.unipampa.crud.model;

import com.unipampa.crud.dto.PropertyDTO;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
public class Ground extends Property {

    public static Ground createGround(PropertyDTO propertyDTO){
        return new ModelMapper().map(propertyDTO, Ground.class);
    }
}