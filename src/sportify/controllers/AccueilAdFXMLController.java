/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportify.controllers;

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
 * @author MYLAPTOP
 */
public class AccueilAdFXMLController implements Initializable {

    @FXML
    private Button AccT;
    @FXML
    private Button AccR;
    
    @FXML
    private Button Retour;
    
    Stage dialogStage= new Stage();
    Scene scene;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void GoT(ActionEvent event) throws IOException {
        Node source = (Node) event.getSource();
                            dialogStage = (Stage) source.getScene().getWindow();
                            dialogStage.close();
                            scene = new Scene(FXMLLoader.load(getClass().getResource("/sportify/views/TerrainAdFXML.fxml")));
                            dialogStage.setScene(scene);
                            dialogStage.show();
        
    }
    @FXML
    private void GoR(ActionEvent event) throws IOException {
         Node source = (Node) event.getSource();
                            dialogStage = (Stage) source.getScene().getWindow();
                            dialogStage.close();
                            scene = new Scene(FXMLLoader.load(getClass().getResource("/sportify/views/ReservationAdFXML.fxml")));
                            dialogStage.setScene(scene);
                            dialogStage.show();
    }
    
    @FXML
    private void RetourP(ActionEvent event) throws IOException {
        
        Node source = (Node) event.getSource();
                            dialogStage = (Stage) source.getScene().getWindow();
                            dialogStage.close();
                            scene = new Scene(FXMLLoader.load(getClass().getResource("/sportify/views/Accueil.fxml")));
                            dialogStage.setScene(scene);
                            dialogStage.show();
    }
    
}
