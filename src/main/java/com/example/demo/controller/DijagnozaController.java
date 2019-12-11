package com.example.demo.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.PregledDTO;
import com.example.demo.model.Dijagnoza;
import com.example.demo.model.Pregled;
import com.example.demo.service.DijagnozaService;
import com.example.demo.service.PregledService;

@RestController
@RequestMapping(value = "dijagnoza")
public class DijagnozaController {

	@Autowired
	public DijagnozaService dijagnozaService;
	
	@Autowired
	public PregledService pregledService;
	
	@RequestMapping(value = "/sve_dijagnoze", method = RequestMethod.GET)
	public ResponseEntity<List<Dijagnoza>> sveDijagnoze() {
		List<Dijagnoza> dijagnoze = dijagnozaService.findAll();
		return new ResponseEntity<List<Dijagnoza>>(dijagnoze, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/pregled/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<Dijagnoza>> pregled(@PathVariable("id") Long identifikacija) {
		List<Dijagnoza> sveDijagnoze = dijagnozaService.findAll();
		List<Dijagnoza> dijagnoze = new ArrayList<Dijagnoza>();
		for(Dijagnoza dijagnoza : sveDijagnoze) {
			for (Pregled pregled : dijagnoza.getPregledi()) {
				if(pregled.getId().equals(identifikacija)) {
					dijagnoze.add(dijagnoza);
				}
			}
		}
		return new ResponseEntity<List<Dijagnoza>>(dijagnoze, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/izmeni_pregled/{text}", method = RequestMethod.GET)
	public ResponseEntity<PregledDTO> izmeni_pregled(@PathVariable("text") String text) {
		String[] splitter = text.split("~");
		Long identifikacija = Long.parseLong(splitter[0]);
		Pregled pregled = pregledService.findOne(identifikacija);
		pregled.setDijagnoze(new HashSet<Dijagnoza>());
		for (int i = 1; i < splitter.length; i++) {
			Dijagnoza dijagnoza = dijagnozaService.findOne(Long.parseLong(splitter[i]));
			pregled.getDijagnoze().add(dijagnoza);
			
		}
		Pregled p = pregledService.save(pregled);
		return new ResponseEntity<PregledDTO>(new PregledDTO(p), HttpStatus.OK);
	}
	
}
