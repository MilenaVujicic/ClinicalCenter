package com.example.demo.model;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Odsustvo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Korisnik korisnik;
	
	@Column(name = "vrsta_odsustva", nullable = false)
	private VrstaOdsustva vrstaOdsustva;
	
	@Column(name = "pocetak_odsustva", nullable = false)
	private Calendar pocetakOdsustva;
	
	@Column(name = "zavrsetak_odsustva", nullable = false)
	private Calendar zavrsetakOdsustva;
	
	@Column(name = "odobren", nullable = false)
	private boolean odobren;
	
	public Odsustvo()  {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Korisnik getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}


	public VrstaOdsustva getVrstaOdsustva() {
		return vrstaOdsustva;
	}

	public void setVrstaOdsustva(VrstaOdsustva vrstaOdsustva) {
		this.vrstaOdsustva = vrstaOdsustva;
	}

	public Calendar getPocetakOdsustva() {
		return pocetakOdsustva;
	}

	public void setPocetakOdsustva(Calendar pocetakOdsustva) {
		this.pocetakOdsustva = pocetakOdsustva;
	}

	public Calendar getZavrsetakOdsustva() {
		return zavrsetakOdsustva;
	}

	public void setZavrsetakOdsustva(Calendar zavrsetakOdsustva) {
		this.zavrsetakOdsustva = zavrsetakOdsustva;
	}

	public boolean isOdobren() {
		return odobren;
	}

	public void setOdobren(boolean odobren) {
		this.odobren = odobren;
	}

	@Override
	public String toString() {
		return "Odsustvo [id=" + id + ", korisnik=" + korisnik + ", vrstaOdsustva=" + vrstaOdsustva
				+ ", pocetakOdsustva=" + pocetakOdsustva + ", zavrsetakOdsustva=" + zavrsetakOdsustva + ", odobren="
				+ odobren + "]";
	}

	
	
}
