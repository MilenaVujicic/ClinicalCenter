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
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.AdministratorKlinickogCentraDTO;
import com.example.demo.dto.DijagnozaDTO;
import com.example.demo.dto.KlinikaDTO;
import com.example.demo.dto.KorisnikDTO;
import com.example.demo.dto.LekDTO;
import com.example.demo.model.AdministratorKlinickogCentra;
import com.example.demo.model.AdministratorKlinike;
import com.example.demo.model.Dijagnoza;
import com.example.demo.model.Klinika;
import com.example.demo.model.Korisnik;
import com.example.demo.model.Lek;
import com.example.demo.model.UlogaKorisnika;
import com.example.demo.service.AdministratorKlinickogCentraService;
import com.example.demo.service.AdministratorKlinikeService;
import com.example.demo.service.DijagnozaService;
import com.example.demo.service.EmailService;
import com.example.demo.service.KlinikaService;
import com.example.demo.service.KorisnikService;
import com.example.demo.service.LekService;

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
	
	@Autowired
	LekService lekService;
	
	@Autowired
	DijagnozaService dijagnozaService;
	
	@Autowired
	EmailService emailService;

	@RequestMapping(value = "/novi_admin", method=RequestMethod.POST)
	public ResponseEntity<Map<AdministratorKlinickogCentraDTO, KorisnikDTO>> dodajAdmina(@RequestBody KorisnikDTO korisnikDTO) {
		Korisnik korisnik = new Korisnik();
		korisnik.setIme(korisnikDTO.getIme());
		korisnik.setPrezime(korisnikDTO.getPrezime());
		korisnik.setEmail(korisnikDTO.getEmail());
		korisnik.setAdresa(korisnikDTO.getAdresa());
		korisnik.setGrad(korisnikDTO.getGrad());
		korisnik.setDrzava(korisnikDTO.getDrzava());
		korisnik.setTelefon("3625415");
		korisnik.setJmbg(korisnikDTO.getJmbg());
		korisnik.setPassword(UUID.randomUUID().toString());
		korisnik.setDatumRodjenja(new Date());
		korisnik.setUloga(UlogaKorisnika.ADMIN_CENTRA);
		korisnik.setAktivan(false);
		korisnik.setAktiviran(false);
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
				if (k.getId().equals(a.getIdKorisnik()) && k.isAktiviran()) {
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
		System.out.println("#######################");
		Korisnik korisnik = new Korisnik();
		korisnik.setIme(korisnikDTO.getIme());
		korisnik.setPrezime(korisnikDTO.getPrezime());
		korisnik.setEmail(korisnikDTO.getEmail());
		korisnik.setAdresa(korisnikDTO.getAdresa());
		korisnik.setGrad(korisnikDTO.getGrad());
		korisnik.setDrzava(korisnikDTO.getDrzava());
		korisnik.setTelefon(korisnikDTO.getTelefon());
		korisnik.setJmbg((long) 3625415);
		korisnik.setAktivan(false);
		korisnik.setAktiviran(false);
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
					if (k.getId().equals(a.getIdKorisnik()) && k.isAktiviran()) {
						administratori.add(k);
					}
				}
			}
		}

		return new ResponseEntity<List<Korisnik>>(administratori, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/sviZahtevi", method = RequestMethod.GET)
	public ResponseEntity<List<Korisnik>> svi_zahtevi() {
		List<Korisnik> korisnici = korisnikService.findByAktivan(false);
		return new ResponseEntity<List<Korisnik>>(korisnici, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/prihvati/{id}", method = RequestMethod.GET)
	public ResponseEntity<KorisnikDTO> prihvati(@PathVariable("id") Long identifikacija) {
		List<Korisnik> korisnici = korisnikService.findAll();
		Korisnik korisnik = new Korisnik();
		for(Korisnik k : korisnici) {
			if(k.getId().equals(identifikacija)) {
				korisnik = k;
			}
		}
		korisnik.setAktivan(true);
		Korisnik kor = korisnikService.save(korisnik);
		try {
			emailService.sendNotificaitionAllow(kor);
		} catch (MailException e) {
			// TODO Auto-generated catch block
			System.out.println("##################### Desila se greska1" + e.getMessage());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.out.println("##################### Desila se greska2");
		}
		return new ResponseEntity<KorisnikDTO>(new KorisnikDTO(kor), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/odbij/{text}", method = RequestMethod.GET)
	public ResponseEntity<String> odbij(@PathVariable("text") String text) {
		System.out.println("##############" + text);
		String[] splitter = text.split("~");
		Long identifikacija = Long.parseLong(splitter[0]);
		String razlog = splitter[1];
		List<Korisnik> korisnici = korisnikService.findAll();
		Korisnik korisnik = new Korisnik();
		for(Korisnik k : korisnici) {
			if(k.getId().equals(identifikacija)) {
				korisnik = k;
			}
		}
		korisnikService.delete(korisnik);
		try {
			emailService.sendNotificationDeny(korisnik, razlog);
		} catch (MailException e) {
			// TODO Auto-generated catch block
			System.out.println("##################### Desila se greska1" + e.getMessage());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.out.println("##################### Desila se greska2");
		}
		return new ResponseEntity<String>("", HttpStatus.OK);
	}
	
	@RequestMapping(value="/nova_dijanoza", method = RequestMethod.POST)
	public ResponseEntity<String> dijagnoza(@RequestBody DijagnozaDTO dijanozaDTO) {
		Dijagnoza dijagnoza = new Dijagnoza();
		dijagnoza.setSifra(dijanozaDTO.getSifra());
		dijagnoza.setIme(dijanozaDTO.getIme());
		dijagnoza.setOpis(dijanozaDTO.getOpis());
		List<Dijagnoza> dijagnoze = dijagnozaService.findAll();
		
		Boolean nasla = false;
		String odgovor = "";
		for (Dijagnoza d : dijagnoze) {
			if (d.getIme().equals(dijagnoza.getIme()) || d.getSifra().equals(dijagnoza.getSifra())) {
				nasla = true;
				break;
			}
		}
		
		if (!nasla) {
			Dijagnoza d = dijagnozaService.save(dijagnoza);
			odgovor = "Nova dijagnoza je uspesno dodata.";
		} else {
			odgovor = "Dijagnoza mora da bude jedinstvena";
		}
		
		return new ResponseEntity<String>(odgovor, HttpStatus.OK);
	}
	
	@RequestMapping(value="/novi_lek", method = RequestMethod.POST)
	public ResponseEntity<String> lek(@RequestBody LekDTO lekDTO) {
		Lek lek = new Lek();
		lek.setSifra(lekDTO.getSifra());
		lek.setIme(lekDTO.getIme());
		lek.setOpis(lekDTO.getOpis());
		
		List<Lek> lekovi = lekService.findAll();
		
		Boolean nasla = false;
		String odgovor = "";
		for (Lek l : lekovi) {
			if (l.getIme().equals(lek.getIme()) || l.getSifra().equals(lek.getSifra())) {
				nasla = true;
				break;
			}
		}
		
		if (!nasla) {
			Lek l = lekService.save(lek);
			odgovor = "Novi lek je uspesno dodat.";
		} else {
			odgovor = "Lek mora da bude jedinstven";
		}
		
		
		return new ResponseEntity<String>(odgovor, HttpStatus.OK);
	}
}
