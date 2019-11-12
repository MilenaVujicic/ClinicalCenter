package com.example.demo.dto;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.example.demo.model.Operacija;
import com.example.demo.model.Pregled;
import com.example.demo.model.Recept;

public class PacijentDTO {
	
	private Long id;
	private int visina;
	private int tezina;
	private double dioptrija;
	private Set<String> alergije = new HashSet<String>();
	private Set<Operacija> operacije = new HashSet<Operacija>();
	private Set<Recept> recepti = new HashSet<Recept>(); 
	private Set<Pregled> pregledi = new HashSet<Pregled>();
	
	public PacijentDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PacijentDTO(int visina, int tezina, double dioptrija, Set<String> alergije, Set<Operacija> operacije,
			Set<Recept> recepti, Set<Pregled> pregledi) {
		super();
		this.visina = visina;
		this.tezina = tezina;
		this.dioptrija = dioptrija;
		this.alergije = alergije;
		this.operacije = operacije;
		this.recepti = recepti;
		this.pregledi = pregledi;
	}

	public int getVisina() {
		return visina;
	}

	public void setVisina(int visina) {
		this.visina = visina;
	}

	public int getTezina() {
		return tezina;
	}

	public void setTezina(int tezina) {
		this.tezina = tezina;
	}

	public double getDioptrija() {
		return dioptrija;
	}

	public void setDioptrija(double dioptrija) {
		this.dioptrija = dioptrija;
	}

	public Set<String> getAlergije() {
		return alergije;
	}

	public void setAlergije(Set<String> alergije) {
		this.alergije = alergije;
	}

	public Set<Operacija> getOperacije() {
		return operacije;
	}

	public void setOperacije(Set<Operacija> operacije) {
		this.operacije = operacije;
	}

	public Set<Recept> getRecepti() {
		return recepti;
	}

	public void setRecepti(Set<Recept> recepti) {
		this.recepti = recepti;
	}

	public Set<Pregled> getPregledi() {
		return pregledi;
	}

	public void setPregledi(Set<Pregled> pregledi) {
		this.pregledi = pregledi;
	}

	@Override
	public String toString() {
		return "Pacijent [visina=" + visina + ", tezina=" + tezina + ", dioptrija=" + dioptrija + ", alergije="
				+ alergije + ", operacije=" + operacije + ", recepti=" + recepti + ", pregledi=" + pregledi + "]";
	}

}
