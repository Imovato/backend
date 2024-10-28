package com.example.rent.entities;

import com.example.rent.entities.composite.Address;
import com.example.rent.entities.composite.PersonalInformation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tbl_customer")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_customer")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_personal")
    private PersonalInformation personalInformation;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_address")
    private Address address;

    private Double salary;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_guarantor")
    private Renter guarantor;

}
