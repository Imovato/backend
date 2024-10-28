package com.example.rent.entities;

import com.example.rent.entities.composite.Address;
import com.example.rent.entities.composite.ContactInformation;
import com.example.rent.entities.composite.PersonalInformation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tbl_guarantor")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Renter implements Serializable {

    private static final long serialVersionUID = -6535707935629446793L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_guarantor")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_personal")
    private PersonalInformation personalInformation;

    private Long rg;
    private String nationality;
    private String profession;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_contact")
    private ContactInformation contactInformation;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_address")
    private Address address;
}
