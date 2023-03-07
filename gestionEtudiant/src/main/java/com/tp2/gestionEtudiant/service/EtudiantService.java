package com.tp2.gestionEtudiant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tp2.gestionEtudiant.repository.EtudiantRepository;

@Service
public class EtudiantService {
    
    @Autowired
    private EtudiantRepository er;
    
    public EtudiantRepository getEtudiantRepository () { 
        return er;
    }

}