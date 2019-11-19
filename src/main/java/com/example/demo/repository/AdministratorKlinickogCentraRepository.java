package com.example.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.AdministratorKlinickogCentra;

public interface AdministratorKlinickogCentraRepository extends JpaRepository<AdministratorKlinickogCentra, Long>{

	Page<AdministratorKlinickogCentra> findAll(Pageable pageable);
}
