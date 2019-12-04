package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Dijagnoza;
import com.example.demo.service.DijagnozaService;

@RestController
@RequestMapping(value = "dijagnoza")
public class DijagnozaController {

	@Autowired
	public DijagnozaService dijagnozaService;
	
	@RequestMapping(value = "/sve_dijagnoze", method = RequestMethod.GET)
	public ResponseEntity<List<Dijagnoza>> sveDijagnoze() {
		List<Dijagnoza> dijagnoze = dijagnozaService.findAll();
		return new ResponseEntity<List<Dijagnoza>>(dijagnoze, HttpStatus.OK);
	}
	
}
