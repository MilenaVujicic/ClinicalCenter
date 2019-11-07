package model;

import java.util.Date;
import java.util.UUID;

public class Absence {
	
	private UUID id;
	private TypeOfService type;
	private Date startDate;
	private Date endDate;
	private boolean isAccepted;
	
	public Absence()  {
		this.id = UUID.randomUUID();
	}
	
	public Absence(TypeOfService type, Date startDate, Date endDate, boolean isAccepted) {
		super();
		this.id = UUID.randomUUID();
		this.type = type;
		this.startDate = startDate;
		this.endDate = endDate;
		this.isAccepted = isAccepted;
	}
	public UUID getId() {
		return id;
	}

	public TypeOfService getType() {
		return type;
	}
	public void setType(TypeOfService type) {
		this.type = type;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public boolean isAccepted() {
		return isAccepted;
	}
	public void setAccepted(boolean isAccepted) {
		this.isAccepted = isAccepted;
	}
	@Override
	public String toString() {
		return "Absence [id=" + id + ", type=" + type + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", isAccepted=" + isAccepted + "]";
	}
	
	
}
