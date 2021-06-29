package com.unipampa.scheduling.dto;

import java.time.LocalDateTime;

import com.unipampa.scheduling.model.Customer;
import com.unipampa.scheduling.model.Property;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@RequiredArgsConstructor
@Accessors(fluent = true)
@Getter
public class AppointmentDTO {

	private final Long id;
	private final @NonNull LocalDateTime date;
	private final @NonNull Property property;
	private final @NonNull Customer customer;
}