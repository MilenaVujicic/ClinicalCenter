package model;

import java.util.Set;

public class Sala  {
	
	private Klinika klinika;
	private String ime;
	private String opis;
	private Set<Pregled> pregledi;
	private Set<Operacija> operacije;
	
	public Sala() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Sala(Klinika klinika, String ime, String opis, Set<Pregled> pregledi, Set<Operacija> operacije) {
		super();
		this.klinika = klinika;
		this.ime = ime;
		this.opis = opis;
		this.pregledi = pregledi;
		this.operacije = operacije;
	}


	public Klinika getKlinika() {
		return klinika;
	}

	public void setKlinika(Klinika klinika) {
		this.klinika = klinika;
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

	

	public Set<Pregled> getPregledi() {
		return pregledi;
	}

	public void setPregledi(Set<Pregled> pregledi) {
		this.pregledi = pregledi;
	}

	public Set<Operacija> getOperacije() {
		return operacije;
	}

	public void setOperacije(Set<Operacija> operacije) {
		this.operacije = operacije;
	}

	@Override
	public String toString() {
		return "Sala [klinika=" + klinika + ", ime=" + ime + ", opis=" + opis + ", pregledi=" + pregledi
				+ ", operacije=" + operacije + "]";
	}
	
	
	
	
}
