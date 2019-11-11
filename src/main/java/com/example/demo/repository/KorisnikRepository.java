package com.example.demo.repository;
import java.util.List;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Korisnik;

public interface KorisnikRepository extends JpaRepository<Korisnik, Long> {
	
	Korisnik findByEmail(String email);
	
}
