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
public class RentDTO {

	private final Long id;
	private final @NonNull Date data;
	private final @NonNull Property propertie;
	private final @NonNull Double amountValue;
}