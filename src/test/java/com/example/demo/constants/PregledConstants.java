package com.example.demo.constants;

import java.util.Calendar;

import com.example.demo.model.StatusPregleda;

public class PregledConstants {

	public static final int PREGLED_SIZE = 9;
	public static final Long PREGLED_ID = 1L;
	
	public static final String PREGLED_NAZIV = "Prvi pregled";
	public static final String PREGLED_ANAMNEZA = "Pacijent je dosao sa povisenom temperaturom";
	public static final Calendar DATUM_PREGLEDA() {
		Calendar c = Calendar.getInstance();
		c.set(2019, 12, 12, 0, 0, 0);
		return c;
	}
	
	public static final Calendar DATUM_PREGLEDA_C = DATUM_PREGLEDA();
	
	public static final StatusPregleda PREGLED_STATUS = StatusPregleda.NERASPOREDJEN;
	public static final int PREGLED_NERASPOREDJEN_SIZE = 6;
	
	public static final Long PREGLED_PACIJENT_ID = 3L;
	public static final int PREGLED_PACIJENT_SIZE = 4;
	
	public static final int PREGLED_ZAHTEV_SIZE = 6;
	
	public static final Long PREGLED_ID_2 = 3L;

	public static final String PREGLED_NOVI_NAZIV = "Novi pregled";
	public static final String PREGLED_NOVI_ANAMNEZA = "Bol u glavi";
	public static final Calendar PREGLED_NOVI_VREME = Calendar.getInstance();
	public static final StatusPregleda PREGLED_NOVI_STATUS = StatusPregleda.NERASPOREDJEN;
}
