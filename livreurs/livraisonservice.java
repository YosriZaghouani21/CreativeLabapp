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
import static javafx.scene.input.KeyCode.T;

/**
 *
 * @author lenovo
 */
public class livraisonservice implements IserviceLivraison<Livraison> {
 private Connection conn;
    private PreparedStatement pst;
    private Statement ste;
    
      public livraisonservice() {
        conn = DBconnector.getInstance().getCnx();
    }

    public void Add(Livraison l) {
      String req = "INSERT INTO `livraison` (`DateLivraison`, `Methtode_payement`,`IdCommande`) VALUES ('"+ l.getDateLivraison()+"','"+ l.getMethode_payement()+"','"+ l.getId()+"')";
        
        try {
            ste = conn.createStatement();
            ste.executeUpdate(req);
            System.out.println("livraison ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
    public void Delete(int l ) {
 String req="DELETE FROM livraison WHERE `IdLiv`='"+l+"'";
        try {
            ste = conn.createStatement();
            ste.executeUpdate(req);
            System.out.println("La livraison "+l+ "est supprimer");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());    

    } }

    
    public void Update(int IdLiv,Date DateLivraison, String Methode_payement,int Id) {
       try {    
    String req="UPDATE livraison SET DateLivraison='"+DateLivraison+"',Methtode_payement='"+Methode_payement+
                "',IdCommande='"+Id+"'WHERE IdLiv="+IdLiv;
       
            ste = conn.prepareStatement(req);
            ste.executeUpdate(req);
            System.out.println("Le coach "+IdLiv+ "est modifier");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

    } }

    @Override
       public List<Livraison> GetById() {
 List<Livraison> liv = new ArrayList<>();
        
        String req = "SELECT * from `livraison`";
        
        try {
            pst = conn.prepareStatement(req);
            ResultSet rs= pst.executeQuery();
            
            while(rs.next()){
                Livraison l = new Livraison();
                l.setIdLiv(rs.getInt("IdLiv"));
                l.setDateLivraison(rs.getDate(2));
                l.setMethode_payement(rs.getString(3));
                l.setId(rs.getInt("IdCommande"));
                liv.add(l);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
     return liv ;    
  }


}
    