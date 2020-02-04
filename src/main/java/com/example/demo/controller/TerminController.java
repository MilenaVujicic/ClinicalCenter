package com.example.demo.controller;



import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.PregledDTO;
import com.example.demo.model.AdministratorKlinike;
import com.example.demo.model.Doktor;
import com.example.demo.model.Klinika;
import com.example.demo.model.Korisnik;
import com.example.demo.model.Pregled;
import com.example.demo.model.Sala;
import com.example.demo.model.Termin;
import com.example.demo.service.AdministratorKlinikeService;
import com.example.demo.service.DoktorService;
import com.example.demo.service.KlinikaService;
import com.example.demo.service.KorisnikService;
import com.example.demo.service.SalaService;
import com.example.demo.service.TerminService;

import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;

@RestController
@RequestMapping(value = "termin")
public class TerminController {
	
	@Autowired
	TerminService terminService;	
	
	@Autowired
	SalaService salaService;
	
	@Autowired
	KlinikaService klinikaService;
	
	@Autowired
	DoktorService doktorService;
	
	@Autowired
	KorisnikService korisnikService;
	
	@Autowired
	AdministratorKlinikeService administratorService;

	@RequestMapping(value = "/noviTermin", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public void dodavanjeTermina(HttpEntity<String> json) throws ParseException {
		String jString = json.getBody();
		Exception te = new Exception("Pogresan format datuma ili vremena");
		
		JSONParser parser = new JSONParser();
		JSONObject jObj = (JSONObject)parser.parse(jString);
		String klinika = (String) jObj.get("klinika");
		String sala = (String) jObj.get("sala");
		String datum = (String) jObj.get("datum");
		String vreme = (String) jObj.get("vreme");
		String tip = (String) jObj.get("tip");
		String trajanje = (String) jObj.get("trajanje");
		String cena = (String) jObj.get("cena");
		String lekar = (String) jObj.get("lekar");
		
		Klinika klinikaFound = klinikaService.findByName(klinika);
		Long id = klinikaFound.getId();
		List<Sala> sveSale = salaService.findAll();
		List<Sala> saleKlinike = new ArrayList<>();
		Sala nadjenaSala = null;
		for(Sala s: sveSale) {
			if(s.getKlinika().getId().equals(id)) {
				saleKlinike.add(s);
			}
		}
		
		for(Sala s: saleKlinike) {
			if(s.getIme().equals(sala)) {
				nadjenaSala = s;
			}
		}
		
		String[] dateParts = null;
		String[] timeParts = null;
		String dayS = "";
		String monthS = "";
		String yearS = "";
		String hourS = "";
		String minuteS = "";
		
		try {
			
			dateParts = datum.split("/");
			timeParts = vreme.split(":");
			
			dayS = dateParts[0];
			monthS = dateParts[1];
			yearS = dateParts[2];
			
			hourS = timeParts[0];
			minuteS = timeParts[1];
		
			System.out.println(dayS + " " + monthS + " " + yearS);
			
			if(dateParts.length != 3) {
				throw te;
			}
			if(timeParts.length != 2) {
				throw te;
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		Integer dayI = Integer.parseInt(dayS);
		int day = (int)dayI;
		
		Integer monthI = Integer.parseInt(monthS);
		int month = (int)monthI;
		
		Integer yearI = Integer.parseInt(yearS);
		int year = (int)yearI;
		
		Integer hourI = Integer.parseInt(hourS);
		int hour = (int)hourI;
		
		Integer minuteI = Integer.parseInt(minuteS);
		int minute = (int)minuteI;
		
		Calendar c = Calendar.getInstance();
		c.set(year, month, day, hour, minute);

		Long salaId = null;
		for(Sala s: saleKlinike) {
			if(s.getIme().equals(sala)) {
				salaId = s.getId();
			}
		}
		
		String[] lekarParts = lekar.split(" ");
		String ime = lekarParts[0];
		String prezime = lekarParts[1];
		List<Korisnik> korisniciPrezime = korisnikService.findByPrezime(prezime);
		Korisnik kDoktor = null;
		Doktor d = null;
		for(Korisnik k : korisniciPrezime) {
			if(k.getIme().equals(ime)) {
				kDoktor = k;
				break;
			}
		}
			
		d = doktorService.findOne(kDoktor.getId());
		Double dTrajanje = new Double(trajanje);
		double trajanjeD = (double) dTrajanje;
		
		Double dCena = new Double(cena);
		double cenaD = (double) dCena;
		Termin t = new Termin(c, true, tip, trajanjeD, cenaD, nadjenaSala, d);
		terminService.save(t);
		
	}
	
	
}
