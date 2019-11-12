package com.example.demo.dto;

import java.util.HashSet;
import java.util.Set;
import com.example.demo.model.Pregled;

public class LekDTO {


	private Long id;
	private String sifra; 
	private String ime;
	private String opis;
	private Set<Pregled> pregledi = new HashSet<Pregled>();
	
	public LekDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LekDTO(String sifra, String ime, String opis) {
		super();
		this.sifra = sifra;
		this.ime = ime;
		this.opis = opis;
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

	@Override
	public String toString() {
		return "Lek [sifra=" + sifra + ", ime=" + ime + ", opis=" + opis + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<Pregled> getPregledi() {
		return pregledi;
	}

	public void setPregledi(Set<Pregled> pregledi) {
		this.pregledi = pregledi;
	}
}
