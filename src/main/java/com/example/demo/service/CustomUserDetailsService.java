package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.demo.model.Korisnik;
import com.example.demo.repository.KorisnikRepository;


public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private KorisnikRepository korisnikRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Korisnik user = korisnikRepository.findByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException(String.format("No user found with email '%s'.", email));
		} else {
			return user;
		}
	}

}
