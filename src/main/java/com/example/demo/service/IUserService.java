package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.KorisnikDTO;
import com.example.demo.error.UserAlreadyExistException;
import com.example.demo.model.Korisnik;


public interface IUserService {
	Korisnik save(KorisnikDTO accountDto) throws UserAlreadyExistException;
	Korisnik findById(Long id);
	Korisnik findByUsername(String username);
    List<Korisnik> findAll ();
}
