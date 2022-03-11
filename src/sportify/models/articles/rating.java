/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportify.models.articles;

/**
 *
 * @author dali
 */
public class rating {
    private int id, iduser, idarticle, nbrate;

    public rating(int iduser, int idarticle, int nbrate) {
        this.iduser = iduser;
        this.idarticle = idarticle;
        this.nbrate = nbrate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public int getIdarticle() {
        return idarticle;
    }

    public void setIdarticle(int idarticle) {
        this.idarticle = idarticle;
    }

    public int getNbrate() {
        return nbrate;
    }

    public void setNbrate(int nbrate) {
        this.nbrate = nbrate;
    }

    public rating() {
    }
    

    public rating(int id, int iduser, int idarticle, int nbrate) {
        this.id = id;
        this.iduser = iduser;
        this.idarticle = idarticle;
        this.nbrate = nbrate;
    }

    @Override
    public String toString() {
        return "rating{" + "id=" + id + ", iduser=" + iduser + ", idarticle=" + idarticle + ", nbrate=" + nbrate + '}';
    }

    
    
    
}
