package com.example.rent.resources;

import com.example.rent.dto.RentDto;
import com.example.rent.dto.RentDtoUpdate;
import com.example.rent.entities.Rent;
import com.example.rent.service.interfaces.IRentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/rent")
@Api(value = "Microserviço aluguel")
@RequiredArgsConstructor
public class RentController {

    private final IRentService rentService;

    //@Retry(name = "retrySave")
    @PostMapping("/save")
    @ApiOperation(value = "Salva um arrendamento/aluguel")
    public ResponseEntity<Rent> save(@RequestBody @Valid RentDto rentDto) {
        return new ResponseEntity<>(rentService.save(rentDto), HttpStatus.CREATED);
    }

    //@Retry(name = "default")
    @PutMapping("/update")
    @ApiOperation(value = "Atualiza um arrendamento/aluguel")
    public ResponseEntity<Void> update(@RequestBody RentDtoUpdate rentDtoUpdate) {
        rentService.update(rentDtoUpdate);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping( "/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        rentService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

   // @Retry(name = "default")
    @GetMapping("/find/{id}")
    @ApiOperation(value = "Pega um arrendamento/aluguel pelo id")
    public ResponseEntity<Rent> findById(@PathVariable Long id) {
        return ResponseEntity.ok(rentService.findById(id));
    }

    @GetMapping("/all")
    @ApiOperation(value = "Lista todos os alugueis cadastrados")
    public ResponseEntity<List<Rent>> listAll() {
        return ResponseEntity.ok(rentService.listAll());
    }

   // @Retry(name = "default")
    @GetMapping("/rents/customer/{idCustomer}")
    @ApiOperation(value = "Busca aluguéis através do id de um cliente")
    public ResponseEntity<List<Rent>> findRentsByCustomerId(@PathVariable Long idCustomer) {
        return ResponseEntity.ok(rentService.findRentsByCustomer_Id(idCustomer));
    }
}
