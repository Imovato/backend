package com.example.rent.service.impl;

import com.example.rent.exceptions.BadRequestException;
import com.example.rent.service.interfaces.IRentService;
import com.example.rent.model.Rent;
import com.example.rent.model.Customer;
import com.example.rent.repository.RentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RentServiceImp implements IRentService {

    private final RentRepository rentRepository;

    @Override
    public void save(Rent rent) {
        rentRepository.save(rent);
    }

    @Override
    public Rent replace(Rent rent) {
        return rentRepository.save(rent);
    }

    @Override
    public Rent findByIdOrThrowBadRequestException(Long id) {
        return rentRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Rent not found"));
    }

    public void delete(Long id) {
        rentRepository.delete(findByIdOrThrowBadRequestException(id));
    }

    public List<Rent> listAll(){
        return rentRepository.findAll();
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
}

