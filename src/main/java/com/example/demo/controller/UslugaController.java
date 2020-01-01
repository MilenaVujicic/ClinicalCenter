package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UslugaDTO;
import com.example.demo.model.Usluga;
import com.example.demo.service.UslugaService;

import net.minidev.json.parser.ParseException;



@RestController
@RequestMapping(value = "usluga")
public class UslugaController {

	@Autowired
	private UslugaService uslugaService;
	
	@RequestMapping(value = "/nova_usluga", method = RequestMethod.POST)
	public ResponseEntity<Object> noviPregled(@RequestBody String json) throws ParseException{
		System.out.println(json);
		String[] selData = null;
		String sel = null;
		String[] nameData = null;
		String eName = null;
		String[] priceData = null;
		String priceS = null;
		double ePrice = 0.0;
		String[] descData = null;
		String eDescription = null;
		
		String[] data = json.split("&");
		
		if((data[0].charAt(data[0].length()-1)) != '=') {
			selData = data[0].split("=");
			sel = selData[1];
			
		}
		
		if((data[1].charAt(data[1].length()-1)) != '=') {
			nameData = data[1].split("=");
			eName = nameData[1];
			if(eName.contains("\\+"))
				eName.replaceAll("\\+", " ");
		}
		
		if((data[2].charAt(data[2].length()-1)) != '=') {
			priceData = data[2].split("=");
			priceS = priceData[1];
			ePrice = Double.parseDouble(priceS);
		}
		
		
		if((data[3].charAt(data[3].length()-1)) != '=') {
			descData = data[3].split("=");
			eDescription = descData[1];
			if(eDescription.contains("\\+"))
				eDescription.replaceAll("\\+", " ");
		}

		Usluga u;
		if (sel.equals("New+Examination")) {
			u = new Usluga(eName, eDescription, ePrice);
			uslugaService.save(u);
		}else {
			System.out.println(sel);
			String[] parts = sel.split("\\.");
			System.out.println(parts.length);
			Long eId = Long.parseLong(parts[0]);
			Optional<Usluga> ou = uslugaService.findById(eId);
			u = ou.get();
			if(eName != null)
				u.setIme(eName);
			if(eDescription != null)
				u.setOpis(eDescription);
			if(ePrice != 0.0)
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
