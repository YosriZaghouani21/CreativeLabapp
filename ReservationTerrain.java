/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportify.entities;

import java.util.Date;

/**
 *
 * @author MYLAPTOP
 */
public class ReservationTerrain {
    
    
    private int numReservation;
    private int idClient; 
    private int referenceTerrain;
    private Date dateReservation;
    private int nombreHeure;
    

    public ReservationTerrain(int numReservation, int idClient, int referenceTerrain, Date dateReservation, int nombreHeure) {
        
        this.numReservation = numReservation;
        this.idClient = idClient;
        this.referenceTerrain = referenceTerrain;
        this.dateReservation = dateReservation;
        this.nombreHeure = nombreHeure;
    }

    public ReservationTerrain() {
    }

    public ReservationTerrain(int idClient, int referenceTerrain, Date dateReservation, int nombreHeure) {
        this.idClient = idClient;
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

    

    @Override
    public String toString() {
        return "ReservationTerrain{" + "numReservation=" + numReservation + ", idClient=" 
                + idClient + ", referenceTerrain=" + referenceTerrain + ",dateReservation=" + dateReservation + ", nombreHeure=" + nombreHeure + '}';
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getReferenceTerrain() {
        return referenceTerrain;
    }

    public void setReferenceTerrain(int referenceTerrain) {
        this.referenceTerrain = referenceTerrain;
    }

    public Date getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(Date dateReservation) {
        this.dateReservation = dateReservation;
    }

    public int getNombreHeure() {
        return nombreHeure;
    }

    public void setNombreHeure(int nombreHeure) {
        this.nombreHeure = nombreHeure;
    }

    
    
}
