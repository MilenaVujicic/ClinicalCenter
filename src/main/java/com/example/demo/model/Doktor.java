/*
 * author: Andrea Mendrei
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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Doktor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="korisnik", nullable = false)
	private Long idKorisnik;
	
	@Column(name = "username", nullable = false)
	private String username;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@Column(name = "ime", nullable = false)
	private String ime;
	
	@Column(name = "prezime", nullable = false)
	private String prezime;
	
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
	
	@Column(name = "specijalizacija", nullable = true)
	private String specijalizacija;
	
	@Column(name = "prosecnaOcena", nullable = false)
	private double prosenaOcena;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Klinika klinika;
	
	@ManyToMany(mappedBy = "doktori")
	private Set<Operacija> operacije = new HashSet<Operacija>();
	
	@OneToMany(mappedBy = "doktor", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Pregled> pregledi = new HashSet<Pregled>();

	public Doktor() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Doktor(Long id, Long idKorisnik, String username, String password, String ime, String prezime, String grad,
			String drzava, Long jmbg, String adresa, Date datumRodjenja, String specijalizacija, double prosenaOcena,
			Klinika klinika, Set<Operacija> operacije, Set<Pregled> pregledi) {
		super();
		this.id = id;
		this.idKorisnik = idKorisnik;
		this.username = username;
		this.password = password;
		this.ime = ime;
		this.prezime = prezime;
		this.grad = grad;
		this.drzava = drzava;
		this.jmbg = jmbg;
		this.adresa = adresa;
		this.datumRodjenja = datumRodjenja;
		this.specijalizacija = specijalizacija;
		this.prosenaOcena = prosenaOcena;
		this.klinika = klinika;
		this.operacije = operacije;
		this.pregledi = pregledi;
	}

	public Long getIdKorisnik() {
		return idKorisnik;
	}

	public void setIdKorisnik(Long idKorisnik) {
		this.idKorisnik = idKorisnik;
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

	public String getSpecijalizacija() {
		return specijalizacija;
	}

	public void setSpecijalizacija(String specijalizacija) {
		this.specijalizacija = specijalizacija;
	}

	public Set<Operacija> getOperacije() {
		return operacije;
	}

	public void setOperacije(Set<Operacija> operacije) {
		this.operacije = operacije;
	}

	public Set<Pregled> getPregledi() {
		return pregledi;
	}

	public void setPregledi(Set<Pregled> pregledi) {
		this.pregledi = pregledi;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public double getProsenaOcena() {
		return prosenaOcena;
	}

	public void setProsenaOcena(double prosenaOcena) {
		this.prosenaOcena = prosenaOcena;
	}

	public Klinika getKlinika() {
		return klinika;
	}

	public void setKlinika(Klinika klinika) {
		this.klinika = klinika;
	}

	@Override
	public String toString() {
		return "Doktor [id=" + id + ", idKorisnik=" + idKorisnik + ", username=" + username + ", password=" + password
				+ ", ime=" + ime + ", prezime=" + prezime + ", grad=" + grad + ", drzava=" + drzava + ", jmbg=" + jmbg
				+ ", adresa=" + adresa + ", datumRodjenja=" + datumRodjenja + ", specijalizacija=" + specijalizacija
				+ ", prosenaOcena=" + prosenaOcena + ", klinika=" + klinika + ", operacije=" + operacije + ", pregledi="
				+ pregledi + "]";
	}
	
	
}
