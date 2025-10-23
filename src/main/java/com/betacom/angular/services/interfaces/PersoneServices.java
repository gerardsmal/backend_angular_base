package com.betacom.angular.services.interfaces;

import java.util.List;

import com.betacom.angular.dto.PersoneDTO;
import com.betacom.angular.requests.PersoneReq;

public interface PersoneServices {
	void create (PersoneReq req) throws Exception;
	void update (PersoneReq req) throws Exception;
	void delete (Integer id) throws Exception;
	
	List<PersoneDTO> list() throws Exception;
	PersoneDTO getById(Integer id) throws Exception;
}
