package com.unipampa.crud.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.unipampa.crud.enums.UserType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;


@EqualsAndHashCode(callSuper = true)
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
public class User extends RepresentationModel<User> implements Serializable {

    private static final long serialVersionUID = 4477471521765649872L;
    //    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id", nullable = false)
    @Id
    private String id;

//    @Column(name = "cpf", nullable = false, length = 20)
    private String userName;

//    @Column(name = "cpf", nullable = false, length = 11)
    private String cpf;

//    @Column(name = "email", nullable = false, unique = true, length = 50)
    private String email;

//    @Column(name = "name", nullable = false, length = 150)
    private String name;

//    @OneToMany
////    private List<Accommodation> properties;

    private UserType type;

    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime creationDate;

    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime lastUpdateDate;

}
