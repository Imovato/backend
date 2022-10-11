package com.example.rent.controller;

import com.example.rent.dto.RentDto;
import com.example.rent.enums.Status;
import com.example.rent.interfaces.services.IPropertyService;
import com.example.rent.interfaces.services.IRentService;
import com.example.rent.interfaces.services.IUserService;
import com.example.rent.model.Property;
import com.example.rent.model.Rent;
import com.example.rent.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/rent")
@Api(value = "MICROSERVICE Rent")
@Slf4j @AllArgsConstructor
public class RentController {

    private IRentService rentService;
    private IPropertyService propertyService;
    private IUserService userService;

    @PostMapping("/save")
    @ApiOperation(value = "Salva um arrendamento/aluguel")
    public ResponseEntity<?> saveRent(@RequestBody RentDto dto) {
        Property property = propertyService.findPropertyById(dto.getId());

        if(property.getStatus().equals(Status.DISPONIVEL)){
            User user = userService.findUserById(dto.getIdUser());
            Rent rent = Rent.builder()
                    .amount(property.getAmount())
                    .value(property.getPrice())
                    .property(property)
                    .user(user)
                    .value(property.getPrice()).build();
            rentService.saveRent(rent);
            property.setStatus(Status.ALUGADO);
            propertyService.updateProperty(property);
            return (ResponseEntity<?>) ResponseEntity.status(HttpStatus.CREATED);
        }
        log.error("Você nao pode alugar esse imóvel!");
        return (ResponseEntity<?>) ResponseEntity.status(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/update")
    @ApiOperation(value = "Atualiza um arrendamento/aluguel")
    public ResponseEntity<Rent> updateRent(@RequestBody Rent rent) {
        Rent updateRent = rentService.updateRent(rent);
        return new ResponseEntity<>(updateRent, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    @ApiOperation(value = "Pega um arrendamento/aluguel pelo id")
    public ResponseEntity<Rent> getRentById(@PathVariable("id") UUID id) {
        Rent rentFind = rentService.getRentById(id);
        if (rentFind == null) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(rentFind, HttpStatus.OK);
    }

    @GetMapping("/user/find/{id}")
    @ApiOperation(value = "Busca aluguéis através do id de um usuário")
    public ResponseEntity<List<Rent>> getAcquisitionsByUserId(@PathVariable("id") UUID idUser) {
        User user = userService.findUserById(idUser);
        List<Rent> rents = rentService.findAllRentsByUser(user);
        if (rents.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(rents);
    }

}
