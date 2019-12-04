package com.example.demo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Klinika;
import com.example.demo.model.Pacijent;

public interface PacijentRepository extends JpaRepository<Pacijent, Long> {

	Page<Pacijent> findAll(Pageable pageable);
	
	List<Pacijent> findAllByKlinika(Klinika klinika);
	
	Pacijent findByIdKorisnik(Long idKorisnik);
	
}
