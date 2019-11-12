package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class AdministratorKlinike {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "Username", nullable = false)
	private String username;

	@Column(name = "Password", nullable = false)
	private String password;

	@Column(name = "Name", nullable = false)
	private String ime;

	@Column(name = "Surname", nullable = false)
	private String prezime;

	@ManyToOne
	@JoinColumn(name="clinic_id",nullable = false)
	private Klinika klinika;
}
