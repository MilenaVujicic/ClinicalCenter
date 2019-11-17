package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;

//promenjeno da bude klasa zbog spring security
@Entity
public class UlogaKorisnika implements GrantedAuthority {


	private static final long serialVersionUID = -7020077012341303719L;

	@Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name="name")
    String name;
	
	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return name;
	}
	
	public void setName(String name) {
        this.name = name;
    }

    @JsonIgnore
    public String getName() {
        return name;
    }

    @JsonIgnore
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
	
}
