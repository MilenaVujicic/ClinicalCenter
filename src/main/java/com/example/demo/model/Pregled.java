/*
 * author: Andrea Mendrei
 */
package com.example.demo.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

enum StatusPregleda {
	ZAKAZAN, ZAVRSEN
}
@Entity
public class Pregled {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "Name", nullable = false)
	private String naziv;
	
	@Column(name = "Anamneza")
	private String anamneza;
	
	@Column(name = "Date", nullable = false)
	private LocalDateTime datumIVremePregleda;
	
	@Column(name = "Type")
	private String tipPregleda;
	
	@ManyToOne
	@JoinColumn(name="appointment_id", nullable = false)
	
	@Column(name = "cena")
	private double cena;
	
	@Column(name="appointment_record_id", nullable = false)
	private Dijagnoza dijagnoza;
	
	
	@Column(name = "status")
	private StatusPregleda status;
	
	
	
}