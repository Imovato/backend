package com.example.rent.controller;

import com.example.rent.dto.RentDto;
import com.example.rent.enums.Status;
import com.example.rent.service.impl.RentServiceImp;
import com.example.rent.service.interfaces.IPropertyService;
import com.example.rent.service.interfaces.IRentService;
import com.example.rent.service.interfaces.ICustomerService;
import com.example.rent.model.Property;
import com.example.rent.model.Rent;
import com.example.rent.model.Customer;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/rent")
@Api(value = "MICROSERVICE Rent")
@Slf4j @AllArgsConstructor
public class RentController {

    private IRentService rentService;
    private IPropertyService propertyService;
    private ICustomerService userService;

    @PostMapping("/save")
    @ApiOperation(value = "Salva um arrendamento/aluguel")
    @Retry(name = "default", fallbackMethod = "")
    @RateLimiter(name = "default")
    @Bulkhead(name = "default")
    public ResponseEntity<Rent> saveRent(Long idProperty, Long idCustomer, @RequestBody RentDto rentDto) {
        Property property = propertyService.findPropertyById(idProperty);
        Customer customer = userService.findCustomerById(idCustomer);
        Rent rent = new Rent();

        //if(property.getStatus().equals(Status.AVAILABLE)){
            //rent.setStartDateRent(LocalDate.now());
                rent.setCustomer(customer);
                rent.setProperty(property);
                rent.setAmount(property.getAmount());
                rent.setValue(property.getPrice());
                rent.setIptu(rentDto.getIptu());
                rent.setExpirationDay(rentDto.getExpirationDay());
                rent.setDescription(rentDto.getDescription());
                rentService.saveRent(rent);
                property.setStatus(Status.RENTED);
                propertyService.updateProperty(property);
               // return ResponseEntity.status(HttpStatus.CREATED).body(rent);
      //  }
        //log.error("Você nao pode alugar esse imóvel!");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(rent);
    }

    @PutMapping("/update")
    @CircuitBreaker(name = "default", fallbackMethod = "s")
    @ApiOperation(value = "Atualiza um arrendamento/aluguel")
    public ResponseEntity<Rent> updateRent(@RequestBody Rent rent) {
        Rent updateRent = rentService.updateRent(rent);
        return new ResponseEntity<>(updateRent, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    @ApiOperation(value = "Pega um arrendamento/aluguel pelo id")
    public ResponseEntity<Rent> getRentById(@PathVariable("id") Long id) {
        Rent rentFind = rentService.getRentById(id);
        if (rentFind == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(rentFind);
    }

    @GetMapping("/user/find/{id}")
    @ApiOperation(value = "Busca aluguéis através do id de um usuário")
    public ResponseEntity<List<Rent>> getAcquisitionsByUserId(@PathVariable("id") Long idUser) {
        Customer customer = userService.findCustomerById(idUser);
        List<Rent> rents = rentService.findAllRentsByUser(customer);
        if (rents.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(rents);
    }

    public void fallbackMethod() {
        log.error("Erro");
    }

}
