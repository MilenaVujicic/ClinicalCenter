/*
 * author: Andrea Mendrei
 */
package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Doktor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToMany
	@JoinTable(name = "pregledi", joinColumns = @JoinColumn(name = "doktor_id", 
			referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "pregled_id", 
			referencedColumnName = "id"))
	private Set<Pregled> preglediZakazani = new HashSet<Pregled>(); 
	
	@ManyToMany
	@JoinTable(name = "pregledi", joinColumns = @JoinColumn(name = "doktor_id", 
			referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "pregled_id", 
			referencedColumnName = "id"))
	private Set<Pregled> preglediZavrseni = new HashSet<Pregled>();	
	
	@ManyToMany
	@JoinTable(name = "operacije", joinColumns = @JoinColumn(name = "doktor_id", 
			referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "operacija_id", 
			referencedColumnName = "id"))
	private Set<Operacija> operacijeZakazane = new HashSet<Operacija>();
	
	@ManyToMany
	@JoinTable(name = "operacije", joinColumns = @JoinColumn(name = "doktor_id", 
			referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "operacija_id", 
			referencedColumnName = "id"))
	private Set<Operacija> operacijeZavrsene = new HashSet<Operacija>();
	
	@OneToMany(mappedBy = "doktor", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Odsustvo> odsustva = new HashSet<Odsustvo>();
	
	@OneToMany(mappedBy = "doktor", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private ArrayList<Integer> ocene = new ArrayList<Integer>();
	
	@Column(name = "prosecnaOcena", nullable = false)
	private double prosecnaOcena = 0;
	
	@OneToMany(mappedBy = "doktor", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Recept> ispisaniRecepti = new HashSet<Recept>();
	
	public Doktor() {
		super();
	}

	public Doktor(Long id, Set<Pregled> preglediZakazani, Set<Pregled> preglediZavrseni,
			Set<Operacija> operacijeZakazane, Set<Operacija> operacijeZavrsene, Set<Odsustvo> odsustva,
			ArrayList<Integer> ocene, double prosecnaOcena, Set<Recept> ispisaniRecepti) {
		super();
		this.id = id;
		this.preglediZakazani = preglediZakazani;
		this.preglediZavrseni = preglediZavrseni;
		this.operacijeZakazane = operacijeZakazane;
		this.operacijeZavrsene = operacijeZavrsene;
		this.odsustva = odsustva;
		this.ocene = ocene;
		this.prosecnaOcena = prosecnaOcena;
		this.ispisaniRecepti = ispisaniRecepti;
	}

	public Doktor(Doktor d) {
		this.id = d.id;
		this.preglediZakazani = d.preglediZakazani;
		this.preglediZavrseni = d.preglediZavrseni;
		this.operacijeZakazane = d.operacijeZakazane;
		this.operacijeZavrsene = d.operacijeZavrsene;
		this.odsustva = d.odsustva;
		this.ocene = d.ocene;
		this.prosecnaOcena = d.prosecnaOcena;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public Set<Pregled> getPreglediZakazani() {
		return preglediZakazani;
	}

	public void setPreglediZakazani(Set<Pregled> preglediZakazani) {
		this.preglediZakazani = preglediZakazani;
	}

	public Set<Pregled> getPreglediZavrseni() {
		return preglediZavrseni;
	}

	public void setPreglediZavrseni(Set<Pregled> preglediZavrseni) {
		this.preglediZavrseni = preglediZavrseni;
	}

	public Set<Operacija> getOperacijeZakazane() {
		return operacijeZakazane;
	}

	public void setOperacijeZakazane(Set<Operacija> operacijeZakazane) {
		this.operacijeZakazane = operacijeZakazane;
	}

	public Set<Operacija> getOperacijeZavrsene() {
		return operacijeZavrsene;
	}

	public void setOperacijeZavrsene(Set<Operacija> operacijeZavrsene) {
		this.operacijeZavrsene = operacijeZavrsene;
	}

	public Set<Odsustvo> getOdsustva() {
		return odsustva;
	}

	public void setOdsustva(Set<Odsustvo> odsustva) {
		this.odsustva = odsustva;
	}

	public ArrayList<Integer> getOcene() {
		return ocene;
	}

	public void setOcene(ArrayList<Integer> ocene) {
		this.ocene = ocene;
	}

	public double getProsecnaOcena() {
		return prosecnaOcena;
	}

	public void setProsecnaOcena(double prosecnaOcena) {
		this.prosecnaOcena = prosecnaOcena;
	}

	public Set<Recept> getIspisaniRecepti() {
		return ispisaniRecepti;
	}

	public void setIspisaniRecepti(Set<Recept> ispisaniRecepti) {
		this.ispisaniRecepti = ispisaniRecepti;
	}

	@Override
	public String toString() {
		return "Doktor [id=" + id + ", preglediZakazani=" + preglediZakazani + ", preglediZavrseni=" + preglediZavrseni
				+ ", operacijeZakazane=" + operacijeZakazane + ", operacijeZavrsene=" + operacijeZavrsene
				+ ", odsustva=" + odsustva + ", ocene=" + ocene + ", prosecnaOcena=" + prosecnaOcena
				+ ", ispisaniRecepti=" + ispisaniRecepti + "]";
	}

	

	
}
