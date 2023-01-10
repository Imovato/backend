package com.example.rent.controller;

import com.example.rent.dto.RentDto;
import com.example.rent.dto.RentDtoUpdate;
import com.example.rent.enums.Status;
import com.example.rent.exceptions.ValidationException;
import com.example.rent.model.Customer;
import com.example.rent.model.Property;
import com.example.rent.model.Rent;
import com.example.rent.service.interfaces.ICustomerService;
import com.example.rent.service.interfaces.IPropertyService;
import com.example.rent.service.interfaces.IRentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/rent")
@Api(value = "Microsserviço aluguel")
@RequiredArgsConstructor
public class RentController {

    private final IRentService rentService;
    private final IPropertyService propertyService;
    private final ICustomerService customerService;

    //@Retry(name = "retrySave")
    @PostMapping("/save")
    @ApiOperation(value = "Salva um arrendamento/aluguel")
    public ResponseEntity<Rent> save(@RequestBody @Valid RentDto rentDto) throws ValidationException {
        Property property = propertyService.findPropertyById(rentDto.getId_property());
        Customer customer = customerService.findCustomerById(rentDto.getId_customer());

        //customerService.validateCustomer(customer, property);

        Rent rent = Rent.builder()
                .customer(customer)
                .property(property)
                .endDateRent(LocalDate.now().plusYears(1))
                .dateAdjustmentIGPM(rentDto.getDateAdjustmentIGPM())
                .iptu(rentDto.getIptu())
                .water(rentDto.getWater())
                .energy(rentDto.getEnergy())
                .condominium(rentDto.getCondominium())
                .value(property.getPrice())
                .description(rentDto.getDescription())
                .build();
        rentService.save(rent);
        property.setStatus(Status.RENTED);
        propertyService.updateProperty(property);
        return new ResponseEntity<>(rent, HttpStatus.CREATED);
    }

    //@Retry(name = "default")
    @PutMapping("/replace")
    @ApiOperation(value = "Atualiza um arrendamento/aluguel")
    public ResponseEntity<Void> replace(@RequestBody RentDtoUpdate rentDtoUpdate) {
        Rent rent = rentService.findByIdOrThrowBadRequestException(rentDtoUpdate.getId_rent());
        rent.setValue(rentDtoUpdate.getValue());
        rent.setIptu(rentDtoUpdate.getIptu());
        rent.setDescription(rentDtoUpdate.getDescription());
        rentService.replace(rent);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

   // @Retry(name = "default")
    @GetMapping("/find/{id}")
    @ApiOperation(value = "Pega um arrendamento/aluguel pelo id")
    public ResponseEntity<Rent> findById(@PathVariable("id") Long id) {
        Rent rentFind = rentService.findByIdOrThrowBadRequestException(id);
        if (rentFind == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(rentFind);
        }
        return ResponseEntity.ok(rentFind);
    }

   // @Retry(name = "default")
    @GetMapping("/user/find/{id}")
    @ApiOperation(value = "Busca aluguéis através do id de um usuário")
    public ResponseEntity<List<Rent>> getAcquisitionsByUserId(@PathVariable("id") Long idUser) {
        Customer customer = customerService.findCustomerById(idUser);
        List<Rent> rents = rentService.findAllRentsByUser(customer);
        if (rents.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(rents);
    }
}
