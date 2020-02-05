package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.example.demo.model.Doktor;
import com.example.demo.model.Klinika;
import com.example.demo.service.DoktorService;

import static com.example.demo.constants.DoktorConstants.DOKTOR_SPEC;
import static com.example.demo.constants.DoktorConstants.DOKTOR_ID;
import static com.example.demo.constants.DoktorConstants.DOKTOR_IDKOR;
import static com.example.demo.constants.DoktorConstants.DOKTOR_SIZE;
import static com.example.demo.constants.DoktorConstants.DOKTOR_BROC;
import static com.example.demo.constants.DoktorConstants.DOKTOR_PROSOC;
import static com.example.demo.constants.DoktorConstants.DOKTOR_SUMAOC;
import static com.example.demo.constants.DoktorConstants.DOKTOR_DATUM;
import static com.example.demo.constants.DoktorConstants.DOKTOR_IDKLINIKE;
import static com.example.demo.constants.DoktorConstants.DOKTOR_SIZEFINDBYDATUM;


@RunWith(SpringRunner.class)
@SpringBootTest
class DoktorServiceTest {

	@Autowired
	DoktorService doktorService;
	
	@Test
	public void findOne() {
		Doktor d = doktorService.findOne(DOKTOR_ID);
		assertThat(d).isNotNull();
		assertThat(d.getSpecijalizacija()).isEqualTo(DOKTOR_SPEC);
		assertThat(d.getBrojOcena()).isEqualTo(DOKTOR_BROC);
		assertThat(d.getProsenaOcena()).isEqualTo(DOKTOR_PROSOC);
		assertThat(d.getSumaOcena()).isEqualTo(DOKTOR_SUMAOC);
	}
	
	@Test
	public void findAll() {
		List<Doktor> allDoctors = doktorService.findAll();
		assertThat(allDoctors).hasSize(DOKTOR_SIZE);
		assertThat(allDoctors.size()).isEqualTo(DOKTOR_SIZE);
	}
	
	@Test
	public void findByIdKorisnik() {
		Doktor d = doktorService.findByIdKorisnik(DOKTOR_IDKOR);
		assertThat(d.getSpecijalizacija()).isEqualTo(DOKTOR_SPEC);
		assertThat(d.getBrojOcena()).isEqualTo(DOKTOR_BROC);
		assertThat(d.getProsenaOcena()).isEqualTo(DOKTOR_PROSOC);
		assertThat(d.getSumaOcena()).isEqualTo(DOKTOR_SUMAOC);
	}
	
	/*
	 * @Test public void findAllByKlinika(Klinika k){ List<Doktor> doktori =
	 * doktorService.findAllByKlinika(k); }
	 */

	@Test
	public void findByDatumPregledaISpecIKlinika() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-dd-MM");
		String datum = DOKTOR_DATUM;
		String spec = DOKTOR_SPEC;
		LocalDate date = LocalDate.parse(datum,formatter);
		List<Doktor> doktori = doktorService.findByDatumPregledaISpecIKlinika(date, DOKTOR_SPEC, DOKTOR_IDKLINIKE);
		assertThat(doktori.size()).isEqualTo(DOKTOR_SIZEFINDBYDATUM);
	}
	
}
