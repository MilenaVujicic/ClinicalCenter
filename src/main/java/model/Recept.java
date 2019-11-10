package model;

import java.time.LocalDateTime;
import java.util.UUID;

enum RecipeStatus {
	UNCERTIFIED, CERTIFIED
}

public class Recipe {

	private UUID recID;
	private String name;
	private String describe;
	private RecipeStatus status;
	private LocalDateTime dateOfPrinting;
	private LocalDateTime dateOfCertifing;
	private UUID patientID;
	private UUID doctorID;
	private UUID nurseID;
	
	public Recipe() {
		super();
		this.recID = UUID.randomUUID();
		this.name = "";
		this.describe = "";
		this.status = RecipeStatus.UNCERTIFIED;
	}

	public Recipe(UUID patientID, UUID doctorID, UUID nurseID) {
		super();
		this.recID = UUID.randomUUID();
		this.name = "";
		this.describe = "";
		this.status = RecipeStatus.UNCERTIFIED;
		this.patientID = patientID;
		this.doctorID = doctorID;
		this.nurseID = nurseID;
	}

	public Recipe(UUID recID, String name, String describe, RecipeStatus status, LocalDateTime dateOfPrinting,
			LocalDateTime dateOfCertifing, UUID patientID, UUID doctorID, UUID nurseID) {
		super();
		this.recID = recID;
		this.name = name;
		this.describe = describe;
		this.status = status;
		this.dateOfPrinting = dateOfPrinting;
		this.dateOfCertifing = dateOfCertifing;
		this.patientID = patientID;
		this.doctorID = doctorID;
		this.nurseID = nurseID;
	}

	public Recipe(Recipe r) {
		this.recID = r.recID;
		this.name = r.name;
		this.describe = r.describe;
		this.status = r.status;
		this.dateOfPrinting = r.dateOfPrinting;
		this.dateOfCertifing = r.dateOfCertifing;
		this.patientID = r.patientID;
		this.doctorID = r.doctorID;
		this.nurseID = r.nurseID;
	}

	public UUID getRecID() {
		return recID;
	}

	public void setRecID(UUID recID) {
		this.recID = recID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public RecipeStatus getStatus() {
		return status;
	}

	public void setStatus(RecipeStatus status) {
		this.status = status;
	}

	public LocalDateTime getDateOfPrinting() {
		return dateOfPrinting;
	}

	public void setDateOfPrinting(LocalDateTime dateOfPrinting) {
		this.dateOfPrinting = dateOfPrinting;
	}

	public LocalDateTime getDateOfCertifing() {
		return dateOfCertifing;
	}

	public void setDateOfCertifing(LocalDateTime dateOfCertifing) {
		this.dateOfCertifing = dateOfCertifing;
	}

	public UUID getPatientID() {
		return patientID;
	}

	public void setPatientID(UUID patientID) {
		this.patientID = patientID;
	}

	public UUID getDoctorID() {
		return doctorID;
	}

	public void setDoctorID(UUID doctorID) {
		this.doctorID = doctorID;
	}

	public UUID getNurseID() {
		return nurseID;
	}

	public void setNurseID(UUID nurseID) {
		this.nurseID = nurseID;
	}

	@Override
	public String toString() {
		return "Recipe [recID=" + recID + ", name=" + name + ", describe=" + describe + ", status=" + status
				+ ", dateOfPrinting=" + dateOfPrinting + ", dateOfCertifing=" + dateOfCertifing + ", patientID="
				+ patientID + ", doctorID=" + doctorID + ", nurseID=" + nurseID + "]";
	}
	
	
	
}
