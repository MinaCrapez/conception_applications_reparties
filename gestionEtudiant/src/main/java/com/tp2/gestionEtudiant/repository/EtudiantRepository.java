package com.tp2.gestionEtudiant.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.tp2.gestionEtudiant.model.Etudiant;

public interface EtudiantRepository extends CrudRepository<Etudiant, Long> {
	
	List<Etudiant> findByNom (String nom);
	
	Etudiant findByEmailAndPassword(String email, String password);
	
	
	
	
	//..... on peut en faire d'autres


}