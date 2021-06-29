package com.unipampa.scheduling.interfaces.service;

import java.util.List;

import com.unipampa.scheduling.dto.AppointmentDTO;
import com.unipampa.scheduling.model.Appointment;

public interface IAppointmentService {
	
	void saveAppointment(Appointment appointment);
	Appointment findAppointmentById(Long id);
	List<Appointment> findAllAppointments();
	void deleteAppointment(Long id);
	Appointment updateAppointment(Appointment appointment);
	public AppointmentDTO createAppointment(AppointmentDTO appointmentDTO);
	
}
