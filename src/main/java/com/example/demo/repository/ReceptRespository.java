package com.example.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Recept;



public interface ReceptRespository extends JpaRepository<Recept, Long>{

	Page<Recept> findAll(Pageable pageable);
}
