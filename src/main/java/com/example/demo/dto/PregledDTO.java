/*
 * author: Andrea Mendrei
 */
package com.example.demo.dto;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.example.demo.model.Dijagnoza;
import com.example.demo.model.Doktor;
import com.example.demo.model.Lek;
import com.example.demo.model.Pacijent;
import com.example.demo.model.Pregled;
import com.example.demo.model.Sala;
import com.example.demo.model.StatusPregleda;
import com.example.demo.model.TipPregleda;


public class PregledDTO {

	private Long id;
	private String naziv;
	private String anamneza;
	private Pacijent pacijent;
	private Calendar datumIVremePregleda;
	private TipPregleda tipPregleda;
	private int duzina;
	private Sala sala;
	private Doktor doktori;
	private Set<Dijagnoza> dijagnoze = new HashSet<Dijagnoza>();
	private Set<Lek> lekovi = new HashSet<Lek>();
	private StatusPregleda status;
	private String imeDoktora;
	private String prezimeDoktora;
	
	public PregledDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PregledDTO(Long id, String naziv, String anamneza, Pacijent pacijent, Calendar datumIVremePregleda,
			TipPregleda tipPregleda, int duzina, Sala sala, Doktor doktori, Set<Dijagnoza> dijagnoze,
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
		this.dijagnoze = dijagnoze;
		this.lekovi = lekovi;
		this.status = status;
	}
	
	public PregledDTO(Pregled p) {
		this.id = p.getId();
		this.naziv = p.getNaziv();
		this.anamneza = p.getAnamneza();
		this.pacijent = p.getPacijent();
		this.datumIVremePregleda = p.getDatumIVremePregleda();
		this.tipPregleda = p.getTipPregleda();
		this.sala = p.getSala();
		this.doktori = p.getDoktor();
		this.dijagnoze = p.getDijagnoze();
		this.status = p.getStatus();
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

	public Calendar getDatumIVremePregleda() {
		return datumIVremePregleda;
	}

	public void setDatumIVremePregleda(Calendar datumIVremePregleda) {
		this.datumIVremePregleda = datumIVremePregleda;
	}

	public TipPregleda getTipPregleda() {
		return tipPregleda;
	}

	public void setTipPregleda(TipPregleda tipPregleda) {
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

	public Doktor getDoktori() {
		return doktori;
	}

	public void setDoktori(Doktor doktori) {
		this.doktori = doktori;
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
				+ ", sala=" + sala + ", doktori=" + doktori + ", dijagnoze=" + dijagnoze + ", lekovi=" + lekovi
				+ ", status=" + status + ", imeDoktora=" + imeDoktora + ", prezimeDoktora=" + prezimeDoktora + "]";
	}

	public String getImeDoktora() {
		return imeDoktora;
	}

	public void setImeDoktora(String imeDoktora) {
		this.imeDoktora = imeDoktora;
	}

	public String getPrezimeDoktora() {
		return prezimeDoktora;
	}

	public void setPrezimeDoktora(String prezimeDoktora) {
		this.prezimeDoktora = prezimeDoktora;
	}
	
	
}
