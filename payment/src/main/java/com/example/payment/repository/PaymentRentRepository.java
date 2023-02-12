package com.example.payment.repository;

import com.example.payment.model.PaymentRent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRentRepository extends JpaRepository<PaymentRent, Long> {
    PaymentRent findPaymentRentById(Long id);
}
