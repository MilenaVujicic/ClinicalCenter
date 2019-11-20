package com.example.demo.controller;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.demo.dto.KorisnikDTO;
import com.example.demo.error.UserAlreadyExistException;
import com.example.demo.model.Korisnik;
import com.example.demo.service.CustomUserDetailsService;
import com.example.demo.service.IUserService;
import com.example.demo.service.UserService;


import org.springframework.stereotype.Controller;

@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthenticationController {
		
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private CustomUserDetailsService userDetailsService;
	
	@Autowired
	private UserService userService;
	
	
	@RequestMapping(method = POST, value = "/signup")
	public ResponseEntity<Korisnik> addUser(@RequestBody String stringRequest, UriComponentsBuilder ucBuilder) {

		System.out.println(stringRequest);
		stringRequest.replace('+', ' ');
		String splitPoEQ[] = stringRequest.split("&");
		
		
		String name[] = splitPoEQ[0].split("=");
		String nameFinal = name[1];//.replace('+', ' ');
		
		String surname[] = splitPoEQ[1].split("=");
		String surnameFinal = surname[1];
		
		String email[] = splitPoEQ[2].split("=");
		String emailFinal = email[1].replace("%40", "@");
		
		String address[] = splitPoEQ[3].split("=");
		String addressFinal = address[1];
		
		String city[] = splitPoEQ[4].split("=");
		String cityFinal = city[1];//.replace('+', ' ');
		
		String state[] = splitPoEQ[5].split("=");
		String stateFinal = state[1];
		
		String phone[] = splitPoEQ[6].split("=");
		String phoneFinal = phone[1];
		
		String jmbg[] = splitPoEQ[7].split("=");
		String jmbgFinal = jmbg[1];
		
		String pass[] = splitPoEQ[8].split("=");
		String passFinal = pass[1];
		
		String confirm[] = splitPoEQ[9].split("=");
		String confirmFinal = confirm[1];
		//System.out.println(nameFinal + " " + surnameFinal + " " + emailFInal);
		Korisnik existUser = this.userService.findByUsername(emailFinal); //samo prosledi email
		if (existUser != null) {
			throw new UserAlreadyExistException();
		}
		Long jmbgFinalFinal = Long.parseLong(jmbgFinal);
		KorisnikDTO userRequest = new KorisnikDTO(nameFinal,surnameFinal,emailFinal,
				addressFinal,cityFinal,stateFinal,phoneFinal,jmbgFinalFinal,passFinal,confirmFinal);
		Korisnik user = this.userService.save(userRequest);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/user/{userId}").buildAndExpand(user.getId()).toUri());
		return new ResponseEntity<Korisnik>(user, HttpStatus.CREATED);
	}
	
	
}
