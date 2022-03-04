/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportify.model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author lenovo
 */
public class Commande {
    private int id ; 
    private float PrixTotale ; 
    private int idClient ; 
    private int id_panier; 

    public Commande(int id, float PrixTotale, int idClient,int id_panier) {
        this.id = id;
        this.PrixTotale = PrixTotale;
        this.idClient = idClient;
        this.id_panier=id_panier;
        
    }

    public int getId_panier() {
        return id_panier;
    }

    public void setId_panier(int id_panier) {
        this.id_panier = id_panier;
    }
    
    public Commande() {
    }
  public Commande( float PrixTotale, int idClient,int id_panier) {
        this.PrixTotale = PrixTotale;
        this.idClient = idClient;
        this.id_panier=id_panier;
    } 

    public int getId() {
        return id;
    }

    public int getIdClient() {
        return idClient;
    }
    

    public float getPrixTotale() {
        return PrixTotale;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPrixTotale(float PrixTotale) {
        this.PrixTotale = PrixTotale;
    }

    @Override
    public String toString() {
        return "Commande{" + "id=" + id + ", PrixTotale=" + PrixTotale + ", idClient=" + idClient + ", id_panier=" + id_panier + "}\n";
    }



    

    
  
  
}
