/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportify.services.articles;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import Sportifydesktop.infrastructure.DBconnector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sportify.models.articles.*;

/**
 *
 * @author dali
 */
public class ServiceRating {

    private Connection conn;
    private PreparedStatement pst;
    private Statement ste;

    public ServiceRating() {
        conn = DBconnector.getInstance().getCnx();
    }

    public void AddRating(rating r) throws SQLException {

        String req = "INSERT INTO `rating` (`Id`, `Ref_article`,`nbrate`) VALUES ('"
                + r.getIduser() + "','" + r.getIdarticle() + "','" + r.getNbrate() + "')";
        try {
            ste = conn.createStatement();
            ste.executeUpdate(req);
            System.out.println("rating add");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
    }

    public List<rating> display(int id) throws SQLException{
        List<rating> ListAr = new ArrayList<>();

        String req = "SELECT * FROM `rating` WHERE `idaritcle`";

        try {
            pst = conn.prepareStatement(req);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                rating r = new rating();
                r.setId(rs.getInt("idrate"));
                r.setIduser(rs.getInt(2));
                r.setIdarticle(rs.getInt(3));
                r.setNbrate(rs.getInt(4));

                ListAr.add(r);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

        return ListAr;

    }
}
