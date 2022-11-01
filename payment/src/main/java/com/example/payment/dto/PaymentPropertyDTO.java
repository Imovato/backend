package com.example.payment.dto;

import com.example.payment.enums.PaymentStatusEnum;
import com.example.payment.model.Property;
import com.example.payment.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PaymentPropertyDTO {
    private Long paymentId;
    private ArrayList<User> clienteComprador;
    private int valor;
    private Date datePayment;
    private Property propertyPayment;
    private PaymentStatusEnum status;
}
