package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.SalaDTO;
import com.example.demo.dto.TerminDTO;
import com.example.demo.model.Klinika;
import com.example.demo.model.Operacija;
import com.example.demo.model.Sala;
import com.example.demo.model.Termin;
import com.example.demo.service.KlinikaService;
import com.example.demo.service.OperacijaService;
import com.example.demo.service.SalaService;

@RestController
@RequestMapping(value = "sala")
public class SalaController {

	@Autowired
	SalaService salaService;
	
	@Autowired
	KlinikaService klinikaService;
	
	@Autowired
	OperacijaService operacijaService;
	
	@RequestMapping(value = "/sveSale/{val}", method=RequestMethod.GET)
	public ResponseEntity<List<SalaDTO>> getSveSale(@PathVariable String val){
		List<Sala> sala = salaService.findAll();
		List<SalaDTO> sale = new ArrayList<SalaDTO>();
		Klinika klinika = klinikaService.findByName(val);
		for (Sala s : sala) {
			if(s.getKlinika().getId() == klinika.getId())
				sale.add(new SalaDTO(s));
		}
		
		return new ResponseEntity<>(sale, HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/sve_sale/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<SalaDTO>>getSaleKlinika(@PathVariable String id){
		List<Sala> sale = salaService.findAll();
		List<SalaDTO> retVal = new ArrayList<SalaDTO>();
		String[] parts = id.split("\\.");
		Long idL = Long.parseLong(parts[0]);
	
		for(Sala s : sale) {
			if(s.getKlinika().getId() == idL) {
				retVal.add(new SalaDTO(s));
			}
		}
		
		return new ResponseEntity<List<SalaDTO>>(retVal, HttpStatus.OK);
	}
	@RequestMapping(value = "/termini/{val}", method = RequestMethod.GET)
	public ResponseEntity<List<TerminDTO>> getSviTermini(@PathVariable String val){
		List<TerminDTO> termini = new ArrayList<TerminDTO>();
		Long salaID = Long.parseLong(val);
		Optional<Sala> oSala = salaService.findById(salaID);
		Sala sala = oSala.get();
		
		for(Termin t : sala.getSlobodniTermini()) {
			TerminDTO addT = new TerminDTO(t);
			termini.add(addT);
		}
		
		return new ResponseEntity<>(termini, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/nova_sala")
	public ResponseEntity<Object> novaSala(@RequestBody String txt){
		String[] selRoomData = null;
		String selRoom = null;
		
		String[] selClinicData = null;
		String selClinic = null;
		
		String[] nameData = null;
		String name = null;
		
		String[] descData = null;
		String desc = null;
		
		String[] data = txt.split("&");
		
	
		if((data[0].charAt(data[0].length()-1)) != '=') {
			selRoomData = data[0].split("=");
			selRoom = selRoomData[1];
			
		}
		
		Long selClinicID = 0L;
		if((data[1].charAt(data[1].length()-1)) != '=') {
			selClinicData = data[1].split("=");
			selClinic = selClinicData[1];
			System.out.println(selClinic);
			String selClinicIDS = selClinic.split("\\.")[0];
			System.out.println(selClinicIDS);
			if(!selClinicIDS.equals("New"))
				selClinicID = Long.parseLong(selClinicIDS);
		}
		
		if((data[2].charAt(data[2].length()-1)) != '=') {
			nameData = data[2].split("=");
			name = nameData[1];
			if(name.contains("\\+"))
				name.replaceAll("\\+", " ");
	
		}
		

		if((data[3].charAt(data[3].length()-1)) != '=') {
			descData = data[3].split("=");
			desc = descData[1];
			if(desc.contains("\\+"))
				desc.replaceAll("\\+", " ");
		}
		
		Sala s;
		
		if(selRoom.equals("New")) {
			s = new Sala();
			Klinika k = klinikaService.findOne(selClinicID);
			if(k == null) {
				return new ResponseEntity<Object>(null, HttpStatus.NOT_FOUND);
			}
			s.setKlinika(k);
			s.setIme(name);
			s.setOpis(desc);
		}else {
			String selRoomIDS = selRoom.split("\\.")[0];
			Long selRoomID = Long.parseLong(selRoomIDS);
			s = salaService.findOne(selRoomID);
			if(name != null) {
				s.setIme(name);
			}
			if(desc != null) {
				s.setOpis(desc);
			}
			salaService.save(s);
		}
		return new ResponseEntity<Object>(null, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/search/{val}", method = RequestMethod.GET)
	public ResponseEntity<List<SalaDTO>> getSale(@PathVariable String val){
		List<SalaDTO> sale = new ArrayList<SalaDTO>();
		
		if(val.equals(":"))
			return new ResponseEntity<>(null, HttpStatus.OK);
		
		boolean num = false;
		System.out.println(val);
		String[] parts = val.split(":");
		String name = parts[0];
		String date = parts[1];
		String klinika = parts[2];
		
	
		for(int i = 0; i < name.length(); i++) {
			if(!Character.isDigit(name.charAt(i))) {
				num = false;
				break;
			}
			num = true;
		}
		
		
		Long sId = 0L;
		if(num) {

			 sId = Long.parseLong(name);
			 Optional<Sala> s = salaService.findById(sId);
			 
			 if(s == null) {
				 System.out.println("Not found");
				 return new ResponseEntity<>(sale, HttpStatus.NOT_FOUND);
				
			 }
			 Sala foundS = s.get();
		
			 if(foundS.getKlinika().getIme().equals(klinika))
				 sale.add(new SalaDTO(foundS));
		}else {
			List<Sala> allS = salaService.findAll();
			
			for(Sala s: allS) {
				if(s.getIme().toLowerCase().contains(name.toLowerCase()) || s.getIme().toLowerCase().equals(name.toLowerCase())) {
					if(s.getKlinika().getIme().equals(klinika))
						sale.add(new SalaDTO(s));
				}
			}
		}
		
		
		return new ResponseEntity<>(sale, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/slobodni_termini/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<Sala>> slobodniTermini(@PathVariable("id") Long identifikacija) {
		Operacija operacija = operacijaService.findOne(identifikacija);
		Klinika klinika = klinikaService.findOne((long) 2);
		List<Sala> sveSale = salaService.findByKlinika(klinika);
		System.out.println("##########sve_sale" + sveSale.size());
		List<Sala> slobodneSale = new ArrayList<Sala>();
		for (Sala sala : sveSale) {
			for (Termin termin : sala.getSlobodniTermini()) {
				if (termin.isSlobodan() && termin.getDatum().equals(operacija.getDatumIVremeOperacije())) {
					slobodneSale.add(sala);
				}
			}
		}
		System.out.println("##########################" + slobodneSale.size());
		return new ResponseEntity<List<Sala>>(slobodneSale, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/drugi_slobodni_termini/{id}")
	public ResponseEntity<Sala> prviSlobodan(@PathVariable("id") Long identifikacija) {
		System.out.println("##############drugi slobodni termini");
		Operacija operacija = operacijaService.findOne(identifikacija);
		Klinika klinika = klinikaService.findOne((long) 2);
		List<Sala> sveSale = salaService.findByKlinika(klinika);
		Sala rezervisanaSala = sveSale.get(0);
		long min = new Long(Long.MAX_VALUE);
		Termin slobodanTermin = new Termin();
		System.out.println("############# sve sale size" + sveSale.size());
		try {
			for (Sala sala : sveSale) {
				for (Termin termin : sala.getSlobodniTermini()) {
					if (termin.isSlobodan()) {
						slobodanTermin = termin;
						break;
					}
				}
			}
	
			for (Sala sala : sveSale) {
				for (Termin termin : sala.getSlobodniTermini()) {
					if (termin.isSlobodan()) {
						long end = termin.getDatum().getTimeInMillis();
					    long start = operacija.getDatumIVremeOperacije().getTimeInMillis();
					    if (min > Math.abs(end - start)) {
					    	min = Math.abs(end - start);
					    	slobodanTermin = termin;
					    }
					}
				}
			}
		
		
			for (Sala sala : sveSale) {
				if (slobodanTermin.getSala().getId().equals(sala.getId())) {
					rezervisanaSala = sala;
				}
			}
		}
		catch (Exception e) {
			System.out.println("########" + e);
			return new ResponseEntity<Sala>(HttpStatus.NOT_FOUND);
		} 

		operacija.setDatumIVremeOperacije(slobodanTermin.getDatum());
		operacijaService.save(operacija);
		return new ResponseEntity<Sala>(rezervisanaSala, HttpStatus.OK);
	}
	
	
}
