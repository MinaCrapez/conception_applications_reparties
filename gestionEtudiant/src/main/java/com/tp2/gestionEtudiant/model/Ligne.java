package com.tp2.gestionEtudiant.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Ligne {

    private long id;
    private long feuilleDePresence;
    private String matiere;
    private String heure;

    public Ligne() {}

    @Id
    @GeneratedValue
    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMatiere() {
        return matiere;
    }

    public void setMatiere(String matiere) {
        this.matiere = matiere;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public long getFeuilleDePresence() {
        return feuilleDePresence;
    }

    public void setFeuilleDePresence(long feuilleDePresence) {
        this.feuilleDePresence = feuilleDePresence;
    }

    
}
