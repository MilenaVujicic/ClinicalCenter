package model;

import java.util.ArrayList;
import java.util.UUID;

public class Doctor {
	
	private UUID userID;
	private ArrayList<UUID> examinationsSKD;	//zakazani pregledi
	private ArrayList<UUID> examinationsFIN;	//zavrseni pregledi
	private ArrayList<UUID> operationsSKD;		//zakazane operacije
	private ArrayList<UUID> operationsFIN;		//zavrsene operacije
	private ArrayList<UUID> holidays;
	private ArrayList<Integer> raitings;
	private double raitingAVG;
	
	public Doctor() {
		super();
		this.userID = UUID.randomUUID();
		this.examinationsSKD = new ArrayList<UUID>();
		this.examinationsFIN = new ArrayList<UUID>();
		this.operationsSKD = new ArrayList<UUID>();
		this.operationsFIN = new ArrayList<UUID>();
		this.holidays = new ArrayList<UUID>();
		this.raitings = new ArrayList<Integer>();
		this.raitingAVG = 0;
	}

	public Doctor(UUID docID) {
		super();
		this.userID = docID;
		this.examinationsSKD = new ArrayList<UUID>();
		this.examinationsFIN = new ArrayList<UUID>();
		this.operationsSKD = new ArrayList<UUID>();
		this.operationsFIN = new ArrayList<UUID>();
		this.holidays = new ArrayList<UUID>();
		this.raitings = new ArrayList<Integer>();
		this.raitingAVG = 0;
	}
	
	public Doctor(UUID userID, ArrayList<UUID> examinationsSKD, ArrayList<UUID> examinationsFIN,
			ArrayList<UUID> operationsSKD, ArrayList<UUID> operationsFIN, ArrayList<UUID> holidays,
			ArrayList<Integer> raitings, double raitingAVG) {
		super();
		this.userID = userID;
		this.examinationsSKD = examinationsSKD;
		this.examinationsFIN = examinationsFIN;
		this.operationsSKD = operationsSKD;
		this.operationsFIN = operationsFIN;
		this.holidays = holidays;
		this.raitings = raitings;
		this.raitingAVG = raitingAVG;
	}

	public Doctor(Doctor d) {
		this.userID = d.userID;
		this.examinationsSKD = d.examinationsSKD;
		this.examinationsFIN = d.examinationsFIN;
		this.operationsSKD = d.operationsSKD;
		this.operationsFIN = d.operationsFIN;
		this.holidays = d.holidays;
		this.raitings = d.raitings;
		this.raitingAVG = d.raitingAVG;
	}

	public UUID getUserID() {
		return userID;
	}

	public void setUserID(UUID userID) {
		this.userID = userID;
	}

	public ArrayList<UUID> getExaminationsSKD() {
		return examinationsSKD;
	}

	public void setExaminationsSKD(ArrayList<UUID> examinationsSKD) {
		this.examinationsSKD = examinationsSKD;
	}

	public ArrayList<UUID> getExaminationsFIN() {
		return examinationsFIN;
	}

	public void setExaminationsFIN(ArrayList<UUID> examinationsFIN) {
		this.examinationsFIN = examinationsFIN;
	}

	public ArrayList<UUID> getOperationsSKD() {
		return operationsSKD;
	}

	public void setOperationsSKD(ArrayList<UUID> operationsSKD) {
		this.operationsSKD = operationsSKD;
	}

	public ArrayList<UUID> getOperationsFIN() {
		return operationsFIN;
	}

	public void setOperationsFIN(ArrayList<UUID> operationsFIN) {
		this.operationsFIN = operationsFIN;
	}

	public ArrayList<UUID> getHolidays() {
		return holidays;
	}

	public void setHolidays(ArrayList<UUID> holidays) {
		this.holidays = holidays;
	}

	public ArrayList<Integer> getRaitings() {
		return raitings;
	}

	public void setRaitings(ArrayList<Integer> raitings) {
		this.raitings = raitings;
	}

	public double getRaitingAVG() {
		return raitingAVG;
	}

	public void setRaitingAVG(double raitingAVG) {
		this.raitingAVG = raitingAVG;
	}

	@Override
	public String toString() {
		return "Doctor [userID=" + userID + ", examinationsSKD=" + examinationsSKD + ", examinationsFIN="
				+ examinationsFIN + ", operationsSKD=" + operationsSKD + ", operationsFIN=" + operationsFIN
				+ ", holidays=" + holidays + ", raitings=" + raitings + ", raitingAVG=" + raitingAVG + "]";
	}
}
