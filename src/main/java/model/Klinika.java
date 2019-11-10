package model;

import java.util.ArrayList;
import java.util.Set;

public class Klinika {

	private String ime;
	private String adresa;
	private String opis;
	private Set<Termin> slobodniTermini;
	private Set<Doktor> doktori;
	private Set<Sala> sale;
	private ArrayList<Usluga> usluge;
	private ArrayList<Integer> ocene;
	private double prosecnaOcena;
	
	public Klinika() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Klinika(String ime, String adresa, String opis, Set<Termin> slobodniTermini, Set<Doktor> doktori,
			Set<Sala> sale, ArrayList<Usluga> usluge, ArrayList<Integer> ocene, double prosecnaOcena) {
		super();
		this.ime = ime;
		this.adresa = adresa;
		this.opis = opis;
		this.slobodniTermini = slobodniTermini;
		this.doktori = doktori;
		this.sale = sale;
		this.usluge = usluge;
		this.ocene = ocene;
		this.prosecnaOcena = prosecnaOcena;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public Set<Termin> getSlobodniTermini() {
		return slobodniTermini;
	}

	public void setSlobodniTermini(Set<Termin> slobodniTermini) {
		this.slobodniTermini = slobodniTermini;
	}

	public Set<Doktor> getDoktori() {
		return doktori;
	}

	public void setDoktori(Set<Doktor> doktori) {
		this.doktori = doktori;
	}

	public Set<Sala> getSale() {
		return sale;
	}

	public void setSale(Set<Sala> sale) {
		this.sale = sale;
	}

	public ArrayList<Usluga> getUsluge() {
		return usluge;
	}

	public void setCenovnik(ArrayList<Usluga> usluge) {
		this.usluge = usluge;
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

	@Override
	public String toString() {
		return "Klinika [ime=" + ime + ", adresa=" + adresa + ", opis=" + opis + ", slobodniTermini=" + slobodniTermini
				+ ", doktori=" + doktori + ", sale=" + sale + ", usluge=" + usluge + ", ocene=" + ocene
				+ ", prosecnaOcena=" + prosecnaOcena + "]";
	}
	
	
	
	
	
}
