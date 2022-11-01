package com.example.payment.interfaces.service;

import com.example.payment.model.PaymentProperty;

import java.util.List;

public interface IPaymentPropertyService {

    void savePayment(PaymentProperty payment);
    PaymentProperty findPaymentById(Long id);
    List<PaymentProperty> findAllPayments();
    void deletePayment(Long id);
    PaymentProperty updatePayment(PaymentProperty payment);
    boolean verificarAtraso(PaymentProperty payment);
    boolean validarAtraso(PaymentProperty payment);
}
