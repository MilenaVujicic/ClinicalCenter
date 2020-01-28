package com.example.demo.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.KorisnikDTO;
import com.example.demo.model.Korisnik;
import com.example.demo.model.Odsustvo;
import com.example.demo.model.Pacijent;
import com.example.demo.model.Recept;
import com.example.demo.model.StatusRecepta;
import com.example.demo.model.UlogaKorisnika;
import com.example.demo.model.VrstaOdsustva;
import com.example.demo.service.KorisnikService;
import com.example.demo.service.OdsustvoService;
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
	
	@Autowired
	OdsustvoService odsustvoService;
	
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
		System.out.println("########################");
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
	
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/odmor/{text}", method = RequestMethod.GET)
	public ResponseEntity<String> odmor(@PathVariable("text") String text) {
		String[] splitter = text.split("~");
		String type = splitter[0];
		String from = splitter[1];
		String to = splitter[2];
		Odsustvo odsustvo = new Odsustvo();
		if (type.equals("Vacation")) {
			odsustvo.setVrstaOdsustva(VrstaOdsustva.ODMOR);
		}
		else {
			odsustvo.setVrstaOdsustva(VrstaOdsustva.BOLOVANJE);
		}
		DateFormat format = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);
		
		//Date today = new Date();
		Calendar today = Calendar.getInstance();
		//Date date1 = new Date(today.getTime() + (1000 * 60 * 60 * 24));
		Calendar date1 = today;
		date1.add(Calendar.DATE, 1);
		try {
			Date temp = format.parse(from);
			date1.set(temp.getYear(),temp.getMonth() ,temp.getDate());
			odsustvo.setPocetakOdsustva(date1);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
		
		Calendar date2 = Calendar.getInstance();
		try {
			Date temp = format.parse(to);
			date2.set(temp.getYear(), temp.getMonth(), temp.getDate());
	
			odsustvo.setZavrsetakOdsustva(date2);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
		
		if (date1.compareTo(date2) > 0) {
			return new ResponseEntity<String>("Datum greska", HttpStatus.BAD_REQUEST);
		}
		
		Korisnik korisnik = korisnikService.findOne((long) 9);
		odsustvo.setKorisnik(korisnik);
		odsustvo.setOdobren(false);
		Odsustvo od = odsustvoService.save(odsustvo);
		
		return new ResponseEntity<String>("Zahtev je poslat", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/kalendar", method = RequestMethod.GET)
	public ResponseEntity<List<Odsustvo>> kalendar() {
		Korisnik korisnik = korisnikService.findOne((long) 9);
		List<Odsustvo> odsustva = new ArrayList<Odsustvo>();
		for (Odsustvo o : korisnik.getOdsustva()) {
			odsustva.add(o);
		}
		return new ResponseEntity<List<Odsustvo>>(odsustva, HttpStatus.OK);
	}
}
