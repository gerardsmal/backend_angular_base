package com.betacom.angular.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.angular.requests.PersoneReq;
import com.betacom.angular.services.interfaces.PersoneServices;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/persone")
public class PersoneController {

	private PersoneServices perS;
	
	public PersoneController(PersoneServices perS) {
		super();
		this.perS = perS;
	}

	
	@PostMapping("/create")
	public ResponseEntity<Object> create(@RequestBody(required = true) PersoneReq req) {
	    String response = "Persona creata";
	    HttpStatus status = HttpStatus.OK;

	    try {
	    	perS.create(req);
	    } catch (Exception e) {
	        response = e.getMessage();
	        status = HttpStatus.BAD_REQUEST;
	    }

	    return ResponseEntity.status(status).body(response);
	}


	@PutMapping("/update")
	public ResponseEntity<Object> update(@RequestBody(required = true) PersoneReq req) {
	    HttpStatus status = HttpStatus.OK;
	    String response="persona aggiornata";
	    try {
	    	perS.update(req);
	    } catch (Exception e) {
	        status = HttpStatus.BAD_REQUEST;
		    response = e.getMessage();	    	
	        
	    }
	    return ResponseEntity.status(status).body(response);	    	

	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> delete(@PathVariable (required = true) Integer id) {
	    HttpStatus status = HttpStatus.OK;
	    String response="persona cancellata";
	    try {
	    	perS.delete(id);
	    } catch (Exception e) {
	        status = HttpStatus.BAD_REQUEST;
		    response = e.getMessage();	    	        
	    }
	    return ResponseEntity.status(status).body(response);	    	

	}

	
	
	@GetMapping("/list")
	public ResponseEntity<Object> list(){	
		Object response = null; 
		HttpStatus status = HttpStatus.OK;
		try {
			response = perS.list();
		} catch (Exception e) {
			response = e.getMessage();
			status = HttpStatus.BAD_REQUEST;
		}
		return ResponseEntity.status(status).body(response);
	}

	@GetMapping("/getById")
	public ResponseEntity<Object> getById(@RequestParam (required = true) Integer id){	
		Object response = null; 
		HttpStatus status = HttpStatus.OK;
		try {
			response = perS.getById(id);
		} catch (Exception e) {
			response = e.getMessage();
			status = HttpStatus.BAD_REQUEST;
		}
		return ResponseEntity.status(status).body(response);
	}
}
