package com.unipampa.crud.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Apartment extends Hosting implements Serializable {

    private static final long serialVersionUID = -4618566071703581190L;

    @Column(name = "block")
    private String block;

}
