package com.example.demo.service;

import java.util.List;

import com.example.demo.model.UlogaKorisnika;

public interface IUlogaKorisnikaService {
	List<UlogaKorisnika> findById(Long id);
	List<UlogaKorisnika> findByname(String name);
}
