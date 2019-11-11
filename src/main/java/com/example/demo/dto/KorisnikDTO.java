package com.example.demo.dto;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.example.demo.validation.PasswordMatches;
import com.example.demo.validation.ValidEmail;
import com.sun.istack.NotNull;

enum UlogaKorisnika {
	ADMIN_CENTRA, ADMIN_KLINIKE, LEKAR, MEDICINSKA_SESTRA, PACIJENT
}

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
	
	public KorisnikDTO() {
		
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
	
}
