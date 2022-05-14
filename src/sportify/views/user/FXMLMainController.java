/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportify.views.user;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author zagho
 */
public class FXMLMainController implements Initializable {

    @FXML
    private Button Editusr;
    @FXML
    private Button btnReclamation;

    @FXML
    private Button coachbtn;
    @FXML
    private Button article;
    @FXML
    private Button terrain;
    @FXML
    private Button commade;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void Edit_User(ActionEvent event) throws IOException {

        Stage primaryStage = (Stage) Editusr.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/sportify/views/user/Affiche.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    @FXML
    private void srcReclamation(ActionEvent event) throws IOException {

        Stage primaryStage = (Stage) Editusr.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/sportify/views/user/Reclamation.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }



    @FXML
    private void Backend_coach(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) coachbtn.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/sportify/views/marketCoach.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    private void goCategorieUser(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) article.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/sportify/views/Article/Categorieuser.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    private void goTerrain(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) article.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/sportify/views/TerrainAdFXML.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }

    @FXML
    private void gocommade(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) commade.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/sportify/views/livraison/acceuil.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
