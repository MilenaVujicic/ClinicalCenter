package com.example.demo.dto;

import java.util.HashSet;
import java.util.Set;

import com.example.demo.model.Alergija;
import com.example.demo.model.Klinika;
import com.example.demo.model.KrvnaGrupa;
import com.example.demo.model.Operacija;
import com.example.demo.model.Pacijent;
import com.example.demo.model.Pregled;
import com.example.demo.model.Recept;

public class PacijentDTO {
	
	private Long id;
	private Long idKorisnik;
	private int visina;
	private int tezina;
	private double dioptrija;
	private Set<Alergija> alergije = new HashSet<Alergija>();
	private Set<Operacija> operacije = new HashSet<Operacija>();
	private Set<Recept> recepti = new HashSet<Recept>(); 
	private Set<Pregled> pregledi = new HashSet<Pregled>();
	private Klinika klinika;
	private KrvnaGrupa krvna_grupa;
	
	public PacijentDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public PacijentDTO(Long id, Long idKorisnik, int visina, int tezina, double dioptrija, Set<Alergija> alergije,
			Set<Operacija> operacije, Set<Recept> recepti, Set<Pregled> pregledi, Klinika klinika, KrvnaGrupa krvna_grupa) {
		super();
		this.id = id;
		this.idKorisnik = idKorisnik;
		this.visina = visina;
		this.tezina = tezina;
		this.dioptrija = dioptrija;
		this.alergije = alergije;
		this.operacije = operacije;
		this.recepti = recepti;
		this.pregledi = pregledi;
		this.klinika = klinika;
		this.krvna_grupa = krvna_grupa;
	}

	public PacijentDTO(Pacijent p) {
		this(p.getId(), p.getIdKorisnik(), p.getVisina(), p.getTezina(), p.getDioptrija(), p.getAlergije(),
				p.getOperacije(), p.getRecepti(),p.getPregledi(), p.getKlinika(), p.getKrvna_grupa());
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

	public Set<Alergija> getAlergije() {
		return alergije;
	}

	public void setAlergije(Set<Alergija> alergije) {
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdKorisnik() {
		return idKorisnik;
	}

	public void setIdKorisnik(Long idKorisnik) {
		this.idKorisnik = idKorisnik;
	}

	public Klinika getKlinika() {
		return klinika;
	}

	public void setKlinika(Klinika klinika) {
		this.klinika = klinika;
	}
	
	

	public KrvnaGrupa getKrvna_grupa() {
		return krvna_grupa;
	}

	public void setKrvna_grupa(KrvnaGrupa krvna_grupa) {
		this.krvna_grupa = krvna_grupa;
	}

	@Override
	public String toString() {
		return "PacijentDTO [id=" + id + ", idKorisnik=" + idKorisnik + ", visina=" + visina + ", tezina=" + tezina
				+ ", dioptrija=" + dioptrija + ", alergije=" + alergije + ", operacije=" + operacije + ", recepti="
				+ recepti + ", pregledi=" + pregledi + ", klinika=" + klinika + ", krvna_grupa=" + krvna_grupa + "]";
	}

}
