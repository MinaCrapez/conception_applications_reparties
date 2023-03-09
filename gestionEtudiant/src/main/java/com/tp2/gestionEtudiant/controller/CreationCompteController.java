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
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String email = request.getParameter("email");
        String mdp = request.getParameter("mdp");
        if (es.creer(nom, prenom, email, mdp)) { // si on a pu creer l'étudiant
            return "authentification"; //On redirige vers la page de connexion
        }
        else {
            model.addAttribute("error","l'adresse email est déjà associée à un compte existant");
            return "creationCompte";
        }
    }
}