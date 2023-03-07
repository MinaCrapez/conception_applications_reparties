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
import com.tp2.gestionEtudiant.service.EtudiantService;
import com.tp2.gestionEtudiant.service.FeuilleDePresenceService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class FicheDePresenceController {
	
	@Autowired
	private EtudiantService es;
	
	@Autowired
	private FeuilleDePresenceService fps;
	
	@PostMapping("/creationFeuillePresence")
	public String creationFeuillePresence(HttpServletRequest request, Model model) {
		// recuperation de l'étudiant
		String mailEtudiant = request.getParameter("mailEtudiant");
		String mdpEtudiant = request.getParameter("mdpEtudiant");
		Etudiant etudiant = es.getEtudiantRepository().findByEmailAndMdp(mailEtudiant, mdpEtudiant);
		
		// recuperation de la date
		Date aujourdhui = new Date();
		SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yy");
    	String date = formater.format(aujourdhui);

		// creation feuille de presence
		FeuilleDePresence feuilleDePresence = new FeuilleDePresence();
		feuilleDePresence.setMailEtudiant(mailEtudiant);
		feuilleDePresence.setDate(date);

		//enregistrement en BDD de la feuille de présence 
		fps.getFeuilleDePresenceRepository().save(feuilleDePresence);
		
		// passage des nouveaux attributs à la jsp
		List<FeuilleDePresence> feuillesPres;
		feuillesPres = fps.getFeuilleDePresenceRepository().findByMailEtudiant(mailEtudiant);
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

		Etudiant etudiant = es.getEtudiantRepository().findByEmailAndMdp(mailEtudiant, mdpEtudiant);
		
		// recuperation de la feuille de presence
		long id = Long.parseLong(idFeuille);
		//Optional<FeuilleDePresence> feuille = fps.getFeuilleDePresenceRepository().findById(id);
		
		//suppression de la feuille de presence
		fps.getFeuilleDePresenceRepository().deleteById(id);
		
		// passage des nouveaux attributs à la jsp
		List<FeuilleDePresence> feuillesPres;
		feuillesPres = fps.getFeuilleDePresenceRepository().findByMailEtudiant(mailEtudiant);
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
		
		Etudiant etudiant = es.getEtudiantRepository().findByEmailAndMdp(mailEtudiant, mdpEtudiant);
		
		// recuperation de la feuille de presence
		long id = Long.parseLong(idFeuille);
		Optional<FeuilleDePresence> ficheDePresence = fps.getFeuilleDePresenceRepository().findById(id);
		
		// passage des nouveaux attributs à la jsp
		model.addAttribute("ficheDePresence",ficheDePresence);
		model.addAttribute("etudiant",etudiant);
		
		return "ficheDePresence";
	}

}