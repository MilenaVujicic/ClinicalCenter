package com.example.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Termin;

public interface TerminRepository extends JpaRepository<Termin, Long>{

	Page<Termin> findAll(Pageable pageable);
}
