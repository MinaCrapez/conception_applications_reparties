package com.tp2.gestionEtudiant.repository;

import org.springframework.data.repository.CrudRepository;

import com.tp2.gestionEtudiant.model.Etudiant;

public interface EtudiantRepository extends CrudRepository<Etudiant, Long> {
	
	Etudiant findByEmailAndMdp(String email, String mdp);

}