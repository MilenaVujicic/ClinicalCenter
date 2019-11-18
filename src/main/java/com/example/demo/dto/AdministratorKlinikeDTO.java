package com.example.demo.dto;

import com.example.demo.model.AdministratorKlinike;
import com.example.demo.model.Klinika;

public class AdministratorKlinikeDTO {

	private Long id;
	private Long idKorisnika;
	private Klinika klinika;
	
	public AdministratorKlinikeDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AdministratorKlinikeDTO(Long id, Long idKorisnika, Klinika klinika) {
		super();
		this.id = id;
		this.idKorisnika = idKorisnika;
		this.klinika = klinika;
	}
	
	public AdministratorKlinikeDTO(AdministratorKlinike a) {
		this(a.getId(), a.getIdKorisnik(), a.getKlinika());
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
	
	public Klinika getKlinika() {
		return klinika;
	}
	public void setKlinika(Klinika klinika) {
		this.klinika = klinika;
	}
	@Override
	public String toString() {
		return "AdministratorKlinikeDTO [id=" + id + ", idKorisnika=" + idKorisnika + ", klinika=" + klinika + "]";
	}
	
}
