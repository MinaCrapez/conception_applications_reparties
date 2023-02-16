package com.tp2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tp2.repository.EtudiantRepository;


@Controller
public class AuthentificationController {
	
	@Autowired
	private EtudiantRepository Er;
	
	@RequestMapping("/authentification")
	public String bonjourTest(Model model) {
		model.addAttribute("nom","Marley");
		model.addAttribute("prenom","Bob");
		return "authentification";
	}

}
