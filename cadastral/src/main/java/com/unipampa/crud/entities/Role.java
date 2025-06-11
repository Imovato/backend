package com.unipampa.crud.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.unipampa.crud.enums.UserType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role implements GrantedAuthority, Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private UserType roleName;

    @Override
    public String getAuthority() {
        return this.roleName.toString();
    }
}
