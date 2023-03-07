package com.tp2.gestionEtudiant.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import com.tp2.gestionEtudiant.model.Etudiant;
import com.tp2.gestionEtudiant.model.FeuilleDePresence;
import com.tp2.gestionEtudiant.model.Ligne;
import com.tp2.gestionEtudiant.service.FeuilleDePresenceService;
import com.tp2.gestionEtudiant.service.LigneService;
import com.tp2.gestionEtudiant.service.EtudiantService;

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
		//recuperation de l'étudiant
		String mailEtudiant = request.getParameter("mailEtudiant");
		String mdpEtudiant = request.getParameter("mdpEtudiant");
		Etudiant etudiant = es.getEtudiantRepository().findByEmailAndMdp(mailEtudiant, mdpEtudiant);

        // recuperation de la fiche de presence
		long idFiche = Long.parseLong(request.getParameter("idFiche"));
		FeuilleDePresence ficheDePresence = fps.getFeuilleDePresenceRepository().findById(idFiche);

        // recuperation de l'heure
        Date aujourdhui = new Date();
		SimpleDateFormat formater = new SimpleDateFormat("HH:mm:ss");
    	String heure = formater.format(aujourdhui);

		// creation de la ligne
        Ligne ligne = new Ligne();
        ligne.setFeuilleDePresence(idFiche);
        ligne.setHeure(heure);
		String matiere = request.getParameter("matiere");
        ligne.setMatiere(matiere);

		//enregistrement en BDD de la ligne 
		ls.getLigneRepository().save(ligne);
		
		// passage des nouveaux attributs à la jsp
		List<Ligne> lignes;
		lignes = ls.getLigneRepository().findByFeuilleDePresence(idFiche);
        model.addattribute("lignes",lignes);
		model.addAttribute("ficheDePresence",ficheDePresence);

        return "ficheDePresence";
    }  
    
    @PostMapping("/suppressionLigne")
	public String suppressionFichePresence(HttpServletRequest request, Model model) {
		//recuperation de l'étudiant
		String mailEtudiant = request.getParameter("mailEtudiant");
		String mdpEtudiant = request.getParameter("mdpEtudiant");
		Etudiant etudiant = es.getEtudiantRepository().findByEmailAndMdp(mailEtudiant, mdpEtudiant);

		// recuperation de la fiche de presence
		long idFeuille = Long.parseLong(request.getParameter("idFeuille"));
		FeuilleDePresence ficheDePresence = fps.getFeuilleDePresenceRepository().findById(idFeuille);
		
        //recuperation de la ligne
        long idLigne = Long.parseLong(request.getParameter("idLigne"));
	
		//suppression de la ligne
		ls.getLigneRepository().deleteById(idLigne);
		
		// passage des nouveaux attributs à la jsp
        List<Ligne> lignes;
		lignes = ls.getLigneRepository().findByFeuilleDePresence(idFeuille);
		model.addattribute("lignes",lignes);
		model.addAttribute("ficheDePresence",ficheDePresence);

        return "ficheDePresence";
	}
}
