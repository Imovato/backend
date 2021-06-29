package com.unipampa.crud.model;

import com.unipampa.crud.dto.ContactDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "tbl_property_contact")
public class Contact {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "message")
	private String message;
	@Column(name = "name")
	private String name;
	@Column(name = "email")
	private String email;
	@Column(name = "number")
	private String number;

	public static Contact createContact(ContactDTO contactDTO) {
		return new ModelMapper().map(contactDTO, Contact.class);
	}
}