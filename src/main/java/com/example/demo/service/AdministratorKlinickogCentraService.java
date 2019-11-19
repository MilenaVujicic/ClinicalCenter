package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.AdministratorKlinickogCentra;
import com.example.demo.repository.AdministratorKlinickogCentraRepository;

@Service
public class AdministratorKlinickogCentraService {

	@Autowired
	AdministratorKlinickogCentraRepository administratorKlinickogCentraRepository;
	
	public AdministratorKlinickogCentra save(AdministratorKlinickogCentra administratorKlinickogCentra) {
		return administratorKlinickogCentraRepository.save(administratorKlinickogCentra);
	}
	
	public List<AdministratorKlinickogCentra> findAll() {
		return administratorKlinickogCentraRepository.findAll();
	}
}
