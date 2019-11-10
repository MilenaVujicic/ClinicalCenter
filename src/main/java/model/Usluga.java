package model;


public class Usluga {
	
	private String ime;
	private String opis;
	private double cena;
	
	public Usluga() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Usluga(String ime, String opis, double cena) {
		super();
		this.ime = ime;
		this.opis = opis;
		this.cena = cena;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}

	@Override
	public String toString() {
		return "Usluga [ime=" + ime + ", opis=" + opis + ", cena=" + cena + "]";
	}
	
	
	
	
}
