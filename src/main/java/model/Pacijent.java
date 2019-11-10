package model;

import java.util.ArrayList;
import java.util.Set;

public class Pacijent {

	private int visina;
	private int tezina;
	private double dioptrija;
	private Set<String> alergije;
	private ArrayList<Operacija> operacije;
	private ArrayList<Recept> recepti; 
	private ArrayList<Pregled> pregledi;
	
	public Pacijent() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Pacijent(int visina, int tezina, double dioptrija, Set<String> alergije, ArrayList<Operacija> operacije,
			ArrayList<Recept> recepti, ArrayList<Pregled> pregledi) {
		super();
		this.visina = visina;
		this.tezina = tezina;
		this.dioptrija = dioptrija;
		this.alergije = alergije;
		this.operacije = operacije;
		this.recepti = recepti;
		this.pregledi = pregledi;
	}

	public int getVisina() {
		return visina;
	}

	public void setVisina(int visina) {
		this.visina = visina;
	}

	public int getTezina() {
		return tezina;
	}

	public void setTezina(int tezina) {
		this.tezina = tezina;
	}

	public double getDioptrija() {
		return dioptrija;
	}

	public void setDioptrija(double dioptrija) {
		this.dioptrija = dioptrija;
	}

	public Set<String> getAlergije() {
		return alergije;
	}

	public void setAlergije(Set<String> alergije) {
		this.alergije = alergije;
	}

	public ArrayList<Operacija> getOperacije() {
		return operacije;
	}

	public void setOperacije(ArrayList<Operacija> operacije) {
		this.operacije = operacije;
	}

	public ArrayList<Recept> getRecepti() {
		return recepti;
	}

	public void setRecepti(ArrayList<Recept> recepti) {
		this.recepti = recepti;
	}

	public ArrayList<Pregled> getPregledi() {
		return pregledi;
	}

	public void setPregledi(ArrayList<Pregled> pregledi) {
		this.pregledi = pregledi;
	}

	@Override
	public String toString() {
		return "Pacijent [visina=" + visina + ", tezina=" + tezina + ", dioptrija=" + dioptrija + ", alergije="
				+ alergije + ", operacije=" + operacije + ", recepti=" + recepti + ", pregledi=" + pregledi + "]";
	}
	
	
	
	
}
