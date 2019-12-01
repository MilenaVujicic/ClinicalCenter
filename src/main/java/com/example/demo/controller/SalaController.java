package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.SalaDTO;
import com.example.demo.model.Sala;
import com.example.demo.service.SalaService;

@RestController
@RequestMapping(value = "sala")
public class SalaController {

	@Autowired
	SalaService salaService;
	
	@RequestMapping(value = "/sveSale", method=RequestMethod.GET)
	public ResponseEntity<List<SalaDTO>> getSveSale(){
		List<Sala> sala = salaService.findAll();
		List<SalaDTO> sale = new ArrayList<SalaDTO>();
		
		for (Sala s : sala) {
			sale.add(new SalaDTO(s));
		}
		
		return new ResponseEntity<>(sale, HttpStatus.OK);
		
	}
	
}
