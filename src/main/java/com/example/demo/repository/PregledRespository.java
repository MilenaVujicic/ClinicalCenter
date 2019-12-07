package com.example.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Pregled;

public interface PregledRespository extends JpaRepository<Pregled, Long>{

	Page<Pregled> findAll(Pageable pageable);
}
