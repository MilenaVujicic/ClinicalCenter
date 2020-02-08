package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Termin;
import com.example.demo.repository.TerminRepository;

@Service
@Transactional(readOnly = true)
public class TerminService {

	@Autowired
	TerminRepository terminRepository;
	
	@Transactional(readOnly = false)
	public Termin save(Termin t) {
		return terminRepository.save(t);
	}
	

	public Optional<Termin> findById(Long id) {
		return terminRepository.findById(id);
	}
	
	public List<Termin> findAll(){
		return terminRepository.findAll();
	}
	
	
	public List<Termin> findBySlobodan(Boolean slobodan) {

		return terminRepository.findBySlobodan(slobodan);
	}


	public Termin findOne(Long id) {
		// TODO Auto-generated method stub
		return terminRepository.findById(id).orElseGet(null);
	}
	
	public List<Termin> findByDatumIDoktor(LocalDate datum, Long doktor_id) {
		return terminRepository.findByDatumIDoktor(datum, doktor_id);
	}
	
}
