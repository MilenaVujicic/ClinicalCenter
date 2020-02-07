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
public class TipPregleda {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "naziv", nullable = false)
	private String naziv;
	
	@JsonIgnore
	@OneToMany(mappedBy = "tipPregleda", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Pregled> pregledi = new HashSet<Pregled>();
	
	@Column(name = "cena", nullable = false)
	private double cena;
	
	@Column(name = "zauzet", nullable = false)
	private Boolean zauzet;
	

	public TipPregleda() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TipPregleda(Long id, String naziv, Set<Pregled> pregledi, double cena, Boolean zauzet) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.pregledi = pregledi;
		this.cena = cena;
		this.zauzet = zauzet;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public Set<Pregled> getPregledi() {
		return pregledi;
	}

	public void setPregledi(Set<Pregled> pregledi) {
		this.pregledi = pregledi;
	}

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}

	public Boolean getZauzet() {
		return zauzet;
	}

	public void setZauzet(Boolean zauzet) {
		this.zauzet = zauzet;
	}
	
	
}
