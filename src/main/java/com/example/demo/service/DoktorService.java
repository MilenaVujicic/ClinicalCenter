package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Doktor;
import com.example.demo.repository.DoktorRespository;


@Service
public class DoktorService {

	@Autowired
	DoktorRespository doktorRespository;
	
	public Doktor findOne(Long id) {
		return doktorRespository.findById(id).orElseGet(null);
	}
}
