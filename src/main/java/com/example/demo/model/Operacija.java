/*
 * author: Andrea Mendrei
 */
package com.example.demo.model;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

enum StatusOperacije {
	ZAKAZAN, ZAVRSEN
}

@Entity
public class Operacija {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Sala sala;
	
	@Column(name = "status")
	private StatusOperacije status;
	
	@Column(name = "datumIVremeOperacije")
	private LocalDateTime datumIVremeOperacije;
	
	@Column(name = "trajanje")
	private int trajanje;
	
	@ManyToMany(mappedBy = "operacije")
	private Set<Doktor> doktori;
	
	public Operacija() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Operacija(Long id, Sala sala, StatusOperacije status, LocalDateTime datumIVremeOperacije, int trajanje,
			Set<Doktor> doktori) {
		super();
		this.id = id;
		this.sala = sala;
		this.status = status;
		this.datumIVremeOperacije = datumIVremeOperacije;
		this.trajanje = trajanje;
		this.doktori = doktori;
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

	public LocalDateTime getDatumIVremeOperacije() {
		return datumIVremeOperacije;
	}

	public void setDatumIVremeOperacije(LocalDateTime datumIVremeOperacije) {
		this.datumIVremeOperacije = datumIVremeOperacije;
	}

	public int getTrajanje() {
		return trajanje;
	}

	public void setTrajanje(int trajanje) {
		this.trajanje = trajanje;
	}

	public Set<Doktor> getDoktori() {
		return doktori;
	}

	public void setDoktori(Set<Doktor> doktori) {
		this.doktori = doktori;
	}

	@Override
	public String toString() {
		return "Operacija [id=" + id + ", sala=" + sala + ", status=" + status + ", datumIVremeOperacije="
				+ datumIVremeOperacije + ", trajanje=" + trajanje + ", doktori=" + doktori + "]";
	}
	
	
	
	
}