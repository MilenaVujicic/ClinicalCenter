/*
 * author: Andrea Mendrei
 */
package com.example.demo.model;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

enum StatusOperacije {
	ZAKAZAN, ZAVRSEN
}

@Entity
public class Operacija {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "sala", nullable = false)
	private String sala;
	
	@Column(name = "status")
	private StatusOperacije status;
	
	@Column(name = "datumIVremeOperacije")
	private LocalDateTime datumIVremeOperacije;
	
	@Column(name = "trajanje")
	private int trajanje;
	
	// @ManyToMany(mappedBy = "operacije")
	// private Set<Doktor> doktori;
	
	
	
}