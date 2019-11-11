package com.example.demo.service;

import com.example.demo.dto.KorisnikDTO;
import com.example.demo.error.UserAlreadyExistException;
import com.example.demo.model.Korisnik;

public interface IUserService {
	Korisnik registerNewUserAccount(KorisnikDTO accountDto) throws UserAlreadyExistException;
}
