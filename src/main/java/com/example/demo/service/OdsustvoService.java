package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Odsustvo;
import com.example.demo.repository.OdsustvoRepository;

@Service
public class OdsustvoService {

	@Autowired
	private OdsustvoRepository odsustvoRepository;
	
	public Odsustvo save(Odsustvo odsustvo) {
		return odsustvoRepository.save(odsustvo);
	}
}
