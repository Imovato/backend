package com.unipampa.scheduling.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "tbl_appointments")
public class Appointment {

	private LocalDate dataLocal;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@DateTimeFormat(pattern = "MM/dd/yyy")
	@Column(name = "date", nullable = false)
	private LocalDateTime date;

	@OneToOne(cascade = CascadeType.ALL)
	private Property property;

	@OneToOne(cascade = CascadeType.ALL)
	private Customer customer;

	public int daysUntilAppointment() {
		return date.compareTo(LocalDateTime.now());
	}
}