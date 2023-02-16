package com.tp2.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.tp2.model.Etudiant;


public interface EtudiantRepository extends CrudRepository<Etudiant, Long> {
	
	List<Etudiant> findByNom (String nom);
	
	
	//..... on peut en faire d'autres

}
