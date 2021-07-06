package com.unipampa.scheduling.dto;

import com.unipampa.scheduling.model.Appointment;
import com.unipampa.scheduling.model.Customer;
import com.unipampa.scheduling.model.Property;
import lombok.*;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class AppointmentDTO {

	private Long id;
	private @NonNull LocalDateTime date;
	private @NonNull Property property;
	private @NonNull Customer customer;

	public static AppointmentDTO createAppointment(Appointment appointment) {
		return new ModelMapper().map(appointment, AppointmentDTO.class);
	}
}