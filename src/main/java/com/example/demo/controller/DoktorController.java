package com.example.demo.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

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
import com.example.demo.model.AdministratorKlinike;
import com.example.demo.model.Dijagnoza;
import com.example.demo.model.Doktor;
import com.example.demo.model.Klinika;
import com.example.demo.model.Korisnik;
import com.example.demo.model.LogedUser;
import com.example.demo.model.Odsustvo;
import com.example.demo.model.Operacija;
import com.example.demo.model.Pacijent;
import com.example.demo.model.Pregled;
import com.example.demo.model.Recept;
import com.example.demo.model.Sala;
import com.example.demo.model.StatusOperacije;
import com.example.demo.model.StatusPregleda;
import com.example.demo.model.StatusRecepta;
import com.example.demo.model.TipPregleda;
import com.example.demo.model.UlogaKorisnika;
import com.example.demo.model.VrstaOdsustva;
import com.example.demo.model.Zahtev;
import com.example.demo.service.AdministratorKlinikeService;
import com.example.demo.service.DijagnozaService;
import com.example.demo.service.DoktorService;
import com.example.demo.service.EmailService;
import com.example.demo.service.KlinikaService;
import com.example.demo.service.KorisnikService;
import com.example.demo.service.OdsustvoService;
import com.example.demo.service.OperacijaService;
import com.example.demo.service.PacijentService;
import com.example.demo.service.PregledService;
import com.example.demo.service.ReceptService;
import com.example.demo.service.SalaService;
import com.example.demo.service.TipPregledaService;
import com.example.demo.service.ZahtevService;

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
	
	@Autowired
	private ZahtevService zahtevService;
	
	@Autowired
	private AdministratorKlinikeService administratorService;
	
	@Autowired
	private OperacijaService operacijaService;
	
	@Autowired
	OdsustvoService odsustvoService;
	
	@Autowired
	private TipPregledaService tipPregledaService;

	@RequestMapping(value = "/svi_pacijenti", method = RequestMethod.GET)
	public ResponseEntity<List<Korisnik>> sviPacijenti() {
		List<Korisnik> pacijenti = korisnikService.findByUloga(UlogaKorisnika.PACIJENT);
		List<Korisnik> sviAktivni = new ArrayList<Korisnik>();
		for(Korisnik k : pacijenti) {
			if(k.isAktiviran())
				sviAktivni.add(k);
		}
		return new ResponseEntity<List<Korisnik>>(sviAktivni, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/posalji_pregled/{text}", method = RequestMethod.POST)
	public ResponseEntity<PregledDTO> pregled(@PathVariable("text") String text, @RequestBody PregledDTO pregledDTO) {
		if(!LogedUser.getInstance().getUserRole().equals(UlogaKorisnika.LEKAR))
			return new ResponseEntity<PregledDTO>(HttpStatus.BAD_REQUEST);
		
		String[] splitter = text.split("~");
		Long identifikacija = Long.parseLong(splitter[0]);
		Pregled pregled = new Pregled();
		if (pregledDTO.getId() != 0) {
			pregled = pregledService.findOne(pregledDTO.getId());
		}
		
		TipPregleda tipPregleda = tipPregledaService.findByNaziv(splitter[1]);
		tipPregleda.setZauzet(true);
		
		
		Doktor doktor = doktorService.findByIdKorisnik(LogedUser.getInstance().getUserId());
		Sala sala = salaService.findOne((long) 1);
		Pacijent pacijent = pacijentService.findByIdKorisnik(identifikacija);
		pregled.setNaziv(pregledDTO.getNaziv());
		pregled.setAnamneza(pregledDTO.getAnamneza());
		pregled.setTipPregleda(tipPregleda);
		Calendar c = Calendar.getInstance();
		pregled.setDatumIVremePregleda(c);

		pregled.setDoktor(doktor);
		pregled.setStatus(StatusPregleda.ZAVRSEN);
		pregled.setPacijent(pacijent);
		pregled.setSala(sala);
		for (int i = 2; i < splitter.length; i++) {
			Dijagnoza dijagnoza = dijagnozaService.findOne(Long.parseLong(splitter[i]));
			pregled.getDijagnoze().add(dijagnoza);
		}
		
		Pregled p = pregledService.save(pregled);
		TipPregleda t = tipPregledaService.save(tipPregleda);
		return new ResponseEntity<PregledDTO>(new PregledDTO(p), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/posalji_recept/{text}", method = RequestMethod.POST)
	public ResponseEntity<String> recept(@PathVariable("text") String text, @RequestBody ReceptDTO receptDTO) {
		String[] splitter = text.split("~");
		if (splitter[1].equals("Open this select menu")) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
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
	
	@RequestMapping(value = "/pacijent_korisnik/{id}", method=RequestMethod.GET)
	public Korisnik getKorisnik(@PathVariable Long id) {
		Pacijent pacijent = pacijentService.findOne(id);
		Korisnik korisnik = korisnikService.findOne(pacijent.getIdKorisnik());
		return korisnik;
	}
	
	@RequestMapping(value = "/odsustva/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<Odsustvo>> odsustva(@PathVariable("id") Long identifikacija) {
		Korisnik korisnik = korisnikService.findOne(identifikacija);
		if (!korisnik.getUloga().equals(UlogaKorisnika.LEKAR)) {
			return new ResponseEntity<List<Odsustvo>>(HttpStatus.BAD_REQUEST);
		}
		List<Odsustvo> odsustva = new ArrayList<Odsustvo>();
		for(Odsustvo o : korisnik.getOdsustva()) {
			odsustva.add(o);
		}
		return new ResponseEntity<List<Odsustvo>>(odsustva, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/zakazani_pregledi/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<Pregled>> pregledi(@PathVariable("id") Long identifikacija) {
		Korisnik korisnik = korisnikService.findOne(identifikacija);
		if (!korisnik.getUloga().equals(UlogaKorisnika.LEKAR)) {
			return new ResponseEntity<List<Pregled>>(HttpStatus.BAD_REQUEST);
		}
		List<Pregled> pregledi = pregledService.findAll();
		List<Pregled> doktorevi_pregledi = new ArrayList<Pregled>();
		Doktor doktor = doktorService.findByIdKorisnik(identifikacija);
		for (Pregled p : pregledi) {
			if (p.getDoktor().getId().equals(doktor.getId())) {
				doktorevi_pregledi.add(p);
				System.out.println(p.getDatumIVremePregleda());
			}
		}
		
		return new ResponseEntity<List<Pregled>>(doktorevi_pregledi, HttpStatus.OK);
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
	
	@RequestMapping(value = "/pregled/{id}", method = RequestMethod.POST)
	public ResponseEntity<String> newExaminationId(HttpEntity<String> json, @PathVariable Long id) throws ParseException, MailException, InterruptedException{
		String jString = json.getBody();
		JSONParser parser = new JSONParser();
		JSONObject jObj = (JSONObject)parser.parse(jString);
		String datum = (String)jObj.get("examDate");
		String vreme = (String)jObj.get("examTime");
		String jmbg = (String)jObj.get("jmbg");
		
		Optional<Korisnik> oDoktor = korisnikService.findById(id);
		Korisnik kDoktor = oDoktor.get();
		Doktor doktor = doktorService.findByIdKorisnik(kDoktor.getId());
		
		Optional<Korisnik> oPacijent = korisnikService.findByJmbg(Long.parseLong(jmbg));
		Korisnik kPacijent = oPacijent.get();
		Pacijent pacijent = pacijentService.findByIdKorisnik(kPacijent.getId());
		
		Long idAdmin = null;
		
		for(AdministratorKlinike k : doktor.getKlinika().getAdministratoriKlinike()) {
			idAdmin = k.getId();
		}
		System.out.println(idAdmin);
		
		Optional<AdministratorKlinike> oAdmin = administratorService.findById(idAdmin);
		AdministratorKlinike admin = oAdmin.get();
		Optional<Korisnik> okAdmin = korisnikService.findById(admin.getIdKorisnik());
		Korisnik kAdmin = okAdmin.get();
		
		String[] timeParts = vreme.split(":");
		String hour = timeParts[0];
		String minute = timeParts[1];
		
		String[] dateParts = datum.split("-");
		String day = dateParts[2];
		String month = dateParts[1];
		String year = dateParts[0];
		
		System.out.println(datum + " " + day + " " + month + " " + year);
		
		Pregled p = new Pregled();
		Calendar c = Calendar.getInstance();
		c.set(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day), Integer.parseInt(hour), Integer.parseInt(minute));
		
		p.setDatumIVremePregleda(c);
		p.setStatus(StatusPregleda.NERASPOREDJEN);
		p.setPacijent(pacijent);
		p.setDoktor(doktor);
		p.setAnamneza("");
		p.setNaziv("");
		doktor.getPregledi().add(p);
		pacijent.getPregledi().add(p);

		pregledService.save(p);
		doktorService.save(doktor);
		pacijentService.save(pacijent);
		
		emailService.sendNotificationExam(kAdmin, kDoktor, kPacijent, datum, vreme);
		return new ResponseEntity<String>("The examination has been successfully added", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/operacija/{id}", method = RequestMethod.POST)
	public ResponseEntity<String> newSurgeryId(HttpEntity<String> json, @PathVariable("id") Long id) throws MailException, InterruptedException, ParseException{
		
		String jString = json.getBody();
		JSONParser parser = new JSONParser();
		JSONObject jObj = (JSONObject)parser.parse(jString);
		String datum = (String)jObj.get("opDate");
		String vreme = (String)jObj.get("opTime");
		String jmbg = (String)jObj.get("jmbg");
		
		Optional<Korisnik> oDoktor = korisnikService.findById(id);
		Korisnik kDoktor = oDoktor.get();
		Doktor doktor = doktorService.findByIdKorisnik(kDoktor.getId());
		
		Optional<Korisnik> oPacijent = korisnikService.findByJmbg(Long.parseLong(jmbg));
		Korisnik kPacijent = oPacijent.get();
		Pacijent pacijent = pacijentService.findByIdKorisnik(kPacijent.getId());
		
		Long idAdmin = null;
		
		for(AdministratorKlinike k : doktor.getKlinika().getAdministratoriKlinike()) {
			idAdmin = k.getId();
		}
		System.out.println(idAdmin);
		
		Optional<AdministratorKlinike> oAdmin = administratorService.findById(idAdmin);
		AdministratorKlinike admin = oAdmin.get();
		Optional<Korisnik> okAdmin = korisnikService.findById(admin.getIdKorisnik());
		Korisnik kAdmin = okAdmin.get();
		
		String[] timeParts = vreme.split(":");
		String hour = timeParts[0];
		String minute = timeParts[1];
		
		String[] dateParts = datum.split("-");
		String day = dateParts[2];
		String month = dateParts[1];
		String year = dateParts[0];
		Calendar c = Calendar.getInstance();
		c.set(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day), Integer.parseInt(hour), Integer.parseInt(minute));
		
		Operacija o = new Operacija();
		o.setDatumIVremeOperacije(c);
		o.getDoktori().add(doktor);
		o.setOpis("");
		o.setPacijent(pacijent);
		o.setSala(null);
		o.setStatus(StatusOperacije.NERASPOREDJEN);
		o.setTrajanje(0);
		
		operacijaService.save(o);
		doktorService.save(doktor);
		pacijentService.save(pacijent);
		
		emailService.sendNotificationRoom(kAdmin, kDoktor, kPacijent, datum, vreme);
		return new ResponseEntity<String>("The surgery has been successfully added", HttpStatus.OK);
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
		Zahtev z = new Zahtev(doktor.getId(), datum, vreme, null);
		List<AdministratorKlinike> allAdmins = administratorService.findAll();
		AdministratorKlinike a = allAdmins.get(0);
		z.setAdministrator_klinike(a);
		zahtevService.save(z);
		
		System.out.println();
		System.out.println(z.getDatum() + " " + z.getVreme());
		
		z.makeDate();
		
		/*try{emailService.sendNotificationExam(admin, doktor, pacijent, datum, vreme);
		}catch(Exception e) {
			e.printStackTrace();
		}*/
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
		
		
			
		/*try{emailService.sendNotificationRoom(admin, doktor, pacijent, datum, vreme);
		}catch(Exception e) {
			e.printStackTrace();
		}*/
		return new ResponseEntity<String>("Uspesno rezervisana sala", HttpStatus.OK);
	}	
	
	@RequestMapping(value="/svi_sa_klinike_2", method = RequestMethod.GET)
	public ResponseEntity<List<Korisnik>> svi_sa_klinike_2() {
		Klinika k = klinikaService.findOne((long) 2);
		List<Doktor> doktori = doktorService.findAllByKlinika(k);
		List<Korisnik> lekari = korisnikService.findByUloga(UlogaKorisnika.LEKAR);
		List<Korisnik> doktori_klinike = new ArrayList<Korisnik>();
		
		for (Korisnik korisnik : lekari) {
			for (Doktor doktor : doktori) {
				if (doktor.getIdKorisnik().equals(korisnik.getId())) {
					doktori_klinike.add(korisnik);
				}
			} 
		}
		
		
		return new ResponseEntity<List<Korisnik>>(doktori_klinike, HttpStatus.OK);
	}
	
	@RequestMapping(value="/svi_slobodni_sa_klinike_2/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<Korisnik>> svi_sa_klinike_slobodni_2(@PathVariable("id") Long identifikacija) {
		System.out.println("######################");
		Klinika k = klinikaService.findOne((long) 2);
		List<Doktor> doktori = doktorService.findAllByKlinika(k);
		List<Korisnik> lekari = korisnikService.findByUloga(UlogaKorisnika.LEKAR);
		List<Korisnik> doktori_klinike = new ArrayList<Korisnik>();
		List<Doktor> slobodni_doktori = new ArrayList<Doktor>();
		System.out.println("######################");
		Operacija operacija = operacijaService.findOne(identifikacija);
		System.out.println("#####operacija_vreme" + operacija.getDatumIVremeOperacije().getTimeInMillis());
		for (Doktor doktor : doktori) {
			Boolean nasla = false;
			for (Pregled pregled : doktor.getPregledi()) {
				if (pregled.getDatumIVremePregleda().getTimeInMillis() == operacija.getDatumIVremeOperacije().getTimeInMillis()) {
					nasla = true;
					continue;
				}
			}
			
			for (Operacija o : doktor.getOperacije()) {
				if (o.getDatumIVremeOperacije().getTimeInMillis() == operacija.getDatumIVremeOperacije().getTimeInMillis()) {
					nasla = true;
					continue;
				}
			}
			
			Korisnik kor = korisnikService.findOne(doktor.getIdKorisnik());
			
			for (Odsustvo od : kor.getOdsustva()) {
				/*if (od.getPocetakOdsustva().compareTo(operacija.getDatumIVremeOperacije().getTime()) < 0 && od.getZavrsetakOdsustva().compareTo(operacija.getDatumIVremeOperacije().getTime()) > 0){
					nasla = true;
					continue;
				}*/
				
				if(od.getPocetakOdsustva().compareTo(operacija.getDatumIVremeOperacije())<0 && od.getZavrsetakOdsustva().compareTo(operacija.getDatumIVremeOperacije())>0) {
					nasla = true;
					continue;
				}
			}
			
			if (!nasla)
				slobodni_doktori.add(doktor);
			
		}
		
		for (Korisnik korisnik : lekari) {
			for (Doktor doktor : slobodni_doktori) {
				if (doktor.getIdKorisnik().equals(korisnik.getId())) {
					doktori_klinike.add(korisnik);
				}
			} 
		}
		
		
		return new ResponseEntity<List<Korisnik>>(doktori_klinike, HttpStatus.OK);
	}


	@RequestMapping(value="/svi_sa_klinike", method = RequestMethod.GET)
	public ResponseEntity<List<Korisnik>> svi_sa_klinike() {
		Klinika k = klinikaService.findOne((long) 2);
		List<Doktor> doktori = doktorService.findAllByKlinika(k);
		List<Korisnik> lekari = korisnikService.findByUloga(UlogaKorisnika.LEKAR);
		List<Korisnik> doktori_klinike = new ArrayList<Korisnik>();
		
		for (Korisnik korisnik : lekari) {
			for (Doktor doktor : doktori) {
				if (doktor.getIdKorisnik().equals(korisnik.getId())) {
					doktori_klinike.add(korisnik);
				}
			} 
		}
		
		
		return new ResponseEntity<List<Korisnik>>(doktori_klinike, HttpStatus.OK);
	}
	

	@RequestMapping(value="/svi_sa_klinike/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<Korisnik>> svi_sa_klinike_id(@PathVariable("id") Long id) {
		/*Optional<Korisnik> oKorisnik = korisnikService.findById(id);
		Korisnik korisnik = oKorisnik.get();*/
		Optional<AdministratorKlinike> oak = administratorService.findByIdKorisnik(id);
		AdministratorKlinike ak = oak.get();
		
		Optional<Klinika> ok = klinikaService.findById(ak.getKlinika().getId());
		Klinika k = ok.get();
		List<Doktor> doktori = doktorService.findAllByKlinika(k);
		List<Korisnik> lekari = korisnikService.findByUloga(UlogaKorisnika.LEKAR);
		List<Korisnik> doktori_klinike = new ArrayList<Korisnik>();
		
		for (Korisnik korisnik : lekari) {
			for (Doktor doktor : doktori) {
				System.out.println(doktor.getSpecijalizacija());
				System.out.println(doktor.getKlinika().getId());
				System.out.println(doktor.getIdKorisnik());
				System.out.println(korisnik.getId());
				if (doktor.getIdKorisnik().equals(korisnik.getId())) {
					doktori_klinike.add(korisnik);
				}
			} 
		}
		
		
		return new ResponseEntity<List<Korisnik>>(doktori_klinike, HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/svi_slobodni_sa_klinike/{id}/{session}", method = RequestMethod.GET)
	public ResponseEntity<List<Korisnik>> svi_sa_klinike_slobodni(@PathVariable("id") Long identifikacija, @PathVariable("session") Long korisnik_id) {
		System.out.println("########");
		Korisnik kori = korisnikService.findOne(korisnik_id);
		if (!kori.getUloga().equals(UlogaKorisnika.ADMIN_KLINIKE)) {
			return new ResponseEntity<List<Korisnik>>(HttpStatus.BAD_REQUEST);
		}
		AdministratorKlinike admin = administratorService.findByIdKorisnik(korisnik_id.toString());
		Klinika k = klinikaService.findOne(admin.getKlinika().getId());
		List<Doktor> doktori = doktorService.findAllByKlinika(k);
		List<Korisnik> lekari = korisnikService.findByUloga(UlogaKorisnika.LEKAR);
		List<Korisnik> doktori_klinike = new ArrayList<Korisnik>();
		List<Doktor> slobodni_doktori = new ArrayList<Doktor>();
		
		Operacija operacija = operacijaService.findOne(identifikacija);
		System.out.println("#####operacija_vreme" + operacija.getDatumIVremeOperacije().getTimeInMillis());
		for (Doktor doktor : doktori) {
			Boolean nasla = false;
			for (Pregled pregled : doktor.getPregledi()) {
				if (pregled.getDatumIVremePregleda().getTimeInMillis() == operacija.getDatumIVremeOperacije().getTimeInMillis()) {
					nasla = true;
					continue;
				}
			}
			
			for (Operacija o : doktor.getOperacije()) {
				if (o.getDatumIVremeOperacije().getTimeInMillis() == operacija.getDatumIVremeOperacije().getTimeInMillis()) {
					nasla = true;
					continue;
				}
			}
			
			Korisnik kor = korisnikService.findOne(doktor.getIdKorisnik());
			
			for (Odsustvo od : kor.getOdsustva()) {
				if (od.getPocetakOdsustva().compareTo(operacija.getDatumIVremeOperacije()) < 0 && od.getZavrsetakOdsustva().compareTo(operacija.getDatumIVremeOperacije()) > 0){
					nasla = true;
					continue;
				}
			}
			
			if (!nasla)
				slobodni_doktori.add(doktor);
			
		}
		
		for (Korisnik korisnik : lekari) {
			for (Doktor doktor : slobodni_doktori) {
				if (doktor.getIdKorisnik().equals(korisnik.getId())) {
					doktori_klinike.add(korisnik);
				}
			} 
		}
		
		System.out.println("###################" + doktori_klinike.size());
		return new ResponseEntity<List<Korisnik>>(doktori_klinike, HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/svi_slobodni_sa_klinike_apt/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<Korisnik>> svi_sa_klinike_slobodni_apt(@PathVariable("id") Long identifikacija) {
		Korisnik kori = korisnikService.findOne(LogedUser.getInstance().getUserId());
		if (!kori.getUloga().equals(UlogaKorisnika.ADMIN_KLINIKE)) {
			return new ResponseEntity<List<Korisnik>>(HttpStatus.BAD_REQUEST);
		}
		AdministratorKlinike admin = administratorService.findByIdKorisnik(LogedUser.getInstance().getUserId().toString());
		Klinika k = klinikaService.findOne(admin.getKlinika().getId());
		List<Doktor> doktori = doktorService.findAllByKlinika(k);
		List<Korisnik> lekari = korisnikService.findByUloga(UlogaKorisnika.LEKAR);
		List<Korisnik> doktori_klinike = new ArrayList<Korisnik>();
		List<Doktor> slobodni_doktori = new ArrayList<Doktor>();
		
		Pregled pregled = pregledService.findOne(identifikacija);
		System.out.println("#####operacija_vreme" + pregled.getDatumIVremePregleda().getTimeInMillis());
		for (Doktor doktor : doktori) {
			Boolean nasla = false;
			for (Pregled p : doktor.getPregledi()) {
				if (pregled.getDatumIVremePregleda().getTimeInMillis() == p.getDatumIVremePregleda().getTimeInMillis()) {
					nasla = true;
					continue;
				}
			}
			
			for (Operacija o : doktor.getOperacije()) {
				if (o.getDatumIVremeOperacije().getTimeInMillis() == pregled.getDatumIVremePregleda().getTimeInMillis()) {
					nasla = true;
					continue;
				}
			}
			
			Korisnik kor = korisnikService.findOne(doktor.getIdKorisnik());
			
			for (Odsustvo od : kor.getOdsustva()) {
				if (od.getPocetakOdsustva().compareTo(pregled.getDatumIVremePregleda()) < 0 && od.getZavrsetakOdsustva().compareTo(pregled.getDatumIVremePregleda()) > 0){
					nasla = true;
					continue;
				}
			}
			
			if (!nasla)
				slobodni_doktori.add(doktor);
			
		}
		
		for (Korisnik korisnik : lekari) {
			for (Doktor doktor : slobodni_doktori) {
				if (doktor.getIdKorisnik().equals(korisnik.getId())) {
					doktori_klinike.add(korisnik);
					System.out.println("#####" + korisnik.getId() + "##" + korisnik.getIme() + korisnik.getPrezime());
				}
			} 
		}
		
		
		return new ResponseEntity<List<Korisnik>>(doktori_klinike, HttpStatus.OK);
	}


	@RequestMapping(value = "/doctor_data/{id}", method = RequestMethod.GET)
	public ResponseEntity<KorisnikDTO> podaciDoktora(@PathVariable("id") Long id){
		KorisnikDTO retVal = null;
		
		Korisnik k = korisnikService.findOne(id);
		
		retVal = new KorisnikDTO(k);
		
		return new ResponseEntity<KorisnikDTO>(retVal, HttpStatus.OK);
	}
	
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/odmor/{text}", method = RequestMethod.GET)
	public ResponseEntity<String> odmor(@PathVariable("text") String text) throws java.text.ParseException, ParseException {
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
		//Date date1 = new Date(today.getTime() + (1000 * 60 * 60 * 24));
		Calendar today = Calendar.getInstance();
		Calendar date1 = Calendar.getInstance();
		date1.add(Calendar.DATE, 1);
		Date temp1 = format.parse(from);
		date1.set(temp1.getYear(), temp1.getMonth(), temp1.getDate());
		odsustvo.setPocetakOdsustva(date1);
		
		//Date date2 = today;
		Calendar date2 = Calendar.getInstance();
		Date temp2 = format.parse(to);
		date2.set(temp2.getYear(), temp2.getMonth(), temp2.getDate());
		odsustvo.setZavrsetakOdsustva(date2);
		
		if (date1.compareTo(date2) > 0) {
			return new ResponseEntity<String>("Datum greska", HttpStatus.BAD_REQUEST);
		}
		
		Korisnik korisnik = korisnikService.findOne((long) 9);
		odsustvo.setKorisnik(korisnik);
		odsustvo.setOdobren(false);
		Odsustvo od = odsustvoService.save(odsustvo);
		
		return new ResponseEntity<String>("Zahtev je poslat", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/specijalizacija/{id}", method = RequestMethod.GET)
	public ResponseEntity<String> spacijalizacija_doktora(@PathVariable("id") Long identifikacija) {
		Doktor doktor = doktorService.findByIdKorisnik(identifikacija);
		String specijalizacija = doktor.getSpecijalizacija();
		return new ResponseEntity<String>(specijalizacija, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/zakazane_operacije/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<Operacija>> zakazane_operacije(@PathVariable("id") Long identifikacija) {
		Korisnik korisnik = korisnikService.findOne(identifikacija);
		if (!korisnik.getUloga().equals(UlogaKorisnika.LEKAR)) {
			return new ResponseEntity<List<Operacija>>(HttpStatus.BAD_REQUEST);
		}
		Doktor doktor = doktorService.findByIdKorisnik(identifikacija);
		List<Operacija> operacije = operacijaService.findAll();
		List<Operacija> operacije_doktora = new ArrayList<Operacija>();
		for (Operacija operacija : operacije) {
			for (Doktor d : operacija.getDoktori()) {
				if (d.getId().equals(doktor.getId()) && !operacija.getStatus().equals(StatusOperacije.NERASPOREDJEN)) {
					operacije_doktora.add(operacija);
				}
			}
		}
		
		return new ResponseEntity<List<Operacija>>(operacije_doktora, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/korisnik_doktor/{id}", method = RequestMethod.GET)
	public ResponseEntity<DoktorDTO> korisnikDoktor(@PathVariable("id") Long id){
		Doktor d = doktorService.findByIdKorisnik(id);
		DoktorDTO doktor = new DoktorDTO(d);
		return new ResponseEntity<DoktorDTO>(doktor, HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/obrisiLekara/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<DoktorDTO> obrisiLekara(@PathVariable("id") Long id){
		Doktor d = doktorService.findByIdKorisnik(id);
		DoktorDTO doktor = null;
		for(Pregled p : d.getPregledi()) {
			if(p.getStatus() == StatusPregleda.ZAKAZAN) {
				return new ResponseEntity<DoktorDTO>(doktor, HttpStatus.OK);
			}
		}
		
		doktor = new DoktorDTO(d);
		Optional<Klinika> ok = klinikaService.findById(d.getKlinika().getId());
		Klinika k = ok.get();
		if(k.getDoktori().contains(d)) {
			d.setKlinika(null);
			k.getDoktori().remove(d);
			System.out.println("OBRISAN DOKTOR");
		}
		klinikaService.save(k);
		doktorService.save(d);
		return new ResponseEntity<DoktorDTO>(doktor, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/novi_doktor/{id}", method = RequestMethod.POST)
	public ResponseEntity<String> noviDoktor(@PathVariable("id") Long id, HttpEntity<String> json) throws ParseException{
		String jString = json.getBody();
		JSONParser parser = new JSONParser();
		JSONObject jObj = (JSONObject)parser.parse(jString);
		String userId = (String)jObj.get("userId");
		String specialization = (String) jObj.get("spec");
		Optional<AdministratorKlinike> oak = administratorService.findByIdKorisnik(id);
		AdministratorKlinike ak = oak.get();
		Optional<Klinika> ok = klinikaService.findById(ak.getKlinika().getId());
		Klinika k = ok.get();
		
		Optional<Korisnik> okr = korisnikService.findById(Long.parseLong(userId));
		Korisnik kr = okr.get();
		
		Doktor d = new Doktor();
		d.setIdKorisnik(Long.parseLong(userId));
		d.setKlinika(k);
		d.setSpecijalizacija(specialization);
		//k.getDoktori().add(d);
		kr.setUloga(UlogaKorisnika.LEKAR);
		//klinikaService.save(k);
		doktorService.save(d);
		korisnikService.save(kr);
		return new ResponseEntity<String>("dodat doktor", HttpStatus.OK);
	}
}

