package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Termin;
import com.example.demo.repository.TerminRepository;

@Service
public class TerminService {

	@Autowired
	TerminRepository terminRepository;
	
	public Termin save(Termin t) {
		return terminRepository.save(t);
	}
	
	public Optional<Termin> findById(Long id) {
		return terminRepository.findById(id);
	}
	
	public List<Termin> findAll(){
		return terminRepository.findAll();
	}
	
	public List<Termin> findBySlobodan(Boolean slobodan){
		return terminRepository.findBySlobodan(slobodan);
	}
	
}
