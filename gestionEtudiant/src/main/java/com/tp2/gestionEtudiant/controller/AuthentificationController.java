package com.tp2.gestionEtudiant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tp2.gestionEtudiant.service.EtudiantService;

@Controller
public class AuthentificationController {
	
	
	@Autowired
	private EtudiantService es;
	
	@RequestMapping("/authentification")
	public String authen(Model model) {
		return "authentification";
	}

}
