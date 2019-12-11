package com.example.demo.dto;

import java.util.Date;

import com.example.demo.model.Doktor;
import com.example.demo.model.Pacijent;
import com.example.demo.model.Recept;
import com.example.demo.model.StatusRecepta;


public class ReceptDTO {

	private Long id;
	private String naziv;
	private String opis;
	private StatusRecepta status;
	private Date datumIspisa;
	private Date datumOvere;
	private Pacijent pacijent;
	
	public ReceptDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ReceptDTO(Long id, String naziv, String opis, StatusRecepta status, Date datumIspisa,
			Date datumOvere, Pacijent pacijent) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.opis = opis;
		this.status = status;
		this.datumIspisa = datumIspisa;
		this.datumOvere = datumOvere;
		this.pacijent = pacijent;
	}
	
	public ReceptDTO(Recept r) {
		this.id = r.getId();
		this.naziv = r.getNaziv();
		this.opis = r.getOpis();
		this.status = r.getStatus();
		this.datumIspisa = r.getDatumIspisa();
		this.datumOvere = r.getDatumOvere();
		this.pacijent = r.getPacijent();
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

	public Date getDatumIspisa() {
		return datumIspisa;
	}

	public void setDatumIspisa(Date datumIspisa) {
		this.datumIspisa = datumIspisa;
	}

	public Date getDatumOvere() {
		return datumOvere;
	}

	public void setDatumOvere(Date datumOvere) {
		this.datumOvere = datumOvere;
	}

	public Pacijent getPacijent() {
		return pacijent;
	}

	public void setPacijent(Pacijent pacijent) {
		this.pacijent = pacijent;
	}


	@Override
	public String toString() {
		return "ReceptDTO [id=" + id + ", naziv=" + naziv + ", opis=" + opis + ", status=" + status + ", datumIspisa="
				+ datumIspisa + ", datumOvere=" + datumOvere + ", pacijent=" + pacijent + "]";
	}
	
}
