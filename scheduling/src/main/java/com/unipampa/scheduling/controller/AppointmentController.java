package com.unipampa.scheduling.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unipampa.scheduling.dto.AppointmentDTO;
import com.unipampa.scheduling.interfaces.service.IAppointmentService;
import com.unipampa.scheduling.model.Appointment;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/appointment")
@Api(value = "API Scheduling")
public class AppointmentController {

	private IAppointmentService appointmentService;

	public AppointmentController(IAppointmentService appointmentService) {
		this.appointmentService = appointmentService;
	}
	
	@PostMapping("/add")
	@ApiOperation(value = "add an appointment")
	public ResponseEntity<?> saveAppointment(@RequestBody AppointmentDTO dto) {
		AppointmentDTO dtoReturn = appointmentService.createAppointment(dto);
		return new ResponseEntity<>(dtoReturn, HttpStatus.OK);
	}
	
	@GetMapping("/all")
	@ApiOperation(value = "get all appointments")
	public ResponseEntity<?> getAllAppointments(){
		List<Appointment> appointments = appointmentService.findAllAppointments();
		return new ResponseEntity<>(appointments, HttpStatus.OK);
	}
	
	@GetMapping("/find/{id}")
	@ApiOperation(value = "get appointment by id")
	public ResponseEntity<?> getAppointmentById (@PathVariable("id") Long id) {
		Appointment appointment = appointmentService.findAppointmentById(id);
		return new ResponseEntity<>(appointment, HttpStatus.OK);
    }
	
	@GetMapping("/find/{id}/days")
	@ApiOperation(value = "get days until appointment by id")
	public ResponseEntity<?> getDaysUntilAppointment (@PathVariable("id") Long id) {
		Appointment appointment = appointmentService.findAppointmentById(id);
		return new ResponseEntity<>(appointment.daysUntilAppointment(), HttpStatus.OK);
    }

    @PutMapping("/update")
    @ApiOperation(value = "update an appointments")
    public ResponseEntity<?> updateAppointment(@RequestBody Appointment appointment) {
    	Appointment updateAppointment = appointmentService.updateAppointment(appointment);
		return new ResponseEntity<>(updateAppointment, HttpStatus.OK);
    }
	
    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "delete a appointment by id")
    public void deleteAppointment(@PathVariable("id") Long id) {
    	appointmentService.deleteAppointment(id);
    }
    
}
