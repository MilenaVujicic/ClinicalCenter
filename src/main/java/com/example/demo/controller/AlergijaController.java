package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Alergija;
import com.example.demo.service.AlergijaService;

@RestController
@RequestMapping(value = "alergija")
public class AlergijaController {

	@Autowired
	private AlergijaService alergijaService;
	
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
}
