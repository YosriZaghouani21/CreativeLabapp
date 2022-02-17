/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportify.models.livreurs;

import Sportifydesktop.infrastructure.DBconnector;
import Sportifydesktop.infrastructure.IService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author lenovo
 */
public class commandeservice implements Iservicecommande<Commande>{

       public commandeservice() {
        conn = DBconnector.getInstance().getCnx();
    }
 private Connection conn;
    private PreparedStatement pst;
    private Statement ste;
 @Override
    public void Add(Commande c) {
        String req = "INSERT INTO `commande` (`PrixTotale`, `Id_user`,`Id_panier`) VALUES ('"+ c.getPrixTotale()+"','"+ c.getIdClient()+"','"+ c.getId_panier()+"')";
        
        try {
            ste = conn.createStatement();
            ste.executeUpdate(req);
            System.out.println("commande ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
 public void Delete(int c ) {
 String req="DELETE FROM commande WHERE `IdCommande`='"+c+"'";
        try {
            ste = conn.createStatement();
            ste.executeUpdate(req);
            System.out.println("La commande "+c+ "est supprimer");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());    

    } }  
   public void Update(int IdCommande,float PrixTotale,int IdClient,int Id_panier) {
       try {    
    String req="UPDATE commande SET PrixTotale'"+ PrixTotale+"',Id_user='"+IdClient+
                "',Id_panier='"+Id_panier+"'WHERE IdCommande="+IdCommande;
       
            ste = conn.prepareStatement(req);
            ste.executeUpdate(req);
            System.out.println("Le commande "+IdCommande+ "est modifier");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

    } }

 
    @Override
 
    
    public List GetById() {
List<Commande> com= new ArrayList<>();
        
        String req = "SELECT * FROM `commande`";
        
        try {
            pst = conn.prepareStatement(req);
            ResultSet rs= pst.executeQuery();
            
            while(rs.next()){
                Commande c = new Commande();
                c.setId(rs.getInt("IdCommande"));
                c.setPrixTotale(rs.getFloat(1));
                c.setIdClient(rs.getInt("Id_user"));
                c.setId_panier(rs.getInt("Id_panier"));
                com.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
     return com ;    
  }


  

 
 }


