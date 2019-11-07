package model;


import java.util.Date;
import java.util.UUID;

public class User {
	private UUID id;
	private String name;
	private String surname;
	private String email;
	private String password;
	private String city;
	private String state;
	private String jmbg;
	private String address;
	private Date birthDay;
	private boolean status;
	
	public User() {
		
	}
	
	public User(UUID id, String name, String surname, String email,
			String pass,String city, String state, String jmbg,
			String address, Date birthDay, boolean status) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = pass;
		this.city = city;
		this.state = state;
		this.jmbg = jmbg;
		this.address = address;
		this.birthDay = birthDay;
		this.status = status;
	}
	
	public User(User u) {
		this.id = u.id;
		this.name = u.name;
		this.surname = u.surname;
		this.email = u.email;
		this.password = u.password;
		this.city = u.city;
		this.state = u.state;
		this.jmbg = u.jmbg;
		this.address = u.address;
		this.birthDay = u.birthDay;
		this.status = u.status;
	}
	
	public UUID getId() {
		return id;
	}
	
	public void setId(UUID id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getJmbg() {
		return jmbg;
	}

	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}
	
	public boolean getStatus() {
		return status;
	}

	public void setBirthDay(boolean status) {
		this.status = status;
	}
	
}
