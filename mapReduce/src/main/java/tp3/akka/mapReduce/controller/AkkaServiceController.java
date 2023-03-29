package tp3.akka.mapReduce.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import tp3.akka.mapReduce.service.AkkaService;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AkkaServiceController {
	
	@RequestMapping("/accueilMapReduce")
	public String accueil(Model model) {
		return "AccueilApp";
	}
	
	@PostMapping("comptageDuMot")
	public String compterLeMot(HttpServletRequest request, Model model) throws IOException {
		String fichier = request.getParameter("fichier");
		String mot = request.getParameter("mot");
		int occurence;
		AkkaService akkaService = AkkaService.getAkkaService();
		// on créé l'architecture
		akkaService.create();
		//on analyse le fichier
		akkaService.analyse(fichier);
		// on cherche le nombre d'itération du mot 
		occurence = akkaService.compteMot(mot);
		model.addAttribute("occurence", occurence);
		model.addAttribute("mot", mot);
		return "AccueilApp";
	}

}
