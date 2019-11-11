/*
 * author: Filip Vozarevic
 */
package com.example.demo.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class MedicinskaSestra {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToMany(mappedBy = "medicinskaSestra")
	private Set<Odsustvo> godisnjiOdmori = new HashSet<Odsustvo>();
	
	@OneToMany(mappedBy = "medicinskaSestra", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Recept> recepti = new HashSet<Recept>();
	
	public MedicinskaSestra() {
		super();
	}
	
	public MedicinskaSestra(Set<Odsustvo> godisnjiOdmori) {
		super();
		this.godisnjiOdmori = godisnjiOdmori;
	}
	
	public MedicinskaSestra(MedicinskaSestra n) {
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
