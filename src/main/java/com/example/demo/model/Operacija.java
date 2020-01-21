/*
 * author: Andrea Mendrei
 */
package com.example.demo.model;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class Operacija {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE, CascadeType.DETACH}, fetch = FetchType.EAGER)
	private Pacijent pacijent;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE, CascadeType.DETACH}, fetch = FetchType.EAGER)
	private Sala sala;
	
	@Column(name = "status", nullable = false)
	private StatusOperacije status;
	
	@Column(name = "datumIVremeOperacije", nullable = false)
	private Calendar datumIVremeOperacije;
	
	@Column(name = "opis")
	private String opis = "";
	
	@Column(name = "trajanje", nullable = false)
	private int trajanje;
	
	@ManyToMany
	@JoinTable(name = "operacije_doktori", joinColumns = @JoinColumn(name = "operacija_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "doktor_id", referencedColumnName = "id"))
	private Set<Doktor> doktori = new HashSet<Doktor>();
	
	public Operacija() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	public StatusOperacije getStatus() {
		return status;
	}

	public void setStatus(StatusOperacije status) {
		this.status = status;
	}

	public Calendar getDatumIVremeOperacije() {
		return datumIVremeOperacije;
	}

	public void setDatumIVremeOperacije(Calendar datumIVremeOperacije) {
		this.datumIVremeOperacije = datumIVremeOperacije;
	}

	public int getTrajanje() {
		return trajanje;
	}

	public void setTrajanje(int trajanje) {
		this.trajanje = trajanje;
	}

	public Pacijent getPacijent() {
		return pacijent;
	}

	public void setPacijent(Pacijent pacijent) {
		this.pacijent = pacijent;
	}

	public Set<Doktor> getDoktori() {
		return doktori;
	}

	public void setDoktori(Set<Doktor> doktori) {
		this.doktori = doktori;
	}
	
	

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	@Override
	public String toString() {
		return "Operacija [id=" + id + ", pacijent=" + pacijent + ", sala=" + sala + ", status=" + status
				+ ", datumIVremeOperacije=" + datumIVremeOperacije + ", opis=" + opis + ", trajanje=" + trajanje
				+ ", doktori=" + doktori + "]";
	}
	
	
}