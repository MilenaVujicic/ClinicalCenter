package com.example.demo;

import static com.example.demo.constants.PregledConstants.PREGLED_ID_2;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.annotation.PostConstruct;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.demo.model.StatusPregleda;
@RunWith(SpringRunner.class)
@SpringBootTest
public class PregledControllerTest {

	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@PostConstruct
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@Test
	public void pregledZahtevi() throws Exception{
		mockMvc.perform(get("/pregled/zahtevi/{id}", 4L))
		.andExpect(status().isOk())
		.andExpect(content().contentType("application/json"))
		.andExpect(jsonPath("$.*", hasSize(6)))
		.andExpect(jsonPath("$.[*].id").value(hasItem(PREGLED_ID_2.intValue())))
		.andExpect(jsonPath("$.[*].status").value(hasItem("NERASPOREDJEN")));
		

	}
	
	@Test
	public void korisnikPreuzmi() throws Exception{
		mockMvc.perform(get("/korisnik/preuzmi/1"))
		.andExpect(status().isOk())
		.andExpect(content().contentType("application/json"))
		.andExpect(jsonPath("$.*", hasSize(1)))
		.andExpect(jsonPath("$.[*].ime").value(hasItem("Petar")))
		.andExpect(jsonPath("$.[*].prezime").value(hasItem("Petrovic")));

	}
	
	@Test
	public void salaTermin() throws Exception{
		mockMvc.perform(get("/sala/termini_pregled/2"))
		.andExpect(status().isOk())
		.andExpect(content().contentType("application/json"))
		.andExpect(jsonPath("$.*", hasSize(1)))
		.andExpect(jsonPath("$.[*].naziv").value(hasItem("")))
		.andExpect(jsonPath("$.[*].status").value(hasItem(StatusPregleda.NERASPOREDJEN)));
		
	}
	
}
