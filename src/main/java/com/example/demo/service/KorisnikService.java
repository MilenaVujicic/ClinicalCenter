package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.KorisnikDTO;
import com.example.demo.error.UserAlreadyExistException;
import com.example.demo.model.Korisnik;
import com.example.demo.model.UlogaKorisnika;
import com.example.demo.repository.KorisnikRepository;

@Service
public class KorisnikService implements IUserService {

	@Bean
	PasswordEncoder getEncoder() {
	    return new BCryptPasswordEncoder();
	}
	
	@Autowired
    PasswordEncoder passwordEncoder;
	
	@Autowired
	KorisnikRepository korisnikRepository;
	
	public List<Korisnik> findByUloga(UlogaKorisnika uloga) {
		return korisnikRepository.findByUloga(uloga);
	}
	
	public Korisnik save(Korisnik korisnik) {
		return korisnikRepository.save(korisnik);
	}
	
	public void delete(Korisnik korisnik) {
		korisnikRepository.delete(korisnik);
	}
	
	public List<Korisnik> findByAktivan(Boolean aktivan) {
		return korisnikRepository.findByAktivan(aktivan);
	}
	
	public List<Korisnik> findAll() {
		return korisnikRepository.findAll();
	}
	
	public List<Korisnik> findByIme(String ime){
		return korisnikRepository.findByIme(ime);
	}
	
	public List<Korisnik> findByPrezime(String prezime){
		return korisnikRepository.findByPrezime(prezime);
	}
	
	public Korisnik findOne(Long id) {
		return korisnikRepository.findById(id).orElse(null);
	}
	
	public Optional<Korisnik> findById(Long id){
		return korisnikRepository.findById(id);
	}
	
	@Transactional
	@Override
	public Korisnik registerNewUserAccount(KorisnikDTO accountDto) throws UserAlreadyExistException {
		if (emailExists(accountDto.getEmail())) {
            throw new UserAlreadyExistException("There is an account with that email adress: " + accountDto.getEmail());
        }
		final Korisnik user = new Korisnik();
		user.setIme(accountDto.getIme());
		user.setPrezime(accountDto.getPrezime());
		user.setEmail(accountDto.getEmail());
		//user.setPassword(passwordEncoder.encode(accountDto.getPassword())); //treba namestiti passwordEncoder
		user.setPassword(accountDto.getPassword());
		user.setAdresa(accountDto.getAdresa());
		user.setGrad(accountDto.getGrad());
		user.setDrzava(accountDto.getDrzava());
		user.setJmbg(accountDto.getJmbg());
		user.setTelefon(accountDto.getTelefon());
		user.setUloga(UlogaKorisnika.PACIJENT);
        return korisnikRepository.save(user);
	}
	
	private boolean emailExists(String email) {
        Korisnik user = korisnikRepository.findByEmail(email);
        if (user != null) {
            return true;
        }
        return false;
    }

	public Korisnik findByEmail(String email) {
		return korisnikRepository.findByEmail(email);
	}
	


}
