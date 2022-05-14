/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportify1;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author zagho
 */
public class Sportify1 extends Application {
    Parent parent;
    Stage stage;
    public static final String CURRENCY = "$";
    @Override
    public void start(Stage primaryStage) {
   
        this.stage = primaryStage;
        primaryStage.setResizable(false);
        try {
            parent = FXMLLoader.load(getClass().getResource("/sportify/views/user/FXMLAccount.fxml"));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.setTitle("Création Comptes");
        stage.show();
        
        

    }
    
    public void changeScene(String fxml) throws IOException {
    Parent pane = FXMLLoader.load(getClass().getResource(fxml));
    stage.getScene().setRoot(pane);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
