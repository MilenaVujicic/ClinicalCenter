/*
 * author: Filip Vozarevic
 */
package com.example.demo.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Korisnik {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "ime", nullable = false)
	private String ime;
	
	@Column(name = "prezime", nullable = false)
	private String prezime;
	
	@Column(name = "email", nullable = false)
	private String email;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@Column(name = "grad")
	private String grad;
	
	@Column(name = "drzava")
	private String drzava;
	
	@Column(name = "jmbg", nullable = false)
	private Long jmbg;
	
	@Column(name = "adresa")
	private String adresa;
	
	@Column(name = "telefon")
	private String telefon;
	
	@Column(name = "datumRodjenja", nullable = true) 
	private Date datumRodjenja;
	
	@Column(name = "uloga", nullable = true) 
	private UlogaKorisnika uloga;		
	
	@JsonIgnore
	@OneToMany(mappedBy = "korisnik", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Odsustvo> odsustva = new HashSet<Odsustvo>();
	
	@Column(name = "aktivan", nullable = false)
	private boolean aktivan = false;
	
	@Column(name = "aktiviran", nullable = false)
	private boolean aktiviran = false;
	
	@Column(name="broj_prijava", nullable = false)
	private int brojPrijava = 1;
	
	public Korisnik() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Korisnik(String ime, String prezime, String email, String password, String grad, String drzava, Long jmbg,
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

	public Korisnik(Long id, String ime, String prezime, String email, String username, String password, String grad,
			String drzava, Long jmbg, String adresa, String telefon, Date datumRodjenja, UlogaKorisnika uloga,
			Set<Odsustvo> odsustva, boolean aktivan, boolean aktiviran) {
		super();
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		this.email = email;
		this.password = password;
		this.grad = grad;
		this.drzava = drzava;
		this.jmbg = jmbg;
		this.adresa = adresa;
		this.telefon = telefon;
		this.datumRodjenja = datumRodjenja;
		this.uloga = uloga;
		this.odsustva = odsustva;
		this.aktivan = aktivan;
		this.aktiviran = aktiviran;
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

	public Long getJmbg() {
		return jmbg;
	}

	public void setJmbg(Long jmbg) {
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
	
	public boolean isAktivan() {
		return aktivan;
	}

	public void setAktivan(boolean aktivan) {
		this.aktivan = aktivan;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<Odsustvo> getOdsustva() {
		return odsustva;
	}

	public void setOdsustva(Set<Odsustvo> odsustva) {
		this.odsustva = odsustva;
	}

	public boolean isAktiviran() {
		return aktiviran;
	}

	public void setAktiviran(boolean aktiviran) {
		this.aktiviran = aktiviran;
	}

	public int getBrojPrijava() {
		return brojPrijava;
	}

	public void setBrojPrijava(int brojPrijava) {
		this.brojPrijava = brojPrijava;
	}
	

	@Override
	public String toString() {
		return "Korisnik [id=" + id + ", ime=" + ime + ", prezime=" + prezime + ", email=" + email + ", password="
				+ password + ", grad=" + grad + ", drzava=" + drzava + ", jmbg=" + jmbg + ", adresa=" + adresa
				+ ", telefon=" + telefon + ", datumRodjenja=" + datumRodjenja + ", uloga=" + uloga + ", odsustva="
				+ odsustva + ", aktivan=" + aktivan + ", aktiviran=" + aktiviran + ", brojPrijava=" + brojPrijava + "]";
	}
	
	
	
}
