package com.example.acquisition.dto;

import com.example.acquisition.model.Acquisition;
import lombok.*;
import org.modelmapper.ModelMapper;

import java.util.Date;

import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class AcquisitionDTO {

	private Long id;
	private @NonNull Date data;
	private @NonNull Integer amount;
	private @NonNull Double value;
	private @NonNull Long idProperty;
	private @NonNull Long idUser;

	public static AcquisitionDTO createAcquisition(Acquisition acquisition) {
		return new ModelMapper().map(acquisition, AcquisitionDTO.class);
	}
}