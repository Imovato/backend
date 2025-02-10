package com.example.rent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication()
public class RentApplication {
    public static void main(String[] args) {
        SpringApplication.run(RentApplication.class, args);
    }
}
