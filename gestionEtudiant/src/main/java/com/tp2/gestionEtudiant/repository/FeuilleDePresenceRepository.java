package com.tp2.gestionEtudiant.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.tp2.gestionEtudiant.model.Etudiant;
import com.tp2.gestionEtudiant.model.FeuilleDePresence;


public interface FeuilleDePresenceRepository  extends CrudRepository<FeuilleDePresence, Long> {
    
    public List<FeuilleDePresence> findByMailEtudiant (String mailEtudiant);

}