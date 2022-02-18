/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportify.entities;

/**
 *
 * @author MYLAPTOP
 */
public class Terrain {
    
    private int referenceTerrain;
    private String nomTerrain; 
    private String disponibilite;
    private float prixHeure;
    

    public Terrain(int referenceTerrain, String nomTerrain, String disponibilite, float prixHeure) 
    {
        this.referenceTerrain = referenceTerrain;
        this.nomTerrain = nomTerrain;
        this.disponibilite = disponibilite;
        this.prixHeure = prixHeure;
    }

    public Terrain() {
    }

    public Terrain(String nomTerrain, String disponibilite, float prixHeure) {
        this.nomTerrain = nomTerrain;
        this.disponibilite = disponibilite;
        this.prixHeure = prixHeure;
        
    }

    public int getReferenceTerrain() {
        return referenceTerrain;
    }

    public void setReferenceTerrain(int referenceTerrain) {
        this.referenceTerrain = referenceTerrain;
    }

    @Override
    public String toString() {
        return "Terrain{" + "referenceTerrain=" + referenceTerrain + ", nomTerrain=" 
                + nomTerrain + ", disponibilite=" + disponibilite + "prixHeure=" + prixHeure +'}';
    }

    public String getNomTerrain() {
        return nomTerrain;
    }

    public void setNomTerrain(String nomTerrain) {
        this.nomTerrain = nomTerrain;
    }

    public String getDisponibilite() {
        return disponibilite;
    }

    public void setDisponibilite(String disponibilite) {
        this.disponibilite = disponibilite;
    }

    public float getPrixHeure() {
        return prixHeure;
    }

    public void setPrixHeure(float prixHeure) {
        this.prixHeure = prixHeure;
    }

    
}
