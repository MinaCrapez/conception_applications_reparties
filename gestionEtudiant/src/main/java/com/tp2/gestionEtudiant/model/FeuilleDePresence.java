package com.tp2.gestionEtudiant.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class FeuilleDePresence {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    Etudiant etudiant;

    @OneToMany(mappedBy="feuilleDePresence", orphanRemoval=true)
    List<Ligne> lignes;

    private String moisAnnee;

    public FeuilleDePresence() {}

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public Etudiant getEtudiant() {
        return this.etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    public String getMoisAnnee() {
        return moisAnnee;
    }

    public void setMoisAnnee(String moisAnnee) {
        this.moisAnnee = moisAnnee;
    }

     public List<Ligne> getLignes() { return lignes; }

     public void setLignes(List<Ligne> lignes) { this.lignes = lignes; }


}