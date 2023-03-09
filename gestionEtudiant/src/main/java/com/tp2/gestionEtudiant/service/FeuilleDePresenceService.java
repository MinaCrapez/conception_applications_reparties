package com.tp2.gestionEtudiant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tp2.gestionEtudiant.model.FeuilleDePresence;
import com.tp2.gestionEtudiant.repository.FeuilleDePresenceRepository;

@Service
public class FeuilleDePresenceService {
    
    @Autowired
    private FeuilleDePresenceRepository fpr;
    
    public void creer (String email, String moisAnnee) {
        // creation feuille de presence
        FeuilleDePresence feuilleDePresence = new FeuilleDePresence();
        feuilleDePresence.setMailEtudiant(email);
        feuilleDePresence.setMoisAnnee(moisAnnee);
        //enregistrement en BDD de la feuille de présence 
        fpr.save(feuilleDePresence);
    }
    
    public List<FeuilleDePresence> findByMailEtudiant(String mail) {
        return fpr.findByMailEtudiant(mail);
    }
    
    public void deleteById(Long id) {
        fpr.deleteById(id);
    }
    
    public FeuilleDePresence findById(long id) {
        return fpr.findById(id);
    }
}