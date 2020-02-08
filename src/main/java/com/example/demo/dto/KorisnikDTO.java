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
	private String telefon;
	
	@NotNull
	@NotEmpty
	private int brojPrijava;
	
	
	public KorisnikDTO() {
		super();
		
	}

	public KorisnikDTO(String ime, String prezime, String email, String password, String matchingPassword, String grad, String drzava, Long jmbg,
			String adresa, Date datumRodjenja, UlogaKorisnika uloga, int brojPrijava) {
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
		this.brojPrijava = brojPrijava;
	}
	
	public KorisnikDTO(Long id, String ime, String prezime, String email, String password, String matchingPassword, String grad, String drzava, Long jmbg,
			String adresa, Date datumRodjenja, UlogaKorisnika ulogaKorisnika, String telefon, int brojPrijava) {
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
		this.brojPrijava = brojPrijava;
	}
	
	public KorisnikDTO(Korisnik k) {
		this(k.getId(), k.getIme(), k.getPrezime(),k.getEmail(), k.getPassword(), k.getPassword(), k.getGrad(),k.getDrzava(), k.getJmbg(),
				k.getAdresa(), k.getDatumRodjenja(), k.getUloga(), k.getTelefon(), k.getBrojPrijava());
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

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	
	public void setIme(String ime) {
		this.ime = ime;
	}
	
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	
	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}
	
	public void setDrzava(String drzava) {
		this.drzava = drzava;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setGrad(String grad) {
		this.grad = grad;
	}
	
	public void setJmbg(Long jmbg) {
		this.jmbg = jmbg;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setMatchingPassword(String matchingPassword) {
		this.matchingPassword = matchingPassword;
	}
	
	public void setUloga(UlogaKorisnika uloga) {
		this.uloga = uloga;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setDatumRodjenja(Date datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}

	public int getBrojPrijava() {
		return brojPrijava;
	}

	public void setBrojPrijava(int brojPrijava) {
		this.brojPrijava = brojPrijava;
	}
	
	
	
	
}
