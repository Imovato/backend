package com.example.rent.resources;

import com.example.rent.dto.RentDto;
import com.example.rent.entities.Rent;
import com.example.rent.service.interfaces.IRentService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/rents")
@RequiredArgsConstructor
public class RentController {

    private final IRentService rentService;

    //@Retry(name = "retrySave")
    @PostMapping
    @Operation(summary = "Salva um arrendamento/aluguel")
    public ResponseEntity<Rent> save(@RequestBody @Valid RentDto rentDto) {
        return new ResponseEntity<>(rentService.createNewRent(rentDto), HttpStatus.CREATED);
    }

    //ESSE FAZ SENTIDO
//    @Retry(name = "default")
//    @GetMapping("/rents/customer/{idCustomer}")
//    @ApiOperation(value = "Busca aluguéis através do id de um cliente")
//    public ResponseEntity<List<Rent>> findRentsByCustomerId(@PathVariable Long idCustomer) {
//        return ResponseEntity.ok(rentService.findRentsByCustomer_Id(idCustomer));
//    }

    //A FUNÇÃO DE ATUALIZAR ALUGUEL SERÁ REPENSADA
    //@Retry(name = "default")
//    @PutMapping("/update")
//    @ApiOperation(value = "Atualiza um arrendamento/aluguel")
//    public ResponseEntity<Void> update(@RequestBody RentDtoUpdate rentDtoUpdate) {
//        rentService.update(rentDtoUpdate);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }

    //NÃO EXISTE DELETAR ALUGUEL, EXISTE APENAS FLUXO DE CANCELAMENTO
//    @DeleteMapping( "/delete/{id}")
//    public ResponseEntity<Void> delete(@PathVariable Long id) {
//        rentService.delete(id);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }

    //NAO FAZ SENTIDO BUSCAR UM ALUGUEL POR ID TALVEZ PARA O ADMINISTRADOR
   // @Retry(name = "default")
//    @GetMapping("/find/{id}")
//    @ApiOperation(value = "Pega um arrendamento/aluguel pelo id")
//    public ResponseEntity<Rent> findById(@PathVariable Long id) {
//        return ResponseEntity.ok(rentService.findById(id));
//    }

    //NAO FAZ SENTIDO LISTAR TODOS OS ALUGUEIS TALVEZ PARA O ADMINISTRADOR
//    @GetMapping("/all")
//    @ApiOperation(value = "Lista todos os alugueis cadastrados")
//    public ResponseEntity<List<Rent>> listAll() {
//        return ResponseEntity.ok(rentService.listAll());
//    }




}
