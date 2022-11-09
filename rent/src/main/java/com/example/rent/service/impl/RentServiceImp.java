package com.example.rent.service.impl;

import com.example.rent.service.interfaces.IRentService;
import com.example.rent.model.Rent;
import com.example.rent.model.Customer;
import com.example.rent.repository.RentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
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

    public String contractTime(Rent rent) {
        StringBuilder sb = new StringBuilder();
        long time = ChronoUnit.MONTHS.between((Temporal) rent.getStartDateRent(), (Temporal) rent.getEndDateRent());
        sb.append(time).append(" MESES");
        return sb.toString();
    }

    /*private boolean expirationDay(Rent rent) {
        Integer expirationDay = rent.getExpirationDay();
        LocalDate now = LocalDate.now();
        if (now.getDayOfMonth() > expirationDay ) return true;
        return false;
    }


    /*public void calculateLatePayment(Rent rent) {
        if (expirationDay(rent)) {
            Double valueWithRate = rent.getValue();
        }
    } */

}

