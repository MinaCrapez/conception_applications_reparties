package com.tp2.gestionEtudiant.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Ligne {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    private FeuilleDePresence feuilleDePresence;

    private String matiere;
    private String heureDebut;
    private String heureFin;
    private String jour;
    private Boolean signatureEtudiant;
    private Boolean signatureProf;

    public Ligne() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMatiere() {
        return matiere;
    }

    public void setMatiere(String matiere) {
        this.matiere = matiere;
    }

    public String getHeureDebut() {
        return heureDebut;
    }

    public void setHeureDebut(String heureDebut) {
        this.heureDebut = heureDebut;
    }

    public String getHeureFin() {
        return heureFin;
    }

    public void setHeureFin(String heureFin) {
        this.heureFin = heureFin;
    }

    public String getJour() {
        return jour;
    }

    public void setJour(String jour) {
        this.jour = jour;
    }

    public FeuilleDePresence getFeuilleDePresence() {
        return feuilleDePresence;
    }

    public void setFeuilleDePresence(FeuilleDePresence feuilleDePresence) {
        this.feuilleDePresence = feuilleDePresence;
    }

    public Boolean getSignatureEtudiant() {
          return signatureEtudiant;
      }

      public void setSignatureEtudiant(Boolean signatureEtudiant) {
          this.signatureEtudiant = signatureEtudiant;
      }

      public Boolean getSignatureProf() {
          return signatureProf;
      }

      public void setSignatureProf(Boolean signatureProf) {
          this.signatureProf = signatureProf;
      }



}