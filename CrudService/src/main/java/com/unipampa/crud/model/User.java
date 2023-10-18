package com.unipampa.crud.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.unipampa.crud.enums.UserType;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Host.class, name = "host"),
        @JsonSubTypes.Type(value = Guest.class, name = "guest")
})
@Document
@Data
@SuperBuilder
public abstract class User {

//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id", nullable = false)
    @Id
    private String id;

//    @Column(name = "cpf", nullable = false, length = 11)
    private String cpf;

//    @Column(name = "email", nullable = false, unique = true, length = 50)
    private String email;

//    @Column(name = "name", nullable = false, length = 150)
    private String name;

//    @OneToMany
//    private List<Accommodation> properties;

    private UserType type;

}
