package com.example.rent.entities;

import com.example.rent.enums.UserType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements Serializable {

    @Serial
    private static final long serialVersionUID = -9206949953784522451L;

    @Id
    private String id;

    private String name;

    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_type", length = 30, nullable = false)
    private UserType userType;

}
