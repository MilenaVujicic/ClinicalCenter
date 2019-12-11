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

import com.example.demo.dto.PacijentDTO;
import com.example.demo.dto.ReceptDTO;
import com.example.demo.model.Recept;
import com.example.demo.service.ReceptService;

@RestController
@RequestMapping("recept")
public class ReceptController {

	@Autowired
	private ReceptService receptService;
	
	@RequestMapping(value = "/sviRecepti/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<Recept>> sviRecepti(@PathVariable("id") Long identifikacija) {
		List<Recept> sviRecepti = receptService.findAll();
		List<Recept> recepti = new ArrayList<Recept>();
		for(Recept recept : sviRecepti) {
			if (recept.getPacijent().getId().equals(identifikacija)) {
				recepti.add(recept);
			}
		}
		return new ResponseEntity<List<Recept>>(recepti, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/izmeni/{text}", method = RequestMethod.PUT)
	public ResponseEntity<ReceptDTO> izmeni(@PathVariable("text") String text, @RequestBody ReceptDTO receptDTO) {
		String[] splitter = text.split(" ");
		Recept recept = receptService.findOne(receptDTO.getId());
		recept.setNaziv(receptDTO.getNaziv());
		recept.setOpis(receptDTO.getOpis());
		recept.setLek(splitter[1]);
		recept.setSifraLek(splitter[0].substring(1, splitter[0].length()-1));
		Recept r = receptService.save(recept);
		return new ResponseEntity<ReceptDTO>(new ReceptDTO(r), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/obrisi/{id}", method = RequestMethod.GET)
	public ResponseEntity<String> obrisi(@PathVariable("id") Long identifikacija) {
		List<Recept> sviRecepti = receptService.findAll();
		Recept r = new Recept();
		for(Recept recept : sviRecepti) {
			if (recept.getId().equals(identifikacija)) {
				r = recept;
			}
		}
		receptService.delete(r);
		return new ResponseEntity<String>("", HttpStatus.OK);
	}
}
