package com.example.demo.dto;

import com.example.demo.model.Alergija;
import com.example.demo.model.Pacijent;

public class AlergijaDTO {

	private Long id;
	private String naziv;
	private String opis;
	private Pacijent pacijent;
	
	public AlergijaDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public AlergijaDTO(Long id, String naziv, String opis, Pacijent pacijent) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.opis = opis;
		this.pacijent = pacijent;
	}

	public AlergijaDTO(Alergija a) {
		this.id = a.getId();
		this.naziv = a.getNaziv();
		this.opis = a.getOpis();
		this.pacijent = a.getPacijent();
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

	public Pacijent getPacijent() {
		return pacijent;
	}

	public void setPacijent(Pacijent pacijent) {
		this.pacijent = pacijent;
	}

	@Override
	public String toString() {
		return "AlergijaDTO [id=" + id + ", naziv=" + naziv + ", opis=" + opis + ", pacijent=" + pacijent + "]";
	}
	
	
}
