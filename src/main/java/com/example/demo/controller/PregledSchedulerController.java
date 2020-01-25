package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import com.example.demo.model.AdministratorKlinike;
import com.example.demo.model.Klinika;
import com.example.demo.model.Korisnik;
import com.example.demo.model.Pregled;
import com.example.demo.model.StatusPregleda;
import com.example.demo.model.Termin;
import com.example.demo.service.AdministratorKlinikeService;
import com.example.demo.service.DoktorService;
import com.example.demo.service.EmailService;
import com.example.demo.service.KlinikaService;
import com.example.demo.service.KorisnikService;
import com.example.demo.service.PregledService;
import com.example.demo.service.TerminService;

@Controller
public class PregledSchedulerController {

	@Autowired
	private TerminService terminService;
	
	@Autowired
	private PregledService pregledService;
	
	@Autowired
	private DoktorService doktorService;
	
	@Autowired
	private KlinikaService klinikaService;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private KorisnikService korisnikService;
	
	@Autowired
	private AdministratorKlinikeService administratorKlinikeService;
	
	
	@SuppressWarnings("static-access")
	@Scheduled(cron = "${greeting2.cron}")
	public void autoDodelaPregleda() {
		System.out.println("Pocetak dodele za pregled");
		List<Pregled> nerasporedjeni_pregledi = pregledService.findByStatus(StatusPregleda.NERASPOREDJEN);
		List<Termin> slobodni_termini  = terminService.findBySlobodan(true);
		List<Pregled> rasporedjeni_pregledi = new ArrayList<Pregled>();
		List<AdministratorKlinike> administratoriKlinike = administratorKlinikeService.findAll();
		
		System.out.println("Nerasporedjeni pregledi " + nerasporedjeni_pregledi.size());
		System.out.println("Rasporedjeni pregledi " + rasporedjeni_pregledi.size());
		
		for(Pregled pregled : nerasporedjeni_pregledi) {
			for(Termin termin : slobodni_termini) {
				if(pregled.getDatumIVremePregleda().getTimeInMillis() == termin.getDatum().getTimeInMillis()) {
					termin.setSlobodan(false);
					rasporedjeni_pregledi.add(pregled);
					Klinika klinika = klinikaService.findOne(termin.getSala().getKlinika().getId());
					pregled.setStatus(StatusPregleda.ZAKAZAN);
					pregled.setSala(termin.getSala());
					pregledService.save(pregled);
					terminService.save(termin);
					slobodni_termini.remove(termin);
					Korisnik pacijent = korisnikService.findOne(pregled.getPacijent().getId());
					for(AdministratorKlinike administrator: administratoriKlinike) {
						if(administrator.getKlinika().getId().equals(klinika.getId())) {
							Korisnik admin = korisnikService.findOne(administrator.getIdKorisnik());
							emailService.sendReservationAptToAdmin(admin, pregled, pacijent);
							
						}
					}
					emailService.sendSuccessfulReservationAptPatient(pacijent, pregled, klinika);
					System.out.println("####" + termin.getDatum().getTime() + " " + pregled.getPacijent().getIdKorisnik() + " " + termin.getSala().getIme());
				}
			}
		}
		
		for(Pregled pregled : rasporedjeni_pregledi) {
			nerasporedjeni_pregledi.remove(pregled);
		}
		
		System.out.println("##########################");
		System.out.println("Nerasporedjeni pregledi" + nerasporedjeni_pregledi.size());
		System.out.println("Rasporedjeni pregledi" + rasporedjeni_pregledi.size());
		System.out.println("##########################");
		
		if(nerasporedjeni_pregledi.size()>0) {
			for(Pregled pregled: nerasporedjeni_pregledi) {
				System.out.println(pregled.getId());
				long min = new Long(Long.MAX_VALUE);
				Termin slobodanTermin = new Termin();
				Termin pomocTermin = new Termin();
				for(Termin termin : slobodni_termini) {
					long end = termin.getDatum().getTimeInMillis();
					long start = pregled.getDatumIVremePregleda().getTimeInMillis();
					if(min > Math.abs(end-start)) {
						min = Math.abs(end - start);
						slobodanTermin = termin;
					}
				}
				if(!slobodanTermin.equals(pomocTermin)) {
					slobodanTermin.setSlobodan(false);
					rasporedjeni_pregledi.add(pregled);
					Klinika klinika = klinikaService.findOne(slobodanTermin.getSala().getKlinika().getId());
					pregled.setStatus(StatusPregleda.ZAKAZAN);
					pregled.setSala(slobodanTermin.getSala());
					pregledService.save(pregled);
					terminService.save(slobodanTermin);
					slobodni_termini.remove(slobodanTermin);
					Korisnik pacijent = korisnikService.findOne(pregled.getPacijent().getIdKorisnik());
					emailService.sendSuccessfulReservationAptPatient(pacijent, pregled, klinika);
					for(AdministratorKlinike administrator : administratoriKlinike) {
						if(administrator.getKlinika().getId().equals(klinika.getId())) {
							Korisnik admin = korisnikService.findOne(administrator.getIdKorisnik());
							emailService.sendReservationAptToAdmin(admin, pregled, pacijent);
						}
					}
					
					System.out.println("####" + slobodanTermin.getDatum().getTime() + " " + pregled.getPacijent().getIdKorisnik() + " " + slobodanTermin.getSala().getIme());
				}else {
					System.out.println("Nema slobodnih termina");
					Korisnik pacijent = korisnikService.findOne(pregled.getPacijent().getIdKorisnik());
					emailService.sendUnsuccessfulReservationAptPatient(pacijent);
				}
			}
		}
		
		for(Pregled pregled : rasporedjeni_pregledi) {
			nerasporedjeni_pregledi.remove(pregled);
		}

		System.out.println("Nerasporedjeni pregledi" + nerasporedjeni_pregledi.size());
		System.out.println("Rasporedjeni pregledi" + rasporedjeni_pregledi.size());
		
		System.out.println("Kraj dodoele za pregled");
	}
}
