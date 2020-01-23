package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Odsustvo;

public interface OdsustvoRepository extends JpaRepository<Odsustvo, Long> {

	Odsustvo save(Odsustvo o);
	List<Odsustvo> findAll();
	Optional<Odsustvo> findById(Long id);
	void delete(Odsustvo o);
}
