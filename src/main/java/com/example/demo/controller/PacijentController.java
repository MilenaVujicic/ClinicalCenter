package com.example.demo.controller;

import java.awt.PageAttributes.MediaType;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
import com.example.demo.service.PacijentService;


@RestController
@RequestMapping(value = "pacijent")
public class PacijentController {
	
	@Autowired
	KlinikaService klinikaService;
	
	@Autowired

	PacijentService pacijentService;

	DoktorService doktorService;

	
	@Autowired
	KorisnikService korisnikService;
	

	private List<Korisnik> foundUsers = new ArrayList<Korisnik>();
	

	@RequestMapping(value = "/sveKlinike", method=RequestMethod.GET)
	public ResponseEntity<List<KlinikaDTO>> getAllClinics() {
		List<Klinika> clinics = klinikaService.findAll();
		List<KlinikaDTO> klinikaDTO = new ArrayList<>();
		
		for (Klinika k : clinics) {
			klinikaDTO.add(new KlinikaDTO(k));
		}
		return new ResponseEntity<>(klinikaDTO, HttpStatus.OK);
	}
	

	@RequestMapping(value = "/listaPacijenata", method = RequestMethod.GET)
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
		System.out.println("#########################");
		return pacijent;
	}
	

	@RequestMapping(value = "/searchK/{val}", method=RequestMethod.GET)
	public ResponseEntity<List<KorisnikDTO>> searchPatientsName(@PathVariable String val){
		List<KorisnikDTO> retVal = new ArrayList<KorisnikDTO>();
		System.out.println(val);
		String[] parts = val.split(":");
		String type = parts[1];
		String value = parts[0];
		value = value.toLowerCase();
		List<Korisnik> korisnici = korisnikService.findAll();
		List<Pacijent> pacijenti = pacijentService.findAll();
		if(type.equals("Ime")) {
			for(Korisnik k : korisnici) {
				System.out.println(k.getIme() + " " +  k.getId());
				if(k.getIme().toLowerCase().contains(value) || k.getIme().toLowerCase().equals(value)) {
					System.out.println(k.getIme());
					for(Pacijent p : pacijenti) {
						if(p.getId() == k.getId()) {
							retVal.add(new KorisnikDTO(k));
						}
					}
					
				}
			}
		}else if(type.equals("Prezime")) {
			for(Korisnik k: korisnici) {
				if(k.getPrezime().toLowerCase().contains(value) || k.getPrezime().toLowerCase().equals(value)) {
					for(Pacijent p : pacijenti) {
						if(p.getId() == k.getId()) {
							retVal.add(new KorisnikDTO(k));
						}
					}
				}
			}
		}
		return new ResponseEntity<>(retVal, HttpStatus.OK);
	}
/*
	@RequestMapping(value = "/izmeni", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces="application/json")
	public ResponseEntity<List<Pacijent>> searchPatients(@RequestBody String param){
		//DTO objekat. 
		List<Pacijent> retVal = new ArrayList<Pacijent>();
		param = param.substring(1);
		param = param.substring(0, param.length()-1);
		String type = "";
		String value = "";
		int i = 0;
		String[] parts = param.split(",");
		for(String p : parts){
			System.out.println(p);
			String[] tokens = p.split(":");
			if (i == 0) {
				type = tokens[1];
				type = type.replace("\"", "");
			}else if(i == 1) {
				value = tokens[1];
				value = value.replace("\"", "");

		}else if(type.equals("JedinstveniBroj")) {
			try {
				Long lval = Long.parseLong(value);
				for(Korisnik k : korisnici) {
					if(k.getId() == lval) {
						for(Pacijent p : pacijenti) {
							if(p.getId() == k.getId()) {
								retVal.add(new KorisnikDTO(k));
							}
						}
					}
				}
			}catch(Exception e) {
				return new ResponseEntity<>(null, HttpStatus.METHOD_NOT_ALLOWED);
			}
			
		}
			
		}
		
		return new ResponseEntity<>(retVal, HttpStatus.OK);


	}
*/
	
	
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
			Optional<Korisnik> kr = korisnikService.findById(d.getIdKorisnik());
			Korisnik k = kr.get();
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
			
