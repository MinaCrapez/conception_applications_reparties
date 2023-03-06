package com.tp2.gestionEtudiant.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class FeuilleDePresence {
    
    private long id;
    private String mailEtudiant;
    private String mois;
    //private List<Ligne> ligne;
    
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

    public String getMois() {
        return mois;
    }

    public void setMois(String mois) {
        this.mois = mois;
    }

    /**public List<Ligne> getLigne() {
        return ligne;
    }

    public void setLigne(List<Ligne> ligne) {
        this.ligne = ligne;
    }
**/
}