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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sportify.models.user.Session;
import sportify.models.user.User;
import sportify.services.user.ImpServiceUser;

/**
 * FXML Controller class
 *
 * @author zagho
 */
public class FXMLMainClientController implements Initializable {

    @FXML
    private Button Editusr;
    @FXML
    private Button btnReclamation;
    @FXML
    private Button Rapport;
    User u = new User();
    @FXML
    private Button terrains;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    private void Edit_User(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) Editusr.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/sportify/views/user/Useredit.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    private void srcReclamation(ActionEvent event) {
    }

    @FXML
    private void Repporter(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) Rapport.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/sportify/views/user/Reports.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        ImpServiceUser su = new ImpServiceUser();
        Session.getUserStatic();

    }

    @FXML
    private void TakemeToCoach(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) Rapport.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/sportify/views/CoachClient.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        ImpServiceUser su = new ImpServiceUser();
        Session.getUserStatic();
    }

    @FXML
    private void TakemeToLivraison(ActionEvent event) {
    }

    @FXML
    private void TakemeToGestionarticle(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) Rapport.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/sportify/views/Article/CategorieClient.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        ImpServiceUser su = new ImpServiceUser();
        Session.getUserStatic();
        
    }

    @FXML
    private void TakemeToEvents(ActionEvent event) {
    }

    @FXML
    private void TakemeToTerrains(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) Rapport.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/sportify/views/TerrainClFXML.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        ImpServiceUser su = new ImpServiceUser();
        Session.getUserStatic();
    }

}
