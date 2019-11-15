package com.example.demo.model;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

public class MedicinskaSestra {

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Klinika klinika;
}
