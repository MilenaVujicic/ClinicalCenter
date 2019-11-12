package com.example.demo.dto;

import java.util.HashSet;
import java.util.Set;

import com.example.demo.model.Odsustvo;
import com.example.demo.model.Recept;

public class MedicinskaSestraDTO {

	private Long id;
	
	private Set<Odsustvo> godisnjiOdmori = new HashSet<Odsustvo>();
	
	private Set<Recept> recepti = new HashSet<Recept>();
	
	public MedicinskaSestraDTO() {
		super();
	}
	
	public MedicinskaSestraDTO(Set<Odsustvo> godisnjiOdmori) {
		super();
		this.godisnjiOdmori = godisnjiOdmori;
	}
	
	public MedicinskaSestraDTO(MedicinskaSestraDTO n) {
		this.godisnjiOdmori = n.godisnjiOdmori;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Set<Odsustvo> getGodisnjiOdmori() {
		return godisnjiOdmori;
	}
	
	public void setGodisnjiOdmori(Set<Odsustvo> godisnjiOdmori) {
		this.godisnjiOdmori = godisnjiOdmori;
	}
	
	public Set<Recept> getRecepti() {
		return recepti;
	}
	
	public void setRecepti(Set<Recept> recepti) {
		this.recepti = recepti;
	}

	@Override
	public String toString() {
		return "MedicinskaSestra [Godisnji odmori=" + godisnjiOdmori + "]";
	}
}
