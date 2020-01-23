package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Odsustvo;
import com.example.demo.repository.OdsustvoRepository;

@Service
public class OdsustvoService {

	@Autowired
	private OdsustvoRepository odsustvoRepository;
	
	public Odsustvo save(Odsustvo odsustvo) {
		return odsustvoRepository.save(odsustvo);
	}
	
	public List<Odsustvo> findAll(){
		return odsustvoRepository.findAll();
	}
	
	public Optional<Odsustvo> findById(Long id){
		return odsustvoRepository.findById(id);
	}
	
	public void delete(Odsustvo o) {
		odsustvoRepository.delete(o);
	}
}
