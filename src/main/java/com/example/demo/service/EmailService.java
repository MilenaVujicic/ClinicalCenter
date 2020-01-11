package com.example.demo.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.example.demo.model.Doktor;
import com.example.demo.model.Klinika;
import com.example.demo.model.Korisnik;
import com.example.demo.model.Operacija;
import com.example.demo.model.Pregled;
import com.example.demo.model.UlogaKorisnika;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private Environment env;
	
	@Autowired
	KorisnikService korisnikService;
	
	@Async
	public void sendNotificaitionAllow(Korisnik user) throws MailException, InterruptedException {
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo("isaps174@gmail.com");
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("Prihvacena registracija");
		mail.setText("Poštovani/-a " + user.getIme() + " " + user.getPrezime() + ",\n\nvaša registracija "
				+ "da budete: "+user.getUloga()+ " je prihvaćena." 
				+ "\nDa biste aktivirali vaš profil, kliknite na link ispod:\n"
				+ "http://localhost:8080/korisnik/aktiviraj/" + user.getId());
		mail.setSentDate(new Date());
		System.out.println(mail);
		javaMailSender.send(mail);
		System.out.println("Email poslat!");
	}
	
	@Async
	public void sendNotificationDeny(Korisnik korisnik, String text) throws MailException, InterruptedException {
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo("isaps174@gmail.com");
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("Odbijena registracija");
		mail.setText("Poštovani/-a " + korisnik.getIme() + " " + korisnik.getPrezime() + ",\n\nvaša registracija "
				+ "da budete "+korisnik.getUloga()+" je odbijena."
				+ "\n\nRazlog vašeg odbijanja je:\n" + text);
		mail.setSentDate(new Date());
		System.out.println(mail);
		javaMailSender.send(mail);
		System.out.println("Email poslat!");
	}
	
	@Async
	public void sendNotificationActivation(Korisnik korisnik) throws MailException, InterruptedException { 
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo("isaps174@gmail.com");
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("Aktiviran nalog");
		if (korisnik.getUloga().equals(UlogaKorisnika.PACIJENT)) {
			mail.setText("Poštovani/-a " + korisnik.getIme() + " " + korisnik.getPrezime() + ",\n\nvaš nalog je "
					+ "aktiviran." 
					+ "\n\nZa dalje korišćenje naše aplikacije idite na:\nhttp://localhost:8080/");
		}
		else {
			mail.setText("Poštovani/-a " + korisnik.getIme() + " " + korisnik.getPrezime() + ",\n\nvaš nalog je "
					+ "aktiviran." 
					+ "\nVaš username: " + korisnik.getUsername()
					+ "\nVaš prvobitan password: " + korisnik.getPassword()
					+ "\nVaš email: " + korisnik.getEmail()
					+ "\n\nZa dalje korišćenje naše aplikacije idite na:\nhttp://localhost:8080/");
		}
		mail.setSentDate(new Date());
		System.out.println(mail);
		javaMailSender.send(mail);
		System.out.println("Email poslat!");
	}
	
	@Async
	public void sendNotificaitionPregled(Korisnik user, Korisnik admin, Pregled pregled) throws MailException, InterruptedException {
		SimpleMailMessage mail = new SimpleMailMessage();
		Doktor doktor = pregled.getDoktor();
		Korisnik doktor_korisnik = korisnikService.findOne(doktor.getIdKorisnik());
		
		mail.setTo("filip.vozarevic@gmail.com");
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("Zahtev za pregled");
		mail.setText("Postovani/-a " + admin.getIme() + " " + admin.getPrezime()
				+ "\nStigao vam je zahtev za pregled od korisnika " + user.getIme() + " " + user.getPrezime()
				+ "\n za datum: "+ pregled.getDatumIVremePregleda()
				+ "\n kod lekara: "+ doktor_korisnik.getIme() + " " + doktor_korisnik.getPrezime());
		mail.setSentDate(new Date());
		System.out.println(mail);
		javaMailSender.send(mail);
		System.out.println("Email poslat!");
	}
	
	@Async
	public void sendNotificationExam(Korisnik admin, Korisnik doktor, Korisnik pacijent, String datum, String vreme) throws MailException, InterruptedException{
		SimpleMailMessage mail = new SimpleMailMessage();
		
		mail.setTo("isaps174@gmail.com");
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("Zahtev za pregled");
		mail.setText("Postovani/-a " + admin.getIme() + " " + admin.getPrezime()
				+ "\nStigao vam je zahtev za pregled od lekara " + doktor.getIme() + " " + doktor.getPrezime()
				+"\nza pacijenta: " + pacijent.getIme() + " " + pacijent.getPrezime()
				+ "\nza datum: "+ datum + " " + vreme);
		mail.setSentDate(new Date());
		System.out.println(mail);
		javaMailSender.send(mail);
		System.out.println("Email poslat!");
	}
	
	@Async
	public void sendNotificationRoom(Korisnik admin, Korisnik doktor, Korisnik pacijent, String datum, String vreme) throws MailException, InterruptedException{
		SimpleMailMessage mail = new SimpleMailMessage();
		
		mail.setTo("isaps174@gmail.com");
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("Zahtev za salu");
		mail.setText("Postovani/-a " + admin.getIme() + " " + admin.getPrezime()
				+ "\nStigao vam je zahtev za nalazenje sale za operaciju od lekara " + doktor.getIme() + " " + doktor.getPrezime()
				+"\nza pacijenta: " + pacijent.getIme() + " " + pacijent.getPrezime()
				+ "\nza datum: "+ datum + " " + vreme);
		mail.setSentDate(new Date());
		System.out.println(mail);
		javaMailSender.send(mail);
		System.out.println("Email poslat!");
	}
	
	@Async
	public void sendSuccessfulReservationPatient(Korisnik pacijent, Operacija operacija, Klinika klinika) {
		SimpleMailMessage mail = new SimpleMailMessage();
		
		mail.setTo("isaps174@gmail.com");
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("Zakazana operacija");
		mail.setText("Postovani/-a" + pacijent.getIme() + " " + pacijent.getPrezime() + ","
					+"\nImate zakazanu operaciju za " + operacija.getDatumIVremeOperacije().getTime()
					+"\nna klinici: " + klinika.getIme() + " (" + klinika.getAdresa() + ") u sali: " + operacija.getSala().getIme());
		mail.setSentDate(new Date());
		System.out.println(mail);
		javaMailSender.send(mail);
		System.out.println("Email poslat!");
	}
	
	@Async
	public void sendSuccessfulReservationDoctor(Korisnik pacijent, Operacija operacija, Korisnik doktor, Klinika klinika) {
		SimpleMailMessage mail = new SimpleMailMessage();
		
		mail.setTo("isaps174@gmail.com");
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("Zakazana operacija");
		mail.setText("Postovani/-a" + doktor.getIme() + " " + doktor.getPrezime() + ","
					+"\nImate zakazanu operaciju za " + operacija.getDatumIVremeOperacije().getTime()
					+"\nna klinici: " + klinika.getIme() + " (" + klinika.getAdresa() + ") u sali: " + operacija.getSala().getIme()
					+".\nPacijent je: " + pacijent.getIme() + " " + pacijent.getPrezime());
		mail.setSentDate(new Date());
		System.out.println(mail);
		javaMailSender.send(mail);
		System.out.println("Email poslat!");
	}
	
	@Async
	public void sendUnsuccessfulReservationPatient(Korisnik pacijent) {
		SimpleMailMessage mail = new SimpleMailMessage();
				
		mail.setTo("isaps174@gmail.com");
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("Neuspesno zakazivanje operacije");
		mail.setText("Postovani/-a" + pacijent.getIme() + " " + pacijent.getPrezime() + ","
					+"\nNazalost nismo mogli da Vam zakazemo operaciju, molimo da se obratite Vasem lekaru.");
					
		mail.setSentDate(new Date());
		System.out.println(mail);
		javaMailSender.send(mail);
		System.out.println("Email poslat!");
	}
	
	@Async
	public void sendReservationToAdmin(Korisnik admin, Operacija operacija, Korisnik pacijent) {
		SimpleMailMessage mail = new SimpleMailMessage();
		
		mail.setTo("isaps174@gmail.com");
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("Zakazivanje operacije");
		mail.setText("Postovani/-a" + admin.getIme() + " " + admin.getPrezime() + ","
					+"\nUspesno je odradjena automatsko zakazivanje operacija za pacijenta: " + pacijent.getIme() + " " + pacijent.getPrezime() + "."
					+"\nOperacija je zakazana za: " + operacija.getDatumIVremeOperacije().getTime() + " u sali: " + operacija.getSala().getIme());
					
		mail.setSentDate(new Date());
		System.out.println(mail);
		javaMailSender.send(mail);
		System.out.println("Email poslat!");
	}
}
