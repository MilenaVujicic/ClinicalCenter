package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Uloga;

public interface UlogaRepository extends JpaRepository<Uloga, Long> {
	Uloga findByName(String name);
	
}
