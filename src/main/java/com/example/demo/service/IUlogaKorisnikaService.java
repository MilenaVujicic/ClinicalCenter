package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Uloga;

public interface IUlogaKorisnikaService {
	List<Uloga> findById(Long id);
	List<Uloga> findByname(String name);
}
