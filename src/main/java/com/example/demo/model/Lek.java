/*
 * author: Filip Vozarevic
 */
package com.example.demo.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Lek {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "sifra", nullable = false)
	private String sifra; 
	
	@Column(name = "ime", nullable = false)
	private String ime;
	
	@Column(name = "opis", nullable = true)
	private String opis;
	
	@ManyToMany(mappedBy = "pregledi")
	private Set<Pregled> pregledi = new HashSet<Pregled>();
	
	public Lek() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Lek(String sifra, String ime, String opis) {
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
	
}
