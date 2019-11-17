package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Klinika;
import com.example.demo.repository.KlinikaRepository;

@Service
public class KlinikaService {

	@Autowired
	KlinikaRepository klinikaRepository;
	
	public List<Klinika> findAll() {
		return klinikaRepository.findAll();
	}
	
	public Klinika save(Klinika klinika) {
		return klinikaRepository.save(klinika);
	}
}
