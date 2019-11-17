package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.model.UlogaKorisnika;
import com.example.demo.repository.UlogaKorisnikaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UlogaKorisnikaService implements IUlogaKorisnikaService {

	 @Autowired
	 private UlogaKorisnikaRepository ulogaKorisnikaRepository;
	 
	@Override
	public List<UlogaKorisnika> findById(Long id) {
		UlogaKorisnika uloga = this.ulogaKorisnikaRepository.getOne(id);
	    List<UlogaKorisnika> uloge = new ArrayList<>();
	    uloge.add(uloga);
	    return uloge;
	}

	@Override
	public List<UlogaKorisnika> findByname(String name) {
		UlogaKorisnika uloga = this.ulogaKorisnikaRepository.findByName(name);
	    List<UlogaKorisnika> uloge = new ArrayList<>();
	    uloge.add(uloga);
	    return uloge;
	}

}
