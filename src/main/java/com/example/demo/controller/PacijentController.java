package com.example.demo.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
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
import com.example.demo.dto.PacijentDTO;
import com.example.demo.dto.PregledDTO;
import com.example.demo.model.Doktor;
import com.example.demo.model.Klinika;
import com.example.demo.model.Korisnik;
import com.example.demo.model.Pacijent;
import com.example.demo.model.Pregled;
import com.example.demo.model.UlogaKorisnika;
import com.example.demo.service.DoktorService;
import com.example.demo.service.EmailService;
import com.example.demo.service.KlinikaService;
import com.example.demo.service.KorisnikService;
import com.example.demo.service.PacijentService;
import com.example.demo.service.PregledService;
import com.example.demo.service.ZahtevService;


@RestController
@RequestMapping(value = "pacijent")
public class PacijentController {
	
	@Autowired
	KlinikaService klinikaService;
	
	@Autowired
	PacijentService pacijentService;

	@Autowired
	DoktorService doktorService;
	
	@Autowired
	KorisnikService korisnikService;
	
	@Autowired
	PregledService pregledService;
	
	@Autowired
	EmailService emailService;
	
	@Autowired
	ZahtevService zahtevService;

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
				if(k.getIme().toLowerCase().contains(value) || k.getIme().toLowerCase().equals(value)) {
					if(k.getUloga() == UlogaKorisnika.PACIJENT) {
						retVal.add(new KorisnikDTO(k));
					}
					
				}
			}
		}else if(type.equals("Prezime")) {
			for(Korisnik k: korisnici) {
				if(k.getPrezime().toLowerCase().contains(value) || k.getPrezime().toLowerCase().equals(value)) {
					if(k.getUloga() == UlogaKorisnika.PACIJENT) {
						retVal.add(new KorisnikDTO(k));
					}
				}
			}
		}else if(type.equals("JedinstveniBroj")) {
			try {
				Long id = Long.parseLong(value);
				for(Korisnik k : korisnici) {
					if(k.getId() == id) {
						if(k.getUloga() == UlogaKorisnika.PACIJENT) {
							retVal.add(new KorisnikDTO(k));
						}
					}
				}
			}catch(Exception e) {
				e.printStackTrace();
				return new ResponseEntity<>(retVal, HttpStatus.BAD_REQUEST);
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
				System.out.println(k.getId() +" "+d.getIdKorisnik());
				if (k.getId() == d.getIdKorisnik()) {
					d.setId(k.getId());
					d.setIme(k.getIme());
					d.setPrezime(k.getPrezime());
				}
			}
		}
		for(DoktorDTO d : doktoriDTO) {
			System.out.println(d.getIme() + " " + d.getPrezime());
		}
		return new ResponseEntity<>(doktoriDTO, HttpStatus.OK);	
	}
	
	@RequestMapping(value = "/oceniKliniku", method = RequestMethod.PUT)
	public ResponseEntity<String> oceniKliniku(@RequestParam(value="id_klinike") int id,
											   @RequestParam(value="ocena") int ocena) {
		
		
		Long id_l = Integer.toUnsignedLong(id);
		Klinika k = klinikaService.findOne(id_l);
		double prethodnaProsecnaOcena = k.getProsecnaOcena();
		k.setBrojOcena(k.getBrojOcena()+1);
		k.setSumaOcena(k.getSumaOcena()+ocena);
		double prosecnaOcena =(double)k.getSumaOcena()/(double)k.getBrojOcena();
		System.out.println("prethodna prosecna ocena je: "+prethodnaProsecnaOcena+" a ocena je: "+prosecnaOcena);
		k.setProsecnaOcena(prosecnaOcena);
		
		k = klinikaService.save(k);
		return new ResponseEntity<String>("",HttpStatus.OK);
	}
	
	@RequestMapping(value = "/oceniDoktora", method = RequestMethod.PUT)
	public ResponseEntity<String> oceniDoktora(@RequestParam(value="id_pregleda") int id,
											   @RequestParam(value="ocena") int ocena) {
		
		
		Long id_l = Integer.toUnsignedLong(id);
		Pregled p = pregledService.findOne(id_l);
		Doktor d = p.getDoktor();
		double prethodnaProsecnaOcena = d.getProsenaOcena();
		d.setBrojOcena(d.getBrojOcena()+1);
		d.setSumaOcena(d.getSumaOcena()+ocena);
		double prosecnaOcena =(double)d.getSumaOcena()/(double)d.getBrojOcena();
		System.out.println("prethodna prosecna ocena je: "+prethodnaProsecnaOcena+" a ocena je: "+prosecnaOcena);
		d.setProsenaOcena(prosecnaOcena);
		
		d = doktorService.save(d);
		return new ResponseEntity<String>("",HttpStatus.OK);
	}
	
	@RequestMapping(value = "/poseceneKlinike", method = RequestMethod.GET)
	public ResponseEntity<List<Klinika>> getKlinike(@RequestParam(value="id") int id) {
		Long id_l = Integer.toUnsignedLong(id);
		Pacijent p = pacijentService.findByIdKorisnik(id_l);
		List<Pregled> pregledi = pregledService.findByPatientId(p.getId());
		
		List<Klinika> klinike = new ArrayList<Klinika>();
		for(Pregled preg : pregledi) {
			if(!klinike.contains(preg.getDoktor().getKlinika()))
				klinike.add(preg.getDoktor().getKlinika());
			
		}
		
		for(Klinika kl : klinike) {
			//System.out.println(kl.getIme());
		}
				
		return new ResponseEntity<List<Klinika>>(klinike,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/pregledi", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<List<PregledDTO>> getPregledi(@RequestParam(value="id") int id)	{
		//System.out.println(id);
		Long id_l = Integer.toUnsignedLong(id);
		Pacijent p = pacijentService.findByIdKorisnik(id_l);
		List<Pregled> pregledi = pregledService.findByPatientId(p.getId());
		List<PregledDTO> preglediDTO = new ArrayList<>();
		
		for(Pregled preg : pregledi) {
			PregledDTO pregDTO = new PregledDTO(preg);
			Optional<Korisnik> k = korisnikService.findById(preg.getDoktor().getId()); //id doktora
			pregDTO.setImeDoktora(k.get().getIme());
			pregDTO.setPrezimeDoktora(k.get().getPrezime());
			preglediDTO.add(pregDTO);
		}
		
		for(PregledDTO preg:preglediDTO) {
			System.out.println(preg.getId());
			System.out.println(preg.getImeDoktora() + ' ' + preg.getPrezimeDoktora());
		}
		
		return new ResponseEntity<>(preglediDTO,HttpStatus.OK);
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
			Korisnik k = korisnikService.findOne(d.getIdKorisnik());
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
	
	
	
	@RequestMapping(value = "/searchClinics", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<List<KlinikaDTO>> getClinicsSearch(@RequestParam(value="name") String name,
															 @RequestParam(value="address") String address,
															 @RequestParam(value="desc") String desc,
															 @RequestParam(value="rating") String rating) {
		
		System.out.println(name+" " + address +  " " + desc + " " + rating);
		List<Klinika> clinics = klinikaService.findAll();
		List<KlinikaDTO> klinikaDTO = new ArrayList<>();
		
		for (Klinika k : clinics) {
			if(k.getIme().contains(name) && k.getAdresa().contains(address)
					&& k.getOpis().contains(desc) && Double.toString(k.getProsecnaOcena()).contains(rating))
			{
				klinikaDTO.add(new KlinikaDTO(k));
			}
			
		}
		
		return new ResponseEntity<>(klinikaDTO, HttpStatus.OK);	
	}
	
	@RequestMapping(value = "/lekar/{id}", method=RequestMethod.GET)
	public Doktor getDoctor(@PathVariable Long id) {
		Doktor doktor = doktorService.findByIdKorisnik(id);
		System.out.println(doktor.getSpecijalizacija());
		return doktor;
	}
	
	@RequestMapping(value = "/izmeni", method = RequestMethod.PUT)
	public ResponseEntity<PacijentDTO> izmeni(@RequestBody PacijentDTO pacijentDTO) {
		Pacijent pacijent = pacijentService.findOne(pacijentDTO.getId());
		pacijent.setVisina(pacijentDTO.getVisina());
		pacijent.setTezina(pacijentDTO.getTezina());
		pacijent.setDioptrija(pacijentDTO.getDioptrija());
		if(pacijentDTO.getKrvna_grupa() != null)
			pacijent.setKrvna_grupa(pacijentDTO.getKrvna_grupa());
		Pacijent p = pacijentService.save(pacijent);
		return new ResponseEntity<PacijentDTO>(new PacijentDTO(p), HttpStatus.OK);
	}
	
	@RequestMapping(value="/zakazi", method=RequestMethod.PUT)
	public ResponseEntity<String> zakaziPregled(@RequestBody String dt) throws ParseException, InterruptedException {
		dt = dt.substring(1, dt.length()-1);
		System.out.println(dt);
		Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(dt);  
		System.out.println(date1+"\t"+dt);  
		
		List<Doktor> doktori = doktorService.findAll();
		Doktor doktor = doktori.get(0);
		System.out.println("dr spec: "+doktor.getSpecijalizacija());
		
		List<Korisnik> korisnici = korisnikService.findByIme("Petar");
		Korisnik user = korisnici.get(0);
		Pacijent pacijent = pacijentService.findByIdKorisnik(user.getId());
		System.out.println("visina pacijent: "+pacijent.getVisina());
		
		List<Korisnik> administratori = korisnikService.findByUloga(UlogaKorisnika.ADMIN_KLINIKE);
		Korisnik admin = administratori.get(0);
		System.out.println("admin: "+admin.getIme());
		
		
		Pregled pregled = new Pregled();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date1);
		pregled.setDatumIVremePregleda(cal);
		pregled.setPacijent(pacijent);
		pregled.setDoktor(doktor);
		//pregledService.save(pregled);
		try {
			emailService.sendNotificaitionPregled(user, admin, pregled);
		} catch (MailException e) {
			// TODO Auto-generated catch block
			System.out.println("##################### Desila se greska1" + e.getMessage());
		}
		
		return new ResponseEntity<String>("", HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "sortiraniPacijenti/{tip}", method = RequestMethod.GET)
	public ResponseEntity<List<KorisnikDTO>> sortiraniPacijenti(@PathVariable String tip){
		List<KorisnikDTO> retVal = new ArrayList<KorisnikDTO>();
		List<Pacijent> pacijenti = pacijentService.findAll();
		
		List<Korisnik> sviKorisnici = korisnikService.findAll();
		List<Korisnik> kPacijenti = new ArrayList<Korisnik>();
		
		for(Pacijent p: pacijenti) {
			for(Korisnik k : sviKorisnici) {
				if(k.getId() == p.getId()) {
					kPacijenti.add(k);
					continue;
				}
			}
		}
		
		if(tip.equals("ime")) {
			Collections.sort(kPacijenti, new Comparator<Korisnik>(){

				@Override
				public int compare(Korisnik o1, Korisnik o2) {
					// TODO Auto-generated method stub
					return o1.getIme().compareTo(o2.getIme());
				}
				
			});
		}else if(tip.equals("prezime")) {
			Collections.sort(kPacijenti, new Comparator<Korisnik>() {

				@Override
				public int compare(Korisnik o1, Korisnik o2) {
					// TODO Auto-generated method stub
					return o1.getPrezime().compareTo(o2.getPrezime());
				}
				
			});
			
		}else if(tip.equals("id")) {
			Collections.sort(kPacijenti, new Comparator<Korisnik>() {

				@Override
				public int compare(Korisnik o1, Korisnik o2) {
					// TODO Auto-generated method stub
					return o1.getJmbg().compareTo(o2.getJmbg());
				}
				
			});
		}
	
		for(Korisnik k : kPacijenti) {
			retVal.add(new KorisnikDTO(k));
		}
		
		return new ResponseEntity<List<KorisnikDTO>>(retVal, HttpStatus.OK);
		
	}
}
			
