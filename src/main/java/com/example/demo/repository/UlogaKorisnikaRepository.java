package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.UlogaKorisnika;

public interface UlogaKorisnikaRepository extends JpaRepository<UlogaKorisnika, Long> {
	UlogaKorisnika findByName(String name);
	
}
