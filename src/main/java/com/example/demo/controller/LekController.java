package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
	
}
