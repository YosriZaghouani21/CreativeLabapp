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
public class ServiceCategorieArt implements CtegorieArt<Categorie_Article> {

    private Connection conn;
    private PreparedStatement pst;
    private Statement ste;

    
    
    public ServiceCategorieArt() {
        conn = DBconnector.getInstance().getCnx();
    }
    @Override
    public void AddCategorie(Categorie_Article cat) {
        String req = "INSERT INTO `categorie_article` (`Nom_categorie`, `Type_categorie`) VALUES ('" + cat.getNom_categorie()
                + "','" + cat.getType_categorie() + "')";
        try {
            ste = conn.createStatement();
            ste.executeUpdate(req);
            System.out.println("La categotrie d'article est ajouté");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    @Override
    public void DeletCategorie(String Nom_categorie) {
        String req="DELETE FROM `categorie_article` WHERE `Nom_categorie`='"+Nom_categorie+"'";
        try {
            ste = conn.createStatement();
            ste.executeUpdate(req);
            System.out.println("La categotrie "+Nom_categorie+ "est supprimer");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

    }
        
}     
}
