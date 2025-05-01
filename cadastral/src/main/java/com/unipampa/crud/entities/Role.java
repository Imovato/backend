package com.unipampa.crud.entities;

import com.unipampa.crud.enums.UserType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;

@Builder
@Document
@Data
public class Role implements Serializable, GrantedAuthority {

    private static final long serialVersionUID = 747266736447607848L;

    @Id
    private String id;

    private UserType roleName;

    @Override
    public String getAuthority() {
        return "";
    }
}
