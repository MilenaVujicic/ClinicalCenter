package com.example.demo.dto;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.example.demo.model.Korisnik;
import com.example.demo.model.Uloga;
import com.example.demo.validation.PasswordMatches;
import com.example.demo.validation.ValidEmail;
import com.sun.istack.NotNull;



public class KorisnikDTO {

	@NotNull
    @NotEmpty
	private Long id;
	
	@NotNull
    @NotEmpty
	private String ime;
	
	@NotNull
    @NotEmpty
	private String prezime;

	@NotNull
    @NotEmpty
	private String email;
	
	@NotNull
    @NotEmpty
	private String password;
	
	@NotNull
	@NotEmpty
    private String matchingPassword;
	
	@NotNull
    @NotEmpty
	private String grad;
	
	@NotNull
    @NotEmpty
	private String drzava;
	
	@NotNull
    @NotEmpty
	private Long jmbg;
	
	@NotNull
    @NotEmpty
	private String adresa;
	
	@NotNull
    @NotEmpty
	private String telefon;
	

	
	
	
	public KorisnikDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public KorisnikDTO(String ime, String prezime, String email, String adresa, String grad, String drzava,
			String telefon, Long jmbg, String password, String matchingPassword) {
		super();
		this.ime = ime;
		this.prezime = prezime;
		this.email = email;
		this.password = password;
		this.matchingPassword = matchingPassword;
		this.grad = grad;
		this.drzava = drzava;
		this.jmbg = jmbg;
		this.adresa = adresa;
		this.telefon = telefon;
	}
	
	public KorisnikDTO(Long id, String ime, String prezime, String email, String password, String matchingPassword, String grad, String drzava, Long jmbg,
			String adresa) {
		super();
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		this.email = email;
		this.password = password;
		this.matchingPassword = matchingPassword;
		this.grad = grad;
		this.drzava = drzava;
		this.jmbg = jmbg;
		this.adresa = adresa;
	}
	

	public KorisnikDTO(Korisnik k) {
		this(k.getId(), k.getIme(), k.getPrezime(),k.getEmail(), k.getPassword(), k.getPassword(), k.getGrad(),k.getDrzava(), k.getJmbg(),
				k.getAdresa());
	}

	public Long getId() {
		return id;
	}

	public String getIme() {
		return ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}
	
	public String getMatchingPassword() {
		return matchingPassword;
	}
	
	public String getGrad() {
		return grad;
	}
	
	public String getDrzava() {
		return drzava;
	}
	
	public Long getJmbg() {
		return jmbg;
	}
	
	public String getAdresa() {
		return adresa;
	}
	
	public void setGrad(String grad) {
		this.grad = grad;
	}
	
	public String getTelefon() {
		return telefon;
	}
	
	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}
	
}
