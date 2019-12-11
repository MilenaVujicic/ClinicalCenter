package com.example.demo.model;

import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Termin {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "datum")
	private Calendar datum;
	
	@Column(name = "slobodan")
	private boolean slobodan;
	
	@Column(name = "tip")
	private String tip;
	
	@Column(name = "trajanje")
	private double trajanje;
	
	@Column(name = "cena")
	private double cena;
	
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Sala sala;
 
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Doktor doktor;


	
	public Termin() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Termin(Calendar datum, boolean slobodan) {
		super();
		this.datum = datum;
		this.slobodan = slobodan;
	}
	
	

	public Termin(Long id, Calendar datum, boolean slobodan, Sala sala, Doktor doktor) {
		super();
		this.id = id;
		this.datum = datum;
		this.slobodan = slobodan;
		this.sala = sala;
		this.doktor = doktor;
	}
	
	

	public Termin( Calendar datum, boolean slobodan, String tip, double trajanje, double cena, Sala sala,
			Doktor doktor) {
		super();
		this.datum = datum;
		this.slobodan = slobodan;
		this.tip = tip;
		this.trajanje = trajanje;
		this.cena = cena;
		this.sala = sala;
		this.doktor = doktor;
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


	public Doktor getDoktor() {
		return doktor;
	}

	public void setDoktor(Doktor doktor) {
		this.doktor = doktor;
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public double getTrajanje() {
		return trajanje;
	}

	public void setTrajanje(double trajanje) {
		this.trajanje = trajanje;
	}

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}

	
	
	
	
}
