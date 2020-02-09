package com.example.demo;

import javax.annotation.PostConstruct;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@RunWith(SpringRunner.class)
@SpringBootTest
public class DoktorControllerTests {
	
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@PostConstruct
    public void setup() {
    	this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
	
	@Test
	public void sviLekari() throws Exception {
		mockMvc.perform(get("/pacijent/sviLekari"))
	   		   .andExpect(status().isOk())
		       .andExpect(content().contentType("application/json"))
		       .andExpect(jsonPath("$.*", hasSize(4)))
		       .andExpect(jsonPath("$.[*].ime").value(hasItem("Borisav")))
		       .andExpect(jsonPath("$.[*].specijalizacija").value(hasItem("Lekar opste prakse")));
	}
	
	@Test
	public void searchLekari() throws Exception {
		mockMvc.perform(get("/pacijent/search")
			   .param("ime", "Borisav")
		       .param("prezime", "")
		       .param("specijalizacija", "Lekar opste prakse")
			   .param("prosecnaOcena", "")
		 	   .param("spec", "")
		 	   .param("datum", "")
		 	   .param("klinika_id", ""))
	     	   .andExpect(status().isOk())
	           .andExpect(content().contentType("application/json"))
	           .andExpect(jsonPath("$.*", hasSize(1)))
	           .andExpect(jsonPath("$.[*].prezime").value(hasItem("Petkovic")));
		
		
		mockMvc.perform(get("/pacijent/search")
				   .param("ime", "Lekar")
			       .param("prezime", "")
			       .param("specijalizacija", "Hirurg")
				   .param("prosecnaOcena", "")
			 	   .param("spec", "")
			 	   .param("datum", "")
			 	   .param("klinika_id", ""))
		     	   .andExpect(status().isOk())
		           .andExpect(content().contentType("application/json"))
		           .andExpect(jsonPath("$.*", hasSize(1)))
		           .andExpect(jsonPath("$.[*].prezime").value(hasItem("Lekarovic")));
	}
	
	
	@Test
	public void slobodniTermini() throws Exception {
		
		//svi slobodni termini
		mockMvc.perform(get("/pacijent/slobodniTermini")
				   .param("doktor", "")
			       .param("datum", ""))
		     	   .andExpect(status().isOk())
		           .andExpect(content().contentType("application/json"))
		           .andExpect(jsonPath("$.*", hasSize(13)));
		
		mockMvc.perform(get("/pacijent/slobodniTermini")
				   .param("doktor", "1")
			       .param("datum", "12/01/2019"))
		     	   .andExpect(status().isOk())
		           .andExpect(content().contentType("application/json"))
		           .andExpect(jsonPath("$.*", hasSize(2)));
		
	}
	

}
