package main.java.message;

import akka.actor.ActorRef;

public class Mapper {
	
	public String ligne;
	public ActorRef reducer1;
	public ActorRef reducer2;
	
	public Mapper(String ligne, ActorRef reducer1, ActorRef reducer2) {
		this.ligne = ligne;
		this.reducer1 = reducer1;
		this.reducer2 = reducer2;
	}

	public String getLigne() {
		return ligne;
	}

	public void setLigne(String ligne) {
		this.ligne = ligne;
	}

	public ActorRef getReducer1() {
		return reducer1;
	}

	public void setReducer1(ActorRef reducer1) {
		this.reducer1 = reducer1;
	}

	public ActorRef getReducer2() {
		return reducer2;
	}

	public void setReducer2(ActorRef reducer2) {
		this.reducer2 = reducer2;
	}
	

}
