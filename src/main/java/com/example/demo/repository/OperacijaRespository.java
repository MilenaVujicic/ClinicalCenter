package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Operacija;



public interface OperacijaRespository extends JpaRepository<Operacija, Long>{

}
