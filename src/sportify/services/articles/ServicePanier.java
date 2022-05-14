/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportify.services.articles;

import sportify.models.articles.*;
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
    private PreparedStatement pst1;

    private Statement ste;

    public ServicePanier() {
        conn = DBconnector.getInstance().getCnx();
    }

    @Override
    public void AddPanier(Panier p) throws SQLException {

        String req = "INSERT INTO `panier` (`Ref_article`, `Id`) VALUES ('"
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
    public void DeletPanier(Panier P) {
        String req = "DELETE FROM `panier` WHERE `Id`='" + P.getId_panier() + "'";
        try {
            ste = conn.createStatement();
            ste.executeUpdate(req);
            System.out.println("le panier id=  " + P.getId_panier() + "est supprimer");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
    }

    @Override
    public void UpdatePanier(Panier P)  {
        String req = "UPDATE `panier` SET `Ref_article`='"
                + P.getRef_panier() + "',`Id='"
                + P.getId_users() + "' WHERE `Id_panier`='" + P.getId_panier() + "'";
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
    public List<Panier> displayPanierUser(Panier p) {
        List<Panier> Panlist = new ArrayList<>();

        String req = "SELECT * from `panier` WHERE `Id`='" + p.getId_users()+ "'";

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
    public List<Article> displayArticlePanier(int id){
        List<Article> ListArticle = new ArrayList<>();

        String req = "SELECT * from `panier` WHERE `Id`='" + id + "'";

        try {
            pst = conn.prepareStatement(req);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                int refart = rs.getInt(2);
                String req1 = "SELECT * from `article` WHERE `Ref_article`='" + refart + "'";
                try {
                    pst = conn.prepareStatement(req1);
                    ResultSet rs1 = pst.executeQuery();

                    while (rs1.next()) {
                        Article l = new Article();
                        l.setRef_articl(rs1.getInt("Ref_article"));
                        l.setId_categorie(rs1.getInt(2));
                        l.setDescription(rs1.getString(3));
                        l.setPrix_article(rs1.getFloat(4));
                        l.setMarque(rs1.getString(5));
                        l.setPathImg(rs1.getString(6));
                        ListArticle.add(l);
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());

                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

        return ListArticle;

    }

    public void DeleteArticleFromPanier(Panier p) throws SQLException {
        String req = "DELETE FROM `panier` WHERE `Id`='" + p.getId_users()  + "'"
                + "AND `Ref_article`='" + p.getRef_panier() + "'";
        try {
            ste = conn.createStatement();
            ste.executeUpdate(req);
            System.out.println("article supprimer de panier");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
    }

    @Override
    public void FindPanier(Panier entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
