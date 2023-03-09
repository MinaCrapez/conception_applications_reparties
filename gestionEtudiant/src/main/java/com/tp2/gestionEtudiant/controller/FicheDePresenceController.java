package com.tp2.gestionEtudiant.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
				
		fps.creer(mailEtudiant,date);
		
		// passage des nouveaux attributs à la jsp
		List<FeuilleDePresence> feuillesPres;
		feuillesPres = fps.findByMailEtudiant(mailEtudiant);
		model.addAttribute("feuillesPres",feuillesPres);
		model.addAttribute("etudiant",etudiant);
		
		return "pageAccueil";
	}

	
	@PostMapping("/suppressionFichePresence")
	public String suppressionFichePresence(HttpServletRequest request, Model model) {
		// recuperation de l'étudiant
		String idFeuille = request.getParameter("idFeuille");
		String mailEtudiant = request.getParameter("mailEtudiant");
		String mdpEtudiant = request.getParameter("mdpEtudiant");

		Etudiant etudiant = es.findByEmailAndMdp(mailEtudiant, mdpEtudiant);
		
		// recuperation de la feuille de presence
		long id = Long.parseLong(idFeuille);
		
		//suppression de la feuille de presence
		fps.deleteById(id);
		
		// passage des nouveaux attributs à la jsp
		List<FeuilleDePresence> feuillesPres;
		feuillesPres = fps.findByMailEtudiant(mailEtudiant);
		model.addAttribute("feuillesPres",feuillesPres);
		model.addAttribute("etudiant",etudiant);
		
		return "pageAccueil";
	}
	
	@PostMapping("/affichageFichePresence")
	public String affichageFichePresence(HttpServletRequest request, Model model) {
		// recuperation de l'étudiant
		String idFeuille = request.getParameter("idFeuille");
		String mailEtudiant = request.getParameter("mailEtudiant");
		String mdpEtudiant = request.getParameter("mdpEtudiant");
		
		Etudiant etudiant = es.findByEmailAndMdp(mailEtudiant, mdpEtudiant);
		
		// recuperation de la feuille de presence
		long id = Long.parseLong(idFeuille);
		FeuilleDePresence ficheDePresence = fps.findById(id);
		
		// recuperation de mes lignes
		List<Ligne> lignes = ls.findByFeuilleDePresence(id);
		
		// passage des nouveaux attributs à la jsp
		model.addAttribute("ficheDePresence",ficheDePresence);
		model.addAttribute("lignes",lignes);
		model.addAttribute("etudiant",etudiant);
		
		return "ficheDePresence";
	}

}