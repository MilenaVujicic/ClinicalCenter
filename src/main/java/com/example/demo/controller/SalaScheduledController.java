package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import com.example.demo.model.AdministratorKlinike;
import com.example.demo.model.Klinika;
import com.example.demo.model.Korisnik;
import com.example.demo.model.Operacija;
import com.example.demo.model.StatusOperacije;
import com.example.demo.model.Termin;
import com.example.demo.service.AdministratorKlinikeService;
import com.example.demo.service.DoktorService;
import com.example.demo.service.EmailService;
import com.example.demo.service.KlinikaService;
import com.example.demo.service.KorisnikService;
import com.example.demo.service.OperacijaService;
import com.example.demo.service.TerminService;

@Controller
public class SalaScheduledController {
	
	@Autowired
	private TerminService terminService;
	
	@Autowired
	private OperacijaService operacijaService;
	
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

	@Scheduled(cron = "${greeting1.cron}")
	public void autoDodelaSala() throws InterruptedException {
		System.out.println("Start of auto dodela sala");
		List<Operacija> nerasporedjene_operacije = operacijaService.findByStatus(StatusOperacije.NERASPOREDJEN);
		List<Termin> slobodni_termini = terminService.findBySlobodan(true);
		List<Operacija> rasporedjene_operacije = new ArrayList<Operacija>();
		List<AdministratorKlinike> administratoriKlinike = administratorKlinikeService.findAll();
		
		System.out.println("Nerasporedjene operacije" + nerasporedjene_operacije.size());
		System.out.println("Rasporedjene operacije" + rasporedjene_operacije.size());
		for (Operacija operacija : nerasporedjene_operacije) {
			for (Termin termin : slobodni_termini) {
				if (operacija.getDatumIVremeOperacije().getTimeInMillis() == termin.getDatum().getTimeInMillis()) {
					termin.setSlobodan(false);
					rasporedjene_operacije.add(operacija);
					Klinika klinika = klinikaService.findOne(termin.getSala().getKlinika().getId());
					operacija.setStatus(StatusOperacije.ZAKAZAN);
					operacija.setSala(termin.getSala());
					operacijaService.save(operacija);
					terminService.save(termin);
					slobodni_termini.remove(termin);
					Korisnik pacijent = korisnikService.findOne(operacija.getPacijent().getIdKorisnik());
					for (AdministratorKlinike admininstrator : administratoriKlinike) {
						if (admininstrator.getKlinika().getId().equals(klinika.getId())) {
							Korisnik admin = korisnikService.findOne(admininstrator.getIdKorisnik());
							emailService.sendReservationToAdmin(admin, operacija, pacijent);
						}
					}
					emailService.sendSuccessfulReservationPatient(pacijent, operacija, klinika);
					System.out.println("####" + termin.getDatum().getTime() + " " + operacija.getPacijent().getIdKorisnik() + " " + termin.getSala().getIme());
					TimeUnit.SECONDS.sleep(20);
					break;
				}
			}
		}
		
		for (Operacija operacija : rasporedjene_operacije) {
			nerasporedjene_operacije.remove(operacija);
		}
		
		System.out.println("##########################");
		System.out.println("Nerasporedjene operacije" + nerasporedjene_operacije.size());
		System.out.println("Rasporedjene operacije" + rasporedjene_operacije.size());
		System.out.println("##########################");

		if (nerasporedjene_operacije.size() > 0) {
			for (Operacija operacija : nerasporedjene_operacije) {
				System.out.println(operacija.getId());
				long min = new Long(Long.MAX_VALUE);
				Termin slobodanTermin = new Termin();
				Termin pomocTermin = new Termin();
				for (Termin termin : slobodni_termini) {
					long end = termin.getDatum().getTimeInMillis();
				    long start = operacija.getDatumIVremeOperacije().getTimeInMillis();
				    if (min > Math.abs(end - start)) {
				    	min = Math.abs(end - start);
				    	slobodanTermin = termin;
				    }
				}
				if (!slobodanTermin.equals(pomocTermin)) {
					slobodanTermin.setSlobodan(false);
					rasporedjene_operacije.add(operacija);
					Klinika klinika = klinikaService.findOne(slobodanTermin.getSala().getKlinika().getId());
					operacija.setStatus(StatusOperacije.ZAKAZAN);
					operacija.setSala(slobodanTermin.getSala());
					operacijaService.save(operacija);
					terminService.save(slobodanTermin);
					slobodni_termini.remove(slobodanTermin);
					Korisnik pacijent = korisnikService.findOne(operacija.getPacijent().getIdKorisnik());
					emailService.sendSuccessfulReservationPatient(pacijent, operacija, klinika);
					for (AdministratorKlinike administrator : administratoriKlinike) {
						if (administrator.getKlinika().getId().equals(klinika.getId())) {
							Korisnik admin = korisnikService.findOne(administrator.getIdKorisnik());
							emailService.sendReservationToAdmin(admin, operacija, pacijent);
						}
					}
					System.out.println("####" + slobodanTermin.getDatum().getTime() + " " + operacija.getPacijent().getIdKorisnik() + " " + slobodanTermin.getSala().getIme());
				}
				else {
					System.out.println("Nema slobodnih termina");
					Korisnik pacijent = korisnikService.findOne(operacija.getPacijent().getIdKorisnik());
					emailService.sendUnsuccessfulReservationPatient(pacijent);
				}
				TimeUnit.SECONDS.sleep(20);
			}
		}
		
		for (Operacija operacija : rasporedjene_operacije) {
			nerasporedjene_operacije.remove(operacija);
		}
		
		System.out.println("Nerasporedjene operacije" + nerasporedjene_operacije.size());
		System.out.println("Rasporedjene operacije" + rasporedjene_operacije.size());
		
		System.out.println("End of auto dodela sala");
		
	}
	
}
