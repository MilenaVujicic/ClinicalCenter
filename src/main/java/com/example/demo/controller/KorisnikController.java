package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.KorisnikDTO;
import com.example.demo.dto.PacijentDTO;
import com.example.demo.model.Korisnik;
import com.example.demo.model.Pacijent;
import com.example.demo.service.EmailService;
import com.example.demo.service.KorisnikService;

@RestController
@RequestMapping(value = "korisnik")
public class KorisnikController {

	@Autowired
	KorisnikService korisnikService;
	
	@Autowired
	EmailService emailService;


	@RequestMapping(value = "/korisnik/{id}", method=RequestMethod.GET)
	public Korisnik getKorisnik(@PathVariable Long id) {
		Korisnik korisnik = korisnikService.findOne(id);
		return korisnik;
	}
	
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
	
	@RequestMapping(value = "/preuzmi/{id}", method = RequestMethod.GET)
	public ResponseEntity<Korisnik> preuzmi(@PathVariable("id") Long identifikacija) {
		Korisnik korisnik = korisnikService.findOne(identifikacija);
		return new ResponseEntity<Korisnik>(korisnik, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/izmeni", method = RequestMethod.PUT)
	public ResponseEntity<KorisnikDTO> izmeni(@RequestBody KorisnikDTO korisnikDTO) {
		Korisnik korisnik = korisnikService.findOne(korisnikDTO.getId());
		korisnik.setIme(korisnikDTO.getIme());
		korisnik.setPrezime(korisnikDTO.getPrezime());
		korisnik.setTelefon(korisnikDTO.getTelefon());
		Korisnik k = korisnikService.save(korisnik);
		return new ResponseEntity<KorisnikDTO>(new KorisnikDTO(k), HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/izmena_podataka/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Object> izmeni(@PathVariable("id") Long id,@RequestBody KorisnikDTO korisnik){
		System.out.println("######" + korisnik.getIme());
		Optional<Korisnik> ok = korisnikService.findById(id);
		Korisnik k = ok.get();
	
		if(k != null) {
			if(!korisnik.getIme().equals("")) {
				k.setIme(korisnik.getIme());
			}
			if(!korisnik.getPrezime().equals("")) {
				k.setPrezime(korisnik.getPrezime());
			}
			if(!korisnik.getDrzava().equals("")) {
				k.setDrzava(korisnik.getDrzava());
			}
			if(!korisnik.getGrad().equals("")) {
				k.setGrad(korisnik.getGrad());
			}
			if (korisnik.getPassword() != null ) {
				if(!korisnik.getPassword().equals("")) {
					k.setPassword(korisnik.getPassword());
				}
			}
			if(!korisnik.getAdresa().equals("")) {
				k.setAdresa(korisnik.getAdresa());
			}
			if(!korisnik.getTelefon().equals("")) {
				k.setTelefon(korisnik.getTelefon());
			}
			if(!korisnik.getEmail().equals("")) {
				k.setEmail(korisnik.getEmail());
			}
		} 
		
		korisnikService.save(k);
		
		
		return new ResponseEntity<Object>(null, HttpStatus.OK);
	}
	
	@RequestMapping(value = "podaci/{id}", method = RequestMethod.GET)
	public ResponseEntity<KorisnikDTO> podaci(@PathVariable("id") Long id){
		Korisnik korisnik = korisnikService.findOne(id);
		
		KorisnikDTO k = new KorisnikDTO(korisnik);
		
		return new ResponseEntity<KorisnikDTO>(k, HttpStatus.OK);
	}
	
	@RequestMapping(value = "promeni_lozinku/{id}/{op}/{np}/{cp}", method = RequestMethod.GET)
	public ResponseEntity<String> change_password(@PathVariable("id") Long identifikacija,
												  @PathVariable("op") String oldPassword,
												  @PathVariable("np") String newPassword,
												  @PathVariable("cp") String confirmPassword) {
		
		if (!newPassword.equals(confirmPassword)) {
			System.out.println("####1");
			return new ResponseEntity<String>("Nova i potvrdjena lozinka se ne poklapaju", HttpStatus.BAD_REQUEST);
		}
		
		Korisnik korisnik = korisnikService.findOne(identifikacija);
		
		if (!korisnik.getPassword().equals(oldPassword)) {
			System.out.println("####2");
			return new ResponseEntity<String>("Stara lozinka nije ova", HttpStatus.BAD_REQUEST);
		}
		
		if (newPassword.equals(oldPassword)) {
			System.out.println("####3");
			return new ResponseEntity<String>("Nova i stara lozinka ne smeju biti isti", HttpStatus.BAD_REQUEST);
		}
		
		korisnik.setPassword(newPassword);
		
		Korisnik k = korisnikService.save(korisnik);
		
		return new ResponseEntity<String>("Uspesno promenjena lozinka", HttpStatus.OK);
	}

}
