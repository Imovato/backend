package com.unipampa.crud.model;

import com.unipampa.crud.dto.UserDTO;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
public class Employee extends User {

    public static Employee createEmployee(UserDTO userDTO){
        return new ModelMapper().map(userDTO, Employee.class);
    }

}