/*
 * author: Andrea Mendrei
 */
package model;

import java.time.LocalDateTime;
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
import javax.persistence.ManyToOne;

enum StatusPregleda {
	ZAKAZAN, ZAVRSEN
}
@Entity
public class Pregled {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "naziv", nullable = false)
	private String naziv;
	
	@Column(name = "anamneza")
	private String anamneza;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Pacijent pacijent;
	
	@Column(name = "datum", nullable = false)
	private LocalDateTime datumIVremePregleda;
	
	@Column(name = "tipPregleda")
	private String tipPregleda;
	
	@Column(name = "duzina")
	private int duzina;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Sala sala;
	
	@ManyToMany(mappedBy = "pregledi")
	private Set<Doktor> doktori = new HashSet<Doktor>();
	
	@Column(name = "cena")
	private double cena;
	
	@ManyToMany
	@JoinTable(name = "dijagnoze", joinColumns = @JoinColumn(name = "dijagnoza_id", 
			referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "pregled_id", 
			referencedColumnName = "id"))
	private Set<Dijagnoza> dijagnoze = new HashSet<Dijagnoza>();
	
	@ManyToMany
	@JoinTable(name = "lekovi", joinColumns = @JoinColumn(name = "lek_id", 
			referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "pregled_id", 
			referencedColumnName = "id"))
	private Set<Lek> lekovi = new HashSet<Lek>();
	
	@Column(name = "status")
	private StatusPregleda status;
	
	public Pregled() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Pregled(Long id, String naziv, String anamneza, Pacijent pacijent, LocalDateTime datumIVremePregleda,
			String tipPregleda, int duzina, Sala sala, Set<Doktor> doktori, double cena, Set<Dijagnoza> dijagnoze,
			Set<Lek> lekovi, StatusPregleda status) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.anamneza = anamneza;
		this.pacijent = pacijent;
		this.datumIVremePregleda = datumIVremePregleda;
		this.tipPregleda = tipPregleda;
		this.duzina = duzina;
		this.sala = sala;
		this.doktori = doktori;
		this.cena = cena;
		this.dijagnoze = dijagnoze;
		this.lekovi = lekovi;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getAnamneza() {
		return anamneza;
	}

	public void setAnamneza(String anamneza) {
		this.anamneza = anamneza;
	}

	public Pacijent getPacijent() {
		return pacijent;
	}

	public void setPacijent(Pacijent pacijent) {
		this.pacijent = pacijent;
	}

	public LocalDateTime getDatumIVremePregleda() {
		return datumIVremePregleda;
	}

	public void setDatumIVremePregleda(LocalDateTime datumIVremePregleda) {
		this.datumIVremePregleda = datumIVremePregleda;
	}

	public String getTipPregleda() {
		return tipPregleda;
	}

	public void setTipPregleda(String tipPregleda) {
		this.tipPregleda = tipPregleda;
	}

	public int getDuzina() {
		return duzina;
	}

	public void setDuzina(int duzina) {
		this.duzina = duzina;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	public Set<Doktor> getDoktori() {
		return doktori;
	}

	public void setDoktori(Set<Doktor> doktori) {
		this.doktori = doktori;
	}

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}

	public Set<Dijagnoza> getDijagnoze() {
		return dijagnoze;
	}

	public void setDijagnoze(Set<Dijagnoza> dijagnoze) {
		this.dijagnoze = dijagnoze;
	}

	public Set<Lek> getLekovi() {
		return lekovi;
	}

	public void setLekovi(Set<Lek> lekovi) {
		this.lekovi = lekovi;
	}

	public StatusPregleda getStatus() {
		return status;
	}

	public void setStatus(StatusPregleda status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Pregled [id=" + id + ", naziv=" + naziv + ", anamneza=" + anamneza + ", pacijent=" + pacijent
				+ ", datumIVremePregleda=" + datumIVremePregleda + ", tipPregleda=" + tipPregleda + ", duzina=" + duzina
				+ ", sala=" + sala + ", doktori=" + doktori + ", cena=" + cena + ", dijagnoze=" + dijagnoze
				+ ", lekovi=" + lekovi + ", status=" + status + "]";
	}
	
	
	
	
	
}