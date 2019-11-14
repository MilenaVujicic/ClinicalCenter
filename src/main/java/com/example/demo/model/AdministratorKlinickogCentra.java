package com.example.demo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AdministratorKlinickogCentra {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="idKorisnik", nullable = false)
	private Long idKorisnik;
	
	@Column(name="ime", nullable = false)
	private String ime;
	
	@Column(name="prezime", nullable = false)
	private String prezime;
	
	@Column(name="username", nullable = false)
	private String username;
	
	@Column(name="password", nullable = false)
	private String password;
	
	@Column(name="email", nullable = false)
	private String email;
	
	@Column(name = "grad")
	private String grad;
	
	@Column(name = "drzava")
	private String drzava;
	
	@Column(name = "jmbg", nullable = false)
	private Long jmbg;
	
	@Column(name = "adresa")
	private String adresa;
	
	@Column(name = "datumRodjenja", nullable = true) 
	private Date datumRodjenja;
	
	@Column(name="telefon")
	private int telefon;

	public AdministratorKlinickogCentra() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AdministratorKlinickogCentra(Long id, Long idKorisnik, String ime, String prezime, String username,
			String password, String email, String grad, String drzava, Long jmbg, String adresa, Date datumRodjenja,
			int telefon) {
		super();
		this.id = id;
		this.idKorisnik = idKorisnik;
		this.ime = ime;
		this.prezime = prezime;
		this.username = username;
		this.password = password;
		this.email = email;
		this.grad = grad;
		this.drzava = drzava;
		this.jmbg = jmbg;
		this.adresa = adresa;
		this.datumRodjenja = datumRodjenja;
		this.telefon = telefon;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdKorisnik() {
		return idKorisnik;
	}

	public void setIdKorisnik(Long idKorisnik) {
		this.idKorisnik = idKorisnik;
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

	public Long getJmbg() {
		return jmbg;
	}

	public void setJmbg(Long jmbg) {
		this.jmbg = jmbg;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getGrad() {
		return grad;
	}

	public void setGrad(String grad) {
		this.grad = grad;
	}

	public int getTelefon() {
		return telefon;
	}

	public void setTelefon(int telefon) {
		this.telefon = telefon;
	}

	public String getDrzava() {
		return drzava;
	}

	public void setDrzava(String drzava) {
		this.drzava = drzava;
	}

	public Date getDatumRodjenja() {
		return datumRodjenja;
	}

	public void setDatumRodjenja(Date datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}

	@Override
	public String toString() {
		return "AdministratorKlinickogCentra [id=" + id + ", idKorisnik=" + idKorisnik + ", ime=" + ime + ", prezime="
				+ prezime + ", username=" + username + ", password=" + password + ", email=" + email + ", grad=" + grad
				+ ", drzava=" + drzava + ", jmbg=" + jmbg + ", adresa=" + adresa + ", datumRodjenja=" + datumRodjenja
				+ ", telefon=" + telefon + "]";
	} 
	
	
}
