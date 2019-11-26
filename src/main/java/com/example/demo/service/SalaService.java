package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.model.Sala;
import com.example.demo.repository.SalaRepository;

@Service
public class SalaService {

	@Autowired
	SalaRepository salaRepository;
	
	public List<Sala> findAll(Pageable page){
		return salaRepository.findAll();
	}
	
	public List<Sala> findAllByKlinika(Long id){
		return salaRepository.findAllByKlinika(id);
	}
	
	public Optional<Sala> findById(Long id){
		return salaRepository.findById(id);
	}
	
	public Sala findByIme(String ime) {
		return salaRepository.findByIme(ime);
	}
	
	public Sala findByOpis(String opis) {
		return salaRepository.findByOpis(opis);
	}
	
}
