package model;

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
import javax.persistence.OneToMany;

@Entity
public class Sala  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToMany(mappedBy = "sala", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Klinika klinika;
	
	@Column(name = "ime")
	private String ime;
	
	@Column(name = "opis")
	private String opis;
	
	@OneToMany
	@JoinTable(name = "pregledi", joinColumns = @JoinColumn(name = "sala_id", 
	referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "pregled_id", 
	referencedColumnName = "id"))
	private Set<Pregled> pregledi;
	
	@OneToMany
	@JoinTable(name = "operacije", joinColumns = @JoinColumn(name = "sala_id", 
	referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "operacije_id", 
	referencedColumnName = "id"))
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
