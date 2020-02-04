package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Doktor;
import com.example.demo.model.Klinika;


public interface DoktorRespository extends JpaRepository<Doktor, Long> {
	
	Doktor findByIdKorisnik(Long idKorisnik);
	List<Doktor> findAllByKlinika(Klinika klinika);
	Page<Doktor> findAll(Pageable pageable);
	Optional<Doktor> findById(Long id);
}
