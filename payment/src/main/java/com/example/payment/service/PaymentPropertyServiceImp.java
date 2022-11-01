package com.example.payment.service;

import com.example.payment.enums.PaymentStatusEnum;
import com.example.payment.interfaces.service.IPaymentPropertyService;
import com.example.payment.model.PaymentProperty;
import com.example.payment.repository.PaymentPropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PaymentPropertyServiceImp implements IPaymentPropertyService {

    private PaymentPropertyRepository paymentPropertyRepository;

    @Autowired
    public PaymentPropertyServiceImp(PaymentPropertyRepository paymentPropertyRepository){
        this.paymentPropertyRepository = paymentPropertyRepository;
    }

    @Override
    public void savePayment(PaymentProperty payment) {
        this.paymentPropertyRepository.save(payment);
    }

    @Override
    public PaymentProperty findPaymentById(Long id) {
        return this.paymentPropertyRepository.findPaymentPropertyById(id);
    }

    @Override
    public List<PaymentProperty> findAllPayments() {
        return paymentPropertyRepository.findAll();
    }

    @Override
    public void deletePayment(Long id) {
        paymentPropertyRepository.deleteById(id);
    }

    @Override
    public PaymentProperty updatePayment(PaymentProperty payment) {
        return paymentPropertyRepository.save(payment);
    }

    @Override
    public boolean verificarAtraso(PaymentProperty payment){
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
    public boolean validarAtraso(PaymentProperty payment){
        if(String.valueOf(payment.getStatus()).equals("PAGO")){
            return true;
        } else {
            return false;
        }
    }


}
