package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.AlergijaDTO;
import com.example.demo.model.Alergija;
import com.example.demo.model.Pacijent;
import com.example.demo.service.AlergijaService;
import com.example.demo.service.PacijentService;

@RestController
@RequestMapping(value = "alergija")
public class AlergijaController {

	@Autowired
	private AlergijaService alergijaService;
	
	@Autowired
	private PacijentService pacijentService;
	
	@RequestMapping(value = "/sveAlergije/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<Alergija>> sveAlergije(@PathVariable("id") Long identifikacija) {
		List<Alergija> sveAlergije = alergijaService.findAll();
		List<Alergija> alergije = new ArrayList<Alergija>();
		for (Alergija alergija : sveAlergije) {
			if(alergija.getPacijent().getId().equals(identifikacija)) {
				alergije.add(alergija);
			}
		}
		return new ResponseEntity<List<Alergija>>(alergije, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/dodaj/{id}", method = RequestMethod.POST)
	public ResponseEntity<AlergijaDTO> dodaj(@PathVariable("id") Long identifikacija, @RequestBody AlergijaDTO alergijaDTO) {
		Alergija alergija = new Alergija();
		alergija.setNaziv(alergijaDTO.getNaziv());
		alergija.setOpis(alergijaDTO.getOpis());
		Pacijent pacijent = pacijentService.findOne(identifikacija);
		alergija.setPacijent(pacijent);
		Alergija a = alergijaService.save(alergija);
		return new ResponseEntity<AlergijaDTO>(new AlergijaDTO(a),HttpStatus.OK);
	}
	
	@RequestMapping(value = "/izmeni/{id}", method = RequestMethod.PUT)
	public ResponseEntity<AlergijaDTO> izmeni(@PathVariable("id") Long identifikacija, @RequestBody Alergija alergija) {
		Alergija a = null;
		try {
			a = alergijaService.update(alergija, identifikacija);
		} catch (Exception e) {
			System.out.println("Ne moze updatovati");
		}
		return new ResponseEntity<AlergijaDTO>(new AlergijaDTO(a), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/obrisi/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> obrisi(@PathVariable("id") Long identifikacija) {
		System.out.println("####" + alergijaService.findAll().size());
		Alergija alergija = alergijaService.findOne(identifikacija);
		alergijaService.delete(alergija);
		System.out.println("####" + alergijaService.findAll().size() + "##");
		return new ResponseEntity<String>("Obrisano", HttpStatus.OK);
	}
}
