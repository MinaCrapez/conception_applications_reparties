package main.java.actor;

import java.util.Map;

import akka.actor.UntypedActor;
import main.java.message.Mapper;
import main.java.message.Reducer;

public class ReducerActor  extends UntypedActor {
	// mettre en var de classe compteur
	@Override
	public void onReceive(Object message) throws Exception {
		Reducer reducer = (Reducer) message;
		String mot = reducer.getMot();
		Map<String, Integer> compteur = reducer.getCompteur();
		if (compteur.containsKey(mot)) { // si le mot est déjà dans le compteur, on incrémente le compteur
			int interation = compteur.get(mot) + 1;
			compteur.remove(mot);
			compteur.put(mot, interation);
		}
		else { // sinon, on l'initialise
			compteur.put(mot, 1);
		}
	}

}
