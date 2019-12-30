package com.example.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Doktor;
import com.example.demo.model.Pregled;

import antlr.collections.List;

public interface PregledRespository extends JpaRepository<Pregled, Long>{

	Page<Pregled> findAll(Pageable pageable);
	
	
}
