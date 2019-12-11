package com.example.demo.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.TerminService;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;

@RestController
@RequestMapping(value = "termin")
public class TerminController {
	
	@Autowired
	TerminService terminService;	

	@RequestMapping(value = "/noviTermin", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public void dodavanjeTermina(HttpEntity<String> json) throws ParseException {
		String jString = json.getBody();
		
		JSONParser parser = new JSONParser();
		JSONObject jObj = (JSONObject)parser.parse(jString);
		String klinika = (String) jObj.get("klinika");
		String sala = (String) jObj.get("sala");
		String datum = (String) jObj.get("datum");
		String vreme = (String) jObj.get("vreme");
		String tip = (String) jObj.get("tip");
		String trajanje = (String) jObj.get("trajanje");
		String cena = (String) jObj.get("cena");
		String lekar = (String) jObj.get("lekar");
	}
}
