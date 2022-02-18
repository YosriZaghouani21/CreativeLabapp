/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportify.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sportify.entities.Terrain;
import sportify.utils.DataSource;

/**
 *
 * @author MYLAPTOP
 */
public abstract class TerrainServices implements IServices <Terrain> {
    
    private Connection conn;
    private PreparedStatement pst;

    public TerrainServices() {
        conn = DataSource.getInstance().getCnx();
    }
    
    @Override
    public void ajouter(Terrain t) {

        String req = "INSERT INTO `terrain` (`nomTerrain`, `disponibilite`, `prixHeure`) VALUES (?,?,?)";
                
        try {
            pst = conn.prepareStatement(req);
            pst.setString(1, t.getNomTerrain());
            pst.setString(2, t.getDisponibilite());
            pst.setFloat(3, t.getPrixHeure());
            pst.executeUpdate();
            System.out.println("terrain ajouté");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }

   
    @Override
    public void supprimer(Terrain t) {

        String req = "DELETE FROM `terrain`";
            try { 
            pst= conn.prepareStatement(req);
            pst.executeUpdate();
            System.out.println("terrain supprimé");
                }
            catch (SQLException ex){

                Logger.getLogger(TerrainServices.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    
    public void modifier( int referenceTerrain, String nomTerrain, String disponibilite, float prixHeure ) {
        try {
            String req = "UPDATE terrain SET nomTerrain='"+nomTerrain +"',disponibilite='"+disponibilite +"',prixHeure='"+prixHeure + "' WHERE referenceTerrain="+ referenceTerrain;
            
            pst = conn.prepareStatement(req);
            pst.executeQuery();
            System.out.println("terrain modifier");
        } catch (SQLException ex) {
            Logger.getLogger(TerrainServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public void rechercher(Terrain entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Terrain> afficher() {
        { 
            List<Terrain> terrain = new ArrayList<>();
            String req = "SELECT * FROM `terrain` ";
            try {
                pst = conn.prepareStatement (req);
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    Terrain t = new Terrain();
                    t.setReferenceTerrain(rs.getInt("referenceTerrain"));
                    t.setNomTerrain(rs.getString(2));
                    t.setDisponibilite(rs.getString(3));
                    t.setPrixHeure(rs.getFloat(4));
                    
                    terrain.add(t);
                }
            } catch (SQLException ex) {
                Logger.getLogger(TerrainServices.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            return terrain;
        }

        
    }
    
}
