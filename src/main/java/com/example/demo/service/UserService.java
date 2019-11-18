package com.example.demo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.KorisnikDTO;
import com.example.demo.error.UserAlreadyExistException;
import com.example.demo.model.Korisnik;
import com.example.demo.model.UlogaKorisnika;
import com.example.demo.repository.KorisnikRepository;


@Service
public class UserService implements IUserService{
	
	@Autowired
    private KorisnikRepository korisnikRepository;
	
	@Autowired
    private UlogaKorisnikaService ulogaKorisnikaService;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	public List<Korisnik> findByUloga(String string) {
		return korisnikRepository.findByUloge(string);
	}
	
	public Korisnik findById(Long id) throws AccessDeniedException {
		Korisnik u = korisnikRepository.findById(id).orElseGet(null);
		return u;
	}

	public List<Korisnik> findAll() throws AccessDeniedException {
		List<Korisnik> result = korisnikRepository.findAll();
		return result;
	}

	@Transactional
	@Override
	public Korisnik save(KorisnikDTO accountDto) throws UserAlreadyExistException {
		if (emailExists(accountDto.getEmail())) {
            throw new UserAlreadyExistException("There is an account with that email adress: " + accountDto.getEmail());
        }
		final Korisnik user = new Korisnik();
		user.setIme(accountDto.getIme());
		user.setPrezime(accountDto.getPrezime());
		user.setEmail(accountDto.getEmail());
		System.out.println(accountDto.getIme() + " " + accountDto.getPrezime() + " " + accountDto.getEmail());
		user.setPassword(passwordEncoder.encode(accountDto.getPassword())); //null
		user.setAdresa(accountDto.getAdresa());
		user.setGrad(accountDto.getGrad());
		user.setDrzava(accountDto.getDrzava());
		user.setJmbg(accountDto.getJmbg());
		
		//List<UlogaKorisnika> uloge = ulogaKorisnikaService.findByname("PACIJENT");
		//user.setUloge(uloge);
		
		//fali jos datumRodjenja, ali datumRodjenja ni ne treba pri registraciji da se unosi, treba da se ukloni
        return korisnikRepository.save(user);
	}
	
	private boolean emailExists(String email) {
        Korisnik user = korisnikRepository.findByEmail(email);
        if (user != null) {
            return true;
        }
        return false;
    }

	@Override
	public Korisnik findByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

}
