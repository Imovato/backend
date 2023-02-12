package com.example.rent;

import com.example.rent.exceptions.ValidationException;

import javax.xml.bind.SchemaOutputResolver;
import java.util.ArrayList;
import java.util.List;

public class ValidationChai  {
    public static void main(String[] args) {
        List<String> lsitString = new ArrayList<>();
        lsitString.add("Mateus");
        if (lsitString.isEmpty()) {
            System.out.println("vazio");
        }
        System.out.println("Nao vazio");
    }

}
