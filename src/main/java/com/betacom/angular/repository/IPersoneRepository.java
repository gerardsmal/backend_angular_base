package com.betacom.angular.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betacom.angular.models.Persone;

public interface IPersoneRepository extends JpaRepository<Persone, Integer>{

}
