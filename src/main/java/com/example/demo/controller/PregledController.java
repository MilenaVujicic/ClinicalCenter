package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.PregledDTO;
import com.example.demo.model.AdministratorKlinike;
import com.example.demo.model.Doktor;
import com.example.demo.model.Klinika;
import com.example.demo.model.Korisnik;
import com.example.demo.model.LogedUser;
import com.example.demo.model.Pregled;
import com.example.demo.model.Sala;
import com.example.demo.model.StatusPregleda;
import com.example.demo.model.Termin;
import com.example.demo.model.TipPregleda;
import com.example.demo.model.UlogaKorisnika;
import com.example.demo.service.AdministratorKlinikeService;
import com.example.demo.service.DoktorService;
import com.example.demo.service.EmailService;
import com.example.demo.service.KlinikaService;
import com.example.demo.service.KorisnikService;
import com.example.demo.service.PregledService;
import com.example.demo.service.SalaService;
import com.example.demo.service.TerminService;
import com.example.demo.service.TipPregledaService;

import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;


@RestController
@RequestMapping(value = "pregled")
public class PregledController {

	@Autowired
	private PregledService pregledService;
	
	@Autowired
	private TerminService terminService;
	
	@Autowired
	private SalaService salaService;
	
	@Autowired
	private DoktorService doktorService;
	
	@Autowired
	private KorisnikService korisnikService;
	
	@Autowired
	private KlinikaService klinikaService;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private AdministratorKlinikeService administratorService;

