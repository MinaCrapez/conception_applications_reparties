package com.tp2.gestionEtudiant.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
@Entity
public class Etudiant {

    @Id
    @GeneratedValue
    private long id;

    @OneToMany(mappedBy="etudiant")
    private List<FeuilleDePresence> feuilles;

    private String nom;
    private String prenom;
    private String email;
    private String mdp;

    public Etudiant() {}

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

    public List<FeuilleDePresence> getFeuilles() {
        return feuilles;
    }

    public void setFeuilles(List<FeuilleDePresence> feuilles) {
        this.feuilles = feuilles;
    }


}