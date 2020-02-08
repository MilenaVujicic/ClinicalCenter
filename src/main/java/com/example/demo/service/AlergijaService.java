package com.example.demo.service;

import java.util.List;

import org.openqa.selenium.Alert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Alergija;
import com.example.demo.repository.AlergijaRepository;

@Service
public class AlergijaService {

	@Autowired
	private AlergijaRepository alergijaRepository;
	
	public List<Alergija> findAll() {
		return alergijaRepository.findAll();
	}
	
	public Alergija save(Alergija alergija) {
		return alergijaRepository.save(alergija);
	}
	
	public Alergija findOne(Long id) {
		return alergijaRepository.findById(id).orElse(null);
	}
	
	public void delete(Alergija alergija) {
		alergijaRepository.delete(alergija);
	}

	public Alergija update(Alergija alergija, Long identifikacija) throws Exception {
		Alergija alergijaToUpdate = findOne(identifikacija);
		alergijaToUpdate.setNaziv(alergija.getNaziv());
		alergijaToUpdate.setOpis(alergija.getOpis());
		alergijaToUpdate.setPacijent(alergija.getPacijent());
		alergijaRepository.save(alergijaToUpdate);
        return alergijaToUpdate; 
	}
}
