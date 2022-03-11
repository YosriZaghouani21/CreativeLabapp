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
import sportify.models.terrain.Terrain;



/**
 *
 * @author macbook
 */
public class TerrainService implements IService<Terrain> {

    Connection connexion;
    Statement ste;
    ResultSet rs;
    PreparedStatement pste;

    public TerrainService() {
        connexion = DBconnector.getInstance().getCnx();
        try {
            ste = connexion.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(TerrainService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void ajouterp(Terrain p) throws SQLException {
        String req = "INSERT INTO `terrain` (`nomTerrain`,`adresse`, `disponibilite`, `prixHeure`) VALUES ( '"
                + p.getNomTerrain() + "', '" + p.getAdresse() + "', '" + p.getDisponibilite() + "', '" + p.getPrixHeure() + "') ";

        ste.executeUpdate(req);
    }

    @Override
    public void modifierp(Terrain p) throws SQLException {

        String req = "UPDATE terrain SET `nomTerrain`='"
                + p.getNomTerrain() + "', `adresse`='"
                + p.getAdresse() + "', `disponibilite`='"
                + p.getDisponibilite() + "',`prixHeure`='" + p.getPrixHeure() + "' WHERE `nomTerrain`='"
                + p.getNomTerrain() + "'";
        try {
            ste = connexion.createStatement();
            ste.executeUpdate(req);
            System.out.println("table modifié");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

    }

    @Override
    public void deletep(Terrain p) throws SQLException {
        String req = "DELETE FROM terrain WHERE `nomTerrain`='" + p.getNomTerrain() + "'";
        try {
            ste = connexion.createStatement();
            ste.executeUpdate(req);
            System.out.println("Terrain supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

    }

    @Override
    public List<Terrain> afficherterrain() throws SQLException {
        List<Terrain> terrain = new ArrayList<>();
        String req = "select * from terrain";
        ste = connexion.createStatement();
        ResultSet rst = ste.executeQuery(req);

        while (rst.next()) {
            Terrain p = new Terrain(rst.getInt("referenceTerrain"),//or rst.getInt(1)
                    rst.getString("nomTerrain"),
                    rst.getString("adresse"),
                    rst.getString("disponibilite"),
                    rst.getFloat("prixHeure"));
            terrain.add(p);
        }
        return terrain;
    }

    @Override
    public List<Terrain> getAll() {
        List<Terrain> terrains = new ArrayList<>();
        String req = "select * from terrain";

        try {
            pste = connexion.prepareStatement(req);
            ResultSet rst = ste.executeQuery(req);
            while(rst.next()){
                                        Terrain p = new Terrain();

                p.setReferenceTerrain(rst.getInt(1));
                p.setNomTerrain(rst.getString(2));
                p.setAdresse(rst.getString(3));
                p.setDisponibilite(rst.getString(4));
                p.setPrixHeure(rst.getFloat(5));
                terrains.add(p);
            }
            return terrains;

        } catch (SQLException ex) {
            Logger.getLogger(TerrainService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return terrains;
    }

}
