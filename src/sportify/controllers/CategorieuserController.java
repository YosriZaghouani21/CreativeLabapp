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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sportify.mainart.Main;
import sportify.mainart.MyListener;
import sportify.mainart.MylistenerCategorie;
import sportify.models.articles.Article;
import sportify.models.articles.Categorie_Article;
import sportify.services.articles.ServiceArticle;
import sportify.services.articles.ServiceCategorieArt;

/**
 * FXML Controller class
 *
 * @author dali
 */
public class CategorieuserController implements Initializable {

    @FXML
    private TextField search;
    @FXML
    private VBox choosarticle;
    @FXML
    private Button buttonadd1;
    @FXML
    private Button buttonadd2;
    @FXML
    private Label articleNameLable;
    @FXML
    private Label articlePriceLabel;
    @FXML
    private ImageView articleImg;
    @FXML
    private Button buttonadd;
    @FXML
    private ScrollPane scroll;
    private Image image;
    @FXML
    private GridPane grid;
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

        List<Categorie_Article> list = new ArrayList();
        for (Categorie_Article a : serviceCategorieArt.display()) {
            if (a.getNom_categorie().toUpperCase().contains(marque.toUpperCase()) || a.getType_categorie().toUpperCase().contains(marque.toUpperCase())) {
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
                fxmlLoader.setLocation(getClass().getResource("../views/Article/ItemCategorie.fxml"));
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

    @FXML
    private void deletbutton(ActionEvent event) {

        ServiceCategorieArt serviceCategorieArt = new ServiceCategorieArt();
        System.out.println(catArt);
        try {
            serviceCategorieArt.DeletCategorie(catArt);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("DEtelt succes");
            alert.setContentText("Delete succes");
            alert.show();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        listArt.clear();
        showScreen();
    }

    @FXML
    private void updatearticle(ActionEvent event) throws IOException {

        ServiceCategorieArt serviceCategorieArt = new ServiceCategorieArt();
        Categorie_Article categorie_Article = new Categorie_Article();
        categorie_Article = serviceCategorieArt.getArticleById(catArt);
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("../views/Article/CatUpdate.fxml"));
        fxmlLoader.load();
        CatUpdateController catUpdateController = fxmlLoader.getController();
        catUpdateController.setTextField(categorie_Article.getId_categorie(), categorie_Article.getNom_categorie(),
                categorie_Article.getType_categorie(), categorie_Article.getImg());
        Parent p = fxmlLoader.getRoot();
        Stage stage = new Stage();
        stage.setTitle("update");
        stage.setScene(new Scene(p));
        stage.show();
    }


    @FXML
    private void hundelbutton(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("../views/Article/Fromcat.fxml"));
            fxmlLoader.load();
            Parent p = fxmlLoader.getRoot();
            Stage stage = new Stage();
            stage.setTitle("ABC");
            stage.setScene(new Scene(p));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void refrechbuton(ActionEvent event) {
        listArt.clear();
        showScreen();
    }



    public void showScreen() {
        ServiceCategorieArt serviceCategorieArt = new ServiceCategorieArt();
        System.out.println("categorietest" + serviceCategorieArt.display());
        listArt.addAll(serviceCategorieArt.display());

        if (listArt.size() > 0) {
            setChosenFruit(listArt.get(0));
            mylistenerCategorie = new MylistenerCategorie() {
                @Override
                public void onClickListener(Categorie_Article categorie_Article) {
                    setChosenFruit(categorie_Article);
                    catArt.setId_categorie(categorie_Article.getId_categorie());
                    catArt.setNom_categorie(categorie_Article.getNom_categorie());
                    catArt.setType_categorie(categorie_Article.getType_categorie());
                    catArt.setImg(categorie_Article.getImg());
                }

            };
        }
        int column = 0;
        int row = 1;
        grid.getChildren().clear();
        try {
            for (int i = 0; i < listArt.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("../views/Article/ItemCategorie.fxml"));
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
    private void consultbutton(ActionEvent event) throws IOException {
        consulter();
        Stage primStage=new Stage(); 
        Parent root=FXMLLoader.load(getClass().getResource("../views/Article/market.fxml")); 
        primStage.setTitle("consulter client");
        primStage.setScene(new Scene(root));
        primStage.show();
    }

    @FXML
    private void retourbutton(ActionEvent event) {
    }

}
