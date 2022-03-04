/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportify.controller;

import com.itextpdf.text.DocumentException;
import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import sportify.model.Commande;
import sportify.service.commandeservice;
import sportify.service.livraisonservice;

/**
 *
 * @author lenovo
 */
public class Sportify extends Application {
    
    
    Parent parent;
    Stage stage; 
       
    
    @Override
    public void start(Stage primaryStage) {
        this.stage = primaryStage;
        try {
            parent = FXMLLoader.load(getClass().getResource("/sportify/acceuil.fxml"));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.setTitle("Add and Show Commande");
        stage.show();

    }
   

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    } 
}