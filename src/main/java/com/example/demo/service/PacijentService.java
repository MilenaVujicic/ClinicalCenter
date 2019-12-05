package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Klinika;
import com.example.demo.model.Pacijent;
import com.example.demo.repository.PacijentRepository;

@Service
public class PacijentService {

	@Autowired
	PacijentRepository pacijentRepository;
	
	public List<Pacijent> findAll() {
		return pacijentRepository.findAll();
	}
	
	public List<Pacijent> findAllByKlinika(Klinika klinika){
		return pacijentRepository.findAllByKlinika(klinika);
	}
	
	public Pacijent findByIdKorisnik(Long idKorisnik) {
		return pacijentRepository.findByIdKorisnik(idKorisnik);
	}
	

	
	public Pacijent findByVisina(int visina) {
		return pacijentRepository.findByVisina(visina);
	}
	
	public Pacijent findByDioptrija(double dioptrija) {
		return pacijentRepository.findByDioptrija(dioptrija);
	}
	


	public Pacijent findOne(Long id) {
		return pacijentRepository.findById(id).orElseGet(null);
	}
	
}

