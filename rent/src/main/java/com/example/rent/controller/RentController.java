package com.example.rent.controller;

import com.example.rent.dto.RentDto;
import com.example.rent.interfaces.services.IPropertyService;
import com.example.rent.interfaces.services.IRentServices;
import com.example.rent.interfaces.services.IUserService;
import com.example.rent.model.Property;
import com.example.rent.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.rent.model.Rent;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("/rent")
@Api(value = "MICROSERVICE Rent")
public class RentController {

    private IRentServices rentService;
    private IPropertyService propertyService;
    private IUserService userService;


    public RentController(IRentServices service) {
        this.rentService = service;
    }

    @PostMapping("/save")
    @ApiOperation(value = "Salva uma arrendamento/aluguel")
    public void saveRent(@RequestBody RentDto dto) {
        Property property = propertyService.findPropertyById(dto.getId());
        User user = userService.finUserById(dto.getId());
        Rent rent = new Rent();
        rent.setProperty(property);
        rent.setValue(property.getPrice());
        rentService.saveRent(rent);
    }

//    @PutMapping("/update")
//    @ApiOperation(value = "atualiza um arrendamento/aluguel")
//    public ResponseEntity<?> updateRent(@RequestBody Rent rent) {
//        Rent updateRent = rentService.updateRent(rent);
//        return new ResponseEntity<>(updateRent, HttpStatus.OK);
//    }
//
//    @GetMapping("/find/{id}")
//    @ApiOperation(value = "pega um arrendamento/aluguel pelo id")
//    public ResponseEntity<?> getRentById(@PathVariable("id") Long id) {
//        Rent rentFinded = rentService.findRentById(id);
//        return new ResponseEntity<>(rentFinded, HttpStatus.OK);
//    }

}
