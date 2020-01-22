/*
 * author: Andrea Mendrei
 */
package com.example.demo.model;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;


@Entity
public class Pregled {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "naziv", nullable = false)
	private String naziv;
	
	@Column(name = "anamneza", nullable = false)
	private String anamneza;
	
	@Column(name = "datumIVremePregleda", nullable = true)
	private Calendar datumIVremePregleda;
	
	@Column(name = "tipPregleda")
	private String tipPregleda;
	
	@Column(name = "cena", nullable = false)
	private double cena;
	
	@ManyToMany
	@JoinTable(name = "pregledi_dijagnoze", joinColumns = @JoinColumn(name = "pregled_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "dijagnoza_id", referencedColumnName = "id"))
	private Set<Dijagnoza> dijagnoze = new HashSet<Dijagnoza>();
	
	@Column(name = "status")
	private StatusPregleda status;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE, CascadeType.DETACH}, fetch = FetchType.EAGER)
	private Pacijent pacijent;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE, CascadeType.DETACH}, fetch = FetchType.EAGER)
	private Doktor doktor;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE, CascadeType.DETACH}, fetch = FetchType.EAGER)
	private Sala sala;


	public Pregled() {
		super();
		// TODO Auto-generated constructor stub
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

	public Calendar getDatumIVremePregleda() {
		return datumIVremePregleda;
	}

	public void setDatumIVremePregleda(Calendar datumIVremePregleda) {
		this.datumIVremePregleda = datumIVremePregleda;
	}

	public String getTipPregleda() {
		return tipPregleda;
	}

	public void setTipPregleda(String tipPregleda) {
		this.tipPregleda = tipPregleda;
	}

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}


	public StatusPregleda getStatus() {
		return status;
	}

	public void setStatus(StatusPregleda status) {
		this.status = status;
	}

	public Set<Dijagnoza> getDijagnoze() {
		return dijagnoze;
	}

	public void setDijagnoze(Set<Dijagnoza> dijagnoze) {
		this.dijagnoze = dijagnoze;
	}

	public Pacijent getPacijent() {
		return pacijent;
	}

	public void setPacijent(Pacijent pacijent) {
		this.pacijent = pacijent;
	}

	public Doktor getDoktor() {
		return doktor;
	}

	public void setDoktor(Doktor doktor) {
		this.doktor = doktor;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	@Override
	public String toString() {
		return "Pregled [id=" + id + ", naziv=" + naziv + ", anamneza=" + anamneza + ", datumIVremePregleda="
				+ datumIVremePregleda + ", tipPregleda=" + tipPregleda + ", cena=" + cena + ", dijagnoze=" + dijagnoze
				+ ", status=" + status + ", pacijent=" + pacijent + ", doktor=" + doktor + ", sala=" + sala + "]";
	}
	
	
	
}