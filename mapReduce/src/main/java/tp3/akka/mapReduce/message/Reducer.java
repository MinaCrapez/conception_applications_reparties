package tp3.akka.mapReduce.message;

import java.util.HashMap;
import java.util.Map;

public class Reducer {
	public String mot;
	public Map<String, Integer> compteur;
	
	public Reducer(String mot) {
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
