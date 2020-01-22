package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.SalaDTO;
import com.example.demo.dto.TerminDTO;
import com.example.demo.model.Klinika;
import com.example.demo.model.Sala;
import com.example.demo.model.Termin;
import com.example.demo.service.KlinikaService;
import com.example.demo.service.SalaService;

@RestController
@RequestMapping(value = "sala")
public class SalaController {

	@Autowired
	SalaService salaService;
	
	@Autowired
	KlinikaService klinikaService;
	
	@RequestMapping(value = "/sveSale/{val}", method=RequestMethod.GET)
	public ResponseEntity<List<SalaDTO>> getSveSale(@PathVariable String val){
		List<Sala> sala = salaService.findAll();
		List<SalaDTO> sale = new ArrayList<SalaDTO>();
		Klinika klinika = klinikaService.findByName(val);
		for (Sala s : sala) {
			if(s.getKlinika().getId() == klinika.getId())
				sale.add(new SalaDTO(s));
		}
		
		return new ResponseEntity<>(sale, HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/termini/{val}", method = RequestMethod.GET)
	public ResponseEntity<List<TerminDTO>> getSviTermini(@PathVariable String val){
		List<TerminDTO> termini = new ArrayList<TerminDTO>();
		Long salaID = Long.parseLong(val);
		Optional<Sala> oSala = salaService.findById(salaID);
		Sala sala = oSala.get();
		
		for(Termin t : sala.getSlobodniTermini()) {
			TerminDTO addT = new TerminDTO(t);
			termini.add(addT);
		}
		
		return new ResponseEntity<>(termini, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/search/{val}", method = RequestMethod.GET)
	public ResponseEntity<List<SalaDTO>> getSale(@PathVariable String val){
		List<SalaDTO> sale = new ArrayList<SalaDTO>();
		
		if(val.equals(":"))
			return new ResponseEntity<>(null, HttpStatus.OK);
		
		boolean num = false;
		System.out.println(val);
		String[] parts = val.split(":");
		String name = parts[0];
		String date = parts[1];
		String klinika = parts[2];
		
	
		for(int i = 0; i < name.length(); i++) {
			if(!Character.isDigit(name.charAt(i))) {
				num = false;
				break;
			}
			num = true;
		}
		
		
		Long sId = 0L;
		if(num) {

			 sId = Long.parseLong(name);
			 Optional<Sala> s = salaService.findById(sId);
			 
			 if(s == null) {
				 System.out.println("Not found");
				 return new ResponseEntity<>(sale, HttpStatus.NOT_FOUND);
				
			 }
			 Sala foundS = s.get();
		
			 if(foundS.getKlinika().getIme().equals(klinika))
				 sale.add(new SalaDTO(foundS));
		}else {
			List<Sala> allS = salaService.findAll();
			
			for(Sala s: allS) {
				if(s.getIme().toLowerCase().contains(name.toLowerCase()) || s.getIme().toLowerCase().equals(name.toLowerCase())) {
					if(s.getKlinika().getIme().equals(klinika))
						sale.add(new SalaDTO(s));
				}
			}
		}
		
		
		return new ResponseEntity<>(sale, HttpStatus.OK);
	}
	
	
	
}
