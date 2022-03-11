/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportify1;

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
import sportify.models.Commande;
import sportify.services.commandeservice;
import sportify.services.livraisonservice;

/**
 *
 * @author lenovo
 */
public class Sportify1 extends Application {
    
    

    
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        
      //  this.stage = primaryStage;
        
          Parent root = FXMLLoader.load(getClass().getResource("/sportify/views/livraison/PasserCommande.fxml"));
       primaryStage.setTitle("Livriason");
       primaryStage.setScene(new Scene(root));
       primaryStage.show(); 
       
     
        
        
        
     
    } 
   

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }  
    
}