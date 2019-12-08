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
}
