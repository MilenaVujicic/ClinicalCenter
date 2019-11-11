package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.ReceptRespository;

@Service
public class ReceptService {

	@Autowired
	private ReceptRespository receptRespository;
}
