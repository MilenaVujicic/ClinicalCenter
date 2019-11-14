package com.example.demo.model;
import java.util.HashSet;
//------------------------------
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
//-------------------------------
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
	
	@OneToMany(mappedBy = "klinika", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Termin> slobodniTermini = new HashSet<Termin>();
	
	@OneToMany(mappedBy = "klinika", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Doktor> doktori = new HashSet<Doktor>();
	
	@OneToMany(mappedBy = "klinika", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Sala> sale = new HashSet<Sala>();
	
	@OneToMany(mappedBy = "klinika", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Usluga> usluge = new HashSet<Usluga>();
	
	//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	//@Column(name = "Ocene",nullable = true)
	//private Set<Integer> ocene;
	
	@Column(name = "ProsecnaOcena", nullable = false)
	private double prosecnaOcena;
	
	@OneToMany(mappedBy = "klinika", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<AdministratorKlinike> administratoriKlinike;
	
	public Klinika() {
		super();
		// TODO Auto-generated constructor stub
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

	/*public Set<Integer> getOcene() {
		return ocene;
	}

	public void setOcene(Set<Integer> ocene) {
		this.ocene = ocene;
	}*/

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
		return "Klinika [id=" + id + ", ime=" + ime + ", adresa=" + adresa + ", opis=" + opis + ", slobodniTermini="
				+ slobodniTermini + ", doktori=" + doktori + ", sale=" + sale + ", usluge=" + usluge
				+ ", prosecnaOcena=" + prosecnaOcena + ", administratoriKlinike=" + administratoriKlinike + "]";
	}
	
	
	
	
	
}
