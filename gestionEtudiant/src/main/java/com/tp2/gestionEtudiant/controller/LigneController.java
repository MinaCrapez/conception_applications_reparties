package com.tp2.gestionEtudiant.controller;

import com.tp2.gestionEtudiant.model.FeuilleDePresence;
import com.tp2.gestionEtudiant.model.Ligne;
import com.tp2.gestionEtudiant.service.FeuilleDePresenceService;
import com.tp2.gestionEtudiant.service.LigneService;

@Controller
public class LigneController {

    @Autowired
	private LigneService ls;

    @Autowired
	private FeuilleDePresenceService fps;

    @PostMapping("creationLigne")
    public String creationLigne(HttpServletRequest request, Model model) {
        String matiere = request.getParameter("matiere");
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
        ligne.setMatiere(matiere);

		//enregistrement en BDD de la ligne 
		fps.getLigneRepository().save(ligne);
		
		// passage des nouveaux attributs à la jsp
		List<Ligne> lignes;
		lignes = ls.getLigneRepository().findByFeuilleDePresence(idFiche);
        ficheDePresence.setLigne(lignes);
		model.addAttribute("ficheDePresence",ficheDePresence);

        return "ficheDePresence";
    }  
    
    @PostMapping("/suppressionLigne")
	public String suppressionFichePresence(HttpServletRequest request, Model model) {
		// recuperation de la fiche de presence
		long idFeuille = Long.parseLong(request.getParameter("idFeuille"));
		FeuilleDePresence ficheDePresence = fps.getFeuilleDePresenceRepository().findById(idFeuille);
		
        //recuperation de la ligne
        long idLigne = Long.parseLong(request.getParameter("idLigne"));
		//Ligne Ligne = ls.getLigneRepository().findById(idLigne);

		//suppression de la feuille de presence
		ls.getLigneRepository().deleteById(idLigne);
		
		// passage des nouveaux attributs à la jsp
        List<Ligne> lignes;
		lignes = ls.getLigneRepository().findByFeuilleDePresence(idFeuille);
        ficheDePresence.setLigne(lignes);
		model.addAttribute("ficheDePresence",ficheDePresence);

        return "ficheDePresence";
	}
}
