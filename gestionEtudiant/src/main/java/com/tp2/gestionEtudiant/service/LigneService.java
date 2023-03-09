package com.tp2.gestionEtudiant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tp2.gestionEtudiant.model.FeuilleDePresence;
import com.tp2.gestionEtudiant.model.Ligne;
import com.tp2.gestionEtudiant.repository.LigneRepository;

@Service
public class LigneService {

    @Autowired
    private LigneRepository lr;

    public List<Ligne> findByFeuilleDePresence(FeuilleDePresence feuilleDePresence) {
        return lr.findByFeuilleDePresence(feuilleDePresence);
    }

    public void creer (String matiere, FeuilleDePresence feuilleDePresence, String heureDebut, String heureFin, String jour) {
        // creation de la ligne
        Ligne ligne = new Ligne();
        ligne.setMatiere(matiere);
        ligne.setFeuilleDePresence(feuilleDePresence);
        ligne.setHeureDebut(heureDebut);
        ligne.setHeureFin(heureFin);
        ligne.setJour(jour);

      //ajout de la ligne à la feuilles de presence
      List <Ligne> lignes = feuilleDePresence.getLignes();
      lignes.add(ligne);
      feuilleDePresence.setLignes(lignes);

        //enregistrement en BDD de la ligne 
        lr.save(ligne);
    }

    public void deleteById(Long id) {
        lr.deleteById(id);
    }

}