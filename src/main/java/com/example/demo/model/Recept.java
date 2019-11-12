/*
 * author:Andrea Mendrei
 */
package com.example.demo.model;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

enum StatusRecepta {
	NEOVEREN, OVEREN
}
@Entity
public class Recept {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "naziv")
	private String naziv;
	
	@Column(name = "opis")
	private String opis;
	
	@Column(name = "status")
	private StatusRecepta status;
	
	@Column(name="datumIspisa")
	private LocalDateTime datumIspisa;
	
	@Column(name="datumOvere")
	private LocalDateTime datumOvere;
	
	@Column(name = "lekId")
	private String lekID;
	
	@Column(name = "lekIme")
	private String lek;
	
	
}
