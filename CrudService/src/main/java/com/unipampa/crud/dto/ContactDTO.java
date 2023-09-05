package com.unipampa.crud.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ContactDTO {

    private Long id;
    private String message;
    private String name;
    private String email;
    private String number;

}