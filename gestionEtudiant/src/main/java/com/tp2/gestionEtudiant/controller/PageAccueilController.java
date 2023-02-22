package com.tp2.gestionEtudiant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tp2.gestionEtudiant.model.Etudiant;
import com.tp2.gestionEtudiant.repository.EtudiantRepository;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class PageAccueilController {
    
    @Autowired
    private EtudiantRepository er;
    
    @RequestMapping("connexionCompte")
    public String connexionCompte(HttpServletRequest request, Model model) {
//Récupérer login pwd dans les paramètres de la requete
        String login = request.getParameter("email");
        String password = request.getParameter("mdp");
        
        Etudiant etudiant = er.findByEmailAndMdp(login, password);
        
        if(etudiant != null) {
            return "pageAccueil";
        }else { //Si pas d'utilisateur alors on redirige ves page de connexion
            return "redirect:/authentification";
        }    
    } 

}