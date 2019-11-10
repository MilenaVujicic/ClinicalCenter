package model;

import java.util.Date;

public class Odsustvo {
	
	private Korisnik korisnik;
	private VrstaOdsustva vrstaOdsustva;
	private Date pocetakOdsustva;
	private Date zavrsetakOdsustva;
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
