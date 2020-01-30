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
import com.example.demo.service.KorisnikService;

@RestController
@RequestMapping(value = "auth")
public class LoginController {
	
	@Autowired
    private KorisnikService korisnikService;
	
	@Autowired
    PasswordEncoder passwordEncoder;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ResponseEntity<Long> login(@RequestParam(value="user") String user,
									  @RequestParam(value="pass") String pass) {
		
		Korisnik korisnik = korisnikService.findByEmail(user);
		
		String sifra_iz_baze = korisnik.getPassword();
		String pass_encoded = passwordEncoder.encode(pass);
		
		if(passwordEncoder.matches(pass_encoded, sifra_iz_baze)) {
			System.out.println("Dobra sifra");
			System.out.println();
		}else {

			System.out.println(sifra_iz_baze+ "_______"+ pass_encoded);
			System.out.println("Pogresna sifra");
		}
		return new ResponseEntity<Long>(korisnik.getId(),HttpStatus.OK);
	}
	
}
