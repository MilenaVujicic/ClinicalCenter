package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.TipPregleda;
import com.example.demo.repository.TipPregledaRepository;

@Service
public class TipPregledaService {
	
	@Autowired
	private TipPregledaRepository tipPregledaRepository;

	public TipPregleda findOne(Long id) {
		return tipPregledaRepository.findById(id).orElse(null);
	}
	
	public TipPregleda findByNaziv(String naziv) {
		return tipPregledaRepository.findByNaziv(naziv);
	}
	
	public List<TipPregleda> findAll() {
		return tipPregledaRepository.findAll();
	}
	
	public TipPregleda save(TipPregleda tipPregleda) {
		return tipPregledaRepository.save(tipPregleda);
	}
	
	public Optional<TipPregleda> findById(Long id){
		return tipPregledaRepository.findById(id);
	}
}
