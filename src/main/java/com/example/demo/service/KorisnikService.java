package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Korisnik;
import com.example.demo.model.UlogaKorisnika;
import com.example.demo.repository.KorisnikRepository;

@Service
public class KorisnikService {

	@Autowired
	KorisnikRepository korisnikRepository;
	
	public List<Korisnik> findByUloga(UlogaKorisnika uloga) {
		return korisnikRepository.findByUloga(uloga);
	}
	
	public Korisnik save(Korisnik korisnik) {
		return korisnikRepository.save(korisnik);
	}
	
	public void delete(Korisnik korisnik) {
		korisnikRepository.delete(korisnik);
	}
	
	public List<Korisnik> findByAktivan(Boolean aktivan) {
		return korisnikRepository.findByAktivan(aktivan);
	}
	
	public List<Korisnik> findAll() {
		return korisnikRepository.findAll();
	}
	

	public List<Korisnik> findByIme(String ime){
		return korisnikRepository.findByIme(ime);
	}
	
	public List<Korisnik> findByPrezime(String prezime){
		return korisnikRepository.findByPrezime(prezime);
	}
	
	public Optional<Korisnik> findById(Long id){
		return korisnikRepository.findById(id);
	}
	
	

	public Korisnik findById(Long id) {
		return korisnikRepository.findById(id).orElseGet(null);
	}


}
