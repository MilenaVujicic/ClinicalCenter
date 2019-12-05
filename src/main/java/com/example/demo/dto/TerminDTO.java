package com.example.demo.dto;

import java.util.Date;

import com.example.demo.model.Sala;
import com.example.demo.model.Termin;

public class TerminDTO {

	private Long id;
	
	private Date datum;
	
	private boolean slobodan;

	private Sala sala;

	
	public TerminDTO(Long id, Date datum, boolean slobodan, Sala sala) {
		super();
		this.id = id;
		this.datum = datum;
		this.slobodan = slobodan;
		this.sala = sala;
	}
	
	public TerminDTO(Termin t) {
		this(t.getId(), t.getDatum(), t.isSlobodan(), t.getSala());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
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
	
	
	
}
