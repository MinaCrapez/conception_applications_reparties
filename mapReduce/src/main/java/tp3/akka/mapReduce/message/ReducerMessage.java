package tp3.akka.mapReduce.message;

import java.util.HashMap;
import java.util.Map;

public class ReducerMessage {
	private String mot;
	private Map<String, Integer> compteur;
	
	public ReducerMessage(String mot) {
		this.mot = mot;
		this.compteur = new HashMap<>();
	}

	public String getMot() {
		return mot;
	}

	public void setMot(String mot) {
		this.mot = mot;
	}

	public Map<String, Integer> getCompteur() {
		return compteur;
	}

	public void setCompteur(Map<String, Integer> compteur) {
		this.compteur = compteur;
	}
	
}
