package com.example.payment.dto;

import com.example.payment.enums.PaymentStatusEnum;
import com.example.payment.model.Rent;
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
public class PaymentRentDTO {
    private Long paymentId;
    private ArrayList<User> clienteComprador;
    private int valor;
    private Date datePayment;
    private Rent rentPayment;
    private PaymentStatusEnum status;
}
