package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dto.KorisnikDTO;
import com.example.demo.error.UserAlreadyExistException;
import com.example.demo.model.AdministratorKlinickogCentra;
import com.example.demo.model.Korisnik;
import com.example.demo.service.AdministratorKlinickogCentraService;
import com.example.demo.service.EmailService;
import com.example.demo.service.IUserService;
import com.example.demo.service.KorisnikService;

import org.springframework.stereotype.Controller;

@RestController
@RequestMapping(value = "registration")
public class RegistrationController {
	
	@Autowired
    private KorisnikService korisnikService;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private AdministratorKlinickogCentraService administratorKlinickogCentraService;
	
	@RequestMapping(value = "/register", method = RequestMethod.PUT)
	public ResponseEntity<String> registracijaPacijenta(@RequestParam(value="ime") String ime,
														@RequestParam(value="prz") String prezime,
														@RequestParam(value="email") String email,
														@RequestParam(value="address") String address,
														@RequestParam(value="city") String city,
														@RequestParam(value="state") String state,
														@RequestParam(value="phone") String phone,
														@RequestParam(value="id") String id,
														@RequestParam(value="password") String password,
														@RequestParam(value="confirm") String confirm) {
		
		if(!password.equals(confirm)) {
			System.out.println("Passwords don't match!");
			return new ResponseEntity<String>("Passwords don't match", HttpStatus.CONFLICT);
			
		}
		Long id_l = null;
		try {
			id_l = Long.parseLong(id);
		} catch (Exception e) {
			System.out.println("Pogresan format za JMBG");
			return new ResponseEntity<String>("Pogresan format za JMBG", HttpStatus.CONFLICT);
		}
		
		KorisnikDTO korisnikDTO = new KorisnikDTO();
		korisnikDTO.setIme(ime);
		korisnikDTO.setPrezime(prezime);
		korisnikDTO.setEmail(email);
		korisnikDTO.setAdresa(address);
		korisnikDTO.setGrad(city);
		korisnikDTO.setDrzava(state);
		korisnikDTO.setTelefon(phone);
		korisnikDTO.setJmbg(id_l);
		korisnikDTO.setPassword(password);
		korisnikDTO.setMatchingPassword(confirm);
		
		Korisnik korisnik = korisnikService.registerNewUserAccount(korisnikDTO);
		
		List<AdministratorKlinickogCentra> admini = administratorKlinickogCentraService.findAll();
		AdministratorKlinickogCentra admin = admini.get(0);
		Korisnik admin_k = korisnikService.findOne(admin.getIdKorisnik());
		emailService.sendRegistrationRequest(korisnik, admin_k);
		
		return new ResponseEntity<String>("",HttpStatus.OK);
	}
	
	
}
