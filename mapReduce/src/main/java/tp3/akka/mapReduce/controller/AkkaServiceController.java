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
	
	private AkkaService akkaService;
	
	@RequestMapping("/accueilMapReduce")
	public String accueil(Model model) {
		return "AccueilApp";
	}
	
	@PostMapping("creationDySysteme") 
	public String creationDuCompte(HttpServletRequest request, Model model) {
		akkaService = AkkaService.getAkkaService();
		// on créé l'architecture
		akkaService.create();
		return "AccueilApp";
	}
	
	@PostMapping("analyseFichier") 
	public String analyseFichier(HttpServletRequest request, Model model) throws IOException {
		String fichier = request.getParameter("fichier");
		//on analyse le fichier
		akkaService.analyse(fichier);
		return "AccueilApp";
	}
	
	@PostMapping("comptageDuMot")
	public String compterLeMot(HttpServletRequest request, Model model){
		//String fichier = request.getParameter("fichier");
		String mot = request.getParameter("mot");
		int occurence;
		//Thread.sleep(1000);		// on cherche le nombre d'itération du mot 
		occurence = akkaService.compteMot(mot);
		model.addAttribute("occurence", occurence);
		model.addAttribute("mot", mot);
		return "AccueilApp";
	}

}
