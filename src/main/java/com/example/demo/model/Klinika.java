package com.example.demo.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

@Entity
public class Klinika {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "ime")
	private String ime;
	
	@Column(name = "adresa")
	private String adresa;
	
	@Column(name = "opis")
	private String opis;
	
	@OneToMany
	@JoinTable(name = "termini", joinColumns = @JoinColumn(name = "klinika_id", 
	referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "termin_id", 
	referencedColumnName = "id"))
	private Set<Termin> slobodniTermini;
	
	
	@OneToMany
	@JoinTable(name = "doktori", joinColumns = @JoinColumn(name = "klinika_id", 
	referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "doktor_id", 
	referencedColumnName = "id"))
	private Set<Doktor> doktori;
	
	
	@OneToMany
	@JoinTable(name = "sala", joinColumns = @JoinColumn(name = "klinika_id", 
	referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "sala_id", 
	referencedColumnName = "id"))
	private Set<Sala> sale;
	
	
	@OneToMany
	@JoinTable(name = "usluga", joinColumns = @JoinColumn(name = "klinika_id", 
	referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "usluga_id", 
	referencedColumnName = "id"))
	private Set<Usluga> usluge;
	
	
	//@OneToMany
	//@JoinTable(name = "ocena", joinColumns = @JoinColumn(name = "klinika_id", 
	//referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "ocena_id", 
	//referencedColumnName = "id"))
	@Column(name = "Ocene",nullable = true)
	private Set<Integer> ocene;
	
	@Column(name = "ProsecnaOcena", nullable = false)
	private double prosecnaOcena = 0;
	
	public Klinika() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Klinika(String ime, String adresa, String opis, Set<Termin> slobodniTermini, Set<Doktor> doktori,
			Set<Sala> sale, Set<Usluga> usluge, Set<Integer> ocene, double prosecnaOcena) {
		super();
		this.ime = ime;
		this.adresa = adresa;
		this.opis = opis;
		this.slobodniTermini = slobodniTermini;
		this.doktori = doktori;
		this.sale = sale;
		this.usluge = usluge;
		this.ocene = ocene;
		this.prosecnaOcena = prosecnaOcena;
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

	public Set<Integer> getOcene() {
		return ocene;
	}

	public void setOcene(Set<Integer> ocene) {
		this.ocene = ocene;
	}

	public double getProsecnaOcena() {
		return prosecnaOcena;
	}

	public void setProsecnaOcena(double prosecnaOcena) {
		this.prosecnaOcena = prosecnaOcena;
	}

	@Override
	public String toString() {
		return "Klinika [ime=" + ime + ", adresa=" + adresa + ", opis=" + opis + ", slobodniTermini=" + slobodniTermini
				+ ", doktori=" + doktori + ", sale=" + sale + ", usluge=" + usluge + ", ocene=" + ocene
				+ ", prosecnaOcena=" + prosecnaOcena + "]";
	}
	
	
	
	
	
}
