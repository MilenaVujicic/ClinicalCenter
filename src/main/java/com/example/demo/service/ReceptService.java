package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Recept;
import com.example.demo.repository.ReceptRespository;

@Service
public class ReceptService {

	@Autowired
	private ReceptRespository receptRespository;
	
	public List<Recept> findAll() {
		return receptRespository.findAll();
	}
	
	public Recept save(Recept recept) {
		return receptRespository.save(recept);
	}
	
	public void delete(Recept recept) {
		receptRespository.deleteById(recept.getId());
	}
	
	public Recept findOne(Long id) {
		return receptRespository.findById(id).orElse(null);
	} 
}
