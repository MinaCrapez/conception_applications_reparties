package tp3.akka.mapReduce.actor;

import java.util.HashMap;
import java.util.Map;

import akka.actor.ActorRef;
import akka.actor.RepointableActorRef;
import akka.actor.UntypedActor;
import tp3.akka.mapReduce.message.ReducerMessage;

public class ReducerActor  extends UntypedActor {
	
	private Map<String, Integer> compteur = new HashMap<>();
	
	@Override
	public void onReceive(Object message) throws Exception {
		if (message instanceof ReducerMessage) {
			ReducerMessage reducerMessage = (ReducerMessage) message;
			String mot = reducerMessage.getMot();
			int count = compteur.getOrDefault(mot, 0);
			compteur.put(mot, count + 1);
		}
		else if(message instanceof String) {
			String mot = (String) message;
			int count = compteur.getOrDefault(mot, 0);
			this.getSender().tell(count, ActorRef.noSender());
		}
		else {
			return;
		}
	}

}
