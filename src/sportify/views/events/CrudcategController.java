/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportify.views.events;

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
public class CrudcategController implements Initializable {

    @FXML
    private Button interAjout;
    @FXML
    private Button interAffiche;
    @FXML
    private Button back1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void interajout(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) interAjout.getScene().getWindow(); 
       Parent root = FXMLLoader.load(getClass().getResource("/sportify/views/events/eventesview.fxml"));
       Scene scene = new Scene (root);
       primaryStage.setScene(scene);
       primaryStage.show();
    }

    @FXML
    private void interAffiche(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) interAffiche.getScene().getWindow(); 
       Parent root = FXMLLoader.load(getClass().getResource("Afficher.fxml"));
       Scene scene = new Scene (root);
       primaryStage.setScene(scene);
       primaryStage.show();
      
    }


    @FXML
    private void back1(ActionEvent event) throws IOException {
       Stage primaryStage = (Stage) back1.getScene().getWindow(); 
       Parent root = FXMLLoader.load(getClass().getResource("/sportify/views/evenCatg/evenCatg.fxml"));
       Scene scene = new Scene (root);
       primaryStage.setScene(scene);
       primaryStage.show();
    }
    
}
