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
public class CreationCompteController {
	
	@Autowired
	private EtudiantRepository er;
	
	@RequestMapping("/creationCompte")
	public String creationDuCompte(Model model) {
		return "creationCompte";
	}
	
	@PostMapping("enregistrerCompte")
	public String enregistreCompte(HttpServletRequest request, Model model) {
		Etudiant etudiant = new Etudiant();
		etudiant.setNom(request.getParameter("nom"));
		//Faire prenom email mdp
		er.save(etudiant); //enregistrement de l'etudiant en BDD
		return "redirect:/authentication"; //On redirige vers la page de connexion
	}

}
