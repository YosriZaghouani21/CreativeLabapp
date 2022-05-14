/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sportify.services.user;

import Sportifydesktop.infrastructure.DBconnector;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import sportify.models.user.User;
import java.util.List;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Comparator;
import java.util.stream.Collectors;
import sportify.models.user.Reclamation;
import sportify.models.user.Session;

/**
 *
 * @author zagho
 */
public class ImpServiceUser implements InterfaceServiceUser<User> {

    private Connection dbcon;
    private PreparedStatement pst;

    public ImpServiceUser() {
        dbcon = DBconnector.getInstance().getCnx();
    }

    @Override
    public void ajouter(User entity) {
        System.out.println("hello");
        String req = "INSERT INTO `user` (`Nom` , `Prenom` , `Adresse`, `Email`, `Password`, `Type` , `Numero`) VALUES (?,?,?,?,?,?,?)";
        try {
            pst = dbcon.prepareStatement(req);
            pst.setString(1, entity.getNom());
            pst.setString(2, entity.getPrenom());
            pst.setString(3, entity.getAdresse());
            pst.setString(4, entity.getEmail());
            pst.setString(5, entity.getPassword());
            pst.setString(6, entity.getType());
            pst.setString(7, entity.getNum());

            pst.executeUpdate();
            System.out.println("sportify user Added");

        } catch (SQLException e) {
            System.err.println(e.getMessage());

        }
    }

    @Override
    public void supprimer(int id) {
        try {
            String req = "DELETE FROM User where Id=" + id;
            Statement st = new DBconnector().getCnx().createStatement();
            st.executeUpdate(req);
            System.out.println("utilisateur supprimée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void modifier(User U) {

        try {
            
            String req = "UPDATE User SET Nom = '"
                    + U.getNom() + "', Prenom='" + U.getPrenom() + "', Adresse='"
                    + U.getAdresse() + "', Email='" + U.getEmail()
                    + "', Password='" + U.getPassword()
                    + "', Type='" + U.getType()
                    + "', Numero='" + U.getNum()
                    + "'WHERE Id=" + U.getId();

            Statement st = new DBconnector().getCnx().createStatement();
            st.executeUpdate(req);
            System.out.println("Utilisateur modifée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public List<User> afficher() {

        List<User> users = new ArrayList<>();
        String req = "SELECT * from User";

        try {
            pst = dbcon.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                User u = new User();
                rs.next();
                u.setId(rs.getInt(1));
                u.setNom(rs.getString(2));
                u.setPrenom(rs.getString(3));
                u.setAdresse(rs.getString(4));
                u.setEmail(rs.getString(5));
                u.setPassword(rs.getString(6));
                u.setType(rs.getString(7));
                u.setNum(rs.getString(8));

                users.add(u);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());

        }
        return users;
    }

    @Override
    public User returnuser(String name, String password) {
        User u = new User ();
        try {
            String request = "SELECT * FROM User where Nom='" + name + "' AND Password='" + password + "'";
            Statement s = dbcon.createStatement();
            ResultSet result = s.executeQuery(request);
            while (result.next()) {
                u.setId(result.getInt("ID"));
                u.setNom(result.getString("Nom"));
                u.setPrenom(result.getString("prenom"));
                u.setPassword(result.getString("Password"));
                u.setType(result.getString("type"));
               
            }

        } catch (SQLException ex) {
            System.out.println(ex);
            return null;

        }
        return u;
    }

    public void rechercher(String index) {
        List<User> result = afficher().stream().filter(line -> index.equals(line.getNom())).collect(Collectors.toList());
        System.out.println("----------");
        result.forEach(System.out::println);
        System.out.println("Recherche");

    }

    public void ajouter(Reclamation R) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public User GetByName(String Nom) {
        String req = "SELECT * from `User` WHERE `Nom` = ?";
        try {
            pst = dbcon.prepareStatement(req);
            pst.setString(1, Nom);
            ResultSet rs = pst.executeQuery();

            User u = new User();
            rs.next();
            if (rs.getRow() != 0) {
                u.setId(rs.getInt(1));
                u.setNom(Nom);
                u.setPrenom(rs.getString(3));
                u.setAdresse(rs.getString(4));
                u.setEmail(rs.getString(5));
                u.setPassword(rs.getString(6));
                u.setType(rs.getString(7));
                u.setNum(rs.getString(8));

            }
            return u;

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            return null;
        }
    }
    


}
