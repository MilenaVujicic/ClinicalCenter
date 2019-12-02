package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Dijagnoza;
import com.example.demo.repository.DijagnozaRepository;

@Service
public class DijagnozaService {

	@Autowired
	DijagnozaRepository dijagnozaRepository;
	
	public List<Dijagnoza> findAll() {
		return dijagnozaRepository.findAll();
	}
	
	public Dijagnoza save(Dijagnoza dijagnoza) {
		return dijagnozaRepository.save(dijagnoza);
	}
	
	public Dijagnoza findOne(Long id) {
		return dijagnozaRepository.findById(id).orElse(null);
	}
}
