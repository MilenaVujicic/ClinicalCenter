package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.model.Uloga;
import com.example.demo.repository.UlogaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UlogaKorisnikaService implements IUlogaKorisnikaService {

	 @Autowired
	 private UlogaRepository ulogaKorisnikaRepository;
	 
	@Override
	public List<Uloga> findById(Long id) {
		Uloga uloga = this.ulogaKorisnikaRepository.getOne(id);
	    List<Uloga> uloge = new ArrayList<>();
	    uloge.add(uloga);
	    return uloge;
	}

	@Override
	public List<Uloga> findByname(String name) {
		Uloga uloga = this.ulogaKorisnikaRepository.findByName(name);
	    List<Uloga> uloge = new ArrayList<>();
	    uloge.add(uloga);
	    return uloge;
	}

}
