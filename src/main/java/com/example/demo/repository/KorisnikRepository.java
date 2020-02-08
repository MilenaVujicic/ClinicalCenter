package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.QueryHints;

import org.springframework.data.repository.query.Param;


import com.example.demo.model.Korisnik;
import com.example.demo.model.UlogaKorisnika;

public interface KorisnikRepository extends JpaRepository<Korisnik, Long> {
	
	
	/*
	 * @Lock(LockModeType.PESSIMISTIC_WRITE)
	 * 
	 * @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value="0")})
	 */
	Korisnik findByEmail(String email);
	
	List<Korisnik> findByUloga(UlogaKorisnika uloga);
	
	List<Korisnik> findByAktivan(Boolean aktivan);
	
	Page<Korisnik> findAll(Pageable pageable);	
	
	List<Korisnik> findByIme(String ime);
	
	List<Korisnik> findByPrezime(String prezime);
	
	Optional<Korisnik> findById(Long id);
	
	Optional<Korisnik> findByJmbg(Long jmbg);
	
	Korisnik save(Korisnik k);
	
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Query("select k from Korisnik k where k.id = :id")
	@QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value ="0")})
	public Korisnik findOneById(@Param("id")Long id);
	
}
