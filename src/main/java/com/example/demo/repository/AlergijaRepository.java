package com.example.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Alergija;

public interface AlergijaRepository extends JpaRepository<Alergija, Long>{

	Page<Alergija> findAll(Pageable pageable);
}
