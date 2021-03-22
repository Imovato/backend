package com.unipampa.scheduling.implement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unipampa.scheduling.interfaces.service.IAppointmentService;
import com.unipampa.scheduling.model.Appointment;
import com.unipampa.scheduling.repository.AppointmentRepository;

@Service
public class AppointmentServiceImp implements IAppointmentService {

	private AppointmentRepository appointmentRepository;
	
	
	@Autowired
	public AppointmentServiceImp(AppointmentRepository appointmentRepository) {
		this.appointmentRepository = appointmentRepository;
	}

	@Override
	@Transactional
	public void saveAppointment(Appointment appointment) {
		appointmentRepository.save(appointment);
	}

	@Override
	public Appointment findAppointmentById(Long id) {
		return appointmentRepository.findAppointmentById(id);
	}

	@Override
	public List<Appointment> findAllAppointments() {
		return appointmentRepository.findAll();
	}

	@Override
	public void deleteAppointment(Long id) {
		appointmentRepository.deleteById(id);
	}

	@Override
	public Appointment updateAppointment(Appointment appointment) {
		return appointmentRepository.save(appointment);
	}

}
