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
public class commande_ss {

      private static int id ; 
    private static float PrixTotale ; 
    private static int idClient ; 
    private static int id_panier; 
    public static Commande CommandeSt; 

    public commande_ss() {
    }

    public static int getId() {
        return id;
    }

    public static float getPrixTotale() {
        return PrixTotale;
    }

    public static int getIdClient() {
        return idClient;
    }

    public static int getId_panier() {
        return id_panier;
    }

    public static Commande getCommandeSt() {
        return CommandeSt;
    }

    public static void setId(int id) {
        commande_ss.id = id;
    }

    public static void setPrixTotale(float PrixTotale) {
        commande_ss.PrixTotale = PrixTotale;
    }

    public static void setIdClient(int idClient) {
        commande_ss.idClient = idClient;
    }

    public static void setId_panier(int id_panier) {
        commande_ss.id_panier = id_panier;
    }

    public static void setCommandeSt(Commande CommandeSt) {
        commande_ss.CommandeSt = CommandeSt;     
    }


   

}
