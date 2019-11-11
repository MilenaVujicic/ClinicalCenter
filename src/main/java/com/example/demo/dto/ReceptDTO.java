package com.example.demo.dto;

import java.time.LocalDateTime;

import com.example.demo.model.Doktor;
import com.example.demo.model.MedicinskaSestra;
import com.example.demo.model.Pacijent;

enum StatusRecepta {
	NEOVEREN, OVEREN
}

public class ReceptDTO {

	private Long id;
	private String naziv;
	private String opis;
	private StatusRecepta status;
	private LocalDateTime datumIspisa;
	private LocalDateTime datumOvere;
	private Pacijent pacijent;
	private Doktor doktor;
	private MedicinskaSestra medicinskaSestra;
	
	public ReceptDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ReceptDTO(Long id, String naziv, String opis, StatusRecepta status, LocalDateTime datumIspisa,
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
		return "ReceptDTO [id=" + id + ", naziv=" + naziv + ", opis=" + opis + ", status=" + status + ", datumIspisa="
				+ datumIspisa + ", datumOvere=" + datumOvere + ", pacijent=" + pacijent + ", doktor=" + doktor
				+ ", medicinskaSestra=" + medicinskaSestra + "]";
	}
	
}
