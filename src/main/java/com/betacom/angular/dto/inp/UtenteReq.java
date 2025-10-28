package com.betacom.angular.dto.inp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UtenteReq {
	private Integer id;
	private String username;
	private String pwd;
	private String role;

}
