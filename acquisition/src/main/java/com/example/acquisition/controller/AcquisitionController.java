package com.example.acquisition.controller;

import com.example.acquisition.enums.Status;
import com.example.acquisition.service.ValidaCpfServiceImp;
import com.example.acquisition.service.ValidaRendaServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.ApiOperation;
import java.time.LocalDate;
import java.util.List;
import com.example.acquisition.model.Acquisition;
import com.example.acquisition.model.Property;
import com.example.acquisition.model.User;
import com.example.acquisition.interfaces.services.IAcquisitionService;
import com.example.acquisition.interfaces.services.IPropertyService;
import com.example.acquisition.interfaces.services.IUserService;

@RestController
@RequestMapping("/acquisition")
public class AcquisitionController {

	ValidaRendaServiceImp vrenda;
	private IAcquisitionService acquisitionService;
	private IPropertyService propertyService;
	private IUserService userService;

	public AcquisitionController(IAcquisitionService service, IPropertyService propertyService, IUserService userService) {
		this.acquisitionService = service;
		this.propertyService = propertyService;
		this.userService = userService;
	}

	@PostMapping("/save")
	@ApiOperation(value = "Salva uma compra")
	public ResponseEntity<Acquisition> saveAcquisition(Long idProperty, Long idUser) {
		Property property = propertyService.findPropertyById(idProperty);
		User user = userService.findUserById(idUser);
        Acquisition acquisition = null;

		userService.validateUser(user, property);

			acquisition = Acquisition.builder()
				.data(LocalDate.now())
				.property(property)
				.user(user)
				.value(property.getPrice())
				.build();

				acquisitionService.save(acquisition);
				property.setStatus(Status.SOLD);
				propertyService.updateProperty(property);
		return ResponseEntity.status(HttpStatus.CREATED).body(acquisition);
	}

	@GetMapping("/user/find/{id}")
	@ApiOperation(value = "Encontra acquisitions através do id de um usuário")
	public ResponseEntity<?> getAcquisitionsByUserId(@PathVariable("id") Long id) {
		User user = userService.findUserById(id);
		List<Acquisition> acquisitions = acquisitionService.findAllAcquisitionsByUser(user);
		return new ResponseEntity<>(acquisitions, HttpStatus.OK);
	}

	@GetMapping("/property/find/{id}")
	@ApiOperation(value = "Encontra acquisitions através do id de um imóvel")
	public ResponseEntity<?> getAcquisitionsByPropertyId(@PathVariable("id") Long id) {
		Property property = propertyService.findPropertyById(id);
		Acquisition acquisition = acquisitionService.findAcquisitionByProperty(property);
		return new ResponseEntity<>(acquisition, HttpStatus.OK);
	}

}
