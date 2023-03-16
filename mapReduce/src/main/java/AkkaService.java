package main.java;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import main.java.actor.MapperActor;
import main.java.actor.ReducerActor;
import main.java.message.Mapper;
import main.java.message.Reducer;

public class AkkaService {
	
	private AkkaService() {}
	
	private static AkkaService akkaService = new AkkaService();
	private ActorRef mapper1;
	private ActorRef mapper2;
	private ActorRef mapper3;
	
	private ActorRef reducer1;
	private ActorRef reducer2;
	
	public static AkkaService getAkkaService() {
		return akkaService;
	}
	
	//methode qui initialise larchitecture (les 3 mappers et 2 reducers)
	// system. actorOf(props.create....
	public void create() {
		// creation archi, 3 mappers et 2 reducers
		System.out.println("on créé l'architecture");
		ActorSystem system = ActorSystem.create("MySystem");
		mapper1 = system.actorOf(Props.create(MapperActor.class), "mapper1");
		mapper2 = system.actorOf(Props.create(MapperActor.class), "mapper2");
		mapper3 = system.actorOf(Props.create(MapperActor.class), "mapper3");
		
		reducer1 = system.actorOf(Props.create(ReducerActor.class), "reducer1");
		reducer2 = system.actorOf(Props.create(ReducerActor.class), "reducer2");
	}
	
	// methode qui donne un fichier à analyser
	//tell pour envoyer un message a un acteur (ici envoie les lignes aux mappers)
	public void analyse(String txt) throws IOException {
		InputStream ips = new FileInputStream(txt);
		InputStreamReader ipsr = new InputStreamReader(ips);
		BufferedReader br = new BufferedReader(ipsr);
		String ligne;
		ActorRef[] mappers = {mapper1,mapper2,mapper3};
		int i = 0;
		while( ((ligne=br.readLine()) != null)){ // tant qu'il reste des lignes au fichier
			mappers[i].tell(new Mapper(ligne,reducer1,reducer2), ActorRef.noSender());
			i = (i + 1) % 3;
		}
	}
	
	// methode qui retourne le resultat avec un mot
	// faire send et receive pour avoir les information de Reducer
	public int compteMot(String mot) {
		String moitieAlphabet = "abcdefghijklm";
		String premiereLettre = mot.substring(0,1).toLowerCase();
		
		if (moitieAlphabet.contains(premiereLettre)) {
			return reducer1.getCompteur().get(mot);
		}
		else {
			return reducer2.getCompteur().get(mot);
		}
		
	}
}
