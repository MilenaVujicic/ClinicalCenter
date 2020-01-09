package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Zahtev;

public interface ZahtevRepository extends JpaRepository<Zahtev, Long> {

	Zahtev save(Zahtev z);
}
