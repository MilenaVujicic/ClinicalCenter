package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Alergija;
import com.example.demo.repository.AlergijaRepository;

@Service
public class AlergijaService {

	@Autowired
	private AlergijaRepository alergijaRepository;
	
	public List<Alergija> findAll() {
		return alergijaRepository.findAll();
	}
	
	public Alergija save(Alergija alergija) {
		return alergijaRepository.save(alergija);
	}
	
	public Alergija findOne(Long id) {
		return alergijaRepository.findById(id).orElse(null);
	}
	
	public void delete(Alergija alergija) {
		alergijaRepository.delete(alergija);
	}
}
