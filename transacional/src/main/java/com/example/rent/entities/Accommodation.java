package com.example.rent.entities;

import com.example.rent.enums.StatusAccommodation;
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
public class Accommodation implements Serializable {

    @Serial
    private static final long serialVersionUID = 6742147475962430968L;

    @Id
    private String id;

    private Double price;

    @Enumerated(EnumType.STRING)
    private StatusAccommodation status;

    private int guestCapacity;

}
