package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.KlinikaDTO;
import com.example.demo.dto.KorisnikDTO;
import com.example.demo.dto.PacijentDTO;
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
	
	@RequestMapping(value = "/searchName/{val}", method=RequestMethod.GET)
	public ResponseEntity<List<KorisnikDTO>> searchPatientsName(@PathVariable String val){
		List<KorisnikDTO> retVal = new ArrayList<KorisnikDTO>();
		System.out.println(val);
		String[] parts = val.split(":");
		String type = parts[1];
		String value = parts[0];
		value = value.toLowerCase();
		List<Korisnik> korisnici = korisnikService.findAll();
		
		if(type.equals("Ime")) {
			for(Korisnik k : korisnici) {
				if(k.getIme().toLowerCase().contains(value) || k.getIme().toLowerCase().equals(value)) {
					System.out.println(k.getIme());
					retVal.add(new KorisnikDTO(k));
				}
			}
		}
		

		
		return new ResponseEntity<>(retVal, HttpStatus.OK);
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
