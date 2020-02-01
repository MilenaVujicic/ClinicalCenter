package com.example.demo.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Klinika;
import com.example.demo.model.Sala;

public interface SalaRepository extends JpaRepository<Sala, Long> {

	List<Sala> findAll();
	
	List<Sala> findAllByKlinika(Long id);
	
	Optional<Sala> findById(Long id);
	
	Sala findByIme(String ime);
	
	Sala findByOpis(String opis);
	
	Sala save(Sala s);
	

	List<Sala> findByKlinika(Klinika k);

	

	
}