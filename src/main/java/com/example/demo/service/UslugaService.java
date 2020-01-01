package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.example.demo.model.Usluga;
import com.example.demo.repository.UslugaRepository;

@Service
public class UslugaService{

	@Autowired
	private UslugaRepository uslugaRepository;
	
	public List<Usluga> findAll(){
		return uslugaRepository.findAll();
	}
	public Optional<Usluga> findById(Long id){
		return uslugaRepository.findById(id);
	}
	public Usluga save(Usluga u) {
		return uslugaRepository.save(u);
	}
	
}
