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

import com.betacom.angular.dto.inp.UtenteReq;
import com.betacom.angular.dto.out.LoginDTO;
import com.betacom.angular.models.Role;
import com.betacom.angular.services.interfaces.UtenteServices;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/utente")
public class UtenteController {

	private UtenteServices utenteS;

	public UtenteController(UtenteServices utenteS) {
		this.utenteS = utenteS;
	}
	
	@PostMapping("/create")
	public ResponseEntity<Object> create(@RequestBody(required = true) UtenteReq req) {
	    String response = "created";
	    HttpStatus status = HttpStatus.OK;
	    try {
	    	utenteS.create(req);
	    } catch (Exception e) {
	        response = e.getMessage();
	        status = HttpStatus.BAD_REQUEST;
	    }

	    return ResponseEntity.status(status).body(response);
	}

	@PutMapping("/update")
	public ResponseEntity<Object> update(@RequestBody(required = true) UtenteReq req) {
	    String response = "updated";
	    HttpStatus status = HttpStatus.OK;
	    try {
	    	utenteS.update(req);
	    } catch (Exception e) {
	        response = e.getMessage();
	        status = HttpStatus.BAD_REQUEST;
	    }
	    return ResponseEntity.status(status).body(response);
	}

	
	@PostMapping("/login")
	public ResponseEntity<Object> login(@RequestBody(required = true) UtenteReq req) {
	    HttpStatus status = HttpStatus.OK;
	    Object response = null;
	    try {
	    	response = utenteS.singUp(req);
	    } catch (Exception e) {
	        response = e.getMessage();
	        status = HttpStatus.BAD_REQUEST;
	    }

	    return ResponseEntity.status(status).body(response);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> delete(@PathVariable (required = true) Integer id) {
	    HttpStatus status = HttpStatus.OK;
	    String response="cancelled";
	    try {
	    	utenteS.delete(id);
	    } catch (Exception e) {
	        status = HttpStatus.BAD_REQUEST;
		    response = e.getMessage();	    	        
	    }
	    return ResponseEntity.status(status).body(response);	    	

	}
	
	
	@GetMapping("/list")
	public ResponseEntity<Object> list(
			@RequestParam (required = false) String userName,
			@RequestParam (required = false) String role){	
		Object response = null; 
		HttpStatus status = HttpStatus.OK;
		try {
			response = utenteS.list(userName,role);
		} catch (Exception e) {
			response = e.getMessage();
			status = HttpStatus.BAD_REQUEST;
		}
		return ResponseEntity.status(status).body(response);
	}
	
	@GetMapping("/getById")
	public ResponseEntity<Object> getById(
			@RequestParam (required = true) Integer id) {
		
		Object response = null; 
		HttpStatus status = HttpStatus.OK;
		try {
			response = utenteS.getById(id);
		} catch (Exception e) {
			response = e.getMessage();
			status = HttpStatus.BAD_REQUEST;
		}
		return ResponseEntity.status(status).body(response);
	}
}
