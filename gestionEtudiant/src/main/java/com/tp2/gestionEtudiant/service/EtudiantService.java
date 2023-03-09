package com.tp2.gestionEtudiant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tp2.gestionEtudiant.model.Etudiant;
import com.tp2.gestionEtudiant.repository.EtudiantRepository;

@Service
public class EtudiantService {
    
    @Autowired
    private EtudiantRepository er;
    
    public boolean creer(String nom, String prenom, String mail, String mdp) {
        //creation d'un etudiant 
        Etudiant etudiant = new Etudiant();
        etudiant.setNom(nom);    
        etudiant.setPrenom(prenom);
        etudiant.setEmail(mail);
        etudiant.setMdp(mdp);
        if (er.findByEmail(mail) == null) { //enregistrement de l'etudiant en BDD s'il n'existe pas deja
            er.save(etudiant);
            return true;
        }
        return false;
    }
    
    public Etudiant findByEmailAndMdp(String login, String password) {
        return er.findByEmailAndMdp(login, password);
    }

}