package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

public class Examination {

	private UUID examID;
	private String title;
	private String describe;
	private UUID patientID;
	private LocalDateTime examDateAndTime;
	private String type;
	private int duration;
	private UUID roomID;
	private ArrayList<UUID> doctors;
	private double price;
	private ArrayList<UUID> diagnoses;
	private ArrayList<UUID> drugs;
	private UUID adminID;
	
	public Examination() {
		super();
		this.examID = UUID.randomUUID();
		this.doctors = new ArrayList<UUID>();
		this.diagnoses = new ArrayList<UUID>();
		this.drugs = new ArrayList<UUID>();
		this.title = "";
		this.describe = "";
		this.type = "";
	}

	public Examination(UUID patientID, UUID roomID, ArrayList<UUID> doctors, UUID adminID) {
		super();
		this.examID = UUID.randomUUID();
		this.patientID = patientID;
		this.roomID = roomID;
		this.doctors = doctors;
		this.adminID = adminID;
		this.diagnoses = new ArrayList<UUID>();
		this.drugs = new ArrayList<UUID>();
		this.title = "";
		this.describe = "";
		this.type = "";
	}

	public Examination(UUID examID, String title, String describe, UUID patientID, LocalDateTime examDateAndTime,
			String type, int duration, UUID roomID, ArrayList<UUID> doctors, double price, ArrayList<UUID> diagnoses,
			ArrayList<UUID> drugs, UUID adminID) {
		super();
		this.examID = examID;
		this.title = title;
		this.describe = describe;
		this.patientID = patientID;
		this.examDateAndTime = examDateAndTime;
		this.type = type;
		this.duration = duration;
		this.roomID = roomID;
		this.doctors = doctors;
		this.price = price;
		this.diagnoses = diagnoses;
		this.drugs = drugs;
		this.adminID = adminID;
	}
	
	public Examination(Examination e) {
		this.examID = e.examID;
		this.title = e.title;
		this.describe = e.describe;
		this.patientID = e.patientID;
		this.examDateAndTime = e.examDateAndTime;
		this.type = e.type;
		this.duration = e.duration;
		this.roomID = e.roomID;
		this.doctors = e.doctors;
		this.price = e.price;
		this.diagnoses = e.diagnoses;
		this.drugs = e.drugs;
		this.adminID = e.adminID;
	}

	public UUID getExamID() {
		return examID;
	}

	public void setExamID(UUID examID) {
		this.examID = examID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public UUID getPatientID() {
		return patientID;
	}

	public void setPatientID(UUID patientID) {
		this.patientID = patientID;
	}

	public LocalDateTime getExamDateAndTime() {
		return examDateAndTime;
	}

	public void setExamDateAndTime(LocalDateTime examDateAndTime) {
		this.examDateAndTime = examDateAndTime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public UUID getRoomID() {
		return roomID;
	}

	public void setRoomID(UUID roomID) {
		this.roomID = roomID;
	}

	public ArrayList<UUID> getDoctors() {
		return doctors;
	}

	public void setDoctors(ArrayList<UUID> doctors) {
		this.doctors = doctors;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public ArrayList<UUID> getDiagnoses() {
		return diagnoses;
	}

	public void setDiagnoses(ArrayList<UUID> diagnoses) {
		this.diagnoses = diagnoses;
	}

	public ArrayList<UUID> getDrugs() {
		return drugs;
	}

	public void setDrugs(ArrayList<UUID> drugs) {
		this.drugs = drugs;
	}

	public UUID getAdminID() {
		return adminID;
	}

	public void setAdminID(UUID adminID) {
		this.adminID = adminID;
	}

	@Override
	public String toString() {
		return "Examination [examID=" + examID + ", title=" + title + ", describe=" + describe + ", patientID="
				+ patientID + ", examDateAndTime=" + examDateAndTime + ", type=" + type + ", duration=" + duration
				+ ", roomID=" + roomID + ", doctors=" + doctors + ", price=" + price + ", diagnoses=" + diagnoses
				+ ", drugs=" + drugs + ", adminID=" + adminID + "]";
	}
	
	
	
	
}