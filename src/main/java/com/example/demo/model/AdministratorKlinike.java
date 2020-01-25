package com.example.demo.model;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class AdministratorKlinike {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="idKorisnik", nullable = false)
	private Long idKorisnik;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Klinika klinika;
	
	@JsonIgnore
	@OneToMany(mappedBy = "administrator_klinike", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Zahtev> zahtevi = new HashSet<Zahtev>();

	public AdministratorKlinike() {
		super();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdKorisnik() {
		return idKorisnik;
	}

	public void setIdKorisnik(Long idKorisnik) {
		this.idKorisnik = idKorisnik;
	}

	public Klinika getKlinika() {
		return klinika;
	}

	public void setKlinika(Klinika klinika) {
		this.klinika = klinika;
	}


	public Set<Zahtev> getZahtevi() {
		return zahtevi;
	}

	public void setZahtevi(Set<Zahtev> zahtevi) {
		this.zahtevi = zahtevi;
	}

	@Override
	public String toString() {
		return "AdministratorKlinike [id=" + id + ", idKorisnik=" + idKorisnik + "]";
	}


	
	
	
}
