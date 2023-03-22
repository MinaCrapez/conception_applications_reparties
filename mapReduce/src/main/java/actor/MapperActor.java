package main.java.actor;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import main.java.message.Mapper;
import main.java.message.Reducer;

public class MapperActor extends UntypedActor {

	@Override
	public void onReceive(Object message) throws Exception {
		// prend le message (une ligne), split et envoie les mots aux acteurs reducer
		//tell pour envoyer un message a un acteur (donc ici à reducer correspondant)
		Mapper mapper = (Mapper) message;
		String ligne = mapper.getLigne();
		
		String[] words = ligne.split(" ");
		String moitieAlphabet = "abcdefghijklm";
		for (int i = 0; i < words.length; i++) {
			String premiereLettre = words[i].substring(0,1).toLowerCase();
			if (moitieAlphabet.contains(premiereLettre)) {
				mapper.getReducer1().tell(mapper.getReducer1(), ActorRef.noSender());
			}
			else {
				mapper.getReducer2().tell(mapper.getReducer2(), ActorRef.noSender());
			}
		}
	}
	
	

}
