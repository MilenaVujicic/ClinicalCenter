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
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Klinika {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "ime", nullable = false)
	private String ime;
	
	@Column(name = "adresa")
	private String adresa;
	
	@Column(name = "opis")
	private String opis;

	@JsonIgnore
	@OneToMany(mappedBy = "klinika", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Doktor> doktori = new HashSet<Doktor>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "klinika", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Sala> sale = new HashSet<Sala>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "klinika", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Usluga> usluge = new HashSet<Usluga>();
	
	@Column(name = "ProsecnaOcena", nullable = false)
	private double prosecnaOcena;
	
	@Column(name="BrojOcena", nullable = false)
	private double brojOcena;

	@Column(name = "SumaOcena", nullable = false)
	private double sumaOcena;
	
	@JsonIgnore
	@OneToMany(mappedBy = "klinika", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<AdministratorKlinike> administratoriKlinike = new HashSet<AdministratorKlinike>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "klinika", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Pacijent> pacijenti = new HashSet<Pacijent>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "klinika", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<MedicinskaSestra> mediniskeSestre = new HashSet<MedicinskaSestra>();
	
	public Klinika() {
		super();
		// TODO Auto-generated constructor stub
		this.prosecnaOcena = 0;
		this.brojOcena = 0;
		this.sumaOcena = 0;
	}
	
	

	public Klinika(String ime, String adresa, String opis) {
		super();
		this.ime = ime;
		this.adresa = adresa;
		this.opis = opis;
		this.prosecnaOcena = 0;
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

	public Set<AdministratorKlinike> getAdministratoriKlinike() {
		return administratoriKlinike;
	}

	public void setAdministratoriKlinike(Set<AdministratorKlinike> administratoriKlinike) {
		this.administratoriKlinike = administratoriKlinike;
	}

	public void setUsluge(Set<Usluga> usluge) {
		this.usluge = usluge;
	}

	@Override
	public String toString() {
		return "Klinika [id=" + id + ", ime=" + ime + ", adresa=" + adresa + ", opis=" + opis + ", doktori=" + doktori + ", sale=" + sale + ", usluge=" + usluge
				+ ", prosecnaOcena=" + prosecnaOcena + ", administratoriKlinike=" + administratoriKlinike + "]";
	}
	
	public double getBrojOcena() {
		return brojOcena;
	}
	
	public void setBrojOcena(double brojOcena) {
		this.brojOcena = brojOcena;
	}
	
	public double getSumaOcena() {
		return sumaOcena;
	}
	
	public void setSumaOcena(double sumaOcena) {
		this.sumaOcena = sumaOcena;
	}
	
	
	
	
	
}
