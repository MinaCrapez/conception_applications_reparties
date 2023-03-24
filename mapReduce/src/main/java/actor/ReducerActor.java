package main.java.actor;

import java.util.Map;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import main.java.message.Mapper;
import main.java.message.Reducer;

public class ReducerActor  extends UntypedActor {
	
	private Map<String, Integer> compteur;
	
	@Override
	public void onReceive(Object message) throws Exception {
		if (message instanceof Reducer) {
			Reducer reducer = (Reducer) message;
			String mot = reducer.getMot();
			compteur = reducer.getCompteur();
			if (compteur.containsKey(mot)) { // si le mot est déjà dans le compteur, on incrémente le compteur
				int interation = compteur.get(mot) + 1;
				compteur.remove(mot);
				compteur.put(mot, interation);
			}
			else { // sinon, on l'initialise
				compteur.put(mot, 1);
			}
		}
		else if(message instanceof String) {
			this.getSender().tell(compteur, ActorRef.noSender());
		}
	}

}
