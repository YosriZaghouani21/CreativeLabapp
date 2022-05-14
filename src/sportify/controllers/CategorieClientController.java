/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportify.controllers;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sportify.mainart.MylistenerCategorie;
import sportify.models.articles.Categorie_Article;
import sportify.services.articles.ServiceCategorieArt;

/**
 * FXML Controller class
 *
 * @author dali
 */
public class CategorieClientController implements Initializable {

    @FXML
    private TextField search;
    @FXML
    private VBox choosarticle;
    @FXML
    private Label articleNameLable;
    @FXML
    private Label articlePriceLabel;
    @FXML
    private ImageView articleImg;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    
    private Image image;
     private MylistenerCategorie mylistenerCategorie;
    Categorie_Article catArt = new Categorie_Article();
    private List<Categorie_Article> listArt = new ArrayList<>();
    
    @FXML
    private Button consulter;
    @FXML
    private Button retour;
    private void setChosenFruit(Categorie_Article categorie_Article) {
        articleNameLable.setText(categorie_Article.getNom_categorie());
        articlePriceLabel.setText(categorie_Article.getType_categorie());
        image = new Image(getClass().getResourceAsStream(categorie_Article.getImg()));
        articleImg.setImage(image);
        choosarticle.setStyle("-fx-background-radius: 30;");
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showScreen();
    }    

    @FXML
    private void searchButton(ActionEvent event) {
        listArt.clear();
        String marque = search.getText();
        
        ServiceCategorieArt serviceCategorieArt = new ServiceCategorieArt();
        
        List<Categorie_Article> list=new ArrayList();
        for (Categorie_Article a:serviceCategorieArt.display()){
            if (a.getNom_categorie().toUpperCase().contains(marque.toUpperCase()) || a.getType_categorie().toUpperCase().contains(marque.toUpperCase())  ){
                list.add(a);
            }
        }
        listArt.addAll(list);

        if (listArt.size() > 0) {
            setChosenFruit(listArt.get(0));
            mylistenerCategorie = new MylistenerCategorie() {
                @Override
                public void onClickListener(Categorie_Article coArticle) {
                    setChosenFruit(coArticle);
                    catArt.setId_categorie(coArticle.getId_categorie());
                    catArt.setNom_categorie(coArticle.getNom_categorie());
                    catArt.setType_categorie(coArticle.getType_categorie());
                    catArt.setImg(coArticle.getImg());

                }
            };
        }
        int column = 0;
        int row = 1;
        grid.getChildren().clear();
        try {
            for (int i = 0; i < listArt.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/sportify/views/Article/ItemCategorie.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                ItemCategorieController itemCategorieController = fxmlLoader.getController();
                itemCategorieController.setData(listArt.get(i), mylistenerCategorie);

               if (column == 2) {
                    column = 0;
                    row++;
                }
                

                grid.add(anchorPane,column++, row); //(child,column,row)
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


    public void showScreen() {
        ServiceCategorieArt serviceCategorieArt = new ServiceCategorieArt();
        System.out.println("categorietest" + serviceCategorieArt.display());
        listArt.addAll(serviceCategorieArt.display());
        if (listArt.size() > 0) {
            setChosenFruit(listArt.get(0));
            mylistenerCategorie = new MylistenerCategorie() {
                @Override
                public void onClickListener(Categorie_Article coArticle) {
                    setChosenFruit(coArticle);
                    catArt.setId_categorie(coArticle.getId_categorie());
                    catArt.setNom_categorie(coArticle.getNom_categorie());
                    catArt.setType_categorie(coArticle.getType_categorie());
                    catArt.setImg(coArticle.getImg());

                }
            };
        }

        
        int column = 0;
        int row = 1;
        grid.getChildren().clear();
        try {
            for (int i = 0; i < listArt.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/sportify/views/Article/ItemCategorie.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                ItemCategorieController itemCategorieController = fxmlLoader.getController();
                itemCategorieController.setData(listArt.get(i), mylistenerCategorie);

               
                    if (column == 2) {
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

    private void consulter() {
       
        Stage stage =(Stage)consulter.getScene().getWindow(); 
        stage.close(); 
    }
    @FXML
    public void continuerView() throws IOException{ 
         
        consulter();
        Stage primStage=new Stage(); 
        Parent root=FXMLLoader.load(getClass().getResource("/sportify/views/Article/MarketClient.fxml")); 
        primStage.setTitle("consulter client");
        primStage.setScene(new Scene(root));
        primStage.show();
    }

    @FXML
    private void refrechbutton(ActionEvent event) throws IOException {
        
    }

    @FXML
    private void retournButton(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) retour.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/sportify/views/user/FXMLMainClient.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
        
    

  
    

    
}
