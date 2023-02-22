package com.tp2.gestionEtudiant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tp2.gestionEtudiant.model.Etudiant;
import com.tp2.gestionEtudiant.repository.EtudiantRepository;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class CreationCompteController {
    
    @Autowired
    private EtudiantRepository er;
    
    @RequestMapping("/creationCompte")
    public String creationDuCompte(Model model) {
        return "creationCompte";
    }
    
    @RequestMapping("/enregistrerCompte")
    public String enregistreCompte(HttpServletRequest request, Model model) {
        // creation d'un etudiant
        Etudiant etudiant = new Etudiant();
        etudiant.setNom(request.getParameter("nom"));
        etudiant.setPrenom(request.getParameter("prenom"));
        etudiant.setEmail(request.getParameter("email"));
        etudiant.setMdp(request.getParameter("mdp"));
         //enregistrement de l'etudiant en BDD
        er.save(etudiant); 
        return "redirect:/authentification"; //On redirige vers la page de connexion
    }

}