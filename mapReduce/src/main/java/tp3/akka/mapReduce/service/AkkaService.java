package tp3.akka.mapReduce.service;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Inbox;
import akka.actor.Props;
import scala.concurrent.duration.FiniteDuration;
import tp3.akka.mapReduce.actor.MapperActor;
import tp3.akka.mapReduce.actor.ReducerActor;
import tp3.akka.mapReduce.message.Mapper;

public class AkkaService {
	
	private AkkaService() {}
	
	private static AkkaService akkaService = new AkkaService();
	private ActorRef mapper1;
	private ActorRef mapper2;
	private ActorRef mapper3;
	private ActorSystem system;
	
	private ActorRef reducer1;
	private ActorRef reducer2;
	
	public static AkkaService getAkkaService() {
		return akkaService;
	}
	
	//methode qui initialise larchitecture (les 3 mappers et 2 reducers)
	public void create() {
		// creation archi, 3 mappers et 2 reducers
		System.out.println("on créé l'architecture");
		system = ActorSystem.create("MySystem");
		mapper1 = system.actorOf(Props.create(MapperActor.class), "mapper1");
		mapper2 = system.actorOf(Props.create(MapperActor.class), "mapper2");
		mapper3 = system.actorOf(Props.create(MapperActor.class), "mapper3");
		
		reducer1 = system.actorOf(Props.create(ReducerActor.class), "reducer1");
		reducer2 = system.actorOf(Props.create(ReducerActor.class), "reducer2");
	}
	
	// methode qui donne un fichier à analyser
	public void analyse(String txt) throws IOException {
		InputStream ips = new FileInputStream(txt);
		InputStreamReader ipsr = new InputStreamReader(ips);
		BufferedReader br = new BufferedReader(ipsr);
		String ligne;
		ActorRef[] mappers = {mapper1,mapper2,mapper3};
		int i = 0;
		while( ((ligne=br.readLine()) != null)){ // tant qu'il reste des lignes au fichier
			mappers[i].tell(new MapperMessage(ligne,reducer1,reducer2), ActorRef.noSender());
			i = (i + 1) % 3;
		}
	}
	
		// methode qui retourne le nombre d'occurence d'un mot
	public int compteMot(String mot) {
		String moitieAlphabet = "abcdefghijklm";
		String premiereLettre = mot.substring(0,1).toLowerCase();
		Inbox inbox = Inbox.create(system);
		if (moitieAlphabet.contains(premiereLettre)) inbox.send(reducer1,mot);
		else inbox.send(reducer2,mot);
		Object count = inbox.receive(FiniteDuration.create(5,TimeUnit.SECONDS)); 
		return (int) count;
	}
}

