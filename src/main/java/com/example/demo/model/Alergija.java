package com.example.demo.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Alergija {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "naziv", nullable = false)
	private String naziv;
	
	@Column(name = "opis")
	private String opis;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Pacijent pacijent;
}
