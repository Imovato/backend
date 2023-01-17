package com.example.rent.service.impl;

import com.example.rent.dto.RentDto;
import com.example.rent.dto.RentDtoUpdate;
import com.example.rent.enums.Status;
import com.example.rent.exceptions.BadRequestException;
import com.example.rent.model.Property;
import com.example.rent.model.Rent;
import com.example.rent.repository.RentRepository;
import com.example.rent.service.interfaces.ICustomerService;
import com.example.rent.service.interfaces.IPropertyService;
import com.example.rent.service.interfaces.IRentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RentServiceImp implements IRentService {

    private final RentRepository rentRepository;
    private final IPropertyService propertyService;
    private final ICustomerService customerService;

    @Override
    @Transactional
    public Rent save(RentDto rentDto) {
        Rent rent = Rent.builder()
                .property(propertyService.findPropertyById(rentDto.getId_property()))
                .customer(customerService.findCustomerById(rentDto.getId_customer())).build();
        BeanUtils.copyProperties(rentDto, rent);
        Property property = propertyService.findPropertyById(rentDto.getId_property());
        property.setStatus(Status.RENTED);
        propertyService.updateProperty(property);
        return rentRepository.save(rent);
    }

    @Override
    public void update(RentDtoUpdate rentDtoUpdate) {
        Rent savedRent = findById(rentDtoUpdate.getId());
        BeanUtils.copyProperties(rentDtoUpdate, savedRent);
        rentRepository.save(savedRent);
    }

    @Override
    public void delete(Long id) {
        rentRepository.delete(findById(id));
    }

    @Override
    public Rent findById(Long id) {
        return rentRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Rent not found"));
    }

    @Override
    public List<Rent> listAll(){
        return rentRepository.findAll();
    }

    @Override
    public List<Rent> findRentsByCustomer_Id(Long id) {
        return rentRepository.findByCustomerId(id);
    }
}
