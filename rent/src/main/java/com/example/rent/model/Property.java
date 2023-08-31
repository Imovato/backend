package com.example.rent.model;

import com.example.rent.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tbl_property")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Property implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_property")
    private Long id;

    private String salesman;
    private Double price;

    @Enumerated(EnumType.STRING)
    private Status status;

}
