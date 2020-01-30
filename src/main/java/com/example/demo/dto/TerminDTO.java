package com.example.demo.dto;

import java.util.Calendar;

import com.example.demo.model.Doktor;
import com.example.demo.model.Sala;
import com.example.demo.model.Termin;

public class TerminDTO {

	private Long id;
	
	private Calendar datum;
	
	private boolean slobodan;

	private Sala sala;

	private Doktor doktor;
	
	private String tip;
	
	private double trajanje;
	
	private double cena;
	
	private String popust;
	
	private String imeIPrezimeDoktora;
	
	public TerminDTO(Long id, Calendar datum, boolean slobodan, String tip, double trajanje, double cena, Sala sala, Doktor doktor) {
		super();
		this.id = id;
		this.datum = datum;
		this.slobodan = slobodan;
		this.sala = sala;
		this.doktor = doktor;
		this.tip = tip;
		this.cena = cena;
		this.popust = "Nema popusta";
	}
	
	public TerminDTO(Termin t) {
		this(t.getId(), t.getDatum(), t.isSlobodan(), t.getTip(), t.getTrajanje(), t.getCena(), t.getSala(), t.getDoktor());
		this.popust = "Nema popusta";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getPopust() {
		return popust;
	}

	public void setPopust(String popust) {
		this.popust = popust;
	}

	public String getImeIPrezimeDoktora() {
		return imeIPrezimeDoktora;
	}

	public void setImeIPrezimeDoktora(String imeIPrezimeDoktora) {
		this.imeIPrezimeDoktora = imeIPrezimeDoktora;
	}
	
	


	
	
	
}
