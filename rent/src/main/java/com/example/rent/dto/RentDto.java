package com.example.rent.dto;


import java.util.Date;
import java.util.UUID;
import com.example.rent.model.Property;
import com.example.rent.model.User;

public class RentDto {

    private UUID id;
    /*private Date data;
    private Property property;
    private User user;
    private Long idProperty;*/
    private UUID idUser;

    public RentDto() {
    }

    public RentDto(UUID id, UUID idUser) {
        this.id = id;
        this.idUser = idUser;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getIdUser() {
        return idUser;
    }

    public void setIdUser(UUID idUser) {
        this.idUser = idUser;
    }
}
