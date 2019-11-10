package model;

import java.util.Set;

public class MedicinskaSestra {
	
	private Set<Odsustvo> holidays;
	
	public MedicinskaSestra() {
		
	}
	
	public MedicinskaSestra(Set<Odsustvo> holidays) {
		this.holidays = holidays;
	}
	
	public MedicinskaSestra(MedicinskaSestra n) {
		this.holidays = n.holidays;
	}
	
	public Set<Odsustvo> getHolidays() {
		return holidays;
	}
	
	public void setHolidays(Set<Odsustvo> holidays) {
		this.holidays = holidays;
	}

	@Override
	public String toString() {
		return "MedicinskaSestra [holidays=" + holidays + "]";
	}
	
	
}
