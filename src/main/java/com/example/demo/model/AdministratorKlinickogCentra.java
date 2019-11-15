package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AdministratorKlinickogCentra {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="idKorisnik", nullable = false)
	private Long idKorisnik;
	
	

	public AdministratorKlinickogCentra() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AdministratorKlinickogCentra(Long id, Long idKorisnik) {
		super();
		this.id = id;
		this.idKorisnik = idKorisnik;
		
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

	@Override
	public String toString() {
		return "AdministratorKlinickogCentra [id=" + id + ", idKorisnik=" + idKorisnik + "]";
	}

	
	
	
}
