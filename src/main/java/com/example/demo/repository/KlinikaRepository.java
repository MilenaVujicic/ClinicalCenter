package com.example.demo.repository;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.model.Klinika;


public interface KlinikaRepository extends JpaRepository<Klinika, Long> {

	Klinika findByIme(String ime);
	Klinika saveAndFlush(Klinika k);
	Page<Klinika> findAll(Pageable pageable);
	
	@Query("Select distinct k\r\n" + 
			"from Klinika k JOIN k.doktori d " +
			"JOIN d.termin t WHERE DATE(t.datum) = ?1 AND t.slobodan = true " +
			"AND d.specijalizacija = ?2 "// 
			) //?1
	List<Klinika> findByDatumPregleda(LocalDate datum, String specijalizacija);
}
