package com.tp2.gestionEtudiant.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;

import com.tp2.gestionEtudiant.model.Etudiant;
import com.tp2.gestionEtudiant.model.FeuilleDePresence;
import com.tp2.gestionEtudiant.model.Ligne;
import com.tp2.gestionEtudiant.service.EtudiantService;
import com.tp2.gestionEtudiant.service.FeuilleDePresenceService;
import com.tp2.gestionEtudiant.service.LigneService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class LigneController {

    @Autowired
	private LigneService ls;

    @Autowired
	private FeuilleDePresenceService fps;
    
    @Autowired
	private EtudiantService es;

    @PostMapping("creationLigne")
    public String creationLigne(HttpServletRequest request, Model model) {
        // recuperation de la fiche de presence
		long idFiche = Long.parseLong(request.getParameter("idFiche"));
		FeuilleDePresence ficheDePresence = fps.findById(idFiche);

        // recuperation des autres donnees
		 String matiere = request.getParameter("matiere");
		 String heureDebut = request.getParameter("heureDebut");
		 String heureFin = request.getParameter("heureFin");
		 String jour = request.getParameter("jour");

		ls.creer(matiere, ficheDePresence, heureDebut, heureFin, jour);
		
		// passage des nouveaux attributs à la jsp
		model.addAttribute("ficheDePresence",ficheDePresence);

        return "ficheDePresence";
    }  
    
    @PostMapping("/suppressionLigne")
	public String suppressionFichePresence(HttpServletRequest request, Model model) {
    
        //recuperation de la ligne
        long idLigne = Long.parseLong(request.getParameter("idLigne"));

		//suppression de la ligne
		ls.deleteById(idLigne);
		
		// recuperation de la fiche de presence
		long idFeuille = Long.parseLong(request.getParameter("idFeuille"));
		FeuilleDePresence ficheDePresence = fps.findById(idFeuille);
		
		// passage des nouveaux attributs à la jsp
        List<Ligne> lignes;
		lignes = ls.findByFeuilleDePresence(ficheDePresence);
		model.addAttribute("lignes",lignes);
		model.addAttribute("ficheDePresence",ficheDePresence);

        return "ficheDePresence";
	}
    