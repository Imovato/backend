package com.unipampa.crud.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Guest extends User  {

    private static final long serialVersionUID = 8684635612451680954L;

    private String phone;
    private String address;

}
