package com.example.demo.model;

import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Zahtev {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "doktorID")
	private Long doktorID;
	
	@Column(name = "datum")
	private String datum;
	
	@Column(name = "vreme")
	private String vreme;
	
	@Column(name = "salaID")
	private Long salaID;
	
	@Column(name = "obradjen")
	private boolean obradjen = false;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private AdministratorKlinike administrator_klinike;
	
	private Calendar datumIVreme = Calendar.getInstance();
	
	public Zahtev() {

	}


	public Zahtev(Long doktorID, String datum, String vreme, Long salaID) {
		super();
		this.doktorID = doktorID;
		this.datum = datum;
		this.vreme = vreme;
		this.salaID = salaID;
	}

	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public Long getDoktorID() {
		return doktorID;
	}



	public void setDoktorID(Long doktorID) {
		this.doktorID = doktorID;
	}



	public Long getSalaID() {
		return salaID;
	}



	public void setSalaID(Long salaID) {
		this.salaID = salaID;
	}



	public String getDatum() {
		return datum;
	}
	public void setDatum(String datum) {
		this.datum = datum;
	}
	public String getVreme() {
		return vreme;
	}
	public void setVreme(String vreme) {
		this.vreme = vreme;
	}

	

	public AdministratorKlinike getAdministrator_klinike() {
		return administrator_klinike;
	}


	public void setAdministrator_klinike(AdministratorKlinike administrator_klinike) {
		this.administrator_klinike = administrator_klinike;
	}

	

	public boolean isObradjen() {
		return obradjen;
	}


	public void setObradjen(boolean obradjen) {
		this.obradjen = obradjen;
	}

	public void makeDate() {
		String[] partsD = this.datum.split("-");
		String[] partsT = this.vreme.split(":");
		
		int dayI = Integer.parseInt(partsD[2]);
		int monthI = Integer.parseInt(partsD[1]);
		int yearI = Integer.parseInt(partsD[0]);
		
		int hourI = Integer.parseInt(partsT[0]);
		int minuteI = Integer.parseInt(partsT[1]);
		
		//System.out.println(dayI + " " + monthI + " " + yearI + " " + hourI + " " + minuteI);
		this.datumIVreme.set(yearI, monthI, dayI, hourI, minuteI);
		
		//System.out.println(this.datumIVreme.toString());
	}

	@Override
	public String toString() {
		return "Zahtev [id=" + id + ", doktorID=" + doktorID + ", datum=" + datum + ", vreme=" + vreme + ", salaID="
				+ salaID + ", administrator_klinike=" + administrator_klinike + "]";
	}


	public Calendar getDatumIVreme() {
		return datumIVreme;
	}


	public void setDatumIVreme(Calendar datumIVreme) {
		this.datumIVreme = datumIVreme;
	}
	
	
	
	
	
}
