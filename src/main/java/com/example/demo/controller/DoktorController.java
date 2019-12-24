package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.DoktorDTO;
import com.example.demo.dto.KorisnikDTO;
import com.example.demo.dto.PregledDTO;
import com.example.demo.dto.ReceptDTO;
import com.example.demo.model.Dijagnoza;
import com.example.demo.model.Doktor;
import com.example.demo.model.Klinika;
import com.example.demo.model.Korisnik;
import com.example.demo.model.Pacijent;
import com.example.demo.model.Pregled;
import com.example.demo.model.Recept;
import com.example.demo.model.Sala;
import com.example.demo.model.StatusPregleda;
import com.example.demo.model.StatusRecepta;
import com.example.demo.model.UlogaKorisnika;
import com.example.demo.service.DijagnozaService;
import com.example.demo.service.DoktorService;
import com.example.demo.service.EmailService;
import com.example.demo.service.KlinikaService;
import com.example.demo.service.KorisnikService;
import com.example.demo.service.PacijentService;
import com.example.demo.service.PregledService;
import com.example.demo.service.ReceptService;
import com.example.demo.service.SalaService;

import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;


@RestController
@RequestMapping(value = "doktor")
public class DoktorController {

	@Autowired
	private DoktorService doktorService;
	
	@Autowired
	private KorisnikService korisnikService;
	
	@Autowired
	private SalaService salaService;
	
	@Autowired
	private PregledService pregledService;
	
	@Autowired
	private PacijentService pacijentService;
	
	@Autowired
	private ReceptService receptService;
	
	@Autowired
	private DijagnozaService dijagnozaService;
	
	@Autowired
	private KlinikaService klinikaService;
	
	@Autowired
	private EmailService emailService;
	
