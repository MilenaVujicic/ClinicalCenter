package com.example.demo;

import static com.example.demo.constants.PregledConstants.DATUM_PREGLEDA_C;
import static com.example.demo.constants.PregledConstants.PREGLED_ANAMNEZA;
import static com.example.demo.constants.PregledConstants.PREGLED_ID;
import static com.example.demo.constants.PregledConstants.PREGLED_NAZIV;
import static com.example.demo.constants.PregledConstants.PREGLED_SIZE;
import static com.example.demo.constants.PregledConstants.PREGLED_STATUS;
import static com.example.demo.constants.PregledConstants.PREGLED_NERASPOREDJEN_SIZE;
import static com.example.demo.constants.PregledConstants.PREGLED_PACIJENT_ID;
import static com.example.demo.constants.PregledConstants.PREGLED_PACIJENT_SIZE;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.model.Pregled;
import com.example.demo.service.PregledService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PregledServiceTest {
	
	@Autowired
	private PregledService pregledService;
	
	@Test
	public void pregledServiceFindAll() {
		List<Pregled> allPregled = pregledService.findAll();
		assertThat(allPregled).hasSize(PREGLED_SIZE);
	}
	
	@Test
	public void pregledFindOne() {
		Pregled p = pregledService.findOne(PREGLED_ID);
		assertThat(p).isNotNull();
		assertThat(p.getNaziv()).isEqualTo(PREGLED_NAZIV);
		assertThat(p.getAnamneza()).isEqualTo(PREGLED_ANAMNEZA);
		assertThat(p.getDatumIVremePregleda()).isEqualTo(DATUM_PREGLEDA_C);
		
	}
	
	@Test
	public void pregledFindByStatus() {
		List<Pregled> p = pregledService.findByStatus(PREGLED_STATUS);
		assertThat(p).hasSize(PREGLED_NERASPOREDJEN_SIZE);
	}
	
	@Test
	public void pregledFindByPatientId() {
		List<Pregled> p = pregledService.findByPatientId(PREGLED_PACIJENT_ID);
		assertThat(p).hasSize(PREGLED_PACIJENT_SIZE);
	}
	
}
