package com.betacom.angular.services.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.betacom.angular.dto.inp.UtenteReq;
import com.betacom.angular.dto.out.LoginDTO;
import com.betacom.angular.dto.out.UtenteDTO;
import com.betacom.angular.models.Role;
import com.betacom.angular.models.Utente;
import com.betacom.angular.repository.IUtenteRepository;
import com.betacom.angular.services.interfaces.UtenteServices;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UtenteImpl implements UtenteServices{

	private IUtenteRepository utenteR;
	
	
	public UtenteImpl(IUtenteRepository utenteR) {
		this.utenteR = utenteR;
	}


	@Override
	public void create(UtenteReq req) throws Exception {
		log.debug("create:" + req);
		
		if (utenteR.existsByUsername(req.getUsername()))
			throw new Exception("Username già utilizzato");
		
		Optional.ofNullable(req.getUsername())      
	    	.filter(n -> !n.isBlank())      
	    	.orElseThrow(() -> new Exception("username mancante o vuoto"));

		Optional.ofNullable(req.getPwd())      
	    	.filter(n -> !n.isBlank())      
	    	.orElseThrow(() -> new Exception("Password mancante o vuoto"));
		
		if (req.getRole() == null) req.setRole("USER");
		
		Utente u = new Utente();
		u.setUsername(req.getUsername());
		u.setPwd(req.getPwd());
		
		try {
			u.setRole(Role.valueOf(req.getRole()));
		} catch (IllegalArgumentException e) {
			throw new Exception("Ruole invalido");
		}
	
		
		utenteR.save(u);
		

	}

	@Override
	public void update(UtenteReq req) throws Exception {
		log.debug("update:" + req);
		Utente  u = utenteR.findById(req.getId())
				.orElseThrow(() -> new Exception("Utente non trovato"));
		
		String userName = u.getUsername();
		Optional.ofNullable(req.getUsername()).ifPresent(u::setUsername);
		Optional.ofNullable(req.getPwd()).ifPresent(u::setPwd);
		Optional.ofNullable(req.getRole()).ifPresent(role -> u.setRole(Role.valueOf(role)));
		
		log.debug(userName + "/" + u.getUsername());
		if (!userName.equals(u.getUsername())) {
			
			if (utenteR.existsByUsername(req.getUsername()))
				throw new Exception("Username già utilizzato");
		}
		
		utenteR.save(u);
	}

	

	@Override
	public LoginDTO singUp(UtenteReq req) throws Exception {
		log.debug("singUp:" + req);
		Utente ut = utenteR.findByUsername(req.getUsername())
				.orElseThrow(() -> new Exception("Username / password invalido"));
		
		if (!ut.getPwd().equals(req.getPwd()))
			throw new Exception("Username / password invalido");
		
		return LoginDTO.builder()
				.role(ut.getRole().toString())
				.build();
	}


	@Override
	public void delete(Integer id) throws Exception {
		log.debug("delete:" + id);
		Utente ut = utenteR.findById(id)
				.orElseThrow(() -> new Exception("utente non trovato"));
		utenteR.delete(ut);
	}


	@Override
	public List<UtenteDTO> list(String userName, String role) throws Exception {
		Role ro = null;
		if (role != null)  ro = Role.valueOf(role.trim().toUpperCase());
		
		List<Utente> lU = utenteR.searchByFilter(userName, ro);
		return lU.stream()
				.map(u -> UtenteDTO.builder()
						.id(u.getId())
						.role(u.getRole().toString())
						.username(u.getUsername())
						.build()
						).toList();
	}


	@Override
	public UtenteDTO getById(Integer id) throws Exception {
		Utente ut = utenteR.findById(id)
				.orElseThrow(() -> new Exception("utente non trovato"));
		
		return UtenteDTO.builder()
				.id(ut.getId())
				.role(ut.getRole().toString())
				.username(ut.getUsername())
				.build();
	}



}
