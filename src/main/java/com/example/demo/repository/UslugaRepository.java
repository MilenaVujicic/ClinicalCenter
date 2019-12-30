package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Usluga;

public interface UslugaRepository extends JpaRepository<Usluga, Long>{
	
	List<Usluga> findAll();
	Optional<Usluga> findById(Long id);
	Usluga save(Usluga u);

}
