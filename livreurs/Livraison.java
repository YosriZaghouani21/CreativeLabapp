/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportify.models.livreurs;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author lenovo
 */
public class Livraison {
    private int idLiv ; 
    private Date DateLivraison ; 
    private String Methode_payement ; 
    private int id ; 

    public Livraison(int idLiv, Date DateLivraison, String Methode_payement, int id) {
        this.idLiv = idLiv;
        this.DateLivraison = DateLivraison;
        this.Methode_payement = Methode_payement;
        this.id = id;
    }

  

        public Livraison() {
    }
        public Livraison( Date DateLivraison, String Methode_payement, int id) {
       
        this.DateLivraison = DateLivraison;
        this.Methode_payement = Methode_payement;
        this.id = id;
    }

    Livraison(SimpleDateFormat m1, String cache, int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Date getDateLivraison() {
        return DateLivraison;
    }

    public int getId() {
        return id;
    }

    public int getIdLiv() {
        return idLiv;
    }

    public String getMethode_payement() {
        return Methode_payement;
    }

    public void setDateLivraison(Date DateLivraison) {
        this.DateLivraison = DateLivraison;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdLiv(int idLiv) {
        this.idLiv = idLiv;
    }

    public void setMethode_payement(String Methode_payement) {
        this.Methode_payement = Methode_payement;
    }




    @Override
    public String toString() {
    return"Id Livraison " + id+"Date Livraison"+DateLivraison+"Méthode de paiement"+Methode_payement + "id" + id;
    }

    
  
  
}
