package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Pacijent;
import com.example.demo.repository.PacijentRepository;

@Service
public class PacijentService {

	@Autowired
	PacijentRepository pacijentRepository;
	
	public List<Pacijent> findAll() {
		return pacijentRepository.findAll();
	}
	
	public Pacijent findByIdKorisnik(Long idKorisnik) {
		return pacijentRepository.findByIdKorisnik(idKorisnik);
	}
	
}
