package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Doktor;
import com.example.demo.model.Klinika;
import com.example.demo.repository.DoktorRespository;


@Service
public class DoktorService {

	@Autowired
	DoktorRespository doktorRespository;
	
	public Doktor findOne(Long id) {
		return doktorRespository.findById(id).orElseGet(null);
	}
	
	public List<Doktor> findAll() {
		return doktorRespository.findAll();
	}

	public Doktor findByIdKorisnik(Long id) {
		return doktorRespository.findByIdKorisnik(id);
	}
	
	public List<Doktor> findAllByKlinika(Klinika k){
		return doktorRespository.findAllByKlinika(k);
	}
}
