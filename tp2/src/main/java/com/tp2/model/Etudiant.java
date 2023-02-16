package com.tp2.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

public class Etudiant {
		
		private long id;
		private String nom;
		private String prenom;
		private String email;
		private String mdp;
		
		public Etudiant(String nom, String prenom) {
			this.nom = nom;
			this.prenom = prenom;
		}
		
		@Id
		@GeneratedValue
		public long getId() {
			return id;
		}
		public void setId(long id) {
			this.id = id;
		}
		
		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getMdp() {
			return mdp;
		}

		public void setMdp(String mdp) {
			this.mdp = mdp;
		}

		public String getNom() {
			return nom;
		}
		public void setNom(String nom) {
			this.nom = nom;
		}

		public String getPrenom() {
			return prenom;
		}
		public void setPrenom(String prenom) {
			this.prenom = prenom;
		}


}
