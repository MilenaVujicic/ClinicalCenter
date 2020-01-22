package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.KorisnikDTO;
import com.example.demo.model.Korisnik;
import com.example.demo.service.KorisnikService;

import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;

@RestController
@RequestMapping(value = "administrator")
public class AdministratorController {

	@Autowired
	KorisnikService korisnikService;
	
	@RequestMapping(value = "/trenutniAdmin/{id}", method = RequestMethod.GET)
	public ResponseEntity<KorisnikDTO> trenutniAdmin(@PathVariable("id") Long adminID){
		KorisnikDTO k = null;
		
		Korisnik korisnik = korisnikService.findOne(adminID);
	
		k = new KorisnikDTO(korisnik);
		
		System.out.println(korisnik.getUsername());
		return new ResponseEntity<>(k, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/promeni/{id}", method = RequestMethod.POST)
	public ResponseEntity<String> promenaPodataka(@PathVariable("id") Long adminID, HttpEntity<String> json) throws ParseException {
		
		
		String jString = json.getBody();
		JSONParser parser = new JSONParser();
		JSONObject jObj = (JSONObject)parser.parse(jString);
		String name = (String)jObj.get("name");
		String surname = (String)jObj.get("surname");
		String email = (String)jObj.get("email");
		String city = (String)jObj.get("city");
		String address = (String)jObj.get("address");
		String country = (String)jObj.get("country");
		String phoneS = (String)jObj.get("phone");
		//int phone = Integer.parseInt(phoneS);
		
		Korisnik k = korisnikService.findOne(adminID);
		k.setIme(name);
		k.setPrezime(surname);
		k.setEmail(email);
		k.setGrad(city);
		k.setDrzava(country);
		k.setAdresa(address);
		k.setTelefon(phoneS);
		
		korisnikService.save(k);
		return new ResponseEntity<>("uspsena izmena", HttpStatus.OK);
	}
	
}
