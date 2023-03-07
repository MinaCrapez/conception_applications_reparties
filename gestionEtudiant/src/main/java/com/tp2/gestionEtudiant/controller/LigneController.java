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

        // recuperation des autres donnees
		String matiere = request.getParameter("matiere");
		String heureDebut = request.getParameter("heureDebut");
		String heureFin = request.getParameter("heureFin");
		String jour = request.getParameter("jour");

		// creation de la ligne
        Ligne ligne = new Ligne();
        ligne.setFeuilleDePresence(idFiche);
		ligne.setMatiere(matiere);
        ligne.setHeureDebut(heureDebut);
		ligne.setHeureFin(heureFin);
		ligne.setJour(jour);

		//enregistrement en BDD de la ligne 
		ls.getLigneRepository().save(ligne);
		
		// passage des nouveaux attributs à la jsp
		List<Ligne> lignes;
		lignes = ls.getLigneRepository().findByFeuilleDePresence(idFiche);
        model.addAttribute("lignes",lignes);
		model.addAttribute("etudiant",etudiant);
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
		model.addAttribute("lignes",lignes);
		model.addAttribute("etudiant",etudiant);
		model.addAttribute("ficheDePresence",ficheDePresence);

        return "ficheDePresence";
	}

	@PostMapping("/changementSignature")
    public String changementSignature(HttpServletRequest request, Model model) {
        // recuperer les nouvelles valeurs de signature
        String nouvSignatureEtudiant = request.getParameter("nouvSignatureEtudiant");
        String nouvSignatureProf = request.getParameter("nouvSignatureProf");
        
        //recuperation de la ligne
        // il faut passer l'attribut de la ligne egalement comme pour les signatures
        
        // setter les valeurs a la ligne
        
        return "ficheDePresence";
    }
}
