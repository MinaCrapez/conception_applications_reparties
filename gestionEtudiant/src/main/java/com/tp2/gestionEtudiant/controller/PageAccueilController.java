package com.tp2.gestionEtudiant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tp2.gestionEtudiant.repository.EtudiantRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
@Controller
public class PageAccueilController {
	
	@Autowired
	private EtudiantRepository er;
	
	@RequestMapping("/pageAccueil")
	public String accueil(Model model) {
		//Récupérer login pwd dans les paramètres de la requete
		String login = null;
		String password = null;
		
		Etudiant etudiant = er.findByEmailAndPassword(login, password);
		
		if(etudiant != null) {
			return "pageAccueil";
		}else { //Si pas d'utilisateur alors on redirige ves page de connexion
			return "redirect:/authentication";
		}
		
		
		
	}

}
