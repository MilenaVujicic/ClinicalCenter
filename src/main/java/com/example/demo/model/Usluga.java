package com.example.demo.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Usluga {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "naziv")
	private String naziv;

	@Column(name = "opis")
	private String opis;
	
	@Column(name = "cena", nullable = false)
	private double cena = 0;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Klinika klinika;
	
	public Usluga() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Usluga(String naziv, String opis, double cena) {
		super();
		this.naziv = naziv;
		this.opis = opis;
		this.cena = cena;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setIme(String naziv) {
		this.naziv = naziv;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Klinika getKlinika() {
		return klinika;
	}

	public void setKlinika(Klinika klinika) {
		this.klinika = klinika;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	@Override
	public String toString() {
		return "Usluga [id=" + id + ", naziv=" + naziv + ", opis=" + opis + ", cena=" + cena + ", klinika=" + klinika
				+ "]";
	}
	
	
	
	
}
