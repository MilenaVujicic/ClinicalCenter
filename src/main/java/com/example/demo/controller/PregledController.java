package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.PregledDTO;
import com.example.demo.model.Pregled;
import com.example.demo.model.StatusPregleda;
import com.example.demo.service.PregledService;

import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;


@RestController
@RequestMapping(value = "pregled")
public class PregledController {

	@Autowired
	private PregledService pregledService;
	
	@RequestMapping(value = "/sviPregledi/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<Pregled>> sviPregledi(@PathVariable("id") Long identifikacija) {
		List<Pregled> sviPregledi = pregledService.findAll();
		List<Pregled> pregledi = new ArrayList<Pregled>();
		for (Pregled pregled : sviPregledi) {
			if (pregled.getPacijent().getId().equals(identifikacija) && pregled.getStatus().equals(StatusPregleda.ZAVRSEN)) {
				pregledi.add(pregled);
			}
		}
		System.out.println("#############" + pregledi.size());
		return new ResponseEntity<List<Pregled>>(pregledi, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/izmeni", method = RequestMethod.PUT)
	public ResponseEntity<PregledDTO> izmeni(@RequestBody PregledDTO pregledDTO) {
		Pregled pregled = pregledService.findOne(pregledDTO.getId());
		pregled.setNaziv(pregledDTO.getNaziv());
		pregled.setAnamneza(pregledDTO.getAnamneza());
		pregled.setTipPregleda(pregledDTO.getTipPregleda());
		pregled.setCena(pregledDTO.getCena());
		Pregled p = pregledService.save(pregled);
		return new ResponseEntity<PregledDTO>(new PregledDTO(p), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/obrisi/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> obrisi(@PathVariable("id") Long identifikacija) {
		Pregled pregled = pregledService.findOne(identifikacija);
		pregledService.delete(pregled);
		return new ResponseEntity<String>("", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/preuzmi/{id}", method = RequestMethod.GET)
	public ResponseEntity<Pregled> preuzmi(@PathVariable("id") Long identifikacija) {
		Pregled pregled = pregledService.findOne(identifikacija);
		return new ResponseEntity<Pregled>(pregled, HttpStatus.OK);
	}

}
