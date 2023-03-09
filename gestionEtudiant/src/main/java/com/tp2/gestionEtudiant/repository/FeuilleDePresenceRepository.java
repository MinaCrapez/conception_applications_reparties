package com.tp2.gestionEtudiant.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.tp2.gestionEtudiant.model.FeuilleDePresence;
import com.tp2.gestionEtudiant.model.Etudiant;



public interface FeuilleDePresenceRepository  extends CrudRepository<FeuilleDePresence, Long> {
    
    public List<FeuilleDePresence> findByMailEtudiant (Etudiant etudiant);

    public FeuilleDePresence findById(long id);

}