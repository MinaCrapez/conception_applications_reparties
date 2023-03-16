package main.java;

import java.io.FileNotFoundException;
import java.io.IOException;

import akka.actor.ActorSystem;

public class MainMapReduce {
	
	public static void main(String[] args) throws IOException{
	ActorSystem system = ActorSystem.create("MySystem");
	
	AkkaService akkaService = AkkaService.getAkkaService();
	// on créé l'architecture
	akkaService.create();
	
	akkaService.analyse(args[0]);
	
	// on cherche le nombre d'itération du mot "bonjour"
	akkaService.compteMot("bonjour");
	}
}
