package com.example.payment.controller;

import com.example.payment.dto.PaymentPropertyDTO;
import com.example.payment.interfaces.service.IPaymentPropertyService;
import com.example.payment.model.PaymentProperty;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/paymentP")
@Api(value = "MICROSERVICE Payment")
public class PaymentPropertyController {

    private IPaymentPropertyService paymentPropertyService;

    @PostMapping("/save")
    @ApiOperation(value = "Salva um pagamento de uma Propriedade")
    public void savePaymentProperty(@RequestBody PaymentPropertyDTO dto){
        PaymentProperty paymentProperty = new PaymentProperty();
        paymentProperty.setPropertyPayment(dto.getPropertyPayment());
        paymentProperty.setDatePayment(dto.getDatePayment());
        paymentProperty.setValor(dto.getValor());
        paymentProperty.setClienteComprador(dto.getClienteComprador());
        paymentProperty.setStatus(dto.getStatus());
        paymentPropertyService.savePayment(paymentProperty);
    }

    @PutMapping("/update")
    @ApiOperation(value = "atualiza um pagamento de uma propriedade")
    public ResponseEntity<?> updatePaymentProperty(@RequestBody PaymentProperty paymentProperty) {
        PaymentProperty updatePayment = paymentPropertyService.updatePayment(paymentProperty);
        return new ResponseEntity<>(updatePayment, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    @ApiOperation(value = "pega um pagamento de propriedade pelo id")
    public ResponseEntity<?> getPaymentPropertyById(@PathVariable("id") Long id) {
        PaymentProperty paymentPropertyFinded = paymentPropertyService.findPaymentById(id);
        return new ResponseEntity<>(paymentPropertyFinded, HttpStatus.OK);
    }

}
