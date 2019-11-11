package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Usluga {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	

	@Column(name = "ime")
	private String ime;

	@Column(name = "opis")
	private String opis;
	

	@Column(name = "cena", nullable = false)
	private double cena = 0;
	
	public Usluga() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Usluga(String ime, String opis, double cena) {
		super();
		this.ime = ime;
		this.opis = opis;
		this.cena = cena;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}

	@Override
	public String toString() {
		return "Usluga [ime=" + ime + ", opis=" + opis + ", cena=" + cena + "]";
	}
	
	
	
	
}
