package com.example.demo.repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Pregled;

@Repository
public interface PregledRespository extends JpaRepository<Pregled, Long>{

	Page<Pregled> findAll(Pageable pageable);
	
	@Query("select p from Pregled p where p.pacijent.id = 1")
	List<Pregled> findByPatientID();//?#{[0]}
	
}
