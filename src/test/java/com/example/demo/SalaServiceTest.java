package com.example.demo;

import static com.example.demo.constants.SalaConstants.SALA_ID;
import static com.example.demo.constants.SalaConstants.SALA_ID_KLINIKA;
import static com.example.demo.constants.SalaConstants.SALA_IME;
import static com.example.demo.constants.SalaConstants.SALA_OPIS;
import static com.example.demo.constants.SalaConstants.SALA_SIZE;
import static com.example.demo.constants.SalaConstants.SALA_SIZE_KLINIKA;
import static com.example.demo.constants.SalaConstants.*;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.model.Sala;
import com.example.demo.service.KlinikaService;
import com.example.demo.service.SalaService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SalaServiceTest {

	@Autowired
	SalaService salaService;
	
	@Autowired
	KlinikaService klinikaService;
	@Test
	public void salaFindAll() {
		List<Sala> sale = salaService.findAll();
		assertThat(sale).hasSize(SALA_SIZE);
	}
	
	@Test
	public void salaFindAllByKlinika() {
		List<Sala> sale = salaService.findAllByKlinika(SALA_ID_KLINIKA);
		assertThat(sale).hasSize(SALA_SIZE_KLINIKA);
	}
	
	@Test
	public void salaFindById() {
		Optional<Sala> s = salaService.findById(SALA_ID);
		assertThat(s.get()).isNotNull();
		Sala sala = s.get();
		assertThat(sala.getIme()).isEqualTo(SALA_IME);
		assertThat(sala.getOpis()).isEqualTo(SALA_OPIS);
	}
	
	@Test
	public void salaFindByName() {
		Sala s = salaService.findByIme(SALA_IME);
		assertThat(s).isNotNull();
		assertThat(s.getId()).isEqualTo(SALA_ID);
		assertThat(s.getIme()).isEqualTo(SALA_IME);
		assertThat(s.getOpis()).isEqualTo(SALA_OPIS);
	}
	
	@Test
	public void salaAdd() {
		Sala s = new Sala();
		s.setIme(SALA_NEW_IME);
		s.setOpis(SALA_NEW_OPIS);
		s.setKlinika(klinikaService.findOne(SALA_NEW_KLINIKA));
		int salaSize = salaService.findAll().size();
		Sala sala = salaService.save(s);
		assertThat(sala).isNotNull();
		List<Sala> sale = salaService.findAll();
		assertThat(sale).hasSize(salaSize+ 1);
		assertThat(sala.getIme()).isEqualTo(SALA_NEW_IME);
		assertThat(sala.getOpis()).isEqualTo(SALA_NEW_OPIS);
	}
}
