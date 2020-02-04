package com.example.demo.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Klinika;


public interface KlinikaRepository extends JpaRepository<Klinika, Long> {

	Klinika findByIme(String ime);
	Klinika saveAndFlush(Klinika k);

	List<Klinika> findAll();
	//Klinika findOne(Long id);

	Optional<Klinika> findById(Long id);
	

	Page<Klinika> findAll(Pageable pageable);
	
	@Query("Select distinct k\r\n" + 
			"from Klinika k JOIN k.doktori d " +
			"JOIN d.termin t WHERE DATE(t.datum) = ?1 AND t.slobodan = true " +
			"AND d.specijalizacija = ?2 "// 
			) //?1
	List<Klinika> findByDatumPregleda(LocalDate datum, String specijalizacija);

}
