package com.example.demo;

import static com.example.demo.constants.DoktorConstants.DOKTOR_BROC;
import static com.example.demo.constants.DoktorConstants.DOKTOR_DATUM;
import static com.example.demo.constants.DoktorConstants.DOKTOR_ID;
import static com.example.demo.constants.DoktorConstants.DOKTOR_IDKLINIKE;
import static com.example.demo.constants.DoktorConstants.DOKTOR_IDKOR;
import static com.example.demo.constants.DoktorConstants.DOKTOR_PROSOC;
import static com.example.demo.constants.DoktorConstants.DOKTOR_SIZE;
import static com.example.demo.constants.DoktorConstants.DOKTOR_SIZEFINDBYDATUM;
import static com.example.demo.constants.DoktorConstants.DOKTOR_SPEC;
import static com.example.demo.constants.DoktorConstants.DOKTOR_SPEC2;
import static com.example.demo.constants.DoktorConstants.DOKTOR_SUMAOC;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.model.Doktor;
import com.example.demo.model.Klinika;
import com.example.demo.service.DoktorService;
import com.example.demo.service.KlinikaService;

@SpringBootTest
public class DoktorServiceTests {
	
	@Autowired
	DoktorService doktorService;
	
	@Autowired
	KlinikaService klinikaService;
	
	@Test
	void findOne() {
		Doktor d = doktorService.findOne(DOKTOR_ID);
		assertThat(d).isNotNull();
		assertThat(d.getSpecijalizacija()).isEqualTo(DOKTOR_SPEC);
		assertThat(d.getBrojOcena()).isEqualTo(DOKTOR_BROC);
		assertThat(d.getProsenaOcena()).isEqualTo(DOKTOR_PROSOC);
		assertThat(d.getSumaOcena()).isEqualTo(DOKTOR_SUMAOC);
	}
	
	@Test
	void findAll() {
		List<Doktor> allDoctors = doktorService.findAll();
		assertThat(allDoctors).hasSize(DOKTOR_SIZE);
		assertThat(allDoctors.size()).isEqualTo(DOKTOR_SIZE);
	}
	
	@Test
	void findByIdKorisnik() {
		Doktor d = doktorService.findByIdKorisnik(DOKTOR_IDKOR);
		assertThat(d.getSpecijalizacija()).isEqualTo(DOKTOR_SPEC);
		assertThat(d.getBrojOcena()).isEqualTo(DOKTOR_BROC);
		assertThat(d.getProsenaOcena()).isEqualTo(DOKTOR_PROSOC);
		assertThat(d.getSumaOcena()).isEqualTo(DOKTOR_SUMAOC);
	}
	
	@Test
	void findAllByKlinika(){
		Klinika k = klinikaService.findOne(1L);
		List<Doktor> doktori =  doktorService.findAllByKlinika(k);
		
		assertThat(doktori).isNotNull();
		assertThat(doktori.size()).isEqualTo(2);
		
	}

	@Test
	void findByDatumPregledaISpecIKlinika() {
		String datum = DOKTOR_DATUM;
		LocalDate date = LocalDate.parse(datum);
		List<Doktor> doktori = doktorService.findByDatumPregledaISpecIKlinika(date, DOKTOR_SPEC, DOKTOR_IDKLINIKE);
		assertThat(doktori.size()).isEqualTo(DOKTOR_SIZEFINDBYDATUM);
		
		List<Doktor> doktori2 = doktorService.findByDatumPregledaISpecIKlinika(date, DOKTOR_SPEC2, DOKTOR_IDKLINIKE);
		assertThat(doktori2.size()).isEqualTo(DOKTOR_SIZEFINDBYDATUM);
	}

}
