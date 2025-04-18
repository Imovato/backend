package com.example.rent.entities;

import com.example.rent.enums.StatusAccommodation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Accommodation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double price;

    @Enumerated(EnumType.STRING)
    private StatusAccommodation status;

    //PRECISA TER UMA FLAG DIZENDO SE A HOSPEDAGEM É COMPARTILHADA OU NÃO
}