	@RequestMapping(value = "/svi_pacijenti", method = RequestMethod.GET)
	public ResponseEntity<List<Korisnik>> sviPacijenti() {
		List<Korisnik> pacijenti = korisnikService.findByUloga(UlogaKorisnika.PACIJENT);
		return new ResponseEntity<List<Korisnik>>(pacijenti, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/posalji_pregled/{text}", method = RequestMethod.POST)
	public ResponseEntity<PregledDTO> pregled(@PathVariable("text") String text, @RequestBody PregledDTO pregledDTO) {
		String[] splitter = text.split("~");
		Long identifikacija = Long.parseLong(splitter[0]);
		Pregled pregled = new Pregled();
		Doktor doktor = doktorService.findOne((long) 1);
		Sala sala = salaService.findOne((long) 1);
		Pacijent pacijent = pacijentService.findOne(identifikacija);
		pregled.setNaziv(pregledDTO.getNaziv());
		pregled.setAnamneza(pregledDTO.getAnamneza());
		pregled.setTipPregleda(pregledDTO.getTipPregleda());
		pregled.setCena(pregledDTO.getCena());
		pregled.setDatumIVremePregleda(pregledDTO.getDatumIVremePregleda());
		pregled.setDoktor(doktor);
		pregled.setStatus(StatusPregleda.ZAVRSEN);
		pregled.setPacijent(pacijent);
		pregled.setSala(sala);
		
		for (int i = 1; i < splitter.length; i++) {
			Dijagnoza dijagnoza = dijagnozaService.findOne(Long.parseLong(splitter[i]));
			pregled.getDijagnoze().add(dijagnoza);
		}
		
		Pregled p = pregledService.save(pregled);
		return new ResponseEntity<PregledDTO>(new PregledDTO(p), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/posalji_recept/{text}", method = RequestMethod.POST)
	public ResponseEntity<String> recept(@PathVariable("text") String text, @RequestBody ReceptDTO receptDTO) {
		String[] splitter = text.split("~");
		Long identifikacija = Long.parseLong(splitter[0]);
		String[] splitter1 = splitter[1].split(" ");
		Recept recept = new Recept();
		recept.setNaziv(receptDTO.getNaziv());
		recept.setOpis(receptDTO.getOpis());
		recept.setLek(splitter1[1]);
		recept.setSifraLek(splitter1[0].substring(1, splitter1[0].length()-1));
		recept.setDatumIspisa(new Date());
		recept.setStatus(StatusRecepta.NEOVEREN);
		recept.setPacijent(pacijentService.findOne(identifikacija));
		Recept r = receptService.save(recept);
		return new ResponseEntity<String>("", HttpStatus.OK);
	}
	

	@RequestMapping(value = "/pacijent/{id}", method=RequestMethod.GET)
	public Pacijent getPatient(@PathVariable Long id) {
		Pacijent pacijent = pacijentService.findByIdKorisnik(id);
		return pacijent;
	}
	
	@RequestMapping(value = "/sviDoktori/{val}", method = RequestMethod.GET)
	public ResponseEntity<List<KorisnikDTO>> sviDoktori(@PathVariable String val){
		List<KorisnikDTO> doktori = new ArrayList<KorisnikDTO>();
		Klinika k = klinikaService.findByName(val);
		List<Doktor> d = doktorService.findAllByKlinika(k);
	
		for(Doktor doc : d) {
			DoktorDTO temp = new DoktorDTO(doc);
			Korisnik oTemp = korisnikService.findOne(temp.getId());
			KorisnikDTO kDto =  new KorisnikDTO(oTemp);
			doktori.add(kDto);
			
		}
		return new ResponseEntity<List<KorisnikDTO>>(doktori, HttpStatus.OK);

	}
	
	@RequestMapping(value = "/pregled/{pName}/{dID}", method = RequestMethod.POST)
	public ResponseEntity<String> dodavanjePregleda(HttpEntity<String> json, @PathVariable String pName, @PathVariable Long dID) throws ParseException, MailException, InterruptedException{
		String jString = json.getBody();
		Exception te = new Exception("Pogresan format datuma ili vremena");
		JSONParser parser = new JSONParser();
		JSONObject jObj = (JSONObject)parser.parse(jString);
		String datum = (String)jObj.get("examDate");
		String vreme = (String)jObj.get("examTime");
		
		String[] parts = pName.split("_");
		String name = parts[0];
		String surname = parts[1];
		
		Korisnik pacijent = null;
		List<Korisnik> sviKorisnici = korisnikService.findAll();
		
		for(Korisnik k : sviKorisnici) {
			if(k.getIme().toLowerCase().equals(name.toLowerCase()) && k.getPrezime().toLowerCase().equals(surname.toLowerCase())){
				pacijent = k;
				break;
			}
					
		}
		
		Korisnik admin = korisnikService.findOne(dID);
		Korisnik doktor = korisnikService.findOne(7L);
		try{emailService.sendNotificationExam(admin, doktor, pacijent, datum, vreme);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>("Uspesno rezervisan pregled", HttpStatus.OK);
	}	
	
	@RequestMapping(value = "/operacija/{pName}/{dID}", method = RequestMethod.POST)
	public ResponseEntity<String> dodavanjeOperacije(HttpEntity<String> json, @PathVariable String pName, @PathVariable Long dID) throws ParseException, MailException, InterruptedException{
		System.out.println(json);
		String jString = json.getBody();
		Exception te = new Exception("Pogresan format datuma ili vremena");
		JSONParser parser = new JSONParser();
		JSONObject jObj = (JSONObject)parser.parse(jString);
		String datum = (String)jObj.get("opDate");
		String vreme = (String)jObj.get("opTime");
		
		String[] parts = pName.split("_");
		String name = parts[0];
		String surname = parts[1];
		
		Korisnik pacijent = null;
		List<Korisnik> sviKorisnici = korisnikService.findAll();
		
		for(Korisnik k : sviKorisnici) {
			if(k.getIme().toLowerCase().equals(name.toLowerCase()) && k.getPrezime().toLowerCase().equals(surname.toLowerCase())){
				pacijent = k;
				break;
			}
					
		}
		
		Korisnik admin = korisnikService.findOne(dID);
		Korisnik doktor = korisnikService.findOne(7L);
		try{emailService.sendNotificationRoom(admin, doktor, pacijent, datum, vreme);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>("Uspesno rezervisana sala", HttpStatus.OK);
	}	
}
