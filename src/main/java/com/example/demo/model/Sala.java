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
public class Sala  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Klinika klinika;
	
	@Column(name = "ime", nullable = false)
	private String ime;
	
	@Column(name = "opis")
	private String opis;
	
	@JsonIgnore
	@OneToMany(mappedBy = "sala", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<Pregled> pregledi = new HashSet<Pregled>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "sala", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<Operacija> operacije = new HashSet<Operacija>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "sala", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<Termin> slobodniTermini = new HashSet<Termin>();
	
	public Sala() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Klinika getKlinika() {
		return klinika;
	}

	public void setKlinika(Klinika klinika) {
		this.klinika = klinika;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	
	public Set<Pregled> getPregledi() {
		return pregledi;
	}

	public void setPregledi(Set<Pregled> pregledi) {
		this.pregledi = pregledi;
	}

	public Set<Operacija> getOperacije() {
		return operacije;
	}

	public void setOperacije(Set<Operacija> operacije) {
		this.operacije = operacije;
	}
	

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Set<Termin> getSlobodniTermini() {
		return slobodniTermini;
	}


	public void setSlobodniTermini(Set<Termin> slobodniTermini) {
		this.slobodniTermini = slobodniTermini;
	}


	@Override
	public String toString() {
		return "Sala [id=" + id + ", klinika=" + klinika + ", ime=" + ime + ", opis=" + opis + ", pregledi=" + pregledi
				+ ", operacije=" + operacije + ", slobodniTermini=" + slobodniTermini + "]";
	}


	
	
	
}
