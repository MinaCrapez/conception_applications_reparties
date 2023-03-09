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
import com.tp2.gestionEtudiant.service.EtudiantService;
import com.tp2.gestionEtudiant.service.FeuilleDePresenceService;
import com.tp2.gestionEtudiant.service.LigneService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class FicheDePresenceController {
	
	@Autowired
	private EtudiantService es;
	
	@Autowired
	private FeuilleDePresenceService fps;
	
	@Autowired
	private LigneService ls;
	
	@PostMapping("/creationFeuillePresence")
	public String creationFeuillePresence(HttpServletRequest request, Model model) {
		// recuperation de l'étudiant
		String mailEtudiant = request.getParameter("mailEtudiant");
		String mdpEtudiant = request.getParameter("mdpEtudiant");
		Etudiant etudiant = es.findByEmailAndMdp(mailEtudiant, mdpEtudiant);
		
		//recuperation du mois et de l'annee
		Date aujourdhui = new Date();
		SimpleDateFormat formater = new SimpleDateFormat("MM-yy");
		String date = formater.format(aujourdhui);
				
		fps.creer(etudiant,date);
		
		// passage des nouveaux attributs à la jsp
		model.addAttribute("etudiant",etudiant);
		
		return "pageAccueil";
	}

	
	@PostMapping("/suppressionFichePresence")
	public String suppressionFichePresence(HttpServletRequest request, Model model) {
		// recuperation de la feuille de presence
		String idFeuille = request.getParameter("idFeuille");
		long id = Long.parseLong(idFeuille);
		
		//suppression de la feuille de presence
		fps.deleteById(id);
		
		// recuperation de l'étudiant
		String mailEtudiant = request.getParameter("mailEtudiant");
		String mdpEtudiant = request.getParameter("mdpEtudiant");

		Etudiant etudiant = es.findByEmailAndMdp(mailEtudiant, mdpEtudiant);
		
		// passage des nouveaux attributs à la jsp
		model.addAttribute("etudiant",etudiant);
		
		return "pageAccueil";
	}
	
	@PostMapping("/affichageFichePresence")
	public String affichageFichePresence(HttpServletRequest request, Model model) {
		// recuperation de la feuille de presence
		String idFeuille = request.getParameter("idFeuille");
		long id = Long.parseLong(idFeuille);
		FeuilleDePresence ficheDePresence = fps.findById(id);
		
		// passage des nouveaux attributs à la jsp
		model.addAttribute("ficheDePresence",ficheDePresence);
		model.addAttribute("etudiant",ficheDePresence.getEtudiant());
		
		return "ficheDePresence";
	}

}