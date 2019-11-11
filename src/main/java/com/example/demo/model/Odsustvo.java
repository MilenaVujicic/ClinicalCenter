package com.example.demo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Odsustvo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "korisnik", nullable = false)
	private Korisnik korisnik;
	
	@Column(name = "vrsta_odsustva", nullable = false)
	private VrstaOdsustva vrstaOdsustva;
	
	@Column(name = "pocetak_odsustva", nullable = false)
	private Date pocetakOdsustva;
	
	@Column(name = "zavrsetak_odsustva", nullable = false)
	private Date zavrsetakOdsustva;
	
	@Column(name = "odobren", nullable = false)
	private boolean odobren;
	
	public Odsustvo()  {
		super();
	}

	public Odsustvo(Korisnik korisnik, VrstaOdsustva vrstaOdsustva, Date pocetakOdsustva, Date zavrsetakOdsustva,
			boolean odobren) {
		super();
		this.korisnik = korisnik;
		this.vrstaOdsustva = vrstaOdsustva;
		this.pocetakOdsustva = pocetakOdsustva;
		this.zavrsetakOdsustva = zavrsetakOdsustva;
		this.odobren = odobren;
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

	public Date getPocetakOdsustva() {
		return pocetakOdsustva;
	}

	public void setPocetakOdsustva(Date pocetakOdsustva) {
		this.pocetakOdsustva = pocetakOdsustva;
	}

	public Date getZavrsetakOdsustva() {
		return zavrsetakOdsustva;
	}

	public void setZavrsetakOdsustva(Date zavrsetakOdsustva) {
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
		return "Odsustvo [korisnik=" + korisnik + ", vrstaOdsustva=" + vrstaOdsustva + ", pocetakOdsustva="
				+ pocetakOdsustva + ", zavrsetakOdsustva=" + zavrsetakOdsustva + ", odobren=" + odobren + "]";
	}

	
	
}
