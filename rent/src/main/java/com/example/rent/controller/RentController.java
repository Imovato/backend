package com.example.rent.controller;

import com.example.rent.dto.RentDto;
import com.example.rent.dto.RentDtoUpdate;
import com.example.rent.enums.Status;
import com.example.rent.exceptions.ValidationException;
import com.example.rent.service.impl.RentServiceImp;
import com.example.rent.service.interfaces.IPropertyService;
import com.example.rent.service.interfaces.IRentService;
import com.example.rent.service.interfaces.ICustomerService;
import com.example.rent.model.Property;
import com.example.rent.model.Rent;
import com.example.rent.model.Customer;
import com.example.rent.service.validate.ValidateAvailableServiceImp;
import com.example.rent.service.validate.ValidateCpfServiceImp;
import com.example.rent.service.validate.ValidateGuarantorServiceImp;
import com.example.rent.service.validate.ValidateSalaryServiceImp;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/rent")
@Api(value = "MICROSERVICE Rent")
@Slf4j
@AllArgsConstructor
public class RentController {

    private IRentService rentService;
    private IPropertyService propertyService;
    private ICustomerService customerService;

    @PostMapping("/save")
    @ApiOperation(value = "Salva um arrendamento/aluguel")
    @Retry(name = "retrySave")
    public ResponseEntity<Rent> saveRent(Long idProperty, Long idCustomer, @RequestBody RentDto rentDto) throws ValidationException {
        Property property = propertyService.findPropertyById(idProperty);
        Customer customer = customerService.findCustomerById(idCustomer);

        customerService.validateCustomer(customer, property);

        Rent rent = Rent.builder()
                .startDateRent(LocalDate.now())
                .endDateRent(LocalDate.now().plusYears(1))
                .customer(customer)
                .property(property)
                .amount(property.getAmount())
                .value(property.getPrice())
                .iptu(rentDto.getIptu())
                .expirationDay(rentDto.getExpirationDay())
                .description(rentDto.getDescription())
                .build();
        rentService.saveRent(rent);
        property.setStatus(Status.RENTED);
        propertyService.updateProperty(property);
        log.info(rentService.contractTime(rent));
        return ResponseEntity.status(HttpStatus.CREATED).body(rent);
    }

    @Retry(name = "default")
    @PutMapping("/update")
    @ApiOperation(value = "Atualiza um arrendamento/aluguel")
    public ResponseEntity<Rent> updateRent(Long idRent, @RequestBody RentDtoUpdate rentDtoUpdate) {
        Rent rent = rentService.getRentById(idRent);
        rent.setValue(rentDtoUpdate.getValue());
        rent.setExpirationDay(rentDtoUpdate.getExpirationDay());
        rent.setIptu(rentDtoUpdate.getIptu());
        rent.setDescription(rentDtoUpdate.getDescription());
        rentService.updateRent(rent);
        return ResponseEntity.status(HttpStatus.OK).body(rent);
    }

    @Retry(name = "default")
    @GetMapping("/find/{id}")
    @ApiOperation(value = "Pega um arrendamento/aluguel pelo id")
    public ResponseEntity<Rent> getRentById(@PathVariable("id") Long id) {
        Rent rentFind = rentService.getRentById(id);
        if (rentFind == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(rentFind);
        }
        return ResponseEntity.status(HttpStatus.OK).body(rentFind);
    }

    @Retry(name = "default")
    @GetMapping("/user/find/{id}")
    @ApiOperation(value = "Busca aluguéis através do id de um usuário")
    public ResponseEntity<List<Rent>> getAcquisitionsByUserId(@PathVariable("id") Long idUser) {
        Customer customer = customerService.findUserById(idUser);
        List<Rent> rents = rentService.findAllRentsByUser(customer);
        if (rents.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(rents);
    }

    @GetMapping("/foo-bar")
    @Retry(name = "default", fallbackMethod = "fallBackMethod")
    //@CircuitBreaker(name = "default", fallbackMethod = "fallBackMethod")
    public String foobar() {
        log.info("Request to foo-bar is received");
        var response = new RestTemplate()
                .getForEntity("http://localhost:8080/foo-bar", String.class);
        return response.getBody();
    }

    @GetMapping("/foo-bar1")
    @RateLimiter(name = "default")
    //@Bulkhead(name = "default")
    public String foobar1() {
        log.info("Request to foo-bar is received");
        return "Foo-Bar!!!";
    }

    @GetMapping("/foo-bar2")
    @CircuitBreaker(name = "default", fallbackMethod = "fallBackMethod")
    public String foobar2() {
        log.info("Request to foo-bar is received");
        var response = new RestTemplate()
                .getForEntity("http://localhost:8080/foo-bar", String.class);
        return response.getBody();
    }

    public String fallBackMethod(Exception ex) {
        return "ERRO CAIU NO FALLBACKMETHOD";
    }

}
