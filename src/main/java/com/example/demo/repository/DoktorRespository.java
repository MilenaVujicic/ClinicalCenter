package com.example.demo.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Doktor;
import com.example.demo.model.Klinika;


public interface DoktorRespository extends JpaRepository<Doktor, Long> {
	
	Doktor findByIdKorisnik(Long idKorisnik);
	List<Doktor> findAllByKlinika(Klinika klinika);
	Page<Doktor> findAll(Pageable pageable);

	Optional<Doktor> findById(Long id);

	

	@Query("Select distinct d\r\n" +
	       "from Klinika k JOIN k.doktori d " +
		   "JOIN d.termin t WHERE DATE(t.datum) = ?1 AND t.slobodan = true " +
	       "AND d.specijalizacija = ?2 AND k.id = ?3")
	List<Doktor> findByDatumPregledaISpecIKlinika(LocalDate datum, String specijalizacija, Long id_klinika);

}