	@Autowired
	private TipPregledaService tipPregledaService;

	
	@RequestMapping(value = "/sviPregledi/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<Pregled>> sviPregledi(@PathVariable("id") Long identifikacija) {
		List<Pregled> sviPregledi = pregledService.findAll();
		List<Pregled> pregledi = new ArrayList<Pregled>();
		for (Pregled pregled : sviPregledi) {
			if (pregled.getPacijent().getId().equals(identifikacija) && pregled.getStatus().equals(StatusPregleda.ZAVRSEN)) {
				pregledi.add(pregled);
			}
		}
		System.out.println("#############" + pregledi.size());
		return new ResponseEntity<List<Pregled>>(pregledi, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/izmeni", method = RequestMethod.PUT)
	public ResponseEntity<PregledDTO> izmeni(@RequestBody PregledDTO pregledDTO) {
		if(!LogedUser.getInstance().getUserRole().equals(UlogaKorisnika.LEKAR)) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Pregled pregled = pregledService.findOne(pregledDTO.getId());
		if (!pregled.getDoktor().getIdKorisnik().equals(LogedUser.getInstance().getUserId())) {
			return new ResponseEntity<PregledDTO>(HttpStatus.BAD_REQUEST);
		}
		pregled.setNaziv(pregledDTO.getNaziv());
		pregled.setAnamneza(pregledDTO.getAnamneza());
		Pregled p = pregledService.save(pregled);
		return new ResponseEntity<PregledDTO>(new PregledDTO(p), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/obrisi/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> obrisi(@PathVariable("id") Long identifikacija) {
		if(!LogedUser.getInstance().getUserRole().equals(UlogaKorisnika.LEKAR)) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Pregled pregled = pregledService.findOne(identifikacija);
		if (!pregled.getDoktor().getIdKorisnik().equals(LogedUser.getInstance().getUserId())) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		//pregledService.delete(pregled);
		pregled.setStatus(StatusPregleda.OBRISAN);
		Pregled p = pregledService.save(pregled);
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	

	@RequestMapping(value = "/rezervisi/{sala}/{pregled}/{doktori}", method = RequestMethod.GET)
	public ResponseEntity<String> rezervisiApt(@PathVariable("sala") Long sala_id, @PathVariable("pregled") Long pregled_id, @PathVariable("doktori") String text) {
		System.out.println("#######################");
		System.out.println("sala" + sala_id + "pregled" + pregled_id + "doktori" + text);
		Sala sala = salaService.findOne(sala_id);
		Pregled pregled = pregledService.findOne(pregled_id);
		pregled.setStatus(StatusPregleda.ZAKAZAN);
		pregled.setSala(sala);
		String[] splitter = text.split("~");
		List<Termin> termini = terminService.findAll();
		Korisnik pacijent = korisnikService.findOne(pregled.getPacijent().getIdKorisnik());
		Klinika klinika = klinikaService.findOne(pregled.getSala().getKlinika().getId());
		
		for (Termin termin : termini) {
			if (termin.getSala().getId().equals(sala.getId()) && termin.getDatum().equals(pregled.getDatumIVremePregleda())) {
				termin.setSlobodan(false);
				terminService.save(termin);
			}
		}
		
		for (String s : splitter) {
			if (!s.equals("")) {
				Doktor doktor = doktorService.findByIdKorisnik(Long.parseLong(s));
				System.out.println("###pre" + doktor.getOperacije().size());
				doktor.getPregledi().add(pregled);
				pregled.setDoktor(doktor);
				Korisnik dok = korisnikService.findOne(doktor.getIdKorisnik());
				emailService.sendSuccessfulReservationAptDoctor(pacijent, pregled, dok, klinika);
				doktorService.save(doktor);
			}
		}
		
		pregledService.save(pregled);
		emailService.sendSuccessfulReservationAptPatient(pacijent, pregled, klinika);
		return new ResponseEntity<String>("Uspesno rezervisana sala", HttpStatus.OK);
	}
	

	@RequestMapping(value = "/preuzmi/{id}", method = RequestMethod.GET)
	public ResponseEntity<Pregled> preuzmi(@PathVariable("id") Long identifikacija) {
		Pregled pregled = pregledService.findOne(identifikacija);
		return new ResponseEntity<Pregled>(pregled, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/cenePregleda/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<PregledDTO>> cenePregleda(@PathVariable("id") Long id){
		Optional<AdministratorKlinike> oak = administratorService.findByIdKorisnik(id);
		AdministratorKlinike ak = oak.get();
		
		Optional<Klinika> ok = klinikaService.findById(ak.getKlinika().getId());
		Klinika k = ok.get();
		
		Set<Doktor> doktori = k.getDoktori();
		List<Pregled> pregledi = new ArrayList<Pregled>();
		for(Doktor d : doktori) {
			for(Pregled p : d.getPregledi()) {
				pregledi.add(p);
			}
		}
		
		List<PregledDTO> retVal = new ArrayList<PregledDTO>();
		for(Pregled p : pregledi) {
			retVal.add(new PregledDTO(p));
		}
		
		return new ResponseEntity<List<PregledDTO>>(retVal, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/zahtevi", method=RequestMethod.GET) 
	public ResponseEntity<List<Pregled>> zahtevi() {
		List<Pregled> pregledi = pregledService.findByStatus(StatusPregleda.NERASPOREDJEN);
		return new ResponseEntity<List<Pregled>>(pregledi, HttpStatus.OK);
	}
	

	@RequestMapping(value = "/pregledi_klinika/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<Integer>> preglediKlinika(@PathVariable("id") Long id){
		List<Integer> retVal = new ArrayList<Integer>();
		
		Optional<AdministratorKlinike> oak = administratorService.findByIdKorisnik(id);
		AdministratorKlinike ak = oak.get();
		
		Optional<Klinika> ok = klinikaService.findById(ak.getKlinika().getId());
		Klinika k = ok.get();
		
		List<Doktor> doktori = doktorService.findAllByKlinika(k);
		int day = 0;
		int week = 0;
		int month = 0;
		Calendar todayBegin = Calendar.getInstance();
		Calendar todayEnd = Calendar.getInstance();
		Calendar weekBegin = Calendar.getInstance();
		Calendar weekEnd = Calendar.getInstance();
		Calendar monthBegin = Calendar.getInstance();
		Calendar monthEnd = Calendar.getInstance();
		
		todayBegin.set(Calendar.HOUR_OF_DAY,0);
		todayBegin.clear(Calendar.MINUTE);
		todayBegin.clear(Calendar.SECOND);
		todayBegin.clear(Calendar.MILLISECOND);
		
		
		todayEnd.set(Calendar.HOUR_OF_DAY,0);
		todayEnd.clear(Calendar.MINUTE);
		todayEnd.clear(Calendar.SECOND);
		todayEnd.clear(Calendar.MILLISECOND);
		
		
		weekBegin.set(Calendar.HOUR_OF_DAY,0);
		weekBegin.clear(Calendar.MINUTE);
		weekBegin.clear(Calendar.SECOND);
		weekBegin.clear(Calendar.MILLISECOND);
		
		weekEnd.set(Calendar.HOUR_OF_DAY,0);
		weekEnd.clear(Calendar.MINUTE);
		weekEnd.clear(Calendar.SECOND);
		weekEnd.clear(Calendar.MILLISECOND);
		
		monthBegin.set(Calendar.HOUR_OF_DAY,0);
		monthBegin.clear(Calendar.MINUTE);
		monthBegin.clear(Calendar.SECOND);
		monthBegin.clear(Calendar.MILLISECOND);
		
		monthEnd.set(Calendar.HOUR_OF_DAY,0);
		monthEnd.clear(Calendar.MINUTE);
		monthEnd.clear(Calendar.SECOND);
		monthEnd.clear(Calendar.MILLISECOND);
		
		
		weekBegin.set(Calendar.DAY_OF_WEEK, weekBegin.getFirstDayOfWeek());
		weekEnd.set(Calendar.DAY_OF_WEEK, weekBegin.getFirstDayOfWeek());
		weekEnd.add(Calendar.WEEK_OF_YEAR, 1);
		
		monthBegin.set(Calendar.DAY_OF_MONTH, 1);
		monthEnd.set(Calendar.DAY_OF_MONTH, monthEnd.getActualMaximum(Calendar.DAY_OF_MONTH));
		todayEnd.add(Calendar.DATE, 1);
		System.out.println("TODAY: " + todayBegin.get(Calendar.DATE) + " " + todayBegin.get(Calendar.MONTH));
		System.out.println("WEEKB: " + weekBegin.get(Calendar.DATE) + " " + weekBegin.get(Calendar.MONTH));
		System.out.println("WEEKE: " + weekEnd.get(Calendar.DATE) + " " + weekEnd.get(Calendar.MONTH));
		System.out.println("MONTHB: " + monthBegin.get(Calendar.DATE) + " " + monthBegin.get(Calendar.MONTH));
		System.out.println("MONTHE: " + monthEnd.get(Calendar.DATE) + " " + monthEnd.get(Calendar.MONTH));
		for(Doktor d : doktori) {
			for(Pregled p : d.getPregledi()) {
				if(p.getStatus() == StatusPregleda.ZAVRSEN) {
					Calendar pDate = p.getDatumIVremePregleda();
					if(pDate.getTimeInMillis() > todayBegin.getTimeInMillis() && pDate.getTimeInMillis() < todayEnd.getTimeInMillis()) {
						day++;
					}
					
					if(pDate.getTimeInMillis() > weekBegin.getTimeInMillis() && pDate.getTimeInMillis() < weekEnd.getTimeInMillis()) {
						week++;
					}
					
					if(pDate.getTimeInMillis() > monthBegin.getTimeInMillis() && pDate.getTimeInMillis() < monthEnd.getTimeInMillis()) {
						month++;
					}
					
				}
			}
		}
		Integer iDay = new Integer(day);
		Integer iWeek = new Integer(week);
		Integer iMonth = new Integer(month);
		System.out.println("#####" + day +" " + week + " " + month);
		retVal.add(iDay);
		retVal.add(iWeek);
		retVal.add(iMonth);
		return new ResponseEntity<List<Integer>>(retVal, HttpStatus.OK);
}

	@RequestMapping(value = "/svi_tipovi", method=RequestMethod.GET)
	public ResponseEntity<List<TipPregleda>> sviTipoviPregleda() {
		System.out.println("#################### svi tipovi pregleda");
		List<TipPregleda> sviTipovi = tipPregledaService.findAll();
		System.out.println("#################### svi tipovi pregleda ####" + sviTipovi.size());
		return new ResponseEntity<List<TipPregleda>>(sviTipovi, HttpStatus.OK);

	}

	@RequestMapping(value = "/novi_pregled_admin/{id}", method = RequestMethod.POST)
	public ResponseEntity<String> noviPregledAdmin(@PathVariable("id") Long id, HttpEntity<String> json) throws ParseException{
		String jString = json.getBody();
		JSONParser parser = new JSONParser();
		JSONObject jObj = (JSONObject)parser.parse(jString);
		String salaIdStr = (String) jObj.get("salaId");
		String tipIdStr = (String) jObj.get("tipId");
		String doktorIdStr = (String) jObj.get("doktorId");
		String datumStr = (String) jObj.get("datum");
		String vremeStr = (String) jObj.get("vreme");
		
		Long salaId = Long.parseLong(salaIdStr);
		Long tipId = Long.parseLong(tipIdStr);
		Long doktorId = Long.parseLong(doktorIdStr);
		
		String[] datumParts = datumStr.split("-");
		int year = Integer.parseInt(datumParts[0]);
		int month = Integer.parseInt(datumParts[1]);
		int date = Integer.parseInt(datumParts[2]);
		String[] vremeParts = vremeStr.split(":");
		int hour = Integer.parseInt(vremeParts[0]);
		int minute = Integer.parseInt(vremeParts[1]);
		
		Optional<TipPregleda> otp = tipPregledaService.findById(tipId);
		TipPregleda tp = otp.get();
		
		Optional<Sala> os = salaService.findById(salaId);
		Sala s = os.get();
		
		Doktor d = doktorService.findByIdKorisnik(id);

		
		Calendar cVreme = Calendar.getInstance();
		cVreme.set(year, month, date, hour, minute);
		
		Pregled p = new Pregled();
		p.setAnamneza("");
		p.setDatumIVremePregleda(cVreme);
		p.setDoktor(d);
		p.setNaziv(tp.getNaziv());
		p.setPacijent(null);
		p.setSala(s);
		p.setStatus(StatusPregleda.NERASPOREDJEN);
		p.setTipPregleda(tp);
		pregledService.save(p);
		return new ResponseEntity<String>("Dodat pregled", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/novi_tip_pregleda", method = RequestMethod.POST)
	public ResponseEntity<String> noviTipPregleda(HttpEntity<String> json) throws ParseException{
		String jString = json.getBody();
		JSONParser parser = new JSONParser();
		JSONObject jObj = (JSONObject)parser.parse(jString);
		String name = (String) jObj.get("name");
		String price = (String) jObj.get("price");
		
		double priceD = Double.parseDouble(price);
		
		TipPregleda t = new TipPregleda();
		t.setCena(priceD);
		t.setNaziv(name);
		t.setZauzet(false);
		
		tipPregledaService.save(t);
		return new ResponseEntity<String>("Dodat pregled", HttpStatus.OK);
	}
}
