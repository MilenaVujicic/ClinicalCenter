package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.DoktorDTO;
import com.example.demo.dto.KlinikaDTO;
import com.example.demo.dto.KorisnikDTO;
import com.example.demo.model.Doktor;
import com.example.demo.model.Klinika;
import com.example.demo.model.Korisnik;
import com.example.demo.model.Pacijent;
import com.example.demo.model.UlogaKorisnika;
import com.example.demo.service.DoktorService;
import com.example.demo.service.KlinikaService;
import com.example.demo.service.KorisnikService;

@RestController
@RequestMapping(value = "pacijent")
public class PacijentController {
	
	@Autowired
	KlinikaService klinikaService;
	
	@Autowired
	DoktorService doktorService;
	
	@Autowired
	KorisnikService korisnikService;
	
	@RequestMapping(value = "/sveKlinike", method=RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<KlinikaDTO>> getAllClinics() {
		List<Klinika> clinics = klinikaService.findAll();
		List<KlinikaDTO> klinikaDTO = new ArrayList<>();
		
		for (Klinika k : clinics) {
			klinikaDTO.add(new KlinikaDTO(k));
		}
		return new ResponseEntity<>(klinikaDTO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/sviLekari", method=RequestMethod.GET)
	public ResponseEntity<List<DoktorDTO>> getAllDoctors() {
		List<Doktor> doctors = doktorService.findAll();
		List<Korisnik> users = korisnikService.findByUloga(UlogaKorisnika.LEKAR);
		List<DoktorDTO> doktoriDTO = new ArrayList<>();
		
		for(Doktor d : doctors) {
			doktoriDTO.add(new DoktorDTO(d));
		}
		
		for(Korisnik k : users) {
			for(DoktorDTO d: doktoriDTO) {
				if (k.getId() == d.getIdKorisnik()) {
					d.setId(k.getId());
					d.setIme(k.getIme());
					d.setPrezime(k.getPrezime());
				}
			}
		}
		return new ResponseEntity<>(doktoriDTO, HttpStatus.OK);	
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<List<DoktorDTO>> getDoctorsSearch(@RequestParam(value="ime") String ime,
															@RequestParam(value="prezime") String prezime,
															@RequestParam(value="specijalizacija") String specijalizacija,
															@RequestParam(value="prosecnaOcena") String prosecnaOcena) {
		
		System.out.println(ime+" " + prezime +  " " + specijalizacija + " " + prosecnaOcena);
		List<Doktor> doctors = doktorService.findAll();
		List<Korisnik> users = korisnikService.findByUloga(UlogaKorisnika.LEKAR);
		List<DoktorDTO> doktoriDTO = new ArrayList<>();
		
		for(Doktor d : doctors) {
			Korisnik k = korisnikService.findById(d.getIdKorisnik());
			if(k.getIme().contains(ime) && k.getPrezime().contains(prezime) && d.getSpecijalizacija().contains(specijalizacija)
					&& Double.toString(d.getProsenaOcena()).contains(prosecnaOcena))
			{
				doktoriDTO.add(new DoktorDTO(d));
			}
				
		}

		for(Korisnik k : users) {
			for(DoktorDTO d: doktoriDTO) {
				if (k.getId() == d.getIdKorisnik()) {
					d.setId(k.getId());
					d.setIme(k.getIme());
					d.setPrezime(k.getPrezime());
					System.out.println(d.getIme()+" " + d.getPrezime() +  " " + d.getSpecijalizacija() + " " + d.getProsecnaOcena());
				}
			}
		}
		
		return new ResponseEntity<>(doktoriDTO, HttpStatus.OK);	
		
	}
	
	@RequestMapping(value = "/lekar/{id}", method=RequestMethod.GET)
	public Doktor getDoctor(@PathVariable Long id) {
		Doktor doktor = doktorService.findByIdKorisnik(id);
		System.out.println(doktor.getSpecijalizacija());
		return doktor;
	}
}
