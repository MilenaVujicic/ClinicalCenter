package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import com.example.demo.model.Klinika;
import com.example.demo.model.Termin;
import com.example.demo.model.Zahtev;
import com.example.demo.service.DoktorService;
import com.example.demo.service.KlinikaService;
import com.example.demo.service.PregledService;
import com.example.demo.service.TerminService;
import com.example.demo.service.ZahtevService;

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
	
	@Autowired ZahtevService zahtevService;
	
	
	@SuppressWarnings("static-access")
	@Scheduled(cron = "${greeting.cron}")
	public void autoDodelaSale() {
		System.out.println("Pocetak dodele sale");
		List<Zahtev> zahtevi = zahtevService.findAll();
		List<Zahtev> nerasporedjeni = new ArrayList<Zahtev>();
		for(Zahtev z : zahtevi) {
			if(!z.isObradjen()) {
				nerasporedjeni.add(z);
			}
		}
		
		List<Termin> slobodniTermini = terminService.findBySlobodan(true);
		List<Zahtev> rasporedjeniZahtevi = new ArrayList<Zahtev>();
		
		for(Zahtev z : nerasporedjeni) {
			for(Termin t: slobodniTermini) {
				/*int tDay = t.getDatum().DAY_OF_MONTH;
				int tMonth = t.getDatum().MONTH;
				int tYear = t.getDatum().YEAR;
				
				int tHour = t.getDatum().HOUR;
				int tMinute = t.getDatum().MINUTE;
				String compT = tDay + " " + tMonth + " " + tYear + " " + tHour + " " + tMinute;
				
				int zDay = z.getDatumIVreme().DAY_OF_MONTH;
				int zMonth = z.getDatumIVreme().MONTH;
				int zYear = z.getDatumIVreme().YEAR;
				
				int zHour = z.getDatumIVreme().HOUR;
				int zMinute = z.getDatumIVreme().MINUTE;
				String compZ = zDay + " " + zMonth + " " + zYear + " " + zHour + " " + zMinute;
				
				if(compZ.equals(compT)) {
					t.setSlobodan(true);
					rasporedjeniZahtevi.add(z);
					Klinika k = klinikaService.findOne(t.getSala().getKlinika().getId());
					z.setObradjen(true);
					z.setSalaID(t.getSala().getId());
					zahtevService.save(z);
					terminService.save(t);
					slobodniTermini.remove(t);
					break;
				}*/
				
				if(z.getDatumIVreme().getTimeInMillis() == t.getDatum().getTimeInMillis()) {
					t.setSlobodan(true);
					rasporedjeniZahtevi.add(z);
					Klinika k = klinikaService.findOne(t.getSala().getKlinika().getId());
					z.setObradjen(true);
					z.setSalaID(t.getSala().getId());
					zahtevService.save(z);
					terminService.save(t);
					slobodniTermini.remove(t);
					break;
				}
			}
		}
		
		for(Zahtev z: rasporedjeniZahtevi) {
			nerasporedjeni.remove(z);
		}
		
		if(nerasporedjeni.size() > 0) {
			for(Zahtev z: nerasporedjeni) {
				System.out.println(z.getId());
				long min = new Long(Long.MAX_VALUE);
				Termin slobodanTermin = new Termin();
				Termin temp = new Termin();
				
				for(Termin t: slobodniTermini) {
					long end = t.getDatum().getTimeInMillis();
					long start = z.getDatumIVreme().getTimeInMillis();
					if(min > Math.abs(end-start)) {
						min = Math.abs(end-start);
						slobodanTermin = t;
					}
				}
				
				if(!slobodanTermin.equals(temp)) {
					slobodanTermin.setSlobodan(false);
					rasporedjeniZahtevi.add(z);
					Klinika k = klinikaService.findOne(slobodanTermin.getSala().getKlinika().getId());
					z.setObradjen(true);
					z.setSalaID(slobodanTermin.getSala().getId());
					terminService.save(slobodanTermin);
					slobodniTermini.remove(slobodanTermin);
					
				}else {
					System.out.println("Nema slobodnih termina");
				}
				
			}
		}
		
		for(Zahtev z : rasporedjeniZahtevi) {
			nerasporedjeni.remove(z);
		}
		
	}
}
