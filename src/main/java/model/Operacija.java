package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

enum OperationStatus {
	SCHEDULED, FINISHED
}

public class Operation {

	private UUID operID;
	private UUID roomID;
	private OperationStatus status;
	private LocalDateTime operDateAndTime;
	private int duration;
	private ArrayList<UUID> doctors;
	private UUID adminID;
	
	public Operation() {
		super();
		this.operID = UUID.randomUUID();
		this.doctors = new ArrayList<UUID>();
		this.status = OperationStatus.SCHEDULED;
		this.duration = 0;
	}
	
	public Operation(UUID roomID, UUID adminID) {
		super();
		this.operID = UUID.randomUUID();
		this.roomID = roomID;
		this.adminID = adminID;
		this.doctors = new ArrayList<UUID>();
		this.status = OperationStatus.SCHEDULED;
		this.duration = 0;
	}

	public Operation(UUID operID, UUID roomID, OperationStatus status, LocalDateTime operDateAndTime, int duration,
			ArrayList<UUID> doctors, UUID adminID) {
		super();
		this.operID = operID;
		this.roomID = roomID;
		this.status = status;
		this.operDateAndTime = operDateAndTime;
		this.duration = duration;
		this.doctors = doctors;
		this.adminID = adminID;
	}

	public Operation(Operation o) {
		this.operID = o.operID;
		this.roomID = o.roomID;
		this.status = o.status;
		this.operDateAndTime = o.operDateAndTime;
		this.duration = o.duration;
		this.doctors = o.doctors;
		this.adminID = o.adminID;
	}

	public UUID getOperID() {
		return operID;
	}
	
	public void setOperID(UUID operID) {
		this.operID = operID;
	}
	
	public UUID getRoomID() {
		return roomID;
	}
	
	public void setRoomID(UUID roomID) {
		this.roomID = roomID;
	}
	
	public OperationStatus getStatus() {
		return status;
	}
	
	public void setStatus(OperationStatus status) {
		this.status = status;
	}
	
	public LocalDateTime getOperDateAndTime() {
		return operDateAndTime;
	}
	
	public void setOperDateAndTime(LocalDateTime operDateAndTime) {
		this.operDateAndTime = operDateAndTime;
	}
	
	public int getDuration() {
		return duration;
	}
	
	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	public ArrayList<UUID> getDoctors() {
		return doctors;
	}
	
	public void setDoctors(ArrayList<UUID> doctors) {
		this.doctors = doctors;
	}
	
	public UUID getAdminID() {
		return adminID;
	}
	
	public void setAdminID(UUID adminID) {
		this.adminID = adminID;
	}

	@Override
	public String toString() {
		return "Operation [operID=" + operID + ", roomID=" + roomID + ", status=" + status + ", operDateAndTime="
				+ operDateAndTime + ", duration=" + duration + ", doctors=" + doctors + ", adminID=" + adminID + "]";
	}
	
	
	
}