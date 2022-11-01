package com.example.payment.service;

import com.example.payment.enums.PaymentStatusEnum;
import com.example.payment.interfaces.service.IPaymentRentService;
import com.example.payment.model.PaymentRent;
import com.example.payment.repository.PaymentRentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PaymentRentServiceImp implements IPaymentRentService {

    private PaymentRentRepository paymentRentRepository;

    @Autowired
    public PaymentRentServiceImp(PaymentRentRepository paymentRentRepository){
        this.paymentRentRepository = paymentRentRepository;
    }

    @Override
    public void savePayment(PaymentRent payment) {
        paymentRentRepository.save(payment);
    }

    @Override
    public PaymentRent findPaymentById(Long id) {
        return paymentRentRepository.findPaymentRentById(id);
    }

    @Override
    public List<PaymentRent> findAllPayments() {
        return paymentRentRepository.findAll();
    }

    @Override
    public void deletePayment(Long id) {
        paymentRentRepository.deleteById(id);
    }

    @Override
    public PaymentRent updatePayment(PaymentRent payment) {
        return paymentRentRepository.save(payment);
    }

    @Override
    public boolean verificarAtraso(PaymentRent payment){
        LocalDate localDate = LocalDate.now();
        if(Integer.parseInt(payment.getDatePaymentMonth())-Integer.parseInt(String.valueOf(localDate.getMonthValue()))==0) {
            return false;
        }
        if ((Integer.parseInt(payment.getDatePaymentMonth()) - Integer.parseInt(String.valueOf(localDate.getMonthValue())) == 1
                && Integer.parseInt(payment.getDatePaymentMonth()) - Integer.parseInt(String.valueOf(localDate.getMonthValue())) == -1)
                || ((Integer.parseInt(payment.getDatePaymentMonth()) == 12 && Integer.parseInt(String.valueOf(localDate.getMonthValue())) == 1))) {
            if (Integer.parseInt(payment.getDatePaymentDay()) < Integer.parseInt(String.valueOf(localDate.getDayOfMonth()))) {
                return false;
            } else {
                payment.setStatus(PaymentStatusEnum.ATRASADO);
                return true;
            }
        } else {
            payment.setStatus(PaymentStatusEnum.ATRASADO);
            return true;
        }
    }

    @Override
    public boolean validarAtraso(PaymentRent payment){
        if(String.valueOf(payment.getStatus()).equals("PAGO")){
            return true;
        } else {
            return false;
        }
    }
}
