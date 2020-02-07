package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Operacija;


import com.example.demo.model.StatusOperacije;

import com.example.demo.repository.OperacijaRespository;

@Service
@Transactional(readOnly = true)
public class OperacijaService {

	@Autowired
	private OperacijaRespository operacijaRespository;

	public List<Operacija> findByStatus(StatusOperacije status){
		return operacijaRespository.findByStatus(status);
	}
	
	public Operacija findOne(Long id) {
		return operacijaRespository.findById(id).orElse(null);
	}
	
	@Transactional(readOnly = false)
	public Operacija save(Operacija operacija) {
		return operacijaRespository.save(operacija);
	}
	
	public List<Operacija> findAll() {
		return operacijaRespository.findAll();
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void delete(Operacija operacija) {
		operacijaRespository.deleteById(operacija.getId());
	}
}
