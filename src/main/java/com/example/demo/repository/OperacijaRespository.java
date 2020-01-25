package com.example.demo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Operacija;
import com.example.demo.model.StatusOperacije;

public interface OperacijaRespository extends JpaRepository<Operacija, Long>{

	List<Operacija> findByStatus(StatusOperacije status);
	
	Page<Operacija> findAll(Pageable pageable);
}
