/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sportify.services.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Date;
import Sportifydesktop.infrastructure.DBconnector;
import sportify.models.user.Reclamation;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import sportify.models.user.User;

/**
 *
 * @author zagho
 */
public class ImpServiceReclamation implements InterfaceServiceReclamation {

    private Connection dbcon;
    private PreparedStatement pst;

    public ImpServiceReclamation() {
        dbcon = DBconnector.getInstance().getCnx();
    }

    public Reclamation Add(Reclamation entity) {
        String req = "INSERT INTO Reclamation (Description ,"
                + " Date , IdReclamation , Reclamateur) "
                + "VALUES (? , ? , ? , ?  )";

        try {
            pst = dbcon.prepareStatement(req);
            pst.setString(1, entity.getDescription());
            pst.setDate(2, entity.getDate());
            pst.setInt(3, entity.getIdReclamation());
            pst.setInt(4, entity.getReclamateur().getId());
            pst.executeUpdate();
            System.out.println("Réclamation ajoutée");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        }
        return null;

    }

    public void Delete(Reclamation entity) {
        String req = "DELETE FROM Reclamation WHERE IdReclamation = ?";
        try {

            pst = dbcon.prepareStatement(req);
            pst.setInt(1, entity.getIdReclamation());
            pst.executeUpdate();
            System.out.println("Réclamation supprimée");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
    }

    public void Update(Reclamation entity) {
        String req = "UPDATE Reclamation SET Sujet = ? AND Description = ?"
                + "  AND  Date = ?"
                + " AND Reclamateur = ? WHERE IdReclamation = ?";
        try {
            pst = dbcon.prepareStatement(req);
            pst.setString(1, entity.getDescription());
            pst.setDate(2, (Date) entity.getDate());
            pst.setInt(3, entity.getReclamateur().getId());
            pst.setInt(4, entity.getIdReclamation());
            pst.executeUpdate();
            System.out.println("Réclamation modifiée");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
    }

    public List<Reclamation> Getall() {
        List<Reclamation> reclamations = new ArrayList<>();

        String req = "SELECT * from Reclamation";

        try {
            pst = dbcon.prepareStatement(req);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Reclamation r = new Reclamation();
                r.setIdReclamation(rs.getInt(1));
                r.setDescription(rs.getString(3));
                r.setDate(rs.getDate(4));
                r.setReclamateur(new User(rs.getInt(5)));
                reclamations.add(r);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return reclamations;
    }

    public Reclamation GetById(int ID) {
        String req = "SELECT * from Reclamation WHERE IdReclamation = ?";
        try {
            pst = dbcon.prepareStatement(req);
            pst.setInt(1, ID);
            ResultSet rs = pst.executeQuery();

            Reclamation r = new Reclamation();
            rs.next();
            r.setIdReclamation(rs.getInt(1));
            r.setDescription(rs.getString(2));
            r.setDate(rs.getDate(3));
            r.setReclamateur(new User(rs.getInt(4)));

            return r;

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            return null;
        }
    }

}
