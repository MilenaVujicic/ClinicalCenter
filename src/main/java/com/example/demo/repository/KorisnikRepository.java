package com.example.demo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Korisnik;
import com.example.demo.model.UlogaKorisnika;

public interface KorisnikRepository extends JpaRepository<Korisnik, Long> {
	
	Korisnik findByEmail(String email);
	
	List<Korisnik> findByUloga(UlogaKorisnika uloga);
	
	List<Korisnik> findByAktivan(Boolean aktivan);
	
	Page<Korisnik> findAll(Pageable pageable);
	
}
