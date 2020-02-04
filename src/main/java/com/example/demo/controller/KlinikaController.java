package com.example.demo.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.KlinikaDTO;
import com.example.demo.model.AdministratorKlinike;
import com.example.demo.model.Klinika;
import com.example.demo.service.AdministratorKlinikeService;
import com.example.demo.service.KlinikaService;

import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;



@RestController
@RequestMapping(value = "klinika")
public class KlinikaController {

	@Autowired
	KlinikaService klinikaService;
	
	
	@Autowired
	AdministratorKlinikeService administratorService;
	
	@RequestMapping(value = "editClinic", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces="application/json")
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<Object>uredjivanjeKlinike(@RequestBody String param) throws ParseException{
		System.out.println(param);
		param = param.substring(1);
		param = param.substring(0, param.length()-1);
		String naziv = "";
		String adresa = "";
		String opis = "";
		String[] parts = param.split(",");
		int i = 0;
		for(String p : parts){
			System.out.println(p);
			String[] tokens = p.split(":");
			if (i == 0) {
				naziv = tokens[1];
				naziv = naziv.replace("\"", "");
			}else if(i == 1) {
				adresa = tokens[1];
				adresa = adresa.replace("\"", "");
			}else if(i == 2) {
				opis = tokens[1];
				opis = opis.replace("\"", "");
			}
			i++;
		}
		
	

		System.out.println(naziv + " " + adresa + " " + opis);
		 
		Klinika k = klinikaService.findByName(naziv);
		if(k != null) {
			//System.out.println(k);

			k.setAdresa(adresa);
			System.out.println(k.getAdresa());
			k.setOpis(opis);
			k = klinikaService.save(k);
		}else {
			
			System.out.println("ERR");
			return new ResponseEntity<Object>(null, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Object>(null, HttpStatus.OK);
	}
	
	@RequestMapping(value = "editClinic/{id}", method = RequestMethod.POST)
	public ResponseEntity<Object>uredjivanjeKlinikeAdmin(@PathVariable("id") Long id, HttpEntity<String> json) throws ParseException{
		
		Optional<AdministratorKlinike> ak = administratorService.findByIdKorisnik(id);
		
	
		Optional<Klinika> k = klinikaService.findById(ak.get().getKlinika().getId());
		Klinika kl = k.get();
		String jString = json.getBody();
		JSONParser parser = new JSONParser();
		JSONObject jObj = (JSONObject)parser.parse(jString);
		String ime = (String) jObj.get("naziv");
		String opis = (String) jObj.get("opis");
		String adresa = (String) jObj.get("adresa");
	
		kl.setIme(ime);
		kl.setOpis(opis);
		kl.setAdresa(adresa);
		Klinika s = klinikaService.save(kl);
		System.out.println(s.getIme());
		return new ResponseEntity<Object>(null, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "sve_klinike", method = RequestMethod.GET)
	public ResponseEntity<List<KlinikaDTO>> sveKlinike(){
		List<Klinika> klinike = klinikaService.findAll();
		List<KlinikaDTO> retVal = new ArrayList<KlinikaDTO>();
		for(Klinika k : klinike) {
			KlinikaDTO kd = new KlinikaDTO(k);
			retVal.add(kd);
		}
		
		return new ResponseEntity<List<KlinikaDTO>>(retVal, HttpStatus.OK);
	}
}
