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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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
	
	@Column(name = "opis", nullable = true)
	private String opis;
	
	@ManyToMany
	@JoinTable(name = "pregledi", joinColumns = @JoinColumn(name = "pregled_id", 
	referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "dijagnoza_id", 
	referencedColumnName = "id"))
	private Set<Pregled> pregledi = new HashSet<Pregled>();

	public Dijagnoza() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Dijagnoza(String sifra, String ime, String opis) {
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
		return "Dijagnoza [sifra=" + sifra + ", ime=" + ime + ", opis=" + opis + " ]";
	}
	
	
	
	
}
