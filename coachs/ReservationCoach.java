/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportify.models.coachs;

import java.util.Date;

/**
 *
 * @author salim
 */
public class ReservationCoach {   
private int RefRes;
private int id_coach; 
private int id_user;
private Date Date_res;
private int nbrHeur;
private float PrixTotal;

    

    public ReservationCoach(int RefRes, int id_coach,int id_user,Date Date_res,int nbrHeur,float PrixTotal) {
        this.RefRes = RefRes;
        this.id_coach = id_coach;
        this.id_user = id_user;
        this.Date_res = Date_res;
        this.nbrHeur = nbrHeur;
        this.PrixTotal = PrixTotal;
    }

    public ReservationCoach() {
    }

    public ReservationCoach(int id_coach,int id_user,Date Date_res,int nbrHeur,float PrixTotal) {
        this.id_coach = id_coach;
        this.id_user = id_user;
        this.Date_res = Date_res;
        this.nbrHeur = nbrHeur;
        this.PrixTotal = PrixTotal;
    }

    public int getRefRes() {
        return RefRes;
    }

    public void setRefRes(int RefRes) {
        this.RefRes = RefRes;
    }

    public int getId_coach() {
        return id_coach;
    }

    public void setId_coach(int id_coach) {
        this.id_coach = id_coach;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }
    

    public Date getDate_res() {
        return Date_res;
    }

    public void setDate_res(Date Date_res) {
        this.Date_res = Date_res;
    }

    public int getNbrHeur() {
        return nbrHeur;
    }

    public void setNbrHeur(int nbrHeur) {
        this.nbrHeur = nbrHeur;
    }

    public float getPrixTotal() {
        return PrixTotal;
    }

    public void setPrixTotal(float PrixTotal) {
        this.PrixTotal = PrixTotal;
    }

    

    @Override
    public String toString() {
        return "Coach{" + "RefRes=" + RefRes +  "id_coach=" + id_coach + ", id_user=" + id_user + ", Date_res=" + Date_res +", nbrHeur=" + nbrHeur +", PrixTotal=" + PrixTotal + '}';
    }

    
    
}



