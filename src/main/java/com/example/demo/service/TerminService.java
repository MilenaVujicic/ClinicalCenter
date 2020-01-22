package com.example.demo.service;

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
	
}
