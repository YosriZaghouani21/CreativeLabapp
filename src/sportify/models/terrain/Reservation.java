/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportify.models.terrain;

import java.util.Date;

/**
 *
 * @author MYLAPTOP
 */
public class Reservation {
    
    private int numReservation;
    private int Id; 
    private int referenceTerrain;
    private String dateReservation;
    private int nombreHeure;
    
    
    public Reservation(int numReservation, int Id, int referenceTerrain, String dateReservation, int nombreHeure) {
        
        this.numReservation = numReservation;
        this.Id = Id;
        this.referenceTerrain = referenceTerrain;
        this.dateReservation = dateReservation;
        this.nombreHeure = nombreHeure;
    }
    
    
    public Reservation() {
    }
 
    
    public Reservation(int Id, int referenceTerrain, String dateReservation, int nombreHeure) {
        this.Id = Id;
        this.referenceTerrain = referenceTerrain;
        this.dateReservation = dateReservation;
        this.nombreHeure = nombreHeure;
        
    }

    public int getNumReservation() {
        return numReservation;
    }

    public void setNumReservation(int numReservation) {
        this.numReservation = numReservation;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    

    public int getReferenceTerrain() {
        return referenceTerrain;
    }

    public void setReferenceTerrain(int referenceTerrain) {
        this.referenceTerrain = referenceTerrain;
    }

    public String getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(String dateReservation) {
        this.dateReservation = dateReservation;
    }

    public int getNombreHeure() {
        return nombreHeure;
    }

    public void setNombreHeure(int nombreHeure) {
        this.nombreHeure = nombreHeure;
    }
    
    
    @Override
    public String toString() {
        return "reservationterrain{" + "numReservation=" + numReservation + 
                ", Id=" + Id + 
                ", referenceTerrain=" + referenceTerrain + 
                ", dateReservation=" + dateReservation + 
                ", nombreHeure=" + nombreHeure + '}';
    }
}

