package com.betacom.angular.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.betacom.angular.models.Role;
import com.betacom.angular.models.Utente;

public interface IUtenteRepository extends JpaRepository<Utente, Integer>{
	
	boolean existsByUsername(String username);
	Optional<Utente> findByUsername(String username); 
	
	@Query(name="utente.searchByFilter")
	List<Utente> searchByFilter(
			@Param("username") String userName,
			@Param("role") Role role
			);
}
