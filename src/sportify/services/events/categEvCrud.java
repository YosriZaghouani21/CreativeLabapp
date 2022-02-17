/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportify.services.events;
import sportify.models.events.Categorieevenement;
import Sportifydesktop.infrastructure.DBconnector;
import Sportifydesktop.infrastructure.IService;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;





/**
 *
 * @author Asus
 */
public class categEvCrud implements IService<Categorieevenement> {
    private Connection conn;
    private PreparedStatement pst;
    private Statement ste;

    public categEvCrud() {
        conn = DBconnector.getInstance().getCnx();
    }
    
    
    @Override
    public void ajouter(Categorieevenement C) {
       String req = "INSERT INTO `categorieevenement` (`NomCategEv`, `TypeCategEv`) VALUES (?,?)";
             
        try {
          
            pst = conn.prepareStatement(req);
            pst.setString(1, C.getNomCategEv());
            pst.setString(2, C.getTypeCategEv());
            pst.executeUpdate();
            System.out.println("categorie ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } 
    }

    @Override
    public void supprimer(int idCategEv) {
         try {
            String req = "DELETE FROM categorieevenement where idCategEv=" +idCategEv;
            Statement st = new DBconnector().getCnx().createStatement();
            st.executeUpdate(req);
            System.out.println("Categorie supprimée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Categorieevenement C) {
                 try {
            String req = "UPDATE categorieevenement SET nomCategEv='" + C.getNomCategEv() + "', TypeCategEv='" + C.getTypeCategEv()+"' where idCategEv="+C.getIdCategEv();
            Statement st = new DBconnector().getCnx().createStatement();
            st.executeUpdate(req);
            System.out.println("Categorie modifée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Categorieevenement> afficher() {
   List<Categorieevenement> Categ = new ArrayList<>();
        
        String req = "SELECT * from `categorieevenement`";
        
        try {
            pst = conn.prepareStatement(req);
            ResultSet rs= pst.executeQuery();
            
            while(rs.next()){
                Categorieevenement C = new Categorieevenement();
                C.setIdCategEv(rs.getInt("idCategEv") );
                C.setNomCategEv(rs.getString(2));
                C.setTypeCategEv(rs.getString(3));
                Categ.add(C);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
        return Categ;
    }

   

  
}
