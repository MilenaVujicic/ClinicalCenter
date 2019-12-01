package com.example.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Dijagnoza;

public interface DijagnozaRepository extends JpaRepository<Dijagnoza, Long> {

	Page<Dijagnoza> findAll(Pageable pageable);
}
