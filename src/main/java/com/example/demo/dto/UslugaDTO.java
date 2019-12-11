package com.example.demo.dto;

import java.util.HashSet;
import java.util.Set;

import com.example.demo.model.Klinika;
import com.example.demo.model.Termin;
import com.example.demo.model.Usluga;

public class UslugaDTO {

	private Long id;
	
	private String naziv;
	
	private double cena;
	
	private Klinika klinika;
	
	

	public UslugaDTO(Long id, String naziv, double cena, Klinika klinika) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.cena = cena;
		this.klinika = klinika;
	
	}
	
	public UslugaDTO(Usluga u) {
		this(u.getId(), u.getNaziv(), u.getCena(), u.getKlinika());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}

	public Klinika getKlinika() {
		return klinika;
	}

	public void setKlinika(Klinika klinika) {
		this.klinika = klinika;
	}



}
