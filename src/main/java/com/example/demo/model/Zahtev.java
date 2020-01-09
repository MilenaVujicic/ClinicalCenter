package com.example.demo.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Zahtev {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "doktorID")
	private Long doktorID;
	
	@Column(name = "datum")
	private String datum;
	
	@Column(name = "vreme")
	private String vreme;
	
	@Column(name = "salaID")
	private Long salaID;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private AdministratorKlinike administrator_klinike;
	
	public Zahtev() {

	}


	public Zahtev(Long doktorID, String datum, String vreme, Long salaID) {
		super();
		this.doktorID = doktorID;
		this.datum = datum;
		this.vreme = vreme;
		this.salaID = salaID;
	}

	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public Long getDoktorID() {
		return doktorID;
	}



	public void setDoktorID(Long doktorID) {
		this.doktorID = doktorID;
	}



	public Long getSalaID() {
		return salaID;
	}



	public void setSalaID(Long salaID) {
		this.salaID = salaID;
	}



	public String getDatum() {
		return datum;
	}
	public void setDatum(String datum) {
		this.datum = datum;
	}
	public String getVreme() {
		return vreme;
	}
	public void setVreme(String vreme) {
		this.vreme = vreme;
	}
	
	
	
}
