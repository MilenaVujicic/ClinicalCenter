package model;

import java.util.UUID;

public class TypeOfService {
	
	private UUID id;
	private String name;
	private String description;
	private double fee;
	
	
	public TypeOfService()  {
		this.id = UUID.randomUUID();
	}
	
	public TypeOfService(String name, String description, double fee) {
		super();
		this.id = UUID.randomUUID();
		this.name = name;
		this.description = description;
		this.fee = fee;
	}
	public UUID getId() {
		return id;
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
	public double getFee() {
		return fee;
	}
	public void setFee(double fee) {
		this.fee = fee;
	}
	@Override
	public String toString() {
		return "TypeOfService [id=" + id + ", name=" + name + ", description=" + description + ", fee=" + fee + "]";
	}
	
	
}
