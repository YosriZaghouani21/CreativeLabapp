/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportify.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import sportify.mainart.Main;
import sportify.mainart.MylistenerCategorie;
import sportify.models.articles.*;
/**
 * FXML Controller class
 *
 * @author dali
 */
public class ItemCategorieController implements Initializable {

    @FXML
    private Label nameLabel;
    @FXML
    private Label priceLable;
    @FXML
    private ImageView img;
    


    /**
     * Initializes the controller class.
     */
    @FXML
    private void click(MouseEvent mouseEvent) {
        mylistenerCategorie.onClickListener(categorie_Article);
    }
    private Categorie_Article categorie_Article;
    private MylistenerCategorie mylistenerCategorie;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void setData(Categorie_Article categorie_Article, MylistenerCategorie mylistenerCategorie) {
        
        this.categorie_Article = categorie_Article;
        this.mylistenerCategorie = mylistenerCategorie;
        nameLabel.setText(categorie_Article.getNom_categorie());
        priceLable.setText(categorie_Article.getType_categorie());
        Image image = new Image(getClass().getResourceAsStream(categorie_Article.getImg()));
        img.setImage(image);
        System.out.println("categorie art"+nameLabel.getText()+priceLable.getText());
    }
    
}
