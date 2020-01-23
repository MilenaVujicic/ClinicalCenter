package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import com.example.demo.model.Odsustvo;
import com.example.demo.service.EmailService;
import com.example.demo.service.KorisnikService;
import com.example.demo.service.OdsustvoService;

import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;

@RestController
@RequestMapping(value = "administrator")
public class AdministratorController {

	@Autowired
	KorisnikService korisnikService;
	
	@Autowired
	OdsustvoService odsustvoService;
	
	@Autowired
	EmailService emailService;
	
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
	
	@RequestMapping(value = "/sva_odsustva", method = RequestMethod.GET)
	public ResponseEntity<List<Odsustvo>> svaOdsustva(){
		List<Odsustvo> retVal = new ArrayList<Odsustvo>();
		List<Odsustvo> allOdsustvo = odsustvoService.findAll();
		
		for(Odsustvo o : allOdsustvo) {
			if(!o.isOdobren()) {
				retVal.add(o);
			}
		}
		return new ResponseEntity<List<Odsustvo>>(retVal, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/odobreno_odsustvo/{id}")
	public ResponseEntity<Object> odobrenoOdsustvo(@PathVariable("id") String id){
		String[] parts = id.substring(1).split("-");
		String idK = parts[0];
		String idO = parts[1];
		Long lIdK = Long.parseLong(idK);
		Long lIdO = Long.parseLong(idO);
		Optional<Korisnik> k = korisnikService.findById(lIdK);
		Korisnik osoblje = k.get();
		Optional<Odsustvo> oo = odsustvoService.findById(lIdO);
		Odsustvo o = oo.get();
		o.setOdobren(true);
		odsustvoService.save(o);
		emailService.sendAbsenceAccept(osoblje, o);
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/odbijeno_odsustvo/{id}")
	public ResponseEntity<Object> odbijenoOdsustvo(@PathVariable("id") String id, String message){
		String[] parts = id.substring(1).split("-");
		String idK = parts[0];
		String idO = parts[1];
		Long lIdK = Long.parseLong(idK);
		Long lIdO = Long.parseLong(idO);
		Optional<Korisnik> k = korisnikService.findById(lIdK);
		Korisnik osoblje = k.get();
		Optional<Odsustvo> oo = odsustvoService.findById(lIdO);
		Odsustvo o = oo.get();
		
		emailService.senAbsenceDeny(osoblje, o, message);
		
		odsustvoService.delete(o);
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
}
