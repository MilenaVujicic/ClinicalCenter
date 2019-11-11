package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Doktor;


public interface DoktorRespository extends JpaRepository<Doktor, Long> {

}
