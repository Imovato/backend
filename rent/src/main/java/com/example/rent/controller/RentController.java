package com.example.rent.controller;

import com.example.rent.dto.RentDto;
import com.example.rent.interfaces.services.IPropertyService;
import com.example.rent.interfaces.services.IRentServices;
import com.example.rent.interfaces.services.IUserService;
import com.example.rent.model.Property;
import com.example.rent.model.Rent;
import com.example.rent.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/rent")
@Api(value = "MICROSERVICE Rent")
public class RentController {

    private IRentServices rentService;
    private IPropertyService propertyService;
    private IUserService userService;


    public RentController(IRentServices rentService, IPropertyService propertyService, IUserService userService ) {
        this.rentService = rentService;
        this.propertyService = propertyService;
        this.userService = userService;
    }

    @PostMapping("/save")
    @ApiOperation(value = "Salva uma arrendamento/aluguel")
    public void saveRent(@RequestBody RentDto dto) {
        Property property = propertyService.findPropertyById(dto.getId());

        if(property.getStatus() == null){
            Rent rent = new Rent();
            User user = userService.finUserById(dto.getIdUser());
            rent.setAmount(property.getAmount());
            rent.setValue(property.getPrice());
            rent.setProperty(property);
            rent.setUser(user);
            rent.setValue(property.getPrice());
            rentService.saveRent(rent);
            property.setStatus("Alugado");
            propertyService.updateProperty(property);
        } else {
            System.out.println("você nao pode alugar esse imóvel");
        }
    }

//    @PutMapping("/update")
//    @ApiOperation(value = "atualiza um arrendamento/aluguel")
//    public ResponseEntity<?> updateRent(@RequestBody Rent rent) {
//        Rent updateRent = rentService.updateRent(rent);
//        return new ResponseEntity<>(updateRent, HttpStatus.OK);
//    }
//
    @GetMapping("/find/{id}")
    @ApiOperation(value = "pega um arrendamento/aluguel pelo id")
    public ResponseEntity<?> getRentById(@PathVariable("id") Long id) {
        Rent rentFind = rentService.getRentById(id);
        return new ResponseEntity<>(rentFind, HttpStatus.OK);
    }

    @GetMapping("/findall")
    @ApiOperation(value = "pega uma lista de arrendamentos/aluguéis")
    public ResponseEntity<?> getRentById(@PathVariable("id") Long id) {
        Rent rentFind = rentService.getRentById(id);
        return new ResponseEntity<>(rentFind, HttpStatus.OK);
    }

}
