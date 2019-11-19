package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.KlinikaDTO;
import com.example.demo.model.Klinika;
import com.example.demo.service.KlinikaService;

@RestController
@RequestMapping(value = "pacijent")
public class PacijentController {
	
	@Autowired
	KlinikaService klinikaService;
	
	@RequestMapping(value = "/sveKlinike", method=RequestMethod.GET)
	public ResponseEntity<List<KlinikaDTO>> getAllClinics() {
		List<Klinika> clinics = klinikaService.findAll();
		List<KlinikaDTO> klinikaDTO = new ArrayList<>();
		
		for (Klinika k : clinics) {
			klinikaDTO.add(new KlinikaDTO(k));
		}
		return new ResponseEntity<>(klinikaDTO, HttpStatus.OK);
	}
}
