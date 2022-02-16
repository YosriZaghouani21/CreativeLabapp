/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sportify.services.user;

import Sportifydesktop.infrastructure.DBconnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import sportify.models.user.User;
import java.util.List;
import java.sql.ResultSet;


/**
 *
 * @author zagho
 */
public class ImpServiceUser implements InterfaceServiceUser {

    private Connection dbcon;
    private PreparedStatement pst;

    public ImpServiceUser() {
        dbcon = DBconnector.getInstance().getCnx();
    }

    @Override
    public User Add(User entity) {
        String req = "INSERT INTO user(Nom, Password, Adresse, Email, Type , Numero) VALUES (?,?,?,?,?,?)";
        try {
            pst = dbcon.prepareStatement(req);
            pst.setString(1, entity.getNom());
            pst.setString(2, entity.getPrenom());
            pst.setString(3, entity.getAdresse());
            pst.setString(4, entity.getEmail());
            pst.setString(5, entity.getType());
            pst.setString(6, entity.getNum());

            pst.executeUpdate();
            System.out.println("sportify user Added");
            return entity;

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    @Override
    public void Delete(User entity) {
        String req = "DELETE FROM `User` WHERE `IdUser` = ? ";
        try {
            pst = dbcon.prepareStatement(req);
            pst.setInt(1, entity.getId());
            pst.executeUpdate();
            System.out.println("sportify User Deleted");

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void Update(User entity) {
        String req = "UPDATE User SET Id_user = ?  AND Nom = ? AND Prenom = ? "
                + " AND Adresse = ? AND Email = ? "
                + "AND Password = ? AND Type = ?"
                + "AND Numero = ? ";
        try {
            pst = dbcon.prepareStatement(req);
            pst.setInt(1, entity.getId());
            pst.setString(2, entity.getNom());
            pst.setString(3, entity.getPrenom());
            pst.setString(4,entity.getAdresse());
            pst.setString(5,entity.getEmail());
            pst.setString(6, entity.getPassword());
            pst.setString(7, entity.getType());
            pst.setString(7, entity.getNum());
            pst.executeUpdate();
            System.out.println("sportify User Updated ");
        } catch (Exception e) {
        }
    }
    
     public List<User> Getall() {
           List<User> users = new ArrayList<>();
           String req = "SELECT * from User";
           
           try {
             pst = dbcon.prepareStatement(req);
             ResultSet rs = pst.executeQuery();
             
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
             
         } catch (Exception e) {
         System.err.println(e.getMessage());
        
         }
        return users;
     
     }
     
        @Override
    public User GetById(int ID) {
    String req = "SELECT * from User WHERE Id = ?";
            try {
                pst = dbcon.prepareStatement(req);
                pst.setInt(1,ID);
                ResultSet rs = pst.executeQuery();
                
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
                
                return u;
            } catch (SQLException e) {
                   System.err.println(e.getMessage());
            return null;
            }

    }

}
