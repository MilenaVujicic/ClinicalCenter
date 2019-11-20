package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Korisnik;
import com.example.demo.model.Uloga;
import com.example.demo.repository.KorisnikRepository;

@Service
public class KorisnikService {

	@Autowired
	KorisnikRepository korisnikRepository;
	
	public List<Korisnik> findByUloge(String uloga) {
		List<Korisnik> korisnici = korisnikRepository.findByUloge(uloga);
		return korisnici;
	}
}
