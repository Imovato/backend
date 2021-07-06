package com.unipampa.crud.dto;

import com.unipampa.crud.model.Contact;
import lombok.*;
import org.modelmapper.ModelMapper;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ContactDTO {

	private Long id;
	private @NonNull String message;
	private @NonNull String name;
	private @NonNull String email;
	private @NonNull String number;

	public static ContactDTO createContact(Contact contact) {
		return new ModelMapper().map(contact, ContactDTO.class);
	}
}