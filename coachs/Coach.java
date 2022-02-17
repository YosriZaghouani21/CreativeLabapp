/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportify.models.coachs;

/**
 *
 * @author salim
 */
public class Coach {
    
 private int id;
 private String nom; 
 private String prenom;
 private boolean disponibilité;
 private Float prixHeure;
    

    public Coach(int id, String nom, String prenom, boolean disponibilité,Float prixHeure) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.disponibilité = disponibilité;
        this.prixHeure = prixHeure;
    }

    public Coach() {
    }

    public Coach(String nom, String prenom, boolean disponibilité, Float prixHeure) {
        this.nom = nom;
        this.prenom = prenom;
        this.disponibilité = disponibilité;
        this.prixHeure = prixHeure;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Coach{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom +", disponibilité=" + disponibilité +", prixHeure=" + prixHeure + '}';
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

    public boolean getDisponibilité() {
        return disponibilité;
    }

    public void setDisponibilité(boolean disponibilité) {
        this.disponibilité = disponibilité;
    }

    public Float getPrixHeure() {
        return prixHeure;
    }

    public void setPrixHeure(Float prixHeure) {
        this.prixHeure = prixHeure;
    }
    
    
}

