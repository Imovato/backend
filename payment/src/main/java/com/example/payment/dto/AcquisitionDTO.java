package com.example.payment.dto;

import java.util.Date;

import com.example.payment.model.Property;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@RequiredArgsConstructor
@Accessors(fluent = true)
@Getter
public class AcquisitionDTO {

	private long id;
	private final @NonNull Date date;
	private final @NonNull Property property;
	private final @NonNull Double value;
}