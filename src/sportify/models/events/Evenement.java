/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sportify.models.events;
import java.util.*;

/**
 *
 * @author zagho
 */
public class Evenement {
    private int idEv;
    private String nomEv;
    private String DateEv;
    private String localisation;
    private String desccription;
    private int idCategEv;

    public Evenement() {
    }

    public Evenement(int idEv, String nomEv, String DateEv, String localisation, String desccription, int idCategEv) {
        this.idEv = idEv;
        this.nomEv = nomEv;
        this.DateEv = DateEv;
        this.localisation = localisation;
        this.desccription = desccription;
        this.idCategEv = idCategEv;
    }

    public Evenement(String nomEv, String DateEv, String localisation, String desccription, int idCategEv) {
        this.nomEv = nomEv;
        this.DateEv = DateEv;
        this.localisation = localisation;
        this.desccription = desccription;
        this.idCategEv = idCategEv;
    }

     public int getIdEv() {
        return idEv;
    }

    public void setIdEv(int idEv) {
        this.idEv = idEv;
    }

    public String getNomEv() {
        return nomEv;
    }

    public void setNomEv(String nomEv) {
        this.nomEv = nomEv;
    }

    public String getDateEv() {
        return DateEv;
    }

    public void setDateEv(String DateEv) {
        this.DateEv = DateEv;
    }
    
    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public String getDesccription() {
        return desccription;
    }

    public void setDesccription(String desccription) {
        this.desccription = desccription;
    }

    public int getIdCategEv() {
        return idCategEv;
    }

    public void setIdCategEv(int idCategEv) {
        this.idCategEv = idCategEv;
    }

    @Override
    public String toString() {
        return "Evenement{" + "idEv=" + idEv + ", nomEv=" + nomEv + ", DateEv=" + DateEv + ", localisation=" + localisation + ", desccription=" + desccription + ", idCategEv=" + idCategEv + '}';
    }
    
    
    
    
    
}
