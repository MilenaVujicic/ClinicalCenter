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

import com.example.demo.dto.OperacijaDTO;
import com.example.demo.model.AdministratorKlinike;
import com.example.demo.model.Doktor;
import com.example.demo.model.Klinika;
import com.example.demo.model.Korisnik;
import com.example.demo.model.LogedUser;
import com.example.demo.model.Odsustvo;
import com.example.demo.model.Operacija;
import com.example.demo.model.Pregled;
import com.example.demo.model.Sala;
import com.example.demo.model.StatusOperacije;
import com.example.demo.model.StatusPregleda;
import com.example.demo.model.Termin;
import com.example.demo.model.UlogaKorisnika;
import com.example.demo.service.AdministratorKlinikeService;
import com.example.demo.service.DoktorService;
import com.example.demo.service.EmailService;
import com.example.demo.service.KlinikaService;
import com.example.demo.service.KorisnikService;
import com.example.demo.service.OperacijaService;
import com.example.demo.service.PregledService;
import com.example.demo.service.SalaService;
import com.example.demo.service.TerminService;


@RestController
@RequestMapping(value = "operacija")
public class OperacijaController {

	@Autowired
	private OperacijaService operacijaService;
	
	@Autowired
	private SalaService salaService;
	
	@Autowired
	private DoktorService doktorService;
	
	@Autowired
	private TerminService terminService;
	
	@Autowired
	private KorisnikService korisnikService;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private KlinikaService klinikaService;
	
	@Autowired
	private AdministratorKlinikeService adminKlinikeService;

	
	@RequestMapping(value = "/zahtevi", method=RequestMethod.GET) 
	public ResponseEntity<List<Operacija>> zahtevi() {
		if (!LogedUser.getInstance().getUserRole().equals(UlogaKorisnika.ADMIN_KLINIKE)) {
			return new ResponseEntity<List<Operacija>>(HttpStatus.BAD_REQUEST);
		}
		AdministratorKlinike admin = adminKlinikeService.findByIdKorisnika(LogedUser.getInstance().getUserId());
		List<Operacija> operacije = operacijaService.findByStatus(StatusOperacije.NERASPOREDJEN);
		List<Operacija> operacijeKlinike = new ArrayList<Operacija>();
		for (Operacija o : operacije) {
			if(o.getPacijent().getKlinika().getId().equals(admin.getKlinika().getId())) {
				operacijeKlinike.add(o);
			}
		}
		return new ResponseEntity<List<Operacija>>(operacijeKlinike, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/rezervisi/{sala}/{operacija}/{doktori}", method = RequestMethod.GET)
	public ResponseEntity<String> rezervisi(@PathVariable("sala") Long sala_id, @PathVariable("operacija") Long operacija_id, @PathVariable("doktori") String text) {
		System.out.println("sala" + sala_id + "operacija" + operacija_id + "doktori" + text);
		Sala sala = salaService.findOne(sala_id);
		Operacija operacija = operacijaService.findOne(operacija_id);
		operacija.setStatus(StatusOperacije.ZAKAZAN);
		operacija.setSala(sala);
		String[] splitter = text.split("~");
		List<Termin> termini = terminService.findAll();
		Korisnik pacijent = korisnikService.findOne(operacija.getPacijent().getIdKorisnik());
		Klinika klinika = klinikaService.findOne(operacija.getSala().getKlinika().getId());
		
		for (Termin termin : termini) {
			if (termin.getSala().getId().equals(sala.getId()) && termin.getDatum().equals(operacija.getDatumIVremeOperacije())) {
				termin.setSlobodan(false);
				terminService.save(termin);
			}
		}
		
		for (String s : splitter) {
			if (!s.equals("")) {
				Doktor doktor = doktorService.findByIdKorisnik(Long.parseLong(s));
				System.out.println("###pre" + doktor.getOperacije().size());
				doktor.getOperacije().add(operacija);
				operacija.getDoktori().add(doktor);
				System.out.println("###posle" + doktor.getOperacije().size());
				Korisnik dok = korisnikService.findOne(doktor.getIdKorisnik());
				emailService.sendSuccessfulReservationDoctor(pacijent, operacija, dok, klinika);
				doktorService.save(doktor);
			}
		}
		
		operacijaService.save(operacija);
		emailService.sendSuccessfulReservationPatient(pacijent, operacija, klinika);
		return new ResponseEntity<String>("Uspesno rezervisana sala", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/preuzmi/{id}", method = RequestMethod.GET)
	public ResponseEntity<Operacija> preuzmi(@PathVariable("id") Long identifikacija) {
		Operacija operacija = operacijaService.findOne(identifikacija);
		return new ResponseEntity<Operacija>(operacija, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/sveOperacije/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<Operacija>> sveOperacijePacijenta(@PathVariable("id") Long identifikacija) {
		List<Operacija> sveOperacije = operacijaService.findAll();
		List<Operacija> operacije = new ArrayList<Operacija>();
		for (Operacija operacija : sveOperacije) {
			if (operacija.getPacijent().getId().equals(identifikacija) && operacija.getStatus().equals(StatusOperacije.ZAVRSEN)) {
				operacije.add(operacija);
			}
		}
		System.out.println("#############" + operacije.size());
		return new ResponseEntity<List<Operacija>>(operacije, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/obrisi/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> obrisi(@PathVariable("id") Long identifikacija) {
		if (!LogedUser.getInstance().getUserRole().equals(UlogaKorisnika.LEKAR)) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		Operacija operacija = operacijaService.findOne(identifikacija);
		Doktor doktor = doktorService.findByIdKorisnik(LogedUser.getInstance().getUserId());
		if (!operacija.getDoktori().contains(doktor)) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		//operacijaService.delete(operacija);
		operacija.setStatus(StatusOperacije.OBRISAN);
		Operacija o = operacijaService.save(operacija);
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/izmeni", method = RequestMethod.PUT)
	public ResponseEntity<OperacijaDTO> izmeni(@RequestBody OperacijaDTO operacijaDTO) {
		if (!LogedUser.getInstance().getUserRole().equals(UlogaKorisnika.LEKAR)) {
			return new ResponseEntity<OperacijaDTO>(HttpStatus.BAD_REQUEST);
		}
		Operacija operacija = operacijaService.findOne(operacijaDTO.getId());
		Doktor doktor = doktorService.findByIdKorisnik(LogedUser.getInstance().getUserId());
		if (!operacija.getDoktori().contains(doktor)) {
			return new ResponseEntity<OperacijaDTO>(HttpStatus.BAD_REQUEST);
		}
		operacija.setOpis(operacijaDTO.getOpis());
		Operacija o = operacijaService.save(operacija);
		return new ResponseEntity<OperacijaDTO>(new OperacijaDTO(o), HttpStatus.OK);
	}
	

}
