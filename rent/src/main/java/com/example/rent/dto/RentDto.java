package com.example.rent.dto;

import java.util.Date;

import com.example.rent.model.Property;
import com.example.rent.model.User;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@RequiredArgsConstructor
@Accessors(fluent = true)
@Getter
public class RentDto {

	private final Long id;
	// private Date data;
	// private Property property;
	// private User user;
	// private Long idProperty;
	private final @NonNull Long idUser;
}