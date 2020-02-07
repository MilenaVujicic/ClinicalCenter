package com.example.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.TipPregleda;

@Repository
public interface TipPregledaRepository extends JpaRepository<TipPregleda, Long>{

	TipPregleda findByNaziv(String naziv);
	
	Page<TipPregleda> findAll(Pageable pageable);
	
	TipPregleda save(TipPregleda tipPregleda);
}
