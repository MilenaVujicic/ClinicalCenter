package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.KlinikaDTO;
import com.example.demo.dto.KorisnikDTO;
import com.example.demo.model.Klinika;
import com.example.demo.model.Korisnik;
import com.example.demo.model.Pacijent;
import com.example.demo.model.UlogaKorisnika;
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
	
	@Autowired
	KorisnikService korisnikService;
	
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
	
	@RequestMapping(value = "/izmeni", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces="application/json")
	public List<Pacijent> searchPatients(@RequestBody String param){
		List<Pacijent> retVal = new ArrayList<Pacijent>();
		System.out.println("HERE");
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
			}
			i++;
		}
		return retVal ;
	}

	public List<Korisnik> searchByCriteria(String type, String value) {
		List<Korisnik> retVal = new ArrayList<Korisnik>();
	
		List<Pacijent> pacijenti = pacijentService.findAll();
		List<Korisnik> korisnici = korisnikService.findAll();
		
		if(type.equals("Ime")) {
			for(Korisnik k : korisnici) {
				if(k.getIme().toLowerCase().equals(value.toLowerCase())) {
					retVal.add(k);
				}
			}
		}else if(type.equals("Prezime")) {
			for(Korisnik k: korisnici) {
				if(k.getPrezime().toLowerCase().equals(value.toLowerCase())) {
					retVal.add(k);
				}
			}
		}else if(type.equals("JedinstveniBroj")) {
			Long id = Long.parseLong(value);
			for(Pacijent p: pacijenti) {
				
				if(p.getId().equals(id)) {
					Optional<Korisnik> k = korisnikService.findById(id);
					retVal.add(k.get());
				}
			}
		}
			
		return retVal;	
	}
	
	public List<Object> filterByCriteria(String type, List<Korisnik> korisnici) {
		List<Object> found = new ArrayList<Object>();
		
		switch(type) {
		case "Ime":
			found.add(1);
			for(Korisnik k : korisnici) {
				found.add(k.getIme());
			}
			break;
		case "Prezime":
			found.add(2);
			for(Korisnik k : korisnici) {
				found.add(k.getPrezime());
			}
			break;
		case "JedinstveniBroj":
			found.add(1);
			for(Korisnik k: korisnici) {
				found.add(k.getId());
			}
			break;
		case "Ime_Prezime":
			found.add(2);
			for(Korisnik k : korisnici) {
				found.add(k.getIme());
				found.add(k.getPrezime());
			}
			break;
		case "Ime_JedinstveniBroj":
			found.add(2);
			for(Korisnik k : korisnici) {
				found.add(k.getIme());
				found.add(k.getId());
			}
			break;
		case "Prezime_JedinstveniBroj":
			found.add(2);
			for(Korisnik k: korisnici) {
				found.add(k.getPrezime());
				found.add(k.getId());
			}
			break;
		case "Ime_Prezime_JedinstveniBroj":
			found.add(3);
			for(Korisnik k : korisnici) {
				found.add(k.getIme());
				found.add(k.getPrezime());
				found.add(k.getId());
			}
			break;
		}
		
		return found;
	}
}
