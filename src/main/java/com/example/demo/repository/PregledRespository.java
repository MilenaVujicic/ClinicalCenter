package com.example.demo.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Pregled;
import com.example.demo.model.StatusPregleda;



@Repository
public interface PregledRespository extends JpaRepository<Pregled, Long>{

	Page<Pregled> findAll(Pageable pageable);
	

	List<Pregled> findByStatus(StatusPregleda status);

	@Query("select p from Pregled p where p.pacijent.id = ?1")
	List<Pregled> findByPatientID(Long id);//?#{[0]}
	
}

