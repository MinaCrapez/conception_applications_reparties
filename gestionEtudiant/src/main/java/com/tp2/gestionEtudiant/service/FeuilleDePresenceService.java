package com.tp2.gestionEtudiant.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.tp2.gestionEtudiant.repository.FeuilleDePresenceRepository;


public class FeuilleDePresenceService {
    
    @Autowired
    private FeuilleDePresenceRepository fpr;
    
    public FeuilleDePresenceRepository getFeuilleDePresenceRepository () { 
        return fpr;
    }

}