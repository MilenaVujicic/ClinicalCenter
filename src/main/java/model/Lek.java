package model;

import java.util.UUID;

public class Drug {
	private UUID id;
	private String code; //sifra
	private String name;
	private String description;
	private String givenBy;
	
	public Drug() {
	}

	public Drug(UUID id, String code, String name, String desc, String givenBy) {
		this.id = id;
		this.code = code;
		this.name = name;
		this.description = desc;
		this.givenBy = givenBy;
	}
	
	public Drug(Drug d) {
		this.id = d.id;
		this.code = d.code;
		this.name = d.name;
		this.description = d.description;
		this.givenBy = d.givenBy;
	}
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
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
	public String getGivenBy() {
		return givenBy;
	}
	public void setGivenBy(String givenBy) {
		this.givenBy = givenBy;
	}
}
