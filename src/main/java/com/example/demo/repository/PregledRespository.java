package com.example.demo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Pregled;
import com.example.demo.model.StatusPregleda;



public interface PregledRespository extends JpaRepository<Pregled, Long>{

	Page<Pregled> findAll(Pageable pageable);
	
	List<Pregled> findByStatus(StatusPregleda status);
}