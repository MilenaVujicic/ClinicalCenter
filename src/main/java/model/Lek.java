package model;

public class Lek {
	
	private String sifra; 
	private String ime;
	private String opis;
	private Korisnik dodatoOdStrane;
	
	public Lek() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Lek(String sifra, String ime, String opis, Korisnik dodatoOdStrane) {
		super();
		this.sifra = sifra;
		this.ime = ime;
		this.opis = opis;
		this.dodatoOdStrane = dodatoOdStrane;
	}

	public String getSifra() {
		return sifra;
	}

	public void setSifra(String sifra) {
		this.sifra = sifra;
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

	public Korisnik getDodatoOdStrane() {
		return dodatoOdStrane;
	}

	public void setDodatoOdStrane(Korisnik dodatoOdStrane) {
		this.dodatoOdStrane = dodatoOdStrane;
	}

	@Override
	public String toString() {
		return "Lek [sifra=" + sifra + ", ime=" + ime + ", opis=" + opis + ", dodatoOdStrane=" + dodatoOdStrane + "]";
	}
	
	
	
	
}
