package com.example.demo.dto;

import java.util.Set;

import com.example.demo.model.AdministratorKlinike;
import com.example.demo.model.Doktor;
import com.example.demo.model.Klinika;
import com.example.demo.model.Sala;
import com.example.demo.model.Termin;
import com.example.demo.model.Usluga;

public class KlinikaDTO {
	
	private Long id;	
	private String ime;	
	private String adresa;
	private String opis;
	private Set<Termin> slobodniTermini;
	private Set<AdministratorKlinike> administratori;
	private Set<Doktor> doktori;
	private Set<Sala> sale;
	private Set<Usluga> usluge;
	private double prosecnaOcena = 0;
	
	public KlinikaDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public KlinikaDTO(Long id, String ime, String adresa, String opis, Set<Termin> slobodniTermini, Set<AdministratorKlinike> administratori, Set<Doktor> doktori,
			Set<Sala> sale, Set<Usluga> usluge, double prosecnaOcena) {
		super();
		this.id = id;
		this.ime = ime;
		this.adresa = adresa;
		this.opis = opis;
		this.slobodniTermini = slobodniTermini;
		this.administratori = administratori;
		this.doktori = doktori;
		this.sale = sale;
		this.usluge = usluge;
		this.prosecnaOcena = prosecnaOcena;
	}


	public KlinikaDTO(String ime, String adresa, String opis, Set<Termin> slobodniTermini, Set<Doktor> doktori,
			Set<Sala> sale, Set<Usluga> usluge, double prosecnaOcena) {
		super();
		this.ime = ime;
		this.adresa = adresa;
		this.opis = opis;
		this.slobodniTermini = slobodniTermini;
		this.doktori = doktori;
		this.sale = sale;
		this.usluge = usluge;
		this.prosecnaOcena = prosecnaOcena;
	}

	public KlinikaDTO(Klinika k) {
		this(k.getId(), k.getIme(), k.getAdresa(), k.getOpis(), k.getSlobodniTermini(), k.getAdministratoriKlinike(), k.getDoktori(), k.getSale(), k.getUsluge(), k.getProsecnaOcena());
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public Set<Termin> getSlobodniTermini() {
		return slobodniTermini;
	}

	public void setSlobodniTermini(Set<Termin> slobodniTermini) {
		this.slobodniTermini = slobodniTermini;
	}

	public Set<Doktor> getDoktori() {
		return doktori;
	}

	public void setDoktori(Set<Doktor> doktori) {
		this.doktori = doktori;
	}

	public Set<Sala> getSale() {
		return sale;
	}

	public void setSale(Set<Sala> sale) {
		this.sale = sale;
	}

	public Set<Usluga> getUsluge() {
		return usluge;
	}

	public void setCenovnik(Set<Usluga> usluge) {
		this.usluge = usluge;
	}

	public double getProsecnaOcena() {
		return prosecnaOcena;
	}

	public void setProsecnaOcena(double prosecnaOcena) {
		this.prosecnaOcena = prosecnaOcena;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "KlinikaDTO [id=" + id + ", ime=" + ime + ", adresa=" + adresa + ", opis=" + opis + ", slobodniTermini="
				+ slobodniTermini + ", doktori=" + doktori + ", sale=" + sale + ", usluge=" + usluge
				+ ", prosecnaOcena=" + prosecnaOcena + "]";
	}
	
	
}
