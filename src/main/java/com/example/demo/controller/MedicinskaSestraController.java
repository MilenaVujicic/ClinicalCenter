package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.KorisnikDTO;
import com.example.demo.model.Korisnik;
import com.example.demo.model.Pacijent;
import com.example.demo.model.Recept;
import com.example.demo.model.StatusRecepta;
import com.example.demo.model.UlogaKorisnika;
import com.example.demo.service.KorisnikService;
import com.example.demo.service.PacijentService;
import com.example.demo.service.ReceptService;

@RestController
@RequestMapping(value = "medicinska_sestra")
public class MedicinskaSestraController {

	@Autowired
	KorisnikService korisnikService;
	
	@Autowired
	PacijentService pacijentService;
	
	@Autowired
	ReceptService receptService;
	
	@RequestMapping(value = "/sviPacijenti", method=RequestMethod.GET)
	public ResponseEntity<List<KorisnikDTO>> getAllPatients() {
		List<Korisnik> patients = korisnikService.findByUloga(UlogaKorisnika.PACIJENT);
		List<KorisnikDTO> pacijentDTO = new ArrayList<>();
		
		for (Korisnik p : patients) {
			pacijentDTO.add(new KorisnikDTO(p));
		}
		return new ResponseEntity<>(pacijentDTO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/pacijent/{id}", method=RequestMethod.GET)
	public Pacijent getPatient(@PathVariable Long id) {
		Pacijent pacijent = pacijentService.findByIdKorisnik(id);
		return pacijent;
	}
	
	@RequestMapping(value = "/recepti/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<Recept>> recepti(@PathVariable("id") Long identifikacija) {
		List<Recept> recepti = receptService.findAll();
		List<Recept> neovereni = new ArrayList<Recept>();
		for (Recept r : recepti) {
			if (r.getPacijent().getId().equals(identifikacija) && r.getStatus().equals(StatusRecepta.NEOVEREN)) {
				neovereni.add(r);
			}
		}
		
		return new ResponseEntity<List<Recept>>(neovereni, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/overi/{id}", method = RequestMethod.GET)
	public ResponseEntity<String> overa(@PathVariable("id") Long identifikacija) {
		List<Recept> recepti = receptService.findAll();
		for (Recept r : recepti) {
			if (r.getPacijent().getId().equals(identifikacija) && r.getStatus().equals(StatusRecepta.NEOVEREN)) {
				r.setStatus(StatusRecepta.OVEREN);
				r.setDatumOvere(new Date());
				Recept rec = receptService.save(r);
			}
		}
		return new ResponseEntity<String>("Uspesno su overeni recepti", HttpStatus.OK);
	}
}
