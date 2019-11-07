package model;

import java.util.ArrayList;
import java.util.UUID;

public class Patient {
	private String id;
	private int height;
	private int weight;
	private ArrayList<UUID> alergies;
	private ArrayList<UUID> surgeries;
	private ArrayList<UUID> prescriptions; //recepti
	private ArrayList<UUID> appointments;
	
	public Patient() {
		this.id = UUID.randomUUID().toString();
	}
	
	public Patient(String id, int height, int weight,
			ArrayList<UUID> alergies, ArrayList<UUID> surgeries,
			ArrayList<UUID> prescriptions, ArrayList<UUID> appointments) {
		this.id = id;
		this.height = height;
		this.weight = weight;
		this.alergies = alergies;
		this.surgeries = surgeries;
		this.prescriptions = prescriptions;
		this.appointments = appointments;
	}
	
	public Patient(Patient p) {
		this.id = p.id;
		this.height = p.height;
		this.weight = p.weight;
		this.alergies = p.alergies;
		this.surgeries = p.surgeries;
		this.prescriptions = p.prescriptions;
		this.appointments = p.appointments;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getTezina() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public ArrayList<UUID> getAlergies() {
		return alergies;
	}
	public void setAlergies(ArrayList<UUID> alergies) {
		this.alergies = alergies;
	}
	public ArrayList<UUID> getSurgeries() {
		return surgeries;
	}
	public void setSurgeries(ArrayList<UUID> surgeries) {
		this.surgeries = surgeries;
	}
	public ArrayList<UUID> getPrescriptions() {
		return prescriptions;
	}
	public void setPrescriptions(ArrayList<UUID> prescriptions) {
		this.prescriptions = prescriptions;
	}
	public ArrayList<UUID> getAppointments() {
		return appointments;
	}
	public void setAppointments(ArrayList<UUID> appointments) {
		this.appointments = appointments;
	}
	
	
}
