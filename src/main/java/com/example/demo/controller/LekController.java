package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.LekDTO;
import com.example.demo.model.Lek;
import com.example.demo.service.LekService;

@RestController
@RequestMapping(value = "lek")
public class LekController {

	@Autowired
	public LekService lekService;
	
	@RequestMapping(value = "/svi_lekovi", method = RequestMethod.GET)
	public ResponseEntity<List<Lek>> sviLekovi() {
		List<Lek> lekovi = lekService.findAll();
		return new ResponseEntity<List<Lek>>(lekovi, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/obrisi/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> obrisiLek(@PathVariable("id") Long identifikacija) {
		Lek lek = lekService.findOne(identifikacija);
		lekService.delete(lek);
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/izmeni", method = RequestMethod.PUT)
	public ResponseEntity<LekDTO> izmeniLek(@RequestBody LekDTO lekDTO) {
		Lek lek = lekService.findOne(lekDTO.getId());
		lek.setIme(lekDTO.getIme());
		lek.setSifra(lekDTO.getSifra());
		lek.setOpis(lekDTO.getOpis());
		Lek l = lekService.save(lek);
		return new ResponseEntity<LekDTO>(new LekDTO(l), HttpStatus.OK);
	}
	
}
