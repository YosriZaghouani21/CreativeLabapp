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
public class ServiceCategorieArt implements CtegorieArt<Categorie_Article> {

    private Connection conn;
    private PreparedStatement pst;
    private Statement ste;

    public ServiceCategorieArt() {
        conn = DBconnector.getInstance().getCnx();
    }

    @Override
    public void AddCategorie(Categorie_Article cat)throws SQLException  {
        String req = "INSERT INTO `categorie_article` (`Nom_categorie`, `Type_categorie`, `image`) VALUES ('" + cat.getNom_categorie()
                + "','" + cat.getType_categorie() + "','" + cat.getImg() + "')";
        try {
            ste = conn.createStatement();
            ste.executeUpdate(req);
            System.out.println("La categotrie d'article est ajouté");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void DeletCategorie(Categorie_Article c) throws SQLException {
        System.out.println("id categorie"+c.getId_categorie());
        String req = "DELETE FROM `categorie_article` WHERE `Id_categorie`='" + c.getId_categorie() + "'";
        try {
            ste = conn.createStatement();
            ste.executeUpdate(req);
            System.out.println("La categotrie " + c.getNom_categorie() + "est supprimer");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

    }

  

    @Override
    public List<Categorie_Article> display() {
        List<Categorie_Article> ListCat = new ArrayList<>();

        String req = "SELECT * from `categorie_article`";

        try {
            pst = conn.prepareStatement(req);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Categorie_Article l = new Categorie_Article();
                l.setId_categorie(rs.getInt("Id_categorie"));
                l.setNom_categorie(rs.getString(2));
                l.setType_categorie(rs.getString(3));
                l.setImg(rs.getString(4));

                ListCat.add(l);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

        return ListCat;
    }

    @Override
    public void UpdateCategirie(Categorie_Article P) throws SQLException {
        String req = "UPDATE `categorie_article` SET `Type_categorie`='"
                + P.getType_categorie() + "',`Nom_categorie`='"
                + P.getNom_categorie() + "', `image`='"+ P.getImg()+"'WHERE `Id_categorie`='" + P.getId_categorie() + "'";
        try {
            ste = conn.createStatement();
            ste.executeUpdate(req);
            System.out.println("panier update");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
    }

    @Override
    public Categorie_Article getArticleById(Categorie_Article coArticle) {
        Categorie_Article categorie_Article= new Categorie_Article();
        String req = "SELECT * FROM `categorie_article` WHERE `Id_categorie`='"+coArticle.getId_categorie()+"'";
        try {
            pst = conn.prepareStatement(req);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                
                categorie_Article.setId_categorie(rs.getInt("Id_categorie"));
                categorie_Article.setNom_categorie(rs.getString(2));
                categorie_Article.setType_categorie(rs.getString(3));
                categorie_Article.setImg(rs.getString(4));
                
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return categorie_Article;
    }
    
}
