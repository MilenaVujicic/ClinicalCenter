package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class Clinic {

	private UUID id;
	private String name;
	private String address;
	private String description;
	private List<UUID> freeAppoitments;
	private List<UUID> doctors;
	private List<UUID> halls;
	private HashMap<UUID, Double> fees;
	private double avgMark;
	
	
	public Clinic()  {
		this.id = UUID.randomUUID();
		this.freeAppoitments = new ArrayList<UUID>();
		this.halls = new ArrayList<UUID>();
		this.fees = new HashMap<UUID, Double>();
		this.doctors = new ArrayList<UUID>();
	}
	
	public Clinic(String name, String address, String description, double avgMark) {
		super();
		this.id = UUID.randomUUID();
		this.freeAppoitments = new ArrayList<UUID>();
		this.halls = new ArrayList<UUID>();
		this.doctors = new ArrayList<UUID>();
		this.fees = new HashMap<UUID, Double>();
		this.name = name;
		this.address = address;
		this.description = description;
		this.avgMark = avgMark;
	}

	public double getAvgMark() {
		return avgMark;
	}

	public void setAvgMark(double avgMark) {
		this.avgMark = avgMark;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<UUID> getFreeAppoitments() {
		return freeAppoitments;
	}
	public void setFreeAppoitments(List<UUID> freeAppoitments) {
		this.freeAppoitments = freeAppoitments;
	}
	public List<UUID> getHalls() {
		return halls;
	}
	public void setHalls(List<UUID> halls) {
		this.halls = halls;
	}
	public HashMap<UUID, Double> getFees() {
		return fees;
	}
	public void setFees(HashMap<UUID, Double> fees) {
		this.fees = fees;
	}

	public List<UUID> getDoctors() {
		return doctors;
	}

	public void setDoctors(List<UUID> doctors) {
		this.doctors = doctors;
	}

	@Override
	public String toString() {
		return "Clinic [id=" + id + ", name=" + name + ", address=" + address + ", description=" + description
				+ ", freeAppoitments=" + freeAppoitments + ", doctors=" + doctors + ", halls=" + halls + ", fees="
				+ fees + ", avgMark=" + avgMark + "]";
	}
	
	
	
	
}
