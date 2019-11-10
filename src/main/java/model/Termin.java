package model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Termin {

	@Column(name = "datum")
	private Date datum;
	@Column(name = "slobodan")
	private boolean slobodan;
	
	public Termin() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Termin(Date datum, boolean slobodan) {
		super();
		this.datum = datum;
		this.slobodan = slobodan;
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public boolean isSlobodan() {
		return slobodan;
	}

	public void setSlobodan(boolean slobodan) {
		this.slobodan = slobodan;
	}

	@Override
	public String toString() {
		return "Termin [datum=" + datum + ", slobodan=" + slobodan + "]";
	}
	
	
	
}
