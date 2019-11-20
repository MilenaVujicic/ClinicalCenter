package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.AdministratorKlinickogCentraDTO;
import com.example.demo.dto.KlinikaDTO;
import com.example.demo.dto.KorisnikDTO;
import com.example.demo.model.AdministratorKlinickogCentra;
import com.example.demo.model.AdministratorKlinike;
import com.example.demo.model.Klinika;
import com.example.demo.model.Korisnik;
import com.example.demo.model.UlogaKorisnika;
import com.example.demo.service.AdministratorKlinickogCentraService;
import com.example.demo.service.AdministratorKlinikeService;
import com.example.demo.service.KlinikaService;
import com.example.demo.service.KorisnikService;

@RestController
@RequestMapping(value = "admin_klinickog_centra")
public class AdministratorKlinickogCentraController {
	
	@Autowired
	KorisnikService korisnikService;
	
	@Autowired
	AdministratorKlinickogCentraService administratorKlinickogCentraService;
	
	@Autowired
	KlinikaService klinikaService;
	
	@Autowired
	AdministratorKlinikeService administratorKlinikeService;

	@RequestMapping(value = "/novi_admin", method=RequestMethod.POST)
	public ResponseEntity<Map<AdministratorKlinickogCentraDTO, KorisnikDTO>> dodajAdmina(@RequestBody KorisnikDTO korisnikDTO) {
		Korisnik korisnik = new Korisnik();
		korisnik.setIme(korisnikDTO.getIme());
		korisnik.setPrezime(korisnikDTO.getPrezime());
		korisnik.setEmail(korisnikDTO.getEmail());
		korisnik.setAdresa(korisnikDTO.getAdresa());
		korisnik.setGrad(korisnikDTO.getGrad());
		korisnik.setDrzava(korisnikDTO.getDrzava());
		korisnik.setTelefon(3625415);
		korisnik.setJmbg(korisnikDTO.getJmbg());
		korisnik.setPassword(UUID.randomUUID().toString());
		korisnik.setDatumRodjenja(new Date());
		korisnik.setUloga(UlogaKorisnika.ADMIN_CENTRA);
		if (korisnik.getIme() == "" || korisnik.getIme() == null){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		if (korisnik.getPrezime() == "" || korisnik.getPrezime() == null){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		if (!korisnik.getEmail().contains("@")){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		List<Korisnik> admini = korisnikService.findByUloga(UlogaKorisnika.ADMIN_CENTRA);
		
		korisnik.setUsername("admin" + admini.size());
		Korisnik k = korisnikService.save(korisnik);
		
		AdministratorKlinickogCentra admin = new AdministratorKlinickogCentra();
		admin.setIdKorisnik(korisnik.getId());
		
		AdministratorKlinickogCentra a = administratorKlinickogCentraService.save(admin);
		
		Map<AdministratorKlinickogCentraDTO, KorisnikDTO> map = new HashMap<AdministratorKlinickogCentraDTO, KorisnikDTO>();
		map.put(new AdministratorKlinickogCentraDTO(a), new KorisnikDTO(k));
		return new ResponseEntity<>(map, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/svi_admini_centra", method = RequestMethod.GET)
	public ResponseEntity<List<Korisnik>> sviAdministratoriKlinickogCentra() {
		List<AdministratorKlinickogCentra> admini = administratorKlinickogCentraService.findAll();
		List<Korisnik> korisnici = korisnikService.findByUloga(UlogaKorisnika.ADMIN_CENTRA);
		List<Korisnik> administratori = new ArrayList<Korisnik>();
		for(AdministratorKlinickogCentra a : admini) {
			for(Korisnik k : korisnici) {
				if (k.getId().equals(a.getIdKorisnik())) {
					administratori.add(k);
				}
			}
		}
		
		return new ResponseEntity<List<Korisnik>>(administratori, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/nova_klinika", method=RequestMethod.POST)
	public ResponseEntity<KlinikaDTO> newClinic(@RequestBody KlinikaDTO klinikaDTO) {
		Klinika klinika = new Klinika();
		klinika.setIme(klinikaDTO.getIme());
		klinika.setOpis(klinikaDTO.getOpis());
		klinika.setAdresa(klinikaDTO.getAdresa());
		klinika.setProsecnaOcena(0);

		if(klinika.getIme() == "" || klinika.getIme() == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		Klinika k = klinikaService.save(klinika);
		return new ResponseEntity<>(new KlinikaDTO(k), HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/novi_admin_klinike/{id}", method = RequestMethod.POST)
	public ResponseEntity<KlinikaDTO> noviAdminKlinike(@PathVariable("id") Long identifikacija, @RequestBody KorisnikDTO korisnikDTO) {
		Korisnik korisnik = new Korisnik();
		korisnik.setIme(korisnikDTO.getIme());
		korisnik.setPrezime(korisnikDTO.getPrezime());
		korisnik.setEmail(korisnikDTO.getEmail());
		korisnik.setAdresa(korisnikDTO.getAdresa());
		korisnik.setGrad(korisnikDTO.getGrad());
		korisnik.setDrzava(korisnikDTO.getDrzava());
		korisnik.setTelefon(korisnikDTO.getTelefon());
		korisnik.setJmbg((long) 3625415);
		List<Korisnik> admini = korisnikService.findByUloga(UlogaKorisnika.ADMIN_KLINIKE);
		if (korisnik.getIme() == "" || korisnik.getIme() == null){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		if (korisnik.getPrezime() == "" || korisnik.getPrezime() == null){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		if (!korisnik.getEmail().contains("@")){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		korisnik.setUsername("admin_klinike" + admini.size());
		korisnik.setPassword(UUID.randomUUID().toString());
		korisnik.setDatumRodjenja(new Date());
		korisnik.setUloga(UlogaKorisnika.ADMIN_KLINIKE);
		System.out.println(korisnik);
		Korisnik k = korisnikService.save(korisnik);
		
		AdministratorKlinike administratorKlinike = new AdministratorKlinike();
		administratorKlinike.setIdKorisnik(k.getId());
		
		Klinika klinika = klinikaService.findOne(identifikacija);
		administratorKlinike.setKlinika(klinika);
		klinika.getAdministratoriKlinike().add(administratorKlinike);
		AdministratorKlinike a = administratorKlinikeService.save(administratorKlinike);
		Klinika kl = klinikaService.save(klinika);
		return new ResponseEntity<>(new KlinikaDTO(kl), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/sve_klinike", method=RequestMethod.GET)
	public ResponseEntity<List<Klinika>> allClinic() {
		System.out.println("################");
		List<Klinika> klinike = klinikaService.findAll();
		return new ResponseEntity<List<Klinika>>(klinike, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/svi_admini_klinike/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<Korisnik>> svi_admini(@PathVariable("id") Long identifikacija) {
		List<AdministratorKlinike> admini = administratorKlinikeService.findAll();
		List<Korisnik> korisnici = korisnikService.findByUloga(UlogaKorisnika.ADMIN_KLINIKE);
		List<Korisnik> administratori = new ArrayList<Korisnik>();
		for(AdministratorKlinike a : admini) {
			if(a.getKlinika().getId().equals(identifikacija)) {
				for(Korisnik k : korisnici) {
					if (k.getId().equals(a.getIdKorisnik())) {
						administratori.add(k);
					}
				}
			}
		}

		return new ResponseEntity<List<Korisnik>>(administratori, HttpStatus.OK);
	}
}
