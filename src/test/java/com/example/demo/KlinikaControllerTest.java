package com.example.demo;

import static com.example.demo.constants.KlinikaConstants.FREE_KLINIKA_SIZE;
import static com.example.demo.constants.KlinikaConstants.KLINIKA_ADRESA;
import static com.example.demo.constants.KlinikaConstants.KLINIKA_ID;
import static com.example.demo.constants.KlinikaConstants.KLINIKA_NAME;
import static com.example.demo.constants.KlinikaConstants.KLINIKA_OPIS;
import static com.example.demo.constants.KlinikaConstants.KLINIKA_SIZE;
import static com.example.demo.constants.KlinikaConstants.SPECIJALIZACIJA;
import static com.example.demo.constants.KlinikaConstants.SPECIJALIZACIJA1;
import static com.example.demo.constants.KlinikaConstants.DATUM_PREGLEDA_STRING;
import static com.example.demo.constants.KlinikaConstants.PRETRAGA_IME;
import static com.example.demo.constants.KlinikaConstants.PRETRAGA_OPIS;
import static com.example.demo.constants.KlinikaConstants.PRETRAGA_ADRESA1;
import static com.example.demo.constants.KlinikaConstants.PRETRAGA_ADRESA2;
import static com.example.demo.constants.KlinikaConstants.PRETRAGA1_KLINIKA_SIZE;
import static com.example.demo.constants.KlinikaConstants.PRETRAGA2_KLINIKA_SIZE;
import static com.example.demo.constants.KlinikaConstants.PRETRAGA3_KLINIKA_SIZE;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import javax.annotation.PostConstruct;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KlinikaControllerTest {
	
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@PostConstruct
    public void setup() {
    	this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

	@Test
	public void allKlinika() throws Exception {
		mockMvc.perform(get("/pacijent/sveKlinike"))
		        .andExpect(status().isOk())
		        .andExpect(content().contentType("application/json"))
		        .andExpect(jsonPath("$.*", hasSize(KLINIKA_SIZE)))
		        .andExpect(jsonPath("$.[*].id").value(hasItem(KLINIKA_ID.intValue())))
		        .andExpect(jsonPath("$.[*].ime").value(hasItem(KLINIKA_NAME)))
		        .andExpect(jsonPath("$.[*].adresa").value(hasItem(KLINIKA_ADRESA)))
		        .andExpect(jsonPath("$.[*].opis").value(hasItem(KLINIKA_OPIS)));
	}
	
	@Test
	public void klinikePoTerminu() throws Exception {
	    String datum = DATUM_PREGLEDA_STRING; 
	    String spec = SPECIJALIZACIJA;   
	    mockMvc.perform(get("/pacijent/klinikePoTerminu")
	    	   .param("datum", datum)
	    	   .param("spec", spec))
	     	   .andExpect(status().isOk())
	           .andExpect(content().contentType("application/json"))
	           .andExpect(jsonPath("$.*", hasSize(FREE_KLINIKA_SIZE)));
	    
	    mockMvc.perform(get("/pacijent/klinikePoTerminu")
		    	   .param("datum", datum)
		    	   .param("spec", SPECIJALIZACIJA1))
		     	   .andExpect(status().isOk())
		           .andExpect(content().contentType("application/json"))
		           .andExpect(jsonPath("$.*", hasSize(0)));
	}
	
	@Test
	public void searchClinic() throws Exception {
		String name = PRETRAGA_IME;
		String address = PRETRAGA_ADRESA1;
		mockMvc.perform(get("/pacijent/searchClinics")
	    	   .param("name", name)
	    	   .param("address", address)
	    	   .param("desc", "")
	    	   .param("rating", "")
	    	   .param("datum", "")
	    	   .param("spec", ""))
	     	   .andExpect(status().isOk())
	           .andExpect(content().contentType("application/json"))
	           .andExpect(jsonPath("$.*", hasSize(PRETRAGA1_KLINIKA_SIZE)))
	           .andExpect(jsonPath("$.[*].id").value(hasItem(KLINIKA_ID.intValue())))
		       .andExpect(jsonPath("$.[*].ime").value(hasItem(KLINIKA_NAME)))
		       .andExpect(jsonPath("$.[*].adresa").value(hasItem(KLINIKA_ADRESA)))
		       .andExpect(jsonPath("$.[*].opis").value(hasItem(KLINIKA_OPIS)));
		
		address = PRETRAGA_ADRESA2;
		mockMvc.perform(get("/pacijent/searchClinics")
		    	   .param("name", name)
		    	   .param("address", address)
		    	   .param("desc", "")
		    	   .param("rating", "")
		    	   .param("datum", "")
		    	   .param("spec", ""))
		     	   .andExpect(status().isOk())
		           .andExpect(content().contentType("application/json"))
		           .andExpect(jsonPath("$.*", hasSize(PRETRAGA2_KLINIKA_SIZE)));
		
		String desc = PRETRAGA_OPIS;
		mockMvc.perform(get("/pacijent/searchClinics")
		    	   .param("name", "")
		    	   .param("address", "")
		    	   .param("desc", desc)
		    	   .param("rating", "")
		    	   .param("datum", "")
		    	   .param("spec", ""))
		     	   .andExpect(status().isOk())
		           .andExpect(content().contentType("application/json"))
		           .andExpect(jsonPath("$.*", hasSize(PRETRAGA3_KLINIKA_SIZE)))
		           .andExpect(jsonPath("$.[*].id").value(hasItem(KLINIKA_ID.intValue())))
			       .andExpect(jsonPath("$.[*].ime").value(hasItem(KLINIKA_NAME)))
			       .andExpect(jsonPath("$.[*].adresa").value(hasItem(KLINIKA_ADRESA)))
			       .andExpect(jsonPath("$.[*].opis").value(hasItem(KLINIKA_OPIS)));
		
		mockMvc.perform(get("/pacijent/searchClinics")
		    	   .param("name", name)
		    	   .param("address", "")
		    	   .param("desc", "")
		    	   .param("rating", "")
		    	   .param("datum", DATUM_PREGLEDA_STRING)
		    	   .param("spec", ""))
		     	   .andExpect(status().isOk())
		           .andExpect(content().contentType("application/json"))
		           .andExpect(jsonPath("$.*", hasSize(PRETRAGA1_KLINIKA_SIZE)))
		           .andExpect(jsonPath("$.[*].id").value(hasItem(KLINIKA_ID.intValue())))
			       .andExpect(jsonPath("$.[*].ime").value(hasItem(KLINIKA_NAME)))
			       .andExpect(jsonPath("$.[*].adresa").value(hasItem(KLINIKA_ADRESA)))
			       .andExpect(jsonPath("$.[*].opis").value(hasItem(KLINIKA_OPIS)));
		
		mockMvc.perform(get("/pacijent/searchClinics")
		    	   .param("name", name)
		    	   .param("address", "")
		    	   .param("desc", "")
		    	   .param("rating", "")
		    	   .param("datum", DATUM_PREGLEDA_STRING)
		    	   .param("spec", SPECIJALIZACIJA))
		     	   .andExpect(status().isOk())
		           .andExpect(content().contentType("application/json"))
		           .andExpect(jsonPath("$.*", hasSize(PRETRAGA1_KLINIKA_SIZE)))
		           .andExpect(jsonPath("$.[*].id").value(hasItem(KLINIKA_ID.intValue())))
			       .andExpect(jsonPath("$.[*].ime").value(hasItem(KLINIKA_NAME)))
			       .andExpect(jsonPath("$.[*].adresa").value(hasItem(KLINIKA_ADRESA)))
			       .andExpect(jsonPath("$.[*].opis").value(hasItem(KLINIKA_OPIS)));
	}
	
	@Test
	public void slobodniTermini() throws Exception {
		String datum = DATUM_PREGLEDA_STRING; 
	    String doktor = "1";   
	    mockMvc.perform(get("/pacijent/slobodniTermini")
	    	   .param("datum", datum)
	    	   .param("doktor", doktor))
	     	   .andExpect(status().isOk())
	           .andExpect(content().contentType("application/json"))
	           .andExpect(jsonPath("$.*", hasSize(2)));
	    
	    mockMvc.perform(get("/pacijent/slobodniTermini")
		    	   .param("datum", datum)
		    	   .param("doktor", "2"))
		     	   .andExpect(status().isOk())
		           .andExpect(content().contentType("application/json"))
		           .andExpect(jsonPath("$.*", hasSize(1)));
	}
	
	@Test
	public void sviLekari() throws Exception {
		mockMvc.perform(get("/pacijent/sviLekari"))
	   		   .andExpect(status().isOk())
		       .andExpect(content().contentType("application/json"))
		       .andExpect(jsonPath("$.*", hasSize(4)))
		       .andExpect(jsonPath("$.[*].ime").value(hasItem("Borisav")))
		       .andExpect(jsonPath("$.[*].specijalizacija").value(hasItem(SPECIJALIZACIJA)));
	}
	
	@Test
	public void filtriranjeLekara() throws Exception {
		mockMvc.perform(get("/pacijent/search")
			   .param("ime", "Borisav")
		       .param("prezime", "")
		       .param("specijalizacija", SPECIJALIZACIJA)
			   .param("prosecnaOcena", "")
		 	   .param("spec", "")
		 	   .param("datum", "")
		 	   .param("klinika_id", ""))
	     	   .andExpect(status().isOk())
	           .andExpect(content().contentType("application/json"))
	           .andExpect(jsonPath("$.*", hasSize(1)))
	           .andExpect(jsonPath("$.[*].prezime").value(hasItem("Petkovic")));
	}
}
