/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionArticle;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import Sportifydesktop.infrastructure.DBconnector;

/**
 *
 * @author dali
 */
public class ServicePanier implements InterfacePanier<Panier> {

    private Connection conn;
    private PreparedStatement pst;
    private Statement ste;

    public ServicePanier() {
        conn = DBconnector.getInstance().getCnx();
    }

    @Override
    public void AddPanier(Panier p) {

        String req = "INSERT INTO `panier` (`Ref_article`, `Id_user`) VALUES ('"
                + p.getRef_panier() + "','" + p.getId_users() + "')";
        try {
            ste = conn.createStatement();
            ste.executeUpdate(req);
            System.out.println("Panier ajouter");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

    }

    @Override
    public void DeletPanier(int ref) {
        String req = "DELETE FROM `panier` WHERE `Id_panier`='" + ref + "'";
        try {
            ste = conn.createStatement();
            ste.executeUpdate(req);
            System.out.println("le panier id=  " + ref + "est supprimer");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
    }

    @Override
    public void UpdatePanier(int id, int ref, int id_user) {
        String req = "UPDATE `panier` SET `Ref_article`='"
                + ref + "',`Id_user`='"
                + id_user + "' WHERE `Id_panier`='" + id + "'";
        try {
            ste = conn.createStatement();
            ste.executeUpdate(req);
            System.out.println("panier update");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
    }

    @Override
    public List<Panier> display() {
        
            List<Panier> Panlist = new ArrayList<>();

        String req = "SELECT * from `panier`";

        try {
            pst = conn.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            

            while (rs.next()) {
                Panier l = new Panier();
                l.setId_panier(rs.getInt("Id_panier"));
                l.setRef_panier(rs.getInt(2));
                l.setId_users(rs.getInt(3));
                
                Panlist.add(l);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            
        }

        return Panlist;
       
    }

    @Override
    public void FindPanier(Panier entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
