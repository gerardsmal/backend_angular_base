package com.betacom.angular.requests;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class PersoneReq {

	private Integer id;
	private String nome;
	private String cognome;
	private String email;
	private String colore;
}
