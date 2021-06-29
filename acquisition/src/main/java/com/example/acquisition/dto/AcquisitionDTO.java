package com.example.acquisition.dto;


import com.example.acquisition.model.Acquisition;
import org.modelmapper.ModelMapper;

import java.util.Date;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@RequiredArgsConstructor
@Accessors(fluent = true)
@Getter
public class AcquisitionDTO {

	private final Long id;
	private final @NonNull Date data;
	private final @NonNull Integer amount;
	private final @NonNull Double value;
	private final @NonNull Long idProperty;
	private final @NonNull Long idUser;
}