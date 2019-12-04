package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Lek;
import com.example.demo.repository.LekRepository;

@Service
public class LekService {

	@Autowired
	LekRepository lekRepository;
	
	public List<Lek> findAll() {
		return lekRepository.findAll();
	}
	
	public Lek save(Lek lek) {
		return lekRepository.save(lek);
	}
}
