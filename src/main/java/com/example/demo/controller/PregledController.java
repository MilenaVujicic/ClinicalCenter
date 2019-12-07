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

import com.example.demo.model.Pregled;
import com.example.demo.service.PregledService;


@RestController
@RequestMapping(value = "pregled")
public class PregledController {

	@Autowired
	private PregledService pregledService;
	
	@RequestMapping(value = "/sviPregledi/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<Pregled>> sviPregledi(@PathVariable("id") Long identifikacija) {
		System.out.println("#####################");
		List<Pregled> sviPregledi = pregledService.findAll();
		List<Pregled> pregledi = new ArrayList<Pregled>();
		for (Pregled pregled : sviPregledi) {
			if (pregled.getPacijent().getId().equals(identifikacija)) {
				pregledi.add(pregled);
			}
		}
		return new ResponseEntity<List<Pregled>>(pregledi, HttpStatus.OK);
	}
}
