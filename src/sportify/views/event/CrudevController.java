/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportify.views.event;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class CrudevController implements Initializable {

    @FXML
    private Button back2;
    @FXML
    private Button interajout;
    @FXML
    private Button interaff;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void back2(ActionEvent event) throws IOException {
       Stage primaryStage = (Stage) back2.getScene().getWindow(); 
       Parent root = FXMLLoader.load(getClass().getResource("/sportify/views/evenCatg/evenCatg.fxml"));
       Scene scene = new Scene (root);
       primaryStage.setScene(scene);
       primaryStage.show();
    }

    @FXML
    private void interajout(ActionEvent event) throws IOException {
      Stage primaryStage = (Stage) interajout.getScene().getWindow(); 
       Parent root = FXMLLoader.load(getClass().getResource("/sportify/views/event/event.fxml"));
       Scene scene = new Scene (root);
       primaryStage.setScene(scene);
       primaryStage.show();
    }

    @FXML
    private void interaff(ActionEvent event) throws IOException {
      Stage primaryStage = (Stage) interaff.getScene().getWindow(); 
       Parent root = FXMLLoader.load(getClass().getResource("AfficherEv.fxml"));
       Scene scene = new Scene (root);
       primaryStage.setScene(scene);
       primaryStage.show();
     
    }

    
}
