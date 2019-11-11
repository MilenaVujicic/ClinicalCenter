/*
 * author:Andrea Mendrei
 */
package com.example.demo.model;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

enum StatusRecepta {
	NEOVEREN, OVEREN
}
@Entity
public class Recept {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "naziv")
	private String naziv;
	
	@Column(name = "opis")
	private String opis;
	
	@Column(name = "status")
	private StatusRecepta status;
	
	@Column(name="datumIspisa")
	private LocalDateTime datumIspisa;
	
	@Column(name="datumOvere")
	private LocalDateTime datumOvere;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Pacijent pacijent;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Doktor doktor;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private MedicinskaSestra medicinskaSestra;
	
	public Recept() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public Recept(Long id, String naziv, String opis, StatusRecepta status, LocalDateTime datumIspisa,
			LocalDateTime datumOvere, Pacijent pacijent, Doktor doktor, MedicinskaSestra medicinskaSestra) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.opis = opis;
		this.status = status;
		this.datumIspisa = datumIspisa;
		this.datumOvere = datumOvere;
		this.pacijent = pacijent;
		this.doktor = doktor;
		this.medicinskaSestra = medicinskaSestra;
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

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public StatusRecepta getStatus() {
		return status;
	}

	public void setStatus(StatusRecepta status) {
		this.status = status;
	}

	public LocalDateTime getDatumIspisa() {
		return datumIspisa;
	}

	public void setDatumIspisa(LocalDateTime datumIspisa) {
		this.datumIspisa = datumIspisa;
	}

	public LocalDateTime getDatumOvere() {
		return datumOvere;
	}

	public void setDatumOvere(LocalDateTime datumOvere) {
		this.datumOvere = datumOvere;
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

	public MedicinskaSestra getMedicinskaSestra() {
		return medicinskaSestra;
	}

	public void setMedicinskaSestra(MedicinskaSestra medicinskaSestra) {
		this.medicinskaSestra = medicinskaSestra;
	}

	@Override
	public String toString() {
		return "Recept [id=" + id + ", naziv=" + naziv + ", opis=" + opis + ", status=" + status + ", datumIspisa="
				+ datumIspisa + ", datumOvere=" + datumOvere + ", pacijent=" + pacijent + ", doktor=" + doktor
				+ ", medicinskaSestra=" + medicinskaSestra + "]";
	}
	
	
}
