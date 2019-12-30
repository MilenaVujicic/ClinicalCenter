package com.example.demo.dto;

import java.util.Date;

import javax.validation.constraints.NotEmpty;

import com.example.demo.model.Korisnik;
import com.example.demo.model.UlogaKorisnika;
import com.example.demo.validation.PasswordMatches;
import com.example.demo.validation.ValidEmail;
import com.sun.istack.NotNull;


@PasswordMatches
public class KorisnikDTO {

	@NotNull
    @NotEmpty
	private Long id;
	
	@NotNull
	@NotEmpty
	private String username;
	
	@NotNull
    @NotEmpty
	private String ime;
	
	@NotNull
    @NotEmpty
	private String prezime;
	
	@ValidEmail
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
	private Date datumRodjenja;
	
	@NotNull
    @NotEmpty
	private UlogaKorisnika uloga;	
	
	@NotNull
    @NotEmpty
	private int telefon;
	
	
	
	public KorisnikDTO() {
		super();
		
	}

	public KorisnikDTO(String ime, String prezime, String email, String password, String matchingPassword, String grad, String drzava, Long jmbg,
			String adresa, Date datumRodjenja, UlogaKorisnika uloga) {
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
		this.datumRodjenja = datumRodjenja;
		this.uloga = uloga;
	}
	
	public KorisnikDTO(Long id, String ime, String prezime, String email, String password, String matchingPassword, String grad, String drzava, Long jmbg,
			String adresa, Date datumRodjenja, UlogaKorisnika ulogaKorisnika, int telefon) {
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
		this.datumRodjenja = datumRodjenja;
		this.uloga = ulogaKorisnika;
		this.telefon = telefon;
	}
	

	public KorisnikDTO(Long id, String username, String ime, String prezime, String email, String password, String matchingPassword, String grad, String drzava, Long jmbg,
			String adresa, Date datumRodjenja, UlogaKorisnika ulogaKorisnika, int telefon) {
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
		this.datumRodjenja = datumRodjenja;
		this.uloga = ulogaKorisnika;
		this.telefon = telefon;
		this.username = username;
	}

	public KorisnikDTO(Korisnik k) {
		this(k.getId(), k.getUsername(), k.getIme(), k.getPrezime(),k.getEmail(), k.getPassword(), k.getPassword(), k.getGrad(),k.getDrzava(), k.getJmbg(),
				k.getAdresa(), k.getDatumRodjenja(), k.getUloga(), k.getTelefon());
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
	
	public Date getDatumRodjenja() {
		return datumRodjenja;
	}
	
	public UlogaKorisnika getUloga() {
		return uloga;
	}

	public int getTelefon() {
		return telefon;
	}

	public void setTelefon(int telefon) {
		this.telefon = telefon;
	}

	public String getUsername() {
		return username;
	}
	
	
	
	
}
