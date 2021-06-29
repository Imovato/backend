package com.example.payment.dto;

import com.example.payment.model.Acquisition;
import com.example.payment.model.Property;
import lombok.*;
import org.modelmapper.ModelMapper;

import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class AcquisitionDTO {

	private long id;
	private @NonNull Date date;
	private @NonNull Property property;
	private @NonNull Double value;
	public static AcquisitionDTO createAcquisition(Acquisition acquisition){
		return new ModelMapper().map(acquisition, AcquisitionDTO.class);
	}

}