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
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Klinika;
import com.example.demo.service.KlinikaService;

@RunWith(SpringRunner.class)
@SpringBootTest
class KlinikaServiceTest {
	
	@Autowired
	private KlinikaService klinikaService;

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
}
