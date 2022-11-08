package com.example.rent;

import com.example.rent.model.Customer;
import com.example.rent.model.Property;
import com.example.rent.service.interfaces.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;

public class ValidationChai {
    public static void main(String[] args) {
        ICustomerService iCustomerService = null;
        Property property = null;
        Customer customer = Customer.builder()
                .cpf("000.000.000-00")
                .salary(1250.00)
                .build();
        iCustomerService.validateCustomer(customer, property);
    }
}
