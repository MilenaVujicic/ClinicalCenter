package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.Korisnik;
import com.example.demo.model.Uloga;

public interface KorisnikRepository extends JpaRepository<Korisnik, Long> {
	
	Korisnik findByEmail(String email);
	
	@Query(value="SELECT k FROM korisnik k",nativeQuery=true)
	List<Korisnik> findByUloge(String uloga);
	
}
