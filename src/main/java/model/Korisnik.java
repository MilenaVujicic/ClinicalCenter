package model;

import java.util.Date;

enum UlogaKorisnika {
	ADMIN_CENTRA, ADMIN_KLINIKE, LEKAR, MEDICINSKA_SESTRA, PACIJENT
}

public class Korisnik {
	
	private String ime;
	private String prezime;
	private String email;
	private String password;
	private String grad;
	private String drzava;
	private int jmbg;
	private String adresa;
	private Date datumRodjenja;
	private UlogaKorisnika uloga;		
	
	public Korisnik() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Korisnik(String ime, String prezime, String email, String password, String grad, String drzava, int jmbg,
			String adresa, Date datumRodjenja, UlogaKorisnika uloga) {
		super();
		this.ime = ime;
		this.prezime = prezime;
		this.email = email;
		this.password = password;
		this.grad = grad;
		this.drzava = drzava;
		this.jmbg = jmbg;
		this.adresa = adresa;
		this.datumRodjenja = datumRodjenja;
		this.uloga = uloga;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGrad() {
		return grad;
	}

	public void setGrad(String grad) {
		this.grad = grad;
	}

	public String getDrzava() {
		return drzava;
	}

	public void setDrzava(String drzava) {
		this.drzava = drzava;
	}

	public int getJmbg() {
		return jmbg;
	}

	public void setJmbg(int jmbg) {
		this.jmbg = jmbg;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public Date getDatumRodjenja() {
		return datumRodjenja;
	}

	public void setDatumRodjenja(Date datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}

	public UlogaKorisnika getUloga() {
		return uloga;
	}

	public void setUloga(UlogaKorisnika uloga) {
		this.uloga = uloga;
	}

	@Override
	public String toString() {
		return "Korisnik [ime=" + ime + ", prezime=" + prezime + ", email=" + email + ", password=" + password
				+ ", grad=" + grad + ", drzava=" + drzava + ", jmbg=" + jmbg + ", adresa=" + adresa + ", datumRodjenja="
				+ datumRodjenja + ", uloga=" + uloga + "]";
	}
	
	
	
}
