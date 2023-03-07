package com.tp2.gestionEtudiant.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class FeuilleDePresence {
    
    private long id;
    private String mailEtudiant;
    private String date;
    private List<Ligne> lignes;
    
    public FeuilleDePresence() {}
    
    @Id
    @GeneratedValue
    public long getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    
    public String getMailEtudiant() {
        return mailEtudiant;
    }

    public void setMailEtudiant(String mailEtudiant) {
        this.mailEtudiant = mailEtudiant;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Ligne> getLignes() {
        return lignes;
    }

    public void setLigne(List<Ligne> lignes) {
        this.lignes = lignes;
    }
}