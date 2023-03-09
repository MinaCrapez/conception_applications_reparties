package com.tp2.gestionEtudiant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tp2.gestionEtudiant.model.Ligne;

import com.tp2.gestionEtudiant.repository.LigneRepository;

@Service
public class LigneService {
    
    @Autowired
    private LigneRepository lr;
    
    public List<Ligne> findByFeuilleDePresence(long id) {
        return lr.findByFeuilleDePresence(id);
    }
    
    public void creer (String matiere, long idFiche, String heureDebut, String heureFin, String jour) {
        // creation de la ligne
        Ligne ligne = new Ligne();
        ligne.setMatiere(matiere);
        ligne.setFeuilleDePresence(idFiche);
        ligne.setHeureDebut(heureDebut);
        ligne.setHeureFin(heureFin);
        ligne.setJour(jour);
    
        //enregistrement en BDD de la ligne 
        lr.save(ligne);
    }
    
    public void deleteById(Long id) {
        lr.deleteById(id);
    }
}