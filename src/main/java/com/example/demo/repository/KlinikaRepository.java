package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Klinika;
import com.example.demo.model.Termin;


public interface KlinikaRepository extends JpaRepository<Klinika, Long> {

	Klinika findByIme(String ime);
	Klinika saveAndFlush(Klinika k);
	List<Klinika> findAll();
	//Klinika findOne(Long id);

	Optional<Klinika> findById(Long id);
	
}
