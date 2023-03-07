package com.tp2.gestionEtudiant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tp2.gestionEtudiant.repository.LigneRepository;

@Service
public class LigneService {
    
    @Autowired
    private LigneRepository lr;
    
    public LigneRepository getLigneRepository () { 
        return lr;
    }
}