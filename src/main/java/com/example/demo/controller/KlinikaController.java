package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Klinika;
import com.example.demo.service.KlinikaService;

import net.minidev.json.JSONObject;

@RestController
@RequestMapping(value = "klinika")
public class KlinikaController {

	@Autowired
	KlinikaService klinikaService;
	
	@RequestMapping(value = "editClinic", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces="application/json")
	@ResponseStatus(value = HttpStatus.OK)
	public void uredjivanjeKlinike(@RequestBody String param){
		System.out.println(param);
		
		String[] parts = param.split(",");
		parts = parts[0].split(":");
		String ime = parts[1];
		ime = ime.replace("\"", "");
		
		System.out.println(ime);
		
		Klinika k = klinikaService.findByName(ime);
		if(k != null) {
			System.out.println(k);
		}else {
			System.out.println("ERR");
		}
	}
}
