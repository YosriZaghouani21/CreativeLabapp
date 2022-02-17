/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportify.services.events;
import Sportifydesktop.infrastructure.DBconnector;
import Sportifydesktop.infrastructure.IService;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sportify.models.events.Evenement;
import java.util.*;
import sportify.models.events.Categorieevenement;

/**
 *
 * @author Asus
 */
public class eventCrud implements IService<Evenement> {
    private Connection conn;
    private PreparedStatement pst;
    private Statement ste;

    public eventCrud() {
         conn = DBconnector.getInstance().getCnx();
    }
    
    

    @Override
    public void ajouter(Evenement E) {
         String req = "INSERT INTO `evenement` (`NomEv`, `DateEv`, `Localisation`, `Description`, `idCategEv`) VALUES (?,?,?,?,?)";
             
        try {
          
            pst = conn.prepareStatement(req);
            pst.setString(1, E.getNomEv());
            pst.setString(2, E.getDateEv());
            pst.setString(3, E.getLocalisation());
            pst.setString(4, E.getDesccription());
            pst.setInt(5, E.getIdCategEv());
            pst.executeUpdate();
            System.out.println("evenement ajouté");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } 
    }

    @Override
    public void supprimer(int  idEv) {
        try {
            String req = "DELETE FROM evenement where idEv=" +idEv;
            Statement st = new DBconnector().getCnx().createStatement();
            st.executeUpdate(req);
            System.out.println("Evenment supprimé !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Evenement E) {
       try {
          String req = "UPDATE evenement SET NomEv='" + E.getNomEv() + "', DateEv='" + E.getDateEv()+"', Localisation='" + E.getLocalisation()+ "', Description='" + E.getDesccription() + "', idCategEv=" + E.getIdCategEv()+ " WHERE idEv=" + E.getIdEv();
          Statement st = new DBconnector().getCnx().createStatement();
          st.executeUpdate(req);
          System.out.println("Evenement modifée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Evenement> afficher() {
      List<Evenement> Ev = new ArrayList<>();
        
        String req = "SELECT * from `evenement`";
        
        try {
            pst = conn.prepareStatement(req);
            ResultSet rs= pst.executeQuery();
            
            while(rs.next()){
                Evenement E = new Evenement();
                E.setIdEv(rs.getInt("idEv") );
                E.setNomEv(rs.getString(2));
                E.setDateEv(rs.getString(3));
                E.setLocalisation(rs.getString(4));
                E.setDesccription(rs.getString(5));
                E.setIdCategEv(rs.getInt(6));
                Ev.add(E);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
        return Ev;
    }

   
    
}
