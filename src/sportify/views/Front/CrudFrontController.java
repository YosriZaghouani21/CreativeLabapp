/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportify.views.Front;

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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class CrudFrontController implements Initializable {

    @FXML
    private Button Editusr;
    @FXML
    private Button btnReclamation;
    @FXML
    private Button Evenement;
    @FXML
    private Button retour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Edit_User(ActionEvent event) {
    }

    @FXML
    private void srcReclamation(ActionEvent event) {
    }

    @FXML
    private void Evenement(ActionEvent event) throws IOException {
         Stage primaryStage = (Stage) Evenement.getScene().getWindow(); 
       Parent root = FXMLLoader.load(getClass().getResource("/sportify/views/Front/AfficherFront.fxml"));
       Scene scene = new Scene (root);
       primaryStage.setScene(scene);
       primaryStage.show();
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) retour.getScene().getWindow(); 
       Parent root = FXMLLoader.load(getClass().getResource("/sportify/views/evenCatg/evenCatg.fxml"));
       Scene scene = new Scene (root);
       primaryStage.setScene(scene);
       primaryStage.show();
    }
    
}
