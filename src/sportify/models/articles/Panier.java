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
public class Panier {
    
    private int Id_panier;
    private int Ref_panier;
    private int id_users;

    public Panier() {
    }

    public Panier(int Id_panier, int Ref_panier, int id_users) {
        this.Id_panier = Id_panier;
        this.Ref_panier = Ref_panier;
        this.id_users = id_users;
        
    }

    public Panier(int Ref_panier, int id_users) {
        this.Ref_panier = Ref_panier;
        this.id_users = id_users;
    }

    public int getId_panier() {
        return Id_panier;
    }

    public void setId_panier(int Id_panier) {
        this.Id_panier = Id_panier;
    }

    public int getRef_panier() {
        return Ref_panier;
    }

    public void setRef_panier(int Ref_panier) {
        this.Ref_panier = Ref_panier;
    }

    public int getId_users() {
        return id_users;
    }

    public void setId_users(int id_users) {
        this.id_users = id_users;
    }

    @Override
    public String toString() {
        return "Panier{" + "Id_panier=" + Id_panier + ", Ref_panier=" + Ref_panier + ", id_users=" + id_users + '}';
    }
    
    

    
    
    
}
