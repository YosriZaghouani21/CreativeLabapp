/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportify.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import sportify.mainart.MyListener;
import sportify.models.articles.Article;
import sportify.models.articles.Panier;
import sportify.models.user.Session;
import sportify.services.articles.ServiceArticle;
import sportify.services.articles.ServicePanier;

/**
 * FXML Controller class
 *
 * @author dali
 */
public class PanierController implements Initializable {

    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    @FXML
    private Button delet;
    @FXML
    private Label totalprix;
    private List<Article> listArt = new ArrayList<>();
    private MyListener myListener;
    Article art = new Article();
    Panier panier=new Panier();
    private float prixtotale;
    @FXML
    private Button commade;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showScreen();
        Prixtotale();
        Session.getUserStatic();
        System.out.println(Session.getUserStatic().getId() + "Client en Reservation client");
    }
    
    
    
public void showScreen() {
        ServicePanier servicePanier = new ServicePanier();
        System.out.println("display article panier" + servicePanier.displayArticlePanier(Session.getUserStatic().getId()) );
        listArt.addAll(servicePanier.displayArticlePanier(Session.getUserStatic().getId()));
      
        if (listArt.size() > 0) {
           
            myListener = new MyListener() {
                @Override
                public void onClickListener(Article article) {
                    art.setRef_articl(article.getRef_articl());
                    art.setMarque(article.getMarque());
                    art.setDescription(article.getDescription());
                    art.setPathImg(article.getPathImg());
                    art.setPrix_article(article.getPrix_article());
                    art.setId_categorie(article.getId_categorie());
                    art.setPathImg(article.getPathImg());
                }
            };
        }
        int column = 0;
        int row = 1;
        grid.getChildren().clear();
        try {
            for (int i = 0; i < listArt.size(); i++) {
                
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/sportify/views/Article/item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                ItemController itemController = fxmlLoader.getController();
                itemController.setData(listArt.get(i), myListener);
               
                
                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width

                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }    

    @FXML
    private void deletArticle(ActionEvent event) {
        ServicePanier servicePanier=new ServicePanier();
        Panier panier=new Panier();
        panier.setId_users(Session.getUserStatic().getId());
        panier.setRef_panier(art.getRef_articl());
        try{
            servicePanier.DeleteArticleFromPanier(panier);
        
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        listArt.clear();
        showScreen();
        Prixtotale();
        
        
    }
    private void Prixtotale(){
        prixtotale=0;
        for (int i = 0; i < listArt.size(); i++){
            prixtotale= prixtotale + listArt.get(i).getPrix_article();

        }
        totalprix.setText("$ "+Float.toString(prixtotale));
    
        
    }

    @FXML
    private void commandebutton(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) commade.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/sportify/views/livraison/PasserCommande.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
}
