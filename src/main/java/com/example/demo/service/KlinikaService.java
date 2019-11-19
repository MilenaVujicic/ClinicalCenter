package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.Klinika;
import com.example.demo.repository.KlinikaRepository;

@Service
public class KlinikaService {

	@Autowired
	KlinikaRepository klinikaRepository;
	
	public Klinika findByName(String ime) {
		return klinikaRepository.findByIme(ime);
	}
	
	public Klinika save(Klinika k) {
		
		return klinikaRepository.save(k);
	}
	
}
