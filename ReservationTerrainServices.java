/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportify.services;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sportify.entities.ReservationTerrain;
import sportify.utils.DataSource;

/**
 *
 * @author MYLAPTOP
 */
public abstract class ReservationTerrainServices implements IServices<ReservationTerrain> {
    
    private Connection conn;
    private PreparedStatement pst;
    
    public ReservationTerrainServices() {
        conn = DataSource.getInstance().getCnx();
    }

    @Override
    public void ajouter(ReservationTerrain rt) {

        String req = "INSERT INTO `reservationTerrain` (`idClient`, `referenceTerrain`, `dateReservation`, `nombreHeure`) VALUES (?,?,?,?)";
                
        try {
            pst = conn.prepareStatement(req);
            pst.setInt(1, rt.getIdClient());
            pst.setInt(2, rt.getReferenceTerrain());
            pst.setDate(3, (Date) rt.getDateReservation());
            pst.setInt(4, rt.getNombreHeure());
            pst.executeUpdate();
            System.out.println("reservation ajouté");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }

   
    @Override
    public void supprimer(ReservationTerrain rt) {

        String req = "DELETE FROM `reservationTerrain`";
            try { 
            pst= conn.prepareStatement(req);
            pst.executeUpdate();
            System.out.println("reservation supprimé");
                }
            catch (SQLException ex){

                Logger.getLogger(TerrainServices.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    
    public void modifier(int numReservation, int idClient, int referenceTerrain, Date dateReservation, int nombreHeure ) {
        try {
            String req = "UPDATE reservationTerrain SET idClient='"+idClient +"',referenceTerrain='"+referenceTerrain +
                    "',dateReservation='"+dateReservation +"',nombreHeure='"+nombreHeure + "' WHERE numReservation="+ numReservation;
            
            pst = conn.prepareStatement(req);
            pst.executeQuery();
            System.out.println("reservation modifier");
        } catch (SQLException ex) {
            Logger.getLogger(TerrainServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public void rechercher(ReservationTerrain entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ReservationTerrain> afficher() {
        { 
            List<ReservationTerrain> reservationTerrain = new ArrayList<>();
            String req = "SELECT * FROM `reservationTerrain` ";
            try {
                pst = conn.prepareStatement (req);
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    ReservationTerrain rt = new ReservationTerrain();
                    rt.setNumReservation(rs.getInt("numReservation"));
                    rt.setIdClient(rs.getInt(2));
                    rt.setReferenceTerrain(rs.getInt(3));
                    rt.setDateReservation(rs.getDate(4));
                    rt.setNombreHeure(rs.getInt(5));
                    
                    reservationTerrain.add(rt);
                }
            } catch (SQLException ex) {
                Logger.getLogger(TerrainServices.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            return reservationTerrain;
        }

        
    }
    
    
}
