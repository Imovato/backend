package com.example.payment.dto;

import com.example.payment.model.Acquisition;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@RequiredArgsConstructor
@Accessors(fluent = true)
@Getter
public class PropertyAcquisitionDTO {

	private Long id;
	private final @NonNull Long idProperty;
	private final @NonNull Integer amount;
	private final @NonNull Acquisition acquisition;
}