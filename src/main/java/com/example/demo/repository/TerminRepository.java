package com.example.demo.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Termin;

public interface TerminRepository extends JpaRepository<Termin, Long>{

	Termin save(Termin t);
	
	Optional<Termin> findById(Long id);
	
	List<Termin> findAll();
	

	Page<Termin> findAll(Pageable pageable);
	
	List<Termin> findBySlobodan(Boolean slobodan);
	
	@Query("Select t " +
		   "from Doktor d JOIN d.termin t " +
		   "WHERE DATE(t.datum) =?1 AND d.id = ?2 AND t.slobodan = true")
	List<Termin> findByDatumIDoktor(LocalDate datum, Long doktor_id);

}
