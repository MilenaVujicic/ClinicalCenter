package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Klinika;
import com.example.demo.model.Sala;
import com.example.demo.repository.SalaRepository;

@Service
public class SalaService {

	@Autowired
	SalaRepository salaRepository;
	
	public Sala findOne(Long id) {
		Sala sala = new Sala();
		sala.setIme("Sala 0");
		sala.setOpis("Nova sala");
		sala.setKlinika(new Klinika("", "", ""));
		return salaRepository.findById(id).orElse(null);
	}
}
