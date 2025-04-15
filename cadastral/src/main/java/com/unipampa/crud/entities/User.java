package com.unipampa.crud.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.unipampa.crud.enums.UserType;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.time.LocalDateTime;

@Builder
@Document
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 4477471521765649872L;

    @Id
    private String id;
    private String userName;
    private String password;
    private String cpf;
    private String email;
    private String name;
    private UserType type;

    @Field
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime creationDate;

    @Field
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime lastUpdateDate;

}
