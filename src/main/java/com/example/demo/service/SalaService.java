package com.example.demo.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Klinika;
import com.example.demo.model.Sala;
import com.example.demo.repository.SalaRepository;

@Service
public class SalaService {

	@Autowired
	SalaRepository salaRepository;
	

	public List<Sala> findAll(){
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
	

	public Sala findOne(Long id) {
		Sala sala = new Sala();
		sala.setIme("Sala 0");
		sala.setOpis("Nova sala");
		sala.setKlinika(new Klinika("", "", ""));
		return salaRepository.findById(id).orElse(null);
	}
}

