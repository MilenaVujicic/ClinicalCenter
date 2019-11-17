package com.example.demo.dto;

import com.example.demo.model.AdministratorKlinickogCentra;

public class AdministratorKlinickogCentraDTO {

	private Long id;
	private Long idKorisnika;
	
	public AdministratorKlinickogCentraDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AdministratorKlinickogCentraDTO(Long id, Long idKorisnika) {
		super();
		this.id = id;
		this.idKorisnika = idKorisnika;
	}
	
	public AdministratorKlinickogCentraDTO(AdministratorKlinickogCentra a) {
		this(a.getId(), a.getIdKorisnik());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdKorisnika() {
		return idKorisnika;
	}

	public void setIdKorisnika(Long idKorisnika) {
		this.idKorisnika = idKorisnika;
	}

	@Override
	public String toString() {
		return "AdministratorKlinickogCentraDTO [id=" + id + ", idKorisnika=" + idKorisnika + "]";
	}
	
	
}
