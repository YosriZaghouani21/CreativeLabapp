/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportify.services.terrain;

import Sportifydesktop.infrastructure.DBconnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sportify.models.terrain.Reservation;
import sportify.models.terrain.Terrain;




/**
 *
 * @author MYLAPTOP
 */
public class ReservationService implements IServ<Reservation>{
    
    Connection connexion;
    Statement ste;
    ResultSet rs;
    PreparedStatement pste;

    
    
    public ReservationService() {
        connexion = DBconnector.getInstance().getCnx();
        try {
            ste = connexion.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(TerrainService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void ajouterR(Reservation r) throws SQLException {
        String req = "INSERT INTO `reservationterrain` (`Id`, `referenceTerrain`, `dateReservation`, `nombreHeure`) VALUES ( '"
                + r.getId() + "', '"
                + r.getReferenceTerrain() + "', '"
                + r.getDateReservation() + "', '" 
                + r.getNombreHeure() + "') ";
            
        ste.executeUpdate(req);
    }
    
    @Override
    public void modifierR(Reservation r) throws SQLException{

        String req = "UPDATE reservationterrain SET "
                + "`Id`='" + r.getId() 
                +  "', `referenceTerrain`='" + r.getReferenceTerrain() 
                + "',`dateReservation`='" + r.getDateReservation()
                + "',`nombreHeure`='" + r.getNombreHeure()
                + "' WHERE `dateReservation`='" + r.getDateReservation() + "'";
        try {
            ste = connexion.createStatement();
            ste.executeUpdate(req);
            System.out.println("Reservation modifié");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

    }

    
    @Override
    public void deleteR(Reservation r)throws SQLException {
        String req = "DELETE FROM reservationterrain WHERE `dateReservation`='" + r.getDateReservation() + "'";
        try {
            ste = connexion.createStatement();
            ste.executeUpdate(req);
            System.out.println("Reservation supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

    }
    
    
    @Override
    public List<Reservation> afficherR() throws SQLException {
        List<Reservation> reservationterrain = new ArrayList<>();
        String req = "select * from reservationterrain";
        ste = connexion.createStatement();
        ResultSet rst = ste.executeQuery(req);

        while (rst.next()) {
            Reservation r = new Reservation(rst.getInt("numReservation"),//or rst.getInt(1)
                    rst.getInt("Id"),
                    rst.getInt("referenceTerrain"),
                    rst.getString("dateReservation"),
                    rst.getInt("nombreHeure"));
            reservationterrain.add(r);
        }
        return reservationterrain;
    }
    public Terrain getTerrainNam(String name){
      Terrain t= new Terrain();
      String req = "select * from `terrain` WHERE `nomTerrain`='"+name+"'";
      try{
          pste=connexion.prepareStatement(req);
          
        ResultSet rst = pste.executeQuery();
         while (rst.next()) {
             t.setReferenceTerrain(rst.getInt("referenceTerrain"));
             t.setNomTerrain(rst.getString(2));
             t.setAdresse(rst.getString(3));
             t.setDisponibilite(rst.getString(4));
             t.setPrixHeure(rst.getFloat(5));
        }
      } catch(SQLException ex){
          System.out.println(ex.getMessage());
      }
      return t;
    
    }

   
    
}


