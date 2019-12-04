package com.example.demo.dto;

import java.util.Set;

import com.example.demo.model.Klinika;
import com.example.demo.model.Operacija;
import com.example.demo.model.Pregled;
import com.example.demo.model.Sala;
import com.example.demo.model.Termin;

public class SalaDTO {

	private Long id;
	private Klinika klinika;
	private String ime;
	private String opis;
	private Set<Pregled> pregledi;
	private Set<Operacija> operacije;
	
	private Set<Termin> termini;
	
	
	
	public SalaDTO(Long id, Klinika klinika, String ime, String opis, Set<Pregled> pregledi, Set<Operacija> operacije, Set<Termin> termini) {
		super();
		this.id = id;
		this.klinika = klinika;
		this.ime = ime;
		this.opis = opis;
		this.pregledi = pregledi;
		this.operacije = operacije;
	}
	
	public SalaDTO(Sala s) {
		this(s.getId(), s.getKlinika(), s.getIme(), s.getOpis(), s.getPregledi(), s.getOperacije(), s.getSlobodniTermini());
		
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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

	public Set<Termin> getTermini() {
		return termini;
	}

	public void setTermini(Set<Termin> termini) {
		this.termini = termini;
	}
	
	
}
