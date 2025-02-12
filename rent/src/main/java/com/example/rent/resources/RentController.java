package com.example.rent.resources;

import com.example.rent.dto.RentDto;
import com.example.rent.entities.Rent;
import com.example.rent.response.RentResponse;
import com.example.rent.service.interfaces.IRentService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/rents")
@RequiredArgsConstructor
public class RentController {

    private final IRentService rentService;

    @PostMapping
    @Operation(summary = "Registra um aluguel")
    public ResponseEntity<Rent> save(@RequestBody @Valid RentDto rentDto) {
        return new ResponseEntity<>(rentService.createNewRent(rentDto), HttpStatus.CREATED);
    }

    @GetMapping("user/{id}")
    public ResponseEntity<List<RentResponse>> findRentsByUserId(@PathVariable Long id) {
        return ResponseEntity.ok(rentService.findByUserId(id));
    }

}
