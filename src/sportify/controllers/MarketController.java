package sportify.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import sportify.mainart.Main;
import sportify.mainart.MyListener;
import sportify.models.articles.Article;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sportify.models.articles.Panier;
import sportify.models.user.Session;
import sportify.services.articles.ServiceArticle;
import sportify.services.articles.ServicePanier;

public class MarketController implements Initializable {

    private String idcat;
    private String desc;
    private String prix;
    private Parent root;
    private Stage stage;
    private Scene scene;
    private String marque;
    private String img;

    @FXML
    private GridPane grid;

    private List<Article> listArt = new ArrayList<>();
    @FXML
    private VBox choosarticle;
    @FXML
    private Label articleNameLable;
    @FXML
    private Label articlePriceLabel;
    @FXML
    private ImageView articleImg;

    @FXML
    private Button buttonadd1;
    @FXML
    private Button buttonadd2;
    @FXML
    private Button buttonadd;
    @FXML
    private ScrollPane scroll;
    @FXML
    private TextField search;

    private Image image;
    private MyListener myListener;
    Article art = new Article();
    @FXML
    private ComboBox<String> idcombo;
    @FXML
    private Button retour;

    private void setChosenFruit(Article article) {
        articleNameLable.setText(article.getMarque());
        articlePriceLabel.setText(Main.CURRENCY + article.getPrix_article());
        image = new Image(getClass().getResourceAsStream(article.getPathImg()));
        articleImg.setImage(image);
        choosarticle.setStyle("-fx-background-radius: 30;");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showScreen();
        idcombo.getItems().add("Alphabet");
        idcombo.getItems().add("Prix");
        Session.getUserStatic();
        System.out.println(Session.getUserStatic().getId() + "Client en Reservation client");

    }

    @FXML
    private void hundelbutton(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/sportify/views/Article/Formulaire.fxml"));
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
    private void deletbutton(ActionEvent event) {
        ServiceArticle ser = new ServiceArticle();
        System.out.println(art);
        try {
            ser.DeletArticle(art);
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
        ServiceArticle ser = new ServiceArticle();
        Article article = new Article();
        article = ser.getArticleById(art);

        this.marque = article.getDescription();
        System.out.println(this.marque);

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/sportify/views/Article/update.fxml"));
        fxmlLoader.load();
        UpdateController updateController = fxmlLoader.getController();
        updateController.setTextField(article.getRef_articl(), article.getMarque(),
                article.getPrix_article(), article.getDescription(), article.getId_categorie(),
                article.getPathImg());
        Parent p = fxmlLoader.getRoot();
        Stage stage = new Stage();
        stage.setTitle("update");
        stage.setScene(new Scene(p));
        stage.show();

    }

    public void showScreen() {
        ServiceArticle serviceArticle = new ServiceArticle();
        System.out.println("test" + serviceArticle.display());
        listArt.addAll(serviceArticle.display());

        if (listArt.size() > 0) {
            setChosenFruit(listArt.get(0));
            myListener = new MyListener() {
                @Override
                public void onClickListener(Article article) {
                    setChosenFruit(article);
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
    private void refrechbuton(ActionEvent event) {
        listArt.clear();
        showScreen();
    }

    @FXML
    private void searchButton(ActionEvent event) {
        listArt.clear();
        String marque = search.getText();
        ServiceArticle serviceArticle = new ServiceArticle();
        System.out.println("recherche" + serviceArticle.FindArticle(marque));
        List<Article> list = new ArrayList();
        for (Article a : serviceArticle.display()) {
            if (a.getMarque().toUpperCase().contains(marque.toUpperCase()) || a.getDescription().toUpperCase().contains(marque.toUpperCase())) {
                list.add(a);
            }
        }
        listArt.addAll(list);

        if (listArt.size() > 0) {
            setChosenFruit(listArt.get(0));
            myListener = new MyListener() {
                @Override
                public void onClickListener(Article article) {
                    setChosenFruit(article);
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
    private void trierarticle(ActionEvent event) {
        listArt.clear();
        ServiceArticle serviceArticle = new ServiceArticle();

        if (idcombo.getValue().equals("Alphabet")) {
            listArt.addAll(serviceArticle.TreeAticleListe());
        } else if (idcombo.getValue().equals("Prix")) {
            listArt.addAll(serviceArticle.TreeAticlePrixListe());
        }

        if (listArt.size() > 0) {
            setChosenFruit(listArt.get(0));
            myListener = new MyListener() {
                @Override
                public void onClickListener(Article article) {
                    setChosenFruit(article);
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
    private void addpanier(ActionEvent event) throws SQLException {
        ServicePanier servicePanier = new ServicePanier();

        Panier panier = new Panier();
        //servicePanier.DeletPanier(panier);
        panier.setRef_panier(art.getRef_articl());
        panier.setId_users(Session.getUserStatic().getId());
        servicePanier.AddPanier(panier);

    }

    @FXML
    private void panierFunction(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/sportify/views/Article/panier.fxml"));
            fxmlLoader.load();
            Parent p = fxmlLoader.getRoot();
            Stage stage = new Stage();
            stage.setTitle("panier");
            stage.setScene(new Scene(p));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void sms(ActionEvent event) {
        ServiceArticle serviceArticle = new ServiceArticle();
        serviceArticle.sms();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("SMS");
        alert.setContentText("SMS envoyer a tous les clients");
        alert.show();
    }

    private void consulter() {

        Stage stage = (Stage) retour.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void retourbutton(ActionEvent event) throws IOException {
        consulter();
        Stage primStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/sportify/views/Article/Categorieuser.fxml"));
        primStage.setTitle("consulter client");
        primStage.setScene(new Scene(root));
        primStage.show();
    }

}
