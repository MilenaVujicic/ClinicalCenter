package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Korisnik;
import com.example.demo.model.LogedUser;
import com.example.demo.model.UlogaKorisnika;
import com.example.demo.service.KorisnikService;

@RestController
@RequestMapping(value = "auth")
public class LoginController {
	
	@Autowired
    private KorisnikService korisnikService;
	
	@Autowired
    PasswordEncoder passwordEncoder;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ResponseEntity<String> login(@RequestParam(value="user") String user,
									  @RequestParam(value="pass") String pass) {
		
		Korisnik korisnik = korisnikService.findByEmail(user);
		if(korisnik == null) {
			return new ResponseEntity<String>("Email i Sifra se ne poklapaju.", HttpStatus.CONFLICT);
		}
		
		String sifra_iz_baze = korisnik.getPassword();
		
		if(korisnik.getUloga() == UlogaKorisnika.PACIJENT) {
			if(pass.equals(sifra_iz_baze)) { //passwordEncoder.matches(pass, sifra_iz_baze
				System.out.println("Dobra sifra");
			}else {
				System.out.println("Pogresna sifra");
				return new ResponseEntity<String>("Email i Sifra se ne poklapaju.", HttpStatus.CONFLICT);
			}
		} else {
			if(pass.equals(sifra_iz_baze)) {
				System.out.println("Dobra sifra");
			}else {

				System.out.println("Pogresna sifra");
				return new ResponseEntity<String>("Email i Sifra se ne poklapaju.", HttpStatus.CONFLICT);
			}
		}
		
		
		if(!korisnik.isAktiviran()) {
			System.out.println(korisnik.getId().toString());
			String response = korisnik.getId().toString();
			korisnik.setBrojPrijava(korisnik.getBrojPrijava() + 1);
			Korisnik k = korisnikService.save(korisnik);
			LogedUser logedUser = LogedUser.getInstance();
			logedUser.setUserId(korisnik.getId());
			logedUser.setUserRole(korisnik.getUloga());
			return new ResponseEntity<String>(response, HttpStatus.OK);
		}else {
			System.out.println(new ResponseEntity<String>("Nalog nije aktiviran.", HttpStatus.CONFLICT));
			return new ResponseEntity<String>("Nalog nije aktiviran.", HttpStatus.CONFLICT);
		}
		
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ResponseEntity<String> logout() {
		LogedUser logedUser = LogedUser.getInstance();
		logedUser.setUserId(null);
		logedUser.setUserRole(null);
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
}
