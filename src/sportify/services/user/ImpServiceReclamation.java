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
import java.sql.Statement;
import java.util.Comparator;
import java.util.stream.Collectors;
import sportify.models.user.User;

/**
 *
 * @author zagho
 */
public class ImpServiceReclamation implements InterfaceServiceUser<Reclamation> {

    private Connection dbcon;
    private PreparedStatement pst;

    public ImpServiceReclamation() {
        dbcon = DBconnector.getInstance().getCnx();
    }

    @Override
    public void ajouter(Reclamation entity) {
        String req = "INSERT INTO Reclamation (Description ,"
                + " Date , IdReclamation , Id) "
                + "VALUES (? , ? , ? , ?  )";

        try {
            pst = dbcon.prepareStatement(req);
            pst.setString(1, entity.getDescription());
            pst.setString(2, entity.getDate());
            pst.setInt(3, entity.getIdReclamation());
            pst.setInt(4, entity.getId());
            pst.executeUpdate();
            System.out.println("Réclamation ajoutée");
        } catch (SQLException e) {
            System.err.println(e.getMessage());

        }

    }

    @Override
    public void supprimer(int id) {
        try {
            String req = "DELETE FROM Reclamation where IdReclamation=" + id;
            Statement st = new DBconnector().getCnx().createStatement();
            st.executeUpdate(req);
            System.out.println("Reclamation supprimée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Reclamation R) {

        try {

            String req = "UPDATE Reclamation SET Description='" + R.getDescription() + "' WHERE IdReclamation=" + R.getIdReclamation();

            Statement st = new DBconnector().getCnx().createStatement();
            st.executeUpdate(req);
            System.out.println("Reclamation modifée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Reclamation> afficher() {
        List<Reclamation> reclamations = new ArrayList<>();

        String req = "SELECT * from Reclamation";

        try {
            pst = dbcon.prepareStatement(req);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Reclamation r = new Reclamation();
                r.setIdReclamation(rs.getInt(1));
                r.setDescription(rs.getString(2));
                r.setDate(rs.getString(3));
                r.setId(rs.getInt(4));
                reclamations.add(r);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return reclamations;
    }



    
    public void rechercher(String index) {
           List<Reclamation> result = afficher().stream().filter(line -> index.equals(line.getIdReclamation())).collect(Collectors.toList());
                    System.out.println("----------");
                    result.forEach(System.out::println);
    }
    
        public void TrierParTitre (String titre_rec){
    ImpServiceReclamation sr = new ImpServiceReclamation();
    List<Reclamation> TrierParTitre = sr.afficher().stream().sorted(Comparator.comparing(Reclamation::getIdReclamation)).collect(Collectors.toList());
                            TrierParTitre.forEach(System.out::println);
}

    @Override
    public User returnuser(String name, String password) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
