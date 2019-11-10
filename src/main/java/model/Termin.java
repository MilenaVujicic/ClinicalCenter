package model;

import java.util.Date;
import java.util.UUID;

public class Appointment {
	private UUID id;
	private Date date;
	private boolean isFree;
	
	public Appointment()  {
		this.id = UUID.randomUUID();
	}
	
	public Appointment(Date date, boolean isFree) {
		super();
		this.id = UUID.randomUUID();
		this.date = date;
		this.isFree = isFree;
	}
	public UUID getId() {
		return id;
	}

	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public boolean isFree() {
		return isFree;
	}
	public void setFree(boolean isFree) {
		this.isFree = isFree;
	}

	@Override
	public String toString() {
		return "Appointment [id=" + id + ", date=" + date + ", isFree=" + isFree + "]";
	}
	
	
	
}
