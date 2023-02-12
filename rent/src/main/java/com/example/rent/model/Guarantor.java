package com.example.rent.model;

import com.example.rent.model.composite.Address;
import com.example.rent.model.composite.ContactInformation;
import com.example.rent.model.composite.PersonalInformation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tbl_guarantor")
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class Guarantor implements Serializable {

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
