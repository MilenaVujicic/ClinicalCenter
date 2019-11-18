package com.example.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.AdministratorKlinike;
import com.example.demo.model.Pacijent;

public interface AdministratorKlinikeRespository extends JpaRepository<AdministratorKlinike, Long> {

	Page<AdministratorKlinike> findAll(Pageable pageable); 
}
