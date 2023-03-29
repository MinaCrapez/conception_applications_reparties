package tp3.akka.mapReduce.actor;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import tp3.akka.mapReduce.message.Mapper;

public class MapperActor extends UntypedActor {

	@Override
	public void onReceive(Object message) throws Exception {
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

