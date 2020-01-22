/*
 * author: Andrea Mendrei
 */
package com.example.demo.dto;

import java.time.LocalDateTime;
import java.util.Set;

import com.example.demo.model.Doktor;
import com.example.demo.model.Sala;


enum StatusOperacije {
	ZAKAZAN, ZAVRSEN
}

public class OperacijaDTO {

	private Long id;
	private Sala sala;
	private StatusOperacije status;
	private LocalDateTime datumIVremeOperacije;
	private int trajanje;
	private Set<Doktor> doktori;
	
	public OperacijaDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OperacijaDTO(Long id, Sala sala, StatusOperacije status, LocalDateTime datumIVremeOperacije, int trajanje,
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
		return "OperacijaDTO [id=" + id + ", sala=" + sala + ", status=" + status + ", datumIVremeOperacije="
				+ datumIVremeOperacije + ", trajanje=" + trajanje + ", doktori=" + doktori + "]";
	}
	
	
}
