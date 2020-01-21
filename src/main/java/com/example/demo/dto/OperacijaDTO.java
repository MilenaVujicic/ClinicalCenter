/*
 * author: Andrea Mendrei
 */
package com.example.demo.dto;

import java.util.Calendar;
import java.util.Set;

import com.example.demo.model.Doktor;
import com.example.demo.model.Operacija;
import com.example.demo.model.Sala;
import com.example.demo.model.StatusOperacije;

public class OperacijaDTO {

	private Long id;
	private Sala sala;
	private StatusOperacije status;
	private Calendar datumIVremeOperacije;
	private int trajanje;
	private Set<Doktor> doktori;
	private String opis;
	
	public OperacijaDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OperacijaDTO(Long id, Sala sala, StatusOperacije statusOperacije, Calendar datumIVremeOperacije, 
			int trajanje, Set<Doktor> doktori, String opis) {
		super();
		this.id = id;
		this.sala = sala;
		this.status = statusOperacije;
		this.datumIVremeOperacije = datumIVremeOperacije;
		this.trajanje = trajanje;
		this.doktori = doktori;
		this.opis = opis;
	}
	
	public OperacijaDTO(Operacija operacija) {
		this(operacija.getId(), operacija.getSala(), operacija.getStatus(), operacija.getDatumIVremeOperacije(),
				operacija.getTrajanje(), operacija.getDoktori(), operacija.getOpis());
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
		return "OperacijaDTO [id=" + id + ", sala=" + sala + ", status=" + status + ", datumIVremeOperacije="
				+ datumIVremeOperacije + ", trajanje=" + trajanje + ", doktori=" + doktori + ", opis=" + opis + "]";
	}
	
	
}
