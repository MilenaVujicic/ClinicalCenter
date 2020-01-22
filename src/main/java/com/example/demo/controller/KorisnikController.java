package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.KorisnikDTO;
import com.example.demo.model.Korisnik;
import com.example.demo.service.EmailService;
import com.example.demo.service.KorisnikService;

@RestController
@RequestMapping(value = "korisnik")
public class KorisnikController {

	@Autowired
	KorisnikService korisnikService;
	
	@Autowired
	EmailService emailService;
	
	@RequestMapping(value = "/aktiviraj/{id}", method = RequestMethod.GET)
	public ResponseEntity<KorisnikDTO> aktiviraj(@PathVariable("id") Long identifikacija) {
		List<Korisnik> korisnici = korisnikService.findAll();
		Korisnik korisnik = new Korisnik();
		for (Korisnik k : korisnici) {
			if (k.getId().equals(identifikacija)) {
				k.setAktiviran(true);
				korisnik = k;
			}
		}
		Korisnik k = korisnikService.save(korisnik);
		try {
			emailService.sendNotificationActivation(korisnik);
		} catch (MailException | InterruptedException e) {
			// TODO Auto-generated catch block
			System.out.println("################ Desila se greska 3");
		}
		return new ResponseEntity<KorisnikDTO>(new KorisnikDTO(k), HttpStatus.OK);
	}
}
