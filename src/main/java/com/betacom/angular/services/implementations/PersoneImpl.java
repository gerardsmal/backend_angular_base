package com.betacom.angular.services.implementations;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.betacom.angular.dto.PersoneDTO;
import com.betacom.angular.models.Persone;
import com.betacom.angular.repository.IPersoneRepository;
import com.betacom.angular.requests.PersoneReq;
import com.betacom.angular.services.interfaces.PersoneServices;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PersoneImpl implements PersoneServices{

	private IPersoneRepository persR;
	
	public PersoneImpl(IPersoneRepository persR) {
		this.persR = persR;
	}

	@Override
	public void create(PersoneReq req) throws Exception {
		log.debug("create:" + req );
		
		Objects.requireNonNull(req.getNome(), "nome non presente");
		Objects.requireNonNull(req.getCognome(), "cognome non presente");
		Objects.requireNonNull(req.getEmail(), "email non presente");
		
		Persone p = new Persone();
		p.setNome(req.getNome());
		p.setCognome(req.getCognome());
		p.setEmail(req.getEmail());
		p.setColore(req.getColore());
		
		persR.save(p);
		
	}

	@Override
	public void update(PersoneReq req) throws Exception {
		log.debug("update:" + req);
		Persone pers = persR.findById(req.getId())
				.orElseThrow(() -> new Exception("Persona non trovata"));
		
		Optional.ofNullable(req.getNome()).ifPresent(pers::setNome);
		Optional.ofNullable(req.getCognome()).ifPresent(pers::setCognome);
		Optional.ofNullable(req.getEmail()).ifPresent(pers::setEmail);
		Optional.ofNullable(req.getColore()).ifPresent(pers::setColore);
		
		persR.save(pers);
		
		
	}
	@Override
	public void delete(PersoneReq req) throws Exception {
		log.debug("delete:" + req);
		Persone per = persR.findById(req.getId())
				.orElseThrow(() -> new Exception("Persona non trovata"));
		
		persR.delete(per);
	}

	@Override
	public List<PersoneDTO> list() throws Exception {
		log.debug("list");
		List<Persone> lP = persR.findAll();
		
		return lP.stream()
				.map(p -> PersoneDTO.builder()
						.id(p.getId())
						.nome(p.getNome())
						.cognome(p.getCognome())
						.email(p.getEmail())
						.colore(p.getColore())
						.build()
						).toList();
	}

	@Override
	public PersoneDTO getById(Integer id) throws Exception {
		log.debug("getById:" + id);
		Persone per = persR.findById(id)
				.orElseThrow(() -> new Exception("Persona non travate"));

		return PersoneDTO.builder()
				.id(per.getId())
				.nome(per.getNome())
				.cognome(per.getCognome())
				.email(per.getEmail())
				.colore(per.getColore())
				.build();
	}



}
