package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Klinika;

public interface KlinikaRepository extends JpaRepository<Klinika, Long>{

	List<Klinika> findAll();

}
