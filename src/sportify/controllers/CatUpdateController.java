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
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import sportify.models.articles.Article;
import sportify.models.articles.Categorie_Article;
import sportify.models.articles.CtegorieArt;
import sportify.services.articles.ServiceArticle;
import sportify.services.articles.ServiceCategorieArt;

/**
 * FXML Controller class
 *
 * @author dali
 */
public class CatUpdateController implements Initializable {

    @FXML
    private TextField upmarque;
    @FXML
    private TextField upPrix;
    @FXML
    private TextField upimage;
    private int id;
    @FXML
    private ImageView img;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void updatearticle(ActionEvent event) {
        
        ServiceCategorieArt serviceCategorieArt= new ServiceCategorieArt();
        Categorie_Article categorie_Article=new Categorie_Article();
        categorie_Article.setId_categorie(id);
        categorie_Article.setNom_categorie(upmarque.getText());
        categorie_Article.setType_categorie(upPrix.getText());
        categorie_Article.setImg(upimage.getText());
       
        try {
            serviceCategorieArt.UpdateCategirie(categorie_Article);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Article is update!");
            alert.show();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void setTextField(int id, String name, String type, String path) {

        this.id = id;
        upmarque.setText(name);
        upPrix.setText(type);
        upimage.setText(path);

    }
    
}
