package com.example.demo;

import static com.example.demo.constants.KlinikaConstants.DATUM_PREGLEDA_STRING;
import static com.example.demo.constants.KlinikaConstants.SPECIJALIZACIJA;
import static com.example.demo.constants.KlinikaConstants.SPECIJALIZACIJA1;
import static com.example.demo.constants.KlinikaConstants.FREE_KLINIKA_SIZE;
import static com.example.demo.constants.KlinikaConstants.KLINIKA_NAME;
import static com.example.demo.constants.KlinikaConstants.PRETRAGA_IME;
import static com.example.demo.constants.KlinikaConstants.DATUM_PRETRAGA_1_STRING;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.demo.dto.DoktorDTO;
import com.example.demo.dto.KlinikaDTO;
import com.example.demo.dto.TerminDTO;
import com.example.demo.model.LogedUser;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class RestKlinikaControllerTest {
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@LocalServerPort
    int randomServerPort;

	@Test
	public void login() {
		HttpHeaders headers = new HttpHeaders();
		String url = "http://localhost:" + randomServerPort + "/auth/login";
		headers.set("Accept", "application/json");

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
		        .queryParam("user", "petar.pertovic@gmail.com")
		        .queryParam("pass", "njegos");

		HttpEntity<?> entity = new HttpEntity<>(headers); //Update this as per your code

		HttpEntity<String> response = restTemplate.exchange(
								        builder.build().encode().toUri(), 
								        HttpMethod.GET, 
								        entity, 
								        String.class);
		
		assertEquals(response.getBody(), LogedUser.getInstance().getUserId().toString());
	}
	
	@Test
	public void klinikePoTerminu() {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", "application/json");

		String url = "http://localhost:" + randomServerPort + "/pacijent/klinikePoTerminu";
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
		        .queryParam("datum", DATUM_PREGLEDA_STRING)
		        .queryParam("spec", SPECIJALIZACIJA);

		HttpEntity<?> entity = new HttpEntity<>(headers);
		
		HttpEntity<List<KlinikaDTO>> response = restTemplate.exchange(
		        builder.build().encode().toUri(), 
		        HttpMethod.GET, 
		        entity, new ParameterizedTypeReference<List<KlinikaDTO>>(){});
		
		List<KlinikaDTO> klinikePoTerminu = response.getBody();
		assertEquals(FREE_KLINIKA_SIZE, klinikePoTerminu.size());
		assertEquals(klinikePoTerminu.get(0).getIme(), KLINIKA_NAME);
	}
	
	@Test
	public void klinikeFiltriranje() {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", "application/json");
		
		String url = "http://localhost:" + randomServerPort + "/pacijent/searchClinics";
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
		        .queryParam("datum", DATUM_PREGLEDA_STRING)
		        .queryParam("spec", SPECIJALIZACIJA)
		        .queryParam("name", PRETRAGA_IME)
				.queryParam("desc", "")
		 	    .queryParam("rating", "")
		 	    .queryParam("address", "");

		HttpEntity<?> entity = new HttpEntity<>(headers);
		
		HttpEntity<List<KlinikaDTO>> response = restTemplate.exchange(
		        builder.build().encode().toUri(), 
		        HttpMethod.GET, 
		        entity, new ParameterizedTypeReference<List<KlinikaDTO>>(){});
		
		List<KlinikaDTO> klinike = response.getBody();
		assertEquals(1, klinike.size());
		assertEquals(klinike.get(0).getIme(), KLINIKA_NAME);
	}
	
	@Test
	public void klinikeFiltriranje1() {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", "application/json");
		
		String url = "http://localhost:" + randomServerPort + "/pacijent/searchClinics";
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
		        .queryParam("datum", DATUM_PRETRAGA_1_STRING)
		        .queryParam("spec", SPECIJALIZACIJA)
		        .queryParam("name", "")
				.queryParam("desc", "")
		 	    .queryParam("rating", "")
		 	    .queryParam("address", "");

		HttpEntity<?> entity = new HttpEntity<>(headers);
		
		HttpEntity<List<KlinikaDTO>> response = restTemplate.exchange(
		        builder.build().encode().toUri(), 
		        HttpMethod.GET, 
		        entity, new ParameterizedTypeReference<List<KlinikaDTO>>(){});
		
		List<KlinikaDTO> klinike = response.getBody();
		assertEquals(0, klinike.size());
	}
	
	@Test
	public void sviLekari() {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", "application/json");
		
		String url = "http://localhost:" + randomServerPort + "/pacijent/sviLekari";
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
		
		HttpEntity<?> entity = new HttpEntity<>(headers);
		
		HttpEntity<List<DoktorDTO>> response = restTemplate.exchange(
		        builder.build().encode().toUri(), 
		        HttpMethod.GET, 
		        entity, new ParameterizedTypeReference<List<DoktorDTO>>(){});
		
		List<DoktorDTO> lekari = response.getBody();
		assertEquals(4, lekari.size());
		assertEquals(lekari.get(0).getIme(), "Borisav");
		assertEquals(lekari.get(0).getSpecijalizacija(), SPECIJALIZACIJA);
		assertNotEquals(lekari.get(1).getSpecijalizacija(), SPECIJALIZACIJA);
	}
	
	@Test
	public void filtriranjeLekara() {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", "application/json");
		
		String url = "http://localhost:" + randomServerPort + "/pacijent/search";
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
		        .queryParam("ime", "Borisav")
		        .queryParam("prezime", "")
		        .queryParam("specijalizacija", SPECIJALIZACIJA)
				.queryParam("prosecnaOcena", "")
		 	    .queryParam("spec", "")
		 	    .queryParam("datum", "")
		 	    .queryParam("klinika_id", "");

		HttpEntity<?> entity = new HttpEntity<>(headers);
		
		HttpEntity<List<DoktorDTO>> response = restTemplate.exchange(
		        builder.build().encode().toUri(), 
		        HttpMethod.GET, 
		        entity, new ParameterizedTypeReference<List<DoktorDTO>>(){});
		
		List<DoktorDTO> lekari = response.getBody();
		assertEquals(1, lekari.size());
		assertEquals(lekari.get(0).getPrezime(), "Petkovic");
		assertEquals(lekari.get(0).getBrojOcena(), 0);
	}
	
	@Test
	public void filtriranjeLekara1() {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", "application/json");
		
		String url = "http://localhost:" + randomServerPort + "/pacijent/search";
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
		        .queryParam("ime", "")
		        .queryParam("prezime", "")
		        .queryParam("specijalizacija", SPECIJALIZACIJA1)
				.queryParam("prosecnaOcena", "")
		 	    .queryParam("spec", "")
		 	    .queryParam("datum", "")
		 	    .queryParam("klinika_id", "");

		HttpEntity<?> entity = new HttpEntity<>(headers);
		
		HttpEntity<List<DoktorDTO>> response = restTemplate.exchange(
		        builder.build().encode().toUri(), 
		        HttpMethod.GET, 
		        entity, new ParameterizedTypeReference<List<DoktorDTO>>(){});
		
		List<DoktorDTO> lekari = response.getBody();
		assertEquals(0, lekari.size());
	}
	
	@Test
	public void slobodniTermini() {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", "application/json");
		
		String url = "http://localhost:" + randomServerPort + "/pacijent/slobodniTermini";
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
		        .queryParam("datum", DATUM_PREGLEDA_STRING)
		        .queryParam("doktor", "1");
		
		HttpEntity<?> entity = new HttpEntity<>(headers);
		
		HttpEntity<List<TerminDTO>> response = restTemplate.exchange(
		        builder.build().encode().toUri(), 
		        HttpMethod.GET, 
		        entity, new ParameterizedTypeReference<List<TerminDTO>>(){});
		
		List<TerminDTO> termini = response.getBody();
		assertNotEquals(0, termini.size());
		assertEquals(2, termini.size());
	}
	
	@Test
	public void slobodniTermini1() {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", "application/json");
		
		String url = "http://localhost:" + randomServerPort + "/pacijent/slobodniTermini";
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
		        .queryParam("datum", DATUM_PREGLEDA_STRING)
		        .queryParam("doktor", "2");
		
		HttpEntity<?> entity = new HttpEntity<>(headers);
		
		HttpEntity<List<TerminDTO>> response = restTemplate.exchange(
		        builder.build().encode().toUri(), 
		        HttpMethod.GET, 
		        entity, new ParameterizedTypeReference<List<TerminDTO>>(){});
		
		List<TerminDTO> termini = response.getBody();
		assertNotEquals(0, termini.size());
		assertEquals(1, termini.size());
	}
	
	 
}
