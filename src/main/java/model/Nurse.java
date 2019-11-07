package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class Nurse {
	private UUID id;
	private ArrayList<UUID> holidays;
	
	public Nurse() {
		
	}
	
	public Nurse(UUID id, ArrayList<UUID> holidays) {
		this.id = id;
		this.holidays = holidays;
	}
	
	public Nurse(Nurse n) {
		this.id = n.id;
		this.holidays = n.holidays;
	}
	
	public UUID getId() {
		return id;
	}
	
	public void setId(UUID id) {
		this.id = id;
	}
	
	public ArrayList<UUID> getHolidays() {
		return holidays;
	}
	
	public void setHolidays(ArrayList<UUID> holidays) {
		this.holidays = holidays;
	}
}
