package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.PregledDTO;
import com.example.demo.model.Doktor;
import com.example.demo.model.Klinika;
import com.example.demo.model.Korisnik;
import com.example.demo.model.Operacija;
import com.example.demo.model.Pregled;
import com.example.demo.model.Sala;
import com.example.demo.model.StatusOperacije;
import com.example.demo.model.StatusPregleda;
import com.example.demo.model.Termin;
import com.example.demo.service.DoktorService;
import com.example.demo.service.EmailService;
import com.example.demo.service.KlinikaService;
import com.example.demo.service.KorisnikService;
import com.example.demo.service.PregledService;
import com.example.demo.service.SalaService;
import com.example.demo.service.TerminService;


@RestController
@RequestMapping(value = "pregled")
public class PregledController {

	@Autowired
	private PregledService pregledService;
	
	@Autowired
	private TerminService terminService;
	
	@Autowired
	private SalaService salaService;
	
	@Autowired
	private DoktorService doktorService;
	
	@Autowired
	private KorisnikService korisnikService;
	
	@Autowired
	private KlinikaService klinikaService;
	
	@Autowired
	private EmailService emailService;
	
	@RequestMapping(value = "/sviPregledi/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<Pregled>> sviPregledi(@PathVariable("id") Long identifikacija) {
		List<Pregled> sviPregledi = pregledService.findAll();
		List<Pregled> pregledi = new ArrayList<Pregled>();
		for (Pregled pregled : sviPregledi) {
			if (pregled.getPacijent().getId().equals(identifikacija) && pregled.getStatus().equals(StatusPregleda.ZAVRSEN)) {
				pregledi.add(pregled);
			}
		}
		System.out.println("#############" + pregledi.size());
		return new ResponseEntity<List<Pregled>>(pregledi, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/izmeni/{id}", method = RequestMethod.PUT)
	public ResponseEntity<PregledDTO> izmeni(@RequestBody PregledDTO pregledDTO, @PathVariable("id") Long identifikacija) {
		Pregled pregled = pregledService.findOne(pregledDTO.getId());
		if (!pregled.getDoktor().getIdKorisnik().equals(identifikacija)) {
			return new ResponseEntity<PregledDTO>(HttpStatus.BAD_REQUEST);
		}
		pregled.setNaziv(pregledDTO.getNaziv());
		pregled.setAnamneza(pregledDTO.getAnamneza());
		pregled.setTipPregleda(pregledDTO.getTipPregleda());
		pregled.setCena(pregledDTO.getCena());
		Pregled p = pregledService.save(pregled);
		return new ResponseEntity<PregledDTO>(new PregledDTO(p), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/obrisi/{id}/{doktor_id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> obrisi(@PathVariable("id") Long identifikacija, @PathVariable("doktor_id") Long doktor_id) {
		Pregled pregled = pregledService.findOne(identifikacija);
		if (!pregled.getDoktor().getIdKorisnik().equals(doktor_id)) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		pregledService.delete(pregled);
		return new ResponseEntity<String>("", HttpStatus.OK);
	}
	

	@RequestMapping(value = "/rezervisi/{sala}/{pregled}/{doktori}", method = RequestMethod.GET)
	public ResponseEntity<String> rezervisiApt(@PathVariable("sala") Long sala_id, @PathVariable("pregled") Long pregled_id, @PathVariable("doktori") String text) {
		System.out.println("#######################");
		System.out.println("sala" + sala_id + "pregled" + pregled_id + "doktori" + text);
		Sala sala = salaService.findOne(sala_id);
		Pregled pregled = pregledService.findOne(pregled_id);
		pregled.setStatus(StatusPregleda.ZAKAZAN);
		pregled.setSala(sala);
		String[] splitter = text.split("~");
		List<Termin> termini = terminService.findAll();
		Korisnik pacijent = korisnikService.findOne(pregled.getPacijent().getIdKorisnik());
		Klinika klinika = klinikaService.findOne(pregled.getSala().getKlinika().getId());
		
		for (Termin termin : termini) {
			if (termin.getSala().getId().equals(sala.getId()) && termin.getDatum().equals(pregled.getDatumIVremePregleda())) {
				termin.setSlobodan(false);
				terminService.save(termin);
			}
		}
		
		for (String s : splitter) {
			if (!s.equals("")) {
				Doktor doktor = doktorService.findByIdKorisnik(Long.parseLong(s));
				System.out.println("###pre" + doktor.getOperacije().size());
				doktor.getPregledi().add(pregled);
				pregled.setDoktor(doktor);
				Korisnik dok = korisnikService.findOne(doktor.getIdKorisnik());
				emailService.sendSuccessfulReservationAptDoctor(pacijent, pregled, dok, klinika);
				doktorService.save(doktor);
			}
		}
		
		pregledService.save(pregled);
		emailService.sendSuccessfulReservationAptPatient(pacijent, pregled, klinika);
		return new ResponseEntity<String>("Uspesno rezervisana sala", HttpStatus.OK);
	}
	

	@RequestMapping(value = "/preuzmi/{id}", method = RequestMethod.GET)
	public ResponseEntity<Pregled> preuzmi(@PathVariable("id") Long identifikacija) {
		Pregled pregled = pregledService.findOne(identifikacija);
		return new ResponseEntity<Pregled>(pregled, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/zahtevi", method=RequestMethod.GET) 
	public ResponseEntity<List<Pregled>> zahtevi() {
		List<Pregled> pregledi = pregledService.findByStatus(StatusPregleda.NERASPOREDJEN);
		return new ResponseEntity<List<Pregled>>(pregledi, HttpStatus.OK);
	}

}
