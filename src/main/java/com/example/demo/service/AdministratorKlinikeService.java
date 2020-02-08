package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.AdministratorKlinike;
import com.example.demo.repository.AdministratorKlinikeRespository;

@Service
public class AdministratorKlinikeService {

	@Autowired
	AdministratorKlinikeRespository administratorKlinikeRespository;
	
	public AdministratorKlinike save(AdministratorKlinike administratorKlinike) {
		return administratorKlinikeRespository.save(administratorKlinike);
	}
	
	public List<AdministratorKlinike> findAll() {
		return administratorKlinikeRespository.findAll();
	}
	
	public AdministratorKlinike findOne(Long id) {
		return administratorKlinikeRespository.findById(id).orElseGet(null);
	}
	
	public Optional<AdministratorKlinike> findById(Long id) {
		return administratorKlinikeRespository.findById(id);
	}
	

	public Optional<AdministratorKlinike> findByIdKorisnik(Long id){
		return administratorKlinikeRespository.findByIdKorisnik(id);
	}
	
	public AdministratorKlinike findByIdKorisnik(String id) {
		return administratorKlinikeRespository.findByIdKorisnik(Long.parseLong(id)).get();
	}




}
