/*
 * author: Andrea Mendrei
 */
package com.example.demo.dto;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.example.demo.model.Dijagnoza;
import com.example.demo.model.Doktor;
import com.example.demo.model.Lek;
import com.example.demo.model.Pacijent;
import com.example.demo.model.Sala;

enum StatusPregleda {
	ZAKAZAN, ZAVRSEN
}

public class PregledDTO {

	private Long id;
	private String naziv;
	private String anamneza;
	private Pacijent pacijent;
	private LocalDateTime datumIVremePregleda;
	private String tipPregleda;
	private int duzina;
	private Sala sala;
	private Set<Doktor> doktori = new HashSet<Doktor>();
	private double cena;
	private Set<Dijagnoza> dijagnoze = new HashSet<Dijagnoza>();
	private Set<Lek> lekovi = new HashSet<Lek>();
	private StatusPregleda status;
	
	public PregledDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PregledDTO(Long id, String naziv, String anamneza, Pacijent pacijent, LocalDateTime datumIVremePregleda,
			String tipPregleda, int duzina, Sala sala, Set<Doktor> doktori, double cena, Set<Dijagnoza> dijagnoze,
			Set<Lek> lekovi, StatusPregleda status) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.anamneza = anamneza;
		this.pacijent = pacijent;
		this.datumIVremePregleda = datumIVremePregleda;
		this.tipPregleda = tipPregleda;
		this.duzina = duzina;
		this.sala = sala;
		this.doktori = doktori;
		this.cena = cena;
		this.dijagnoze = dijagnoze;
		this.lekovi = lekovi;
		this.status = status;
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

	public String getAnamneza() {
		return anamneza;
	}

	public void setAnamneza(String anamneza) {
		this.anamneza = anamneza;
	}

	public Pacijent getPacijent() {
		return pacijent;
	}

	public void setPacijent(Pacijent pacijent) {
		this.pacijent = pacijent;
	}

	public LocalDateTime getDatumIVremePregleda() {
		return datumIVremePregleda;
	}

	public void setDatumIVremePregleda(LocalDateTime datumIVremePregleda) {
		this.datumIVremePregleda = datumIVremePregleda;
	}

	public String getTipPregleda() {
		return tipPregleda;
	}

	public void setTipPregleda(String tipPregleda) {
		this.tipPregleda = tipPregleda;
	}

	public int getDuzina() {
		return duzina;
	}

	public void setDuzina(int duzina) {
		this.duzina = duzina;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	public Set<Doktor> getDoktori() {
		return doktori;
	}

	public void setDoktori(Set<Doktor> doktori) {
		this.doktori = doktori;
	}

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}

	public Set<Dijagnoza> getDijagnoze() {
		return dijagnoze;
	}

	public void setDijagnoze(Set<Dijagnoza> dijagnoze) {
		this.dijagnoze = dijagnoze;
	}

	public Set<Lek> getLekovi() {
		return lekovi;
	}

	public void setLekovi(Set<Lek> lekovi) {
		this.lekovi = lekovi;
	}

	public StatusPregleda getStatus() {
		return status;
	}

	public void setStatus(StatusPregleda status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "PregledDTO [id=" + id + ", naziv=" + naziv + ", anamneza=" + anamneza + ", pacijent=" + pacijent
				+ ", datumIVremePregleda=" + datumIVremePregleda + ", tipPregleda=" + tipPregleda + ", duzina=" + duzina
				+ ", sala=" + sala + ", doktori=" + doktori + ", cena=" + cena + ", dijagnoze=" + dijagnoze
				+ ", lekovi=" + lekovi + ", status=" + status + "]";
	}
	
	
}
