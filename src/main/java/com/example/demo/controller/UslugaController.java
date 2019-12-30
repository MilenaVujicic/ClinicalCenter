package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UslugaDTO;
import com.example.demo.model.Usluga;
import com.example.demo.service.UslugaService;

import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;



@RestController
@RequestMapping(value = "usluga")
public class UslugaController {

	@Autowired
	private UslugaService uslugaService;
	
	@RequestMapping(value = "/nova_usluga", method = RequestMethod.POST)
	public ResponseEntity<Object> noviPregled(HttpEntity<String> json) throws ParseException{
		String jString = json.getBody();
		JSONParser parser = new JSONParser();
		JSONObject jObj = (JSONObject)parser.parse(jString);
		String eName = (String)jObj.get("eName");
		String eDescription = (String)jObj.get("eDescription");
		String ePriceS = (String)jObj.get("ePrice");
		String sel = (String)jObj.get("eSel");
		double ePrice = Double.parseDouble(ePriceS);
		Usluga u;
		if (sel.equals("New Examination")) {
			u = new Usluga(eName, eDescription, ePrice);
			uslugaService.save(u);
		}else {
			String[] parts = sel.split(".");
			Long eId = Long.parseLong(parts[0]);
			Optional<Usluga> ou = uslugaService.findById(eId);
			u = ou.get();
			u.setIme(eName);
			u.setOpis(eDescription);
			u.setCena(ePrice);
			uslugaService.save(u);
		}
		
		
	
		
		
		return new ResponseEntity<Object>(null, HttpStatus.OK);
	}

	@RequestMapping(value = "/sve_usluge", method = RequestMethod.GET)
	public ResponseEntity<List<UslugaDTO>> sveUsluge(){
		List<Usluga> usluge = uslugaService.findAll();
		List<UslugaDTO> retVal = new ArrayList<UslugaDTO>();
		
		for(Usluga u : usluge) {
			UslugaDTO temp = new UslugaDTO(u);
			retVal.add(temp);
		}
		
		return new ResponseEntity<List<UslugaDTO>>(retVal, HttpStatus.OK);
		
		
	}
}
