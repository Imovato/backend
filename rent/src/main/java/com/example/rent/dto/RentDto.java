package com.example.rent.dto;


import java.util.Date;
import com.example.rent.model.Property;
import com.example.rent.model.User;

public class RentDto {

    private Long id;
    /*private Date data;
    private Property property;
    private User user;
    private Long idProperty;*/
    private Long idUser;

    public RentDto() {
    }

    public RentDto(Long id, Long idUser) {
        this.id = id;
        this.idUser = idUser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }
}
