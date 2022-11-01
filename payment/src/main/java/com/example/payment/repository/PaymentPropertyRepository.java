package com.example.payment.repository;

import com.example.payment.model.PaymentProperty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentPropertyRepository extends JpaRepository<PaymentProperty,Long> {
    public PaymentProperty findPaymentPropertyById(Long id);
}
