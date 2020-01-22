package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Operacija;
import com.example.demo.model.StatusOperacije;
import com.example.demo.repository.OperacijaRespository;

@Service
public class OperacijaService {

	@Autowired
	private OperacijaRespository operacijaRespository;
	
	public List<Operacija> findByStatus(StatusOperacije status){
		return operacijaRespository.findByStatus(status);
	}
	
	public Operacija findOne(Long id) {
		return operacijaRespository.findById(id).orElse(null);
	}
	
	public Operacija save(Operacija operacija) {
		return operacijaRespository.save(operacija);
	}
	
	public List<Operacija> findAll() {
		return operacijaRespository.findAll();
	}
	
	public void delete(Operacija operacija) {
		operacijaRespository.delete(operacija);
	}
}
