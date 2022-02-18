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
public class ServiceArticle implements ArticleInterface<Article> {

    private Connection conn;
    private PreparedStatement pst;
    private Statement ste;

    public ServiceArticle() {
        conn = DBconnector.getInstance().getCnx();
    }

    @Override
    public void AddArticle(Article a) {
        String req = "INSERT INTO `article` (`Id_categorie`, `Description`, `Prix_article`, `Marque`) VALUES ('"
                + a.getId_categorie() + "','" + a.getDescription() + "','" + a.getPrix_article() + "','"
                + a.getMarque() + "')";
        try {
            ste = conn.createStatement();
            ste.executeUpdate(req);
            System.out.println("article est ajouté");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
    }

    @Override
    public void DeletArticle(int ref) {
        String req = "DELETE FROM `article` WHERE `Ref_article`='" + ref + "'";
        try {
            ste = conn.createStatement();
            ste.executeUpdate(req);
            System.out.println("L'article de reférence  " + ref + "est supprimer");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

    }

    @Override
    public void UpdateArticle(int ref, int id, String Des, float prix, String Marque) {

        String req = "UPDATE `article` SET `Id_categorie`='"
                + id + "',`Description`='"
                + Des + "', `Prix_article`='"
                + prix + "',`Marque`='" + Marque + "' WHERE `Ref_article`='" + ref + "'";
        try {
            ste = conn.createStatement();
            ste.executeUpdate(req);
            System.out.println("table update");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

    }

    

    @Override
    public void FindArticle(Article entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Article> display() {
        List<Article> ListAr = new ArrayList<>();

        String req = "SELECT * from article";

        try {
            pst = conn.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            

            while (rs.next()) {
                Article l = new Article();
                l.setRef_articl(rs.getInt("Ref_article"));
                l.setId_categorie(rs.getInt(2));
                l.setDescription(rs.getString(3));
                l.setPrix_article(rs.getFloat(4));
                l.setMarque(rs.getString(4));
                ListAr.add(l);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            
        }

        return ListAr;
    }

}
