package com.tp2.gestionEtudiant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tp2.gestionEtudiant.model.Etudiant;
import com.tp2.gestionEtudiant.model.FeuilleDePresence;
import com.tp2.gestionEtudiant.repository.FeuilleDePresenceRepository;

@Service
public class FeuilleDePresenceService {

    @Autowired
    private FeuilleDePresenceRepository fpr;

    public void creer (Etudiant etudiant, String moisAnnee) {
        // creation feuille de presence
        FeuilleDePresence feuilleDePresence = new FeuilleDePresence();
        feuilleDePresence.setEtudiant(etudiant);
        feuilleDePresence.setMoisAnnee(moisAnnee);

        //ajout à l'étudiant de la nouvelle feuilles de presence
        List <FeuilleDePresence> feuilles = etudiant.getFeuilles();
        feuilles.add(feuilleDePresence);
        etudiant.setFeuilles(feuilles);

        //enregistrement en BDD de la feuille de présence 
        fpr.save(feuilleDePresence);

    }


     public List<FeuilleDePresence> findByEtudiant(Etudiant etudiant) { 
         return fpr.findByEtudiant(etudiant); 
     }


    public void deleteById(Long id) {
        fpr.deleteById(id);
    }

    public FeuilleDePresence findById(long id) {
        return fpr.findById(id);
    }

}