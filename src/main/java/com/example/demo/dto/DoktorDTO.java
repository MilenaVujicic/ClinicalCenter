package com.example.demo.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.example.demo.model.Odsustvo;
import com.example.demo.model.Operacija;
import com.example.demo.model.Pregled;
import com.example.demo.model.Recept;

public class DoktorDTO {
	
	private Long id;
	private Set<Pregled> preglediZakazani = new HashSet<Pregled>();
	private Set<Pregled> preglediZavrseni = new HashSet<Pregled>();
	private Set<Operacija> operacijeZakazane = new HashSet<Operacija>();
	private Set<Operacija> operacijeZavrsene = new HashSet<Operacija>();
	private Set<Odsustvo> odsustva = new HashSet<Odsustvo>();
	private ArrayList<Integer> ocene = new ArrayList<Integer>();
	private double prosecnaOcena = 0;
	private Set<Recept> ispisaniRecepti = new HashSet<Recept>();
	
	public DoktorDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public DoktorDTO(Long id, Set<Pregled> preglediZakazani, Set<Pregled> preglediZavrseni,
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
		return "DoktorDTO [id=" + id + ", preglediZakazani=" + preglediZakazani + ", preglediZavrseni="
				+ preglediZavrseni + ", operacijeZakazane=" + operacijeZakazane + ", operacijeZavrsene="
				+ operacijeZavrsene + ", odsustva=" + odsustva + ", ocene=" + ocene + ", prosecnaOcena=" + prosecnaOcena
				+ ", ispisaniRecepti=" + ispisaniRecepti + "]";
	}
	
	
}
