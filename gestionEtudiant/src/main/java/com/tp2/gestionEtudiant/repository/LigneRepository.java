package com.tp2.gestionEtudiant.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.tp2.gestionEtudiant.model.Etudiant;
import com.tp2.gestionEtudiant.model.Ligne;


public interface LigneRepository  extends CrudRepository<Ligne, Long> {
    
    public List<Ligne> findByFeuilleDePresence(long feuilleDePresence);

    public Ligne findById(long idLigne);

}