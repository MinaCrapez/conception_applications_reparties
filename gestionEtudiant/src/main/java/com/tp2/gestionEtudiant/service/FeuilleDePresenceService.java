package com.tp2.gestionEtudiant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tp2.gestionEtudiant.repository.FeuilleDePresenceRepository;

@Service
public class FeuilleDePresenceService {
    
    @Autowired
    private FeuilleDePresenceRepository fpr;
    
    public FeuilleDePresenceRepository getFeuilleDePresenceRepository () { 
        return fpr;
    }

}