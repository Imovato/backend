package com.unipampa.crud.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.unipampa.crud.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Customer extends User {

    @Column(name = "cpf")
    private String cpf;
    @Column(name = "phone")
    private String phone;
    @Column(name = "address")
    private String address;

    public static Customer createCustomer(UserDTO userDTO) {
        return new ModelMapper().map(userDTO, Customer.class);
    }
}