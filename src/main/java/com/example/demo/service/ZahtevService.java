package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Zahtev;
import com.example.demo.repository.ZahtevRepository;


@Service
public class ZahtevService {

	@Autowired
	ZahtevRepository zahtevRepository;
	
	public Zahtev save(Zahtev z) {
		return zahtevRepository.save(z);
	}
	
	public List<Zahtev> findAll(){
		return zahtevRepository.findAll();
	}
	
}
