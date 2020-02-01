package com.example.demo.service;


import java.util.Arrays;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.KorisnikDTO;
import com.example.demo.error.UserAlreadyExistException;
import com.example.demo.model.Korisnik;
import com.example.demo.repository.KorisnikRepository;

@Service
public class UserService implements IUserService{
	
	@Autowired
    private KorisnikRepository korisnikRepository;
	
	//@Autowired
    //private PasswordEncoder passwordEncoder;

	@Transactional
	@Override
	public Korisnik registerNewUserAccount(KorisnikDTO accountDto) throws UserAlreadyExistException {
		if (emailExists(accountDto.getEmail())) {
            throw new UserAlreadyExistException("There is an account with that email adress: " + accountDto.getEmail());
        }
		final Korisnik user = new Korisnik();
		user.setIme(accountDto.getIme());
		user.setPrezime(accountDto.getPrezime());
		user.setEmail(accountDto.getEmail());
		user.setPassword(accountDto.getPassword()); //treba namestiti passwordEncoder
		user.setAdresa(accountDto.getAdresa());
		user.setGrad(accountDto.getGrad());
		user.setDrzava(accountDto.getDrzava());
		user.setJmbg(accountDto.getJmbg());
		//fale jos datumRodjenja i uloga, ali datumRodjenja ni ne treba pri registraciji da se unosi, treba da se ukloni
        return korisnikRepository.save(user);
	}
	
	private boolean emailExists(String email) {
        Korisnik user = korisnikRepository.findByEmail(email);
        if (user != null) {
            return true;
        }
        return false;
    }

}
