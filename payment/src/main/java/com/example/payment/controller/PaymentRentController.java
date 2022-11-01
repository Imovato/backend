package com.example.payment.controller;

import com.example.payment.dto.PaymentRentDTO;
import com.example.payment.interfaces.service.IPaymentRentService;
import com.example.payment.model.PaymentRent;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/paymentR")
@Api(value = "MICROSERVICE Payment")
public class PaymentRentController {

    private IPaymentRentService paymentRentService;

    @PostMapping("/save")
    @ApiOperation(value = "Salva um pagamento de uma Propriedade")
    public void savePaymentRent(@RequestBody PaymentRentDTO dto){
        PaymentRent paymentRent = new PaymentRent();
        paymentRent.setRentPayment(dto.getRentPayment());
        paymentRent.setDatePayment(dto.getDatePayment());
        paymentRent.setValor(dto.getValor());
        paymentRent.setClienteComprador(dto.getClienteComprador());
        paymentRent.setStatus(dto.getStatus());
        paymentRentService.savePayment(paymentRent);
    }

    @PutMapping("/update")
    @ApiOperation(value = "atualiza um pagamento de uma propriedade")
    public ResponseEntity<?> updatePaymentRent(@RequestBody PaymentRent paymentRent) {
        PaymentRent updatePayment = paymentRentService.updatePayment(paymentRent);
        return new ResponseEntity<>(updatePayment, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    @ApiOperation(value = "pega um pagamento de propriedade pelo id")
    public ResponseEntity<?> getPaymentRentById(@PathVariable("id") Long id) {
        PaymentRent paymentRentFinded = paymentRentService.findPaymentById(id);
        return new ResponseEntity<>(paymentRentFinded, HttpStatus.OK);
    }

}
