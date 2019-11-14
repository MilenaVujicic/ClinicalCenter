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
public class Dijagnoza {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "sifra", nullable = false)
	private String sifra; 
	
	@Column(name = "ime", nullable = false)
	private String ime;
	
	@Column(name = "opis")
	private String opis;
	
	@ManyToMany(mappedBy = "dijagnoze")
	private Set<Pregled> pregledi = new HashSet<Pregled>();
	
	public Dijagnoza() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Dijagnoza(Long id, String sifra, String ime, String opis, Set<Pregled> pregledi) {
		super();
		this.id = id;
		this.sifra = sifra;
		this.ime = ime;
		this.opis = opis;
		this.pregledi = pregledi;
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

	@Override
	public String toString() {
		return "Dijagnoza [id=" + id + ", sifra=" + sifra + ", ime=" + ime + ", opis=" + opis + ", pregledi=" + pregledi
				+ "]";
	}

	
	
	
	
}
