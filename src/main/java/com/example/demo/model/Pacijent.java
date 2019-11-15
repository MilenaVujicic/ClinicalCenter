/*
 * author: Filip Vozarevic
 */
package com.example.demo.model;
//------------------------------------
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Pacijent {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="idKorisnik", nullable = false)
	private Long idKorisnik;
	
	@Column(name = "username", nullable = false)
	private String username;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@Column(name = "email", nullable = false)
	private String email;
	
	@Column(name = "ime", nullable = false)
	private String ime;
	
	@Column(name = "prezime", nullable = false)
	private String prezime;
	
	@Column(name = "datumRodjenja", nullable = false)
	private Date datumRodjenja;
	
	@Column(name = "visina", nullable = false)
	private int visina;
	
	@Column(name = "tezina", nullable = false)
	private int tezina;
	
	@Column(name = "dioptrija", nullable = false)
	private double dioptrija;
	
	@OneToMany(mappedBy = "pacijent", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Alergija> alergije = new HashSet<Alergija>();
	
	@OneToMany(mappedBy = "pacijent", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Operacija> operacije = new HashSet<Operacija>();
	
	@OneToMany(mappedBy = "pacijent", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Recept> recepti = new HashSet<Recept>(); 
	
	@OneToMany(mappedBy = "pacijent", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Pregled> pregledi = new HashSet<Pregled>();
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Klinika klinika;
	
	public Pacijent() {
		super();
		// TODO Auto-generated constructor stub
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



	public Date getDatumRodjenja() {
		return datumRodjenja;
	}



	public void setDatumRodjenja(Date datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}



	public int getVisina() {
		return visina;
	}

	public void setVisina(int visina) {
		this.visina = visina;
	}

	public int getTezina() {
		return tezina;
	}

	public void setTezina(int tezina) {
		this.tezina = tezina;
	}

	public double getDioptrija() {
		return dioptrija;
	}

	public void setDioptrija(double dioptrija) {
		this.dioptrija = dioptrija;
	}

	/*public Set<String> getAlergije() {
		return alergije;
	}

	public void setAlergije(Set<String> alergije) {
		this.alergije = alergije;
	}*/

	public Set<Operacija> getOperacije() {
		return operacije;
	}

	public void setOperacije(Set<Operacija> operacije) {
		this.operacije = operacije;
	}

	public Set<Recept> getRecepti() {
		return recepti;
	}

	public void setRecepti(Set<Recept> recepti) {
		this.recepti = recepti;
	}

	public Set<Pregled> getPregledi() {
		return pregledi;
	}

	public void setPregledi(Set<Pregled> pregledi) {
		this.pregledi = pregledi;
	}

	@Override
	public String toString() {
		return "Pacijent [id=" + id + ", idKorisnik=" + idKorisnik + ", username=" + username + ", password=" + password
				+ ", email=" + email + ", ime=" + ime + ", prezime=" + prezime + ", datumRodjenja=" + datumRodjenja
				+ ", visina=" + visina + ", tezina=" + tezina + ", dioptrija=" + dioptrija + ", operacije=" + operacije
				+ ", recepti=" + recepti + ", pregledi=" + pregledi + "]";
	}
	
	
	
	
}
