package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Klinika;


public interface KlinikaRepository extends JpaRepository<Klinika, Long> {

	Klinika findByIme(String ime);
	Klinika saveAndFlush(Klinika k);
	Page<Klinika> findAll(Pageable pageable);

}
