package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.KorisnikDTO;
import com.example.demo.error.UserAlreadyExistException;
import com.example.demo.model.Korisnik;
import com.example.demo.model.Uloga;


public interface IUserService {
	Korisnik save(KorisnikDTO accountDto) throws UserAlreadyExistException;
	Korisnik findById(Long id);
	Korisnik findByUsername(String username);
	List<Korisnik> findByUloge(String uloge);
    List<Korisnik> findAll ();
}
