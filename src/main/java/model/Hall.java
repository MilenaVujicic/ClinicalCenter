package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Hall  {
	
	private UUID id;
	private Clinic parentClinic;
	private String name;
	private String description;
	private List<UUID> schedule;
	
	public Hall() {
		this.id = UUID.randomUUID();
		this.schedule = new ArrayList<UUID>();
	}
	
	public Hall(Clinic parentClinic, String name, String description, List<UUID> schedule) {
		super();
		this.id = UUID.randomUUID();
		this.parentClinic = parentClinic;
		this.name = name;
		this.description = description;
		this.schedule = schedule;
	}
	public Clinic getParentClinic() {
		return parentClinic;
	}
	public void setParentClinic(Clinic parentClinic) {
		this.parentClinic = parentClinic;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<UUID> getSchedule() {
		return schedule;
	}
	public void setSchedule(List<UUID> schedule) {
		this.schedule = schedule;
	}
	public UUID getId() {
		return id;
	}
	@Override
	public String toString() {
		return "Hall [id=" + id + ", parentClinic=" + parentClinic + ", name=" + name + ", description=" + description
				+ ", schedule=" + schedule + "]";
	}
	
	
}
