package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Operacija;
import com.example.demo.repository.OperacijaRespository;

@Service
public class OperacijaService {

	@Autowired
	private OperacijaRespository operacijaRespository;

	
	public Operacija findOne(Long id) {
		return operacijaRespository.findById(id).orElse(null);
	}
	
	public Operacija save(Operacija operacija) {
		return operacijaRespository.save(operacija);
	}
	
}
