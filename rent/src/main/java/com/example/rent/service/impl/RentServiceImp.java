package com.example.rent.service.impl;

import com.example.rent.service.interfaces.IRentService;
import com.example.rent.model.Rent;
import com.example.rent.model.Customer;
import com.example.rent.repository.RentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.InputMismatchException;
import java.util.List;

@Service
public class RentServiceImp implements IRentService {

    @Autowired
    private RentRepository rentRepository;

    @Override
    public void saveRent(Rent rent) {
        rentRepository.save(rent);
    }

    @Override
    public Rent updateRent(Rent rent) {
        return rentRepository.save(rent);
    }

    @Override
    public Rent getRentById(Long id) {
        return rentRepository.findRentById(id);
    }

    @Override
    public List<Rent> findAllRentsByUser(Customer customer) {
        return rentRepository.findAllRentsByCustomer(customer);
    }

    public boolean isCPF(Customer customer) {
    //String CPF = customer.getCpf();
    String CPF = customer.getCpf().replaceAll("\\.", "").replaceAll("\\/","").replaceAll("\\-","");

        if (CPF.equals("00000000000") ||
                CPF.equals("11111111111") ||
                CPF.equals("22222222222") || CPF.equals("33333333333") ||
                CPF.equals("44444444444") || CPF.equals("55555555555") ||
                CPF.equals("66666666666") || CPF.equals("77777777777") ||
                CPF.equals("88888888888") || CPF.equals("99999999999") ||
                (CPF.length() != 11))
            return(false);

        char dig10, dig11;
        int sm, i, r, num, peso;

        try {
            sm = 0;
            peso = 10;
            for (i=0; i<9; i++) {
                num = (int)(CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig10 = '0';
            else dig10 = (char)(r + 48);

            sm = 0;
            peso = 11;
            for(i=0; i<10; i++) {
                num = (int)(CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig11 = '0';
            else dig11 = (char)(r + 48);

            if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10)))
                return(true);
            else return(false);
        } catch (InputMismatchException erro) {
            return(false);
        }
    }

    public boolean isSalary(Customer customer) {
        if (customer.getSalary() > 1211D) return true;
        return false;
    }

    public boolean hasGuarantor(Customer customer) {
        return customer.isGuarantor();
    }

    private boolean expirationDay(Rent rent) {
        Integer expirationDay = rent.getExpirationDay();
        LocalDate now = LocalDate.now();
        if (now.getDayOfMonth() > expirationDay ) return true;
        return false;
    }

    public String contractTime(Rent rent) {
        StringBuilder sb = new StringBuilder();
        long time = ChronoUnit.MONTHS.between((Temporal) rent.getStartDateRent(), (Temporal) rent.getEndDateRent());
        sb.append(time).append(" MESES");
        return sb.toString();
    }

    /*public void calculateLatePayment(Rent rent) {
        if (expirationDay(rent)) {
            Double valueWithRate = rent.getValue();
        }
    } */

}

