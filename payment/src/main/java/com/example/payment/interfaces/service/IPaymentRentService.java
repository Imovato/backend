package com.example.payment.interfaces.service;

import com.example.payment.model.PaymentProperty;
import com.example.payment.model.PaymentRent;

import java.util.List;

public interface IPaymentRentService {

    void savePayment(PaymentRent payment);
    PaymentRent findPaymentById(Long id);
    List<PaymentRent> findAllPayments();
    void deletePayment(Long id);
    PaymentRent updatePayment(PaymentRent payment);
    boolean verificarAtraso(PaymentRent payment);
    boolean validarAtraso(PaymentRent payment);
}
