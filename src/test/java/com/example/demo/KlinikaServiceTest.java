package com.example.demo;

import static com.example.demo.constants.KlinikaConstants.DATUM_PREGLEDA;
import static com.example.demo.constants.KlinikaConstants.FREE_KLINIKA_SIZE;
import static com.example.demo.constants.KlinikaConstants.KLINIKA_ADRESA;
import static com.example.demo.constants.KlinikaConstants.KLINIKA_ID;
import static com.example.demo.constants.KlinikaConstants.KLINIKA_NAME;
import static com.example.demo.constants.KlinikaConstants.KLINIKA_OPIS;
import static com.example.demo.constants.KlinikaConstants.KLINIKA_SIZE;
import static com.example.demo.constants.KlinikaConstants.NEW_KLINIKA_ADRESA;
import static com.example.demo.constants.KlinikaConstants.NEW_KLINIKA_NAME;
import static com.example.demo.constants.KlinikaConstants.NEW_KLINIKA_OPIS;
import static com.example.demo.constants.KlinikaConstants.SPECIJALIZACIJA;
import static com.example.demo.constants.TerminConstants.TERMIN_SIZE;
import static com.example.demo.constants.TerminConstants.TERMIN_ID;
import static com.example.demo.constants.TerminConstants.FREE_TERMIN_COUNT;
import static com.example.demo.constants.TerminConstants.DOKTOR_ID;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Calendar;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Klinika;
import com.example.demo.model.Termin;
import com.example.demo.service.KlinikaService;
import com.example.demo.service.TerminService;

@RunWith(SpringRunner.class)
@SpringBootTest
class KlinikaServiceTest {
	
	@Autowired
	private KlinikaService klinikaService;
	
	@Autowired
	private TerminService terminService;

	@Test
	public void klinikaServiceFindAll() {
		List<Klinika> allKlinika = klinikaService.findAll();
		assertThat(allKlinika).hasSize(KLINIKA_SIZE);
	}
	
	@Test
	public void klinikaServiceFindOne() {
		Klinika klinika = klinikaService.findOne(KLINIKA_ID);
		assertThat(klinika).isNotNull();
		assertThat(klinika.getIme()).isEqualTo(KLINIKA_NAME);
		assertThat(klinika.getAdresa()).isEqualTo(KLINIKA_ADRESA);
		assertThat(klinika.getOpis()).isEqualTo(KLINIKA_OPIS);
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void klinikaServiceAdd() {
		Klinika klinika = new Klinika(NEW_KLINIKA_NAME, NEW_KLINIKA_ADRESA, NEW_KLINIKA_OPIS);
		int klinikSize = klinikaService.findAll().size();
		
		Klinika k = klinikaService.save(klinika);
		assertThat(k).isNotNull();
		
		List<Klinika> allKlinika = klinikaService.findAll();
		assertThat(allKlinika).hasSize(klinikSize + 1);
		k = allKlinika.get(klinikSize);
		assertThat(k.getIme()).isEqualTo(NEW_KLINIKA_NAME);
		assertThat(k.getAdresa()).isEqualTo(NEW_KLINIKA_ADRESA);
		assertThat(k.getOpis()).isEqualTo(NEW_KLINIKA_OPIS);
	}
	
	@Test
	public void klinikaServiceFindByName() {
		Klinika klinika = klinikaService.findByName(KLINIKA_NAME);
		assertThat(klinika).isNotNull();
		assertThat(klinika.getAdresa()).isEqualTo(KLINIKA_ADRESA);
		assertThat(klinika.getOpis()).isEqualTo(KLINIKA_OPIS);
	}
	
	@Test
	public void klinikaServiceFindByDatumPregleda() {
		List<Klinika> klinike = klinikaService.findByDatumPregleda(DATUM_PREGLEDA, SPECIJALIZACIJA);
		assertThat(klinike).hasSize(FREE_KLINIKA_SIZE);
	}
	
	@Test
	public void terminFindAll() {
		List<Termin> termini = terminService.findAll();
		assertThat(termini).hasSize(TERMIN_SIZE);
	}
	
	@Test
	public void terminFindById() {
		Termin termin = terminService.findOne(TERMIN_ID);
		assertThat(termin).isNotNull();
		assertThat(termin.getCena()).isEqualTo(200);
		assertThat(termin.getTip()).isEqualTo("redovni");
	}
	
	@Test
	public void findBySlobodanTermin() {
		List<Termin> slobodniTermini = terminService.findBySlobodan(true);
		assertThat(slobodniTermini).hasSize(FREE_TERMIN_COUNT);
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void terminServiceAdd() {
		Calendar datum = Calendar.getInstance();
		Termin termin = new Termin(datum, true);
		int count = terminService.findAll().size();
		
		Termin t = terminService.save(termin);
		assertThat(t).isNotNull();
		
		List<Termin> sviTermini = terminService.findAll();
		assertThat(sviTermini).hasSize(count + 1);
		assertThat(sviTermini.get(count).getDatum()).isEqualTo(datum);
		assertThat(sviTermini.get(count).isSlobodan()).isEqualTo(true);
	}
	
	@Test
	public void terminFindByDatumIDoktor() {
		List<Termin> doktor_datum = terminService.findByDatumIDoktor(DATUM_PREGLEDA, DOKTOR_ID);
		assertThat(doktor_datum).hasSize(2);
	}
}
