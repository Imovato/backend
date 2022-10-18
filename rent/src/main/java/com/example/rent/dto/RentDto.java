package com.example.rent.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class RentDto {

    private Long id;
    /*private Date data;
    private Property property;
    private User user;
    private Long idProperty;*/
    private Long idUser;

}
