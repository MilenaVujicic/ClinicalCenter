package com.example.demo.model;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Termin {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "datum")
	private Calendar datum;
	
	@Column(name = "slobodan")
	private boolean slobodan;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Sala sala;
	
	public Termin() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Termin(Calendar datum, boolean slobodan) {
		super();
		this.datum = datum;
		this.slobodan = slobodan;
	}
	
	

	public Termin(Long id, Calendar datum, boolean slobodan, Sala sala) {
		super();
		this.id = id;
		this.datum = datum;
		this.slobodan = slobodan;
		this.sala = sala;
	}

	public Calendar getDatum() {
		return datum;
	}

	public void setDatum(Calendar datum) {
		this.datum = datum;
	}

	public boolean isSlobodan() {
		return slobodan;
	}

	public void setSlobodan(boolean slobodan) {
		this.slobodan = slobodan;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	@Override
	public String toString() {
		return "Termin [id=" + id + ", datum=" + datum + ", slobodan=" + slobodan + ", klinika=" + sala + "]";
	}
	
	
	
}
