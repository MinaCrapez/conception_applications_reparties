package com.tp2.gestionEtudiant.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.tp2.gestionEtudiant.model.Ligne;
import com.tp2.gestionEtudiant.model.FeuilleDePresence;


public interface LigneRepository  extends CrudRepository<Ligne, Long> {
    
    public List<Ligne> findByFeuilleDePresence(FeuilleDePresence feuilleDePresence);

    public Ligne findById(long idLigne);

}