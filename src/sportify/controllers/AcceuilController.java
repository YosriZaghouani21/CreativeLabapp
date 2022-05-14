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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class AcceuilController implements Initializable {

    @FXML
    private Button commande;
    @FXML
    private Button livraison;
    @FXML
    private ImageView image1;
    @FXML
    private Button Retour;
 
    /**
     * Initializes the controller class.
     */
    @Override 
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

     public void commmande(){
        Stage stage =(Stage)commande.getScene().getWindow(); 
        stage.close(); 
    }
    @FXML
    public void Commandeview() throws IOException{
        Stage primStage=new Stage(); 
        Parent root=FXMLLoader.load(getClass().getResource("/sportify/views/livraison/main.fxml")); 
        primStage.setTitle("Add coammande");
        primStage.setScene(new Scene(root));
        primStage.show();
    }
     public void livraison(){
        Stage stage =(Stage)livraison.getScene().getWindow(); 
        stage.close(); 
    }
    @FXML
    public void livraisonview() throws IOException{
        Stage primStage=new Stage(); 
        Parent root=FXMLLoader.load(getClass().getResource("/sportify/views/livraison/livraison.fxml")); 
        primStage.setTitle("Add Livraison");
        primStage.setScene(new Scene(root));
        primStage.show();
    }

    @FXML
    private void retourfuction(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) Retour.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/sportify/views/user/FXMLMain.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
