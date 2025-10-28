package com.betacom.angular.services.interfaces;

import java.util.List;

import com.betacom.angular.dto.inp.UtenteReq;
import com.betacom.angular.dto.out.LoginDTO;
import com.betacom.angular.dto.out.UtenteDTO;
import com.betacom.angular.models.Role;

public interface UtenteServices {

	void create(UtenteReq req) throws Exception;
	void update(UtenteReq req) throws Exception;
	void delete(Integer id) throws Exception;
	
	LoginDTO singUp(UtenteReq req) throws Exception;
	
	List<UtenteDTO> list(String userName, String role) throws Exception;
	UtenteDTO getById(Integer id) throws Exception;
}
