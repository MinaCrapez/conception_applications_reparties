package com.tp2.gestionEtudiant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import com.tp2.gestionEtudiant.model.Etudiant;
import com.tp2.gestionEtudiant.service.EtudiantService;
import com.tp2.gestionEtudiant.model.FeuilleDePresence;
import com.tp2.gestionEtudiant.service.FeuilleDePresenceService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class PageAccueilController {
    
    @Autowired
    private EtudiantService es;

    @Autowired
    private FeuilleDePresenceService fps;
    
    @PostMapping("/connexionCompte")
    public String connexionCompte(HttpServletRequest request, Model model) {
        // recuperation du login et mdp
        String login = request.getParameter("email");
        String password = request.getParameter("mdp");
        
        // on verifie qu'un etudiant avec ces identifiants existe
        Etudiant etudiant = es.getEtudiantRepository().findByEmailAndMdp(login, password);
        
        if(etudiant != null) { // s'il existe, on envoie vers la page d'accueil
            model.addAttribute("etudiant", etudiant);
            //on recuperer les feuilles de presence associées
            List<FeuilleDePresence> feuillesPres;
            feuillesPres = fps.getFeuilleDePresenceRepository().findByMailEtudiant(login);
            model.addAttribute("feuillesPres", feuillesPres);
            return "pageAccueil";
        }
        else { //Si pas d'utilisateur alors on redirige ves page de connexion avec une erreur
            model.addAttribute("error", "L'identifiant ou le mot de passe n'est pas correct");
            return "authentification";
        }    
    } 

}