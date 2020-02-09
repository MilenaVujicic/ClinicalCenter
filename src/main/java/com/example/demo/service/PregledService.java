package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Pregled;
import com.example.demo.model.StatusPregleda;
import com.example.demo.repository.PregledRespository;

@Service
@Transactional(readOnly = true)
public class PregledService {

	@Autowired
	private PregledRespository pregledRespository;
	
	@Transactional(readOnly = false)
	public Pregled save(Pregled pregled) {
		return pregledRespository.save(pregled);
	}

	public List<Pregled> findAll() {
		return pregledRespository.findAll();
	}
	
	public Pregled findOne(Long id) {
		return pregledRespository.findById(id).orElse(null);
	}
	
	public List<Pregled> findByStatus(StatusPregleda status){
		return pregledRespository.findByStatus(status);
	}

	public List<Pregled> findByPatientId(Long id) {
		return pregledRespository.findByPatientID(id);
	}
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void delete(Pregled pregled) {
		pregledRespository.delete(pregled);
	}

}
