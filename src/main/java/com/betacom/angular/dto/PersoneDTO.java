package com.betacom.angular.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PersoneDTO {
	private Integer id;	
	private String nome;
	private String cognome;
	private String email;
	private String colore;
}
