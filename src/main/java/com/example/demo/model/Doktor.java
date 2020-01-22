/*
 * author: Andrea Mendrei
 */
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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Doktor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="idKorisnik", nullable = false)
	private Long idKorisnik;
	
	@Column(name = "specijalizacija", nullable = true)
	private String specijalizacija;
	
	@Column(name = "prosecnaOcena", nullable = false)
	private double prosenaOcena;
	
	@Column(name = "brojOcena", nullable = false)
	private int brojOcena;
	
	@Column(name = "sumaOcena", nullable = false)
	private int sumaOcena;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Klinika klinika;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "doktori")
	private Set<Operacija> operacije = new HashSet<Operacija>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "doktor", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Pregled> pregledi = new HashSet<Pregled>();

	@JsonIgnore
	@OneToMany(mappedBy = "doktor", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Termin> termin = new HashSet<Termin>();
	
	public Doktor() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getIdKorisnik() {
		return idKorisnik;
	}

	public void setIdKorisnik(Long idKorisnik) {
		this.idKorisnik = idKorisnik;
	}

	public String getSpecijalizacija() {
		return specijalizacija;
	}

	public void setSpecijalizacija(String specijalizacija) {
		this.specijalizacija = specijalizacija;
	}

	public Set<Operacija> getOperacije() {
		return operacije;
	}

	public void setOperacije(Set<Operacija> operacije) {
		this.operacije = operacije;
	}

	public Set<Pregled> getPregledi() {
		return pregledi;
	}

	public void setPregledi(Set<Pregled> pregledi) {
		this.pregledi = pregledi;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getProsenaOcena() {
		return prosenaOcena;
	}

	public void setProsenaOcena(double prosenaOcena) {
		this.prosenaOcena = prosenaOcena;
	}
	
	public int getSumaOcena() {
		return sumaOcena;
	}
	
	public void setSumaOcena(int sumaOcena) {
		this.sumaOcena = sumaOcena;
	}
	
	public int getBrojOcena() {
		return brojOcena;
	}
	
	public void setBrojOcena(int brojOcena) {
		this.brojOcena = brojOcena;
	}

	public Klinika getKlinika() {
		return klinika;
	}

	public void setKlinika(Klinika klinika) {
		this.klinika = klinika;
	}

	public Set<Termin> getTermin() {
		return termin;
	}

	public void setTermin(Set<Termin> termin) {
		this.termin = termin;
	}

	
	
	
}
