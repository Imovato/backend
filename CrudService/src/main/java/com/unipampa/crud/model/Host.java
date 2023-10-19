package com.unipampa.crud.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class Host extends User {

}
