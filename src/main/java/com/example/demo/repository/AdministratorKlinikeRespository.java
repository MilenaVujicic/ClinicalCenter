package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.AdministratorKlinike;

public interface AdministratorKlinikeRespository extends JpaRepository<AdministratorKlinike, Long> {

	Page<AdministratorKlinike> findAll(Pageable pageable); 
	Optional<AdministratorKlinike> findById(Long id);
}
