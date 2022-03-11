/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportify.controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import sportify.models.articles.Article;
import sportify.models.articles.Categorie_Article;
import sportify.services.articles.ServiceArticle;
import sportify.services.articles.ServiceCategorieArt;
import sportify.services.articles.ServicePanier;

/**
 * FXML Controller class
 *
 * @author dali
 */
public class FromcatController implements Initializable {

    @FXML
    private TextField marqueId;
    @FXML
    private TextField descId;
    @FXML
    private TextField pathiamge;
    @FXML
    private Button addbution;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Addarticle(ActionEvent event) {
        ServiceCategorieArt serviceCategorieArt= new ServiceCategorieArt() ;        
        Categorie_Article categorie_Article= new Categorie_Article();
        categorie_Article.setNom_categorie(marqueId.getText());
        categorie_Article.setType_categorie(descId.getText());
        categorie_Article.setImg(pathiamge.getText());
         
        System.out.println(categorie_Article);
        
        try {
            System.out.println("58");
            serviceCategorieArt.AddCategorie(categorie_Article);
            System.out.println("58");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Article is added successfully!");
            alert.show();
            marqueId.setText("");
            descId.setText("");
            pathiamge.setText("");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
