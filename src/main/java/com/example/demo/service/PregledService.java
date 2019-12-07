package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Pregled;
import com.example.demo.repository.PregledRespository;

@Service
public class PregledService {

	@Autowired
	private PregledRespository pregledRespository;
	
	public Pregled save(Pregled pregled) {
		return pregledRespository.save(pregled);
	}

	public List<Pregled> findAll() {
		return pregledRespository.findAll();
	}
}
