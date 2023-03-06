package com.tp2.gestionEtudiant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import com.tp2.gestionEtudiant.model.Etudiant;
import com.tp2.gestionEtudiant.service.EtudiantService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class CreationCompteController {
    
    @Autowired
	private EtudiantService es;
    
    @PostMapping("/creationCompte")
    public String creationDuCompte(Model model) {
        return "creationCompte";
    }
    
    @PostMapping("/enregistrerCompte")
    public String enregistreCompte(HttpServletRequest request, Model model) {
        // creation d'un etudiant
        Etudiant etudiant = new Etudiant();
        etudiant.setNom(request.getParameter("nom"));
        etudiant.setPrenom(request.getParameter("prenom"));
        etudiant.setEmail(request.getParameter("email"));
        etudiant.setMdp(request.getParameter("mdp"));
         //enregistrement de l'etudiant en BDD s'il n'existe pas deja
        if (es.getEtudiantRepository().findByEmail(request.getParameter("email")) == null) {
            es.getEtudiantRepository().save(etudiant); 
            return "redirect:/authentification"; //On redirige vers la page de connexion
        }
        else {
            model.addAttribute("error","l'adresse email est déjà associée à un compte existant");
            return "creationCompte";
        }
    }

}