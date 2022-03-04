/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportify.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
 * @author salim
 */
public class AcceuiFXMLController implements Initializable {

    @FXML
    private Button Idcoach;
    @FXML
    private Button idreservation;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
      
     public void coach(){
        Stage stage =(Stage)Idcoach.getScene().getWindow(); 
        stage.close(); 
    }
    @FXML
    public void coachview() throws IOException{
        Stage primStage=new Stage(); 
        Parent root=FXMLLoader.load(getClass().getResource("/sportify/controller/coachFXML.fxml")); 
        primStage.setTitle("Add Livraison");
        primStage.setScene(new Scene(root));
        primStage.show();
    }
     public void Reservation(){
        Stage stage =(Stage)idreservation.getScene().getWindow(); 
        stage.close(); 
    }
    @FXML
    public void Reservationview() throws IOException{
        Stage primStage=new Stage(); 
        Parent root=FXMLLoader.load(getClass().getResource("/sportify/controller/reservationFXML.fxml")); 
        primStage.setTitle("Add Livraison");
        primStage.setScene(new Scene(root));
        primStage.show();
    }
}
