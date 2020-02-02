package com.example.demo.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.DijagnozaDTO;
import com.example.demo.dto.PregledDTO;
import com.example.demo.model.Dijagnoza;
import com.example.demo.model.Korisnik;
import com.example.demo.model.Pregled;
import com.example.demo.model.UlogaKorisnika;
import com.example.demo.service.DijagnozaService;
import com.example.demo.service.KorisnikService;
import com.example.demo.service.PregledService;

@RestController
@RequestMapping(value = "dijagnoza")
public class DijagnozaController {

	@Autowired
	public DijagnozaService dijagnozaService;
	
	@Autowired
	public PregledService pregledService;
	
	@Autowired
	public KorisnikService korisnikService;
	
	@RequestMapping(value = "/sve_dijagnoze", method = RequestMethod.GET)
	public ResponseEntity<List<Dijagnoza>> sveDijagnoze() {
		List<Dijagnoza> dijagnoze = dijagnozaService.findAll();
		return new ResponseEntity<List<Dijagnoza>>(dijagnoze, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/pregled/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<Dijagnoza>> pregled(@PathVariable("id") Long identifikacija) {
		List<Dijagnoza> sveDijagnoze = dijagnozaService.findAll();
		List<Dijagnoza> dijagnoze = new ArrayList<Dijagnoza>();
		for(Dijagnoza dijagnoza : sveDijagnoze) {
			for (Pregled pregled : dijagnoza.getPregledi()) {
				if(pregled.getId().equals(identifikacija)) {
					dijagnoze.add(dijagnoza);
				}
			}
		}
		return new ResponseEntity<List<Dijagnoza>>(dijagnoze, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/izmeni_pregled/{text}/{id}", method = RequestMethod.GET)
	public ResponseEntity<PregledDTO> izmeni_pregled(@PathVariable("text") String text, @PathVariable("id") Long doktor_id) {
		Korisnik korisnik = korisnikService.findOne(doktor_id);
		if(!korisnik.getUloga().equals(UlogaKorisnika.LEKAR)) {
			return new ResponseEntity<PregledDTO>(HttpStatus.BAD_REQUEST);
		}
		String[] splitter = text.split("~");
		Long identifikacija = Long.parseLong(splitter[0]);
		Pregled pregled = pregledService.findOne(identifikacija);
		
		if (!pregled.getDoktor().getIdKorisnik().equals(doktor_id)) {
			return new ResponseEntity<PregledDTO>(HttpStatus.BAD_REQUEST);
		}
		pregled.setDijagnoze(new HashSet<Dijagnoza>());
		for (int i = 1; i < splitter.length; i++) {
			Dijagnoza dijagnoza = dijagnozaService.findOne(Long.parseLong(splitter[i]));
			pregled.getDijagnoze().add(dijagnoza);
			
		}
		Pregled p = pregledService.save(pregled);
		return new ResponseEntity<PregledDTO>(new PregledDTO(p), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/obrisi/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> obrisiDijagnozu(@PathVariable("id") Long identifikacija) {
		Dijagnoza dijagnoza = dijagnozaService.findOne(identifikacija);
		dijagnozaService.delete(dijagnoza);
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/izmeni", method = RequestMethod.PUT)
	public ResponseEntity<DijagnozaDTO> izmeniDijagnozu(@RequestBody DijagnozaDTO dijagnozaDTO) {
		Dijagnoza dijagnoza = dijagnozaService.findOne(dijagnozaDTO.getId());
		dijagnoza.setIme(dijagnozaDTO.getIme());
		dijagnoza.setSifra(dijagnozaDTO.getSifra());
		dijagnoza.setOpis(dijagnozaDTO.getOpis());
		Dijagnoza d = dijagnozaService.save(dijagnoza);
		return new ResponseEntity<DijagnozaDTO>(new DijagnozaDTO(d), HttpStatus.OK);
	}
	
}
