 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportify.models;

/**
 *
 * @author lenovo
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
    private String adresse ; 
    private String comp_adresse ; 
    private int Code_postal ; 
    private String Ville ; 
    private int id ; 

    public Livraison(int idLiv, Date DateLivraison, String Methode_payement, int id) {
        this.idLiv = idLiv;
        this.DateLivraison = DateLivraison;
        this.Methode_payement = Methode_payement;
        this.id = id;
    }

  

        public Livraison() {
    }
       /* public Livraison( Date DateLivraison, String Methode_payement, int id) {
       
        this.DateLivraison = DateLivraison;
        this.Methode_payement = Methode_payement;
        this.id = id;
    }*/

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
        return "Livraison{" + "idLiv=" + idLiv + ", DateLivraison=" + DateLivraison + ", Methode_payement=" + Methode_payement + ", adresse=" + adresse + ", comp_adresse=" + comp_adresse + ", Code_postal=" + Code_postal + ", Ville=" + Ville + ", id=" + id + '}';
    }

    public Livraison(Date DateLivraison, String Methode_payement, String adresse, String comp_adresse, int Code_postal, String Ville, int id) {
        this.DateLivraison = DateLivraison;
        this.Methode_payement = Methode_payement;
        this.adresse = adresse;
        this.comp_adresse = comp_adresse;
        this.Code_postal = Code_postal;
        this.Ville = Ville;
        this.id = id;
    }

    public Livraison(int idLiv, Date DateLivraison, String Methode_payement, String adresse, String comp_adresse, int Code_postal, String Ville, int id) {
        this.idLiv = idLiv;
        this.DateLivraison = DateLivraison;
        this.Methode_payement = Methode_payement;
        this.adresse = adresse;
        this.comp_adresse = comp_adresse;
        this.Code_postal = Code_postal;
        this.Ville = Ville;
        this.id = id;
    }



    public String getAdresse() {
        return adresse;
    }

    public String getComp_adresse() {
        return comp_adresse;
    }

    public int getCode_postal() {
        return Code_postal;
    }

    public String getVille() {
        return Ville;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setComp_adresse(String comp_adresse) {
        this.comp_adresse = comp_adresse;
    }

    public void setCode_postal(int Code_postal) {
        this.Code_postal = Code_postal;
    }

    public void setVille(String Ville) {
        this.Ville = Ville;
    }




    
    

    
  
  
}

    
  
  

