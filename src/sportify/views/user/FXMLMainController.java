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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author zagho
 */
public class FXMLMainController implements Initializable {

    @FXML
    private Button Editusr;
    @FXML
    private Button btnReclamation;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void Edit_User(ActionEvent event) throws IOException {

        Stage primaryStage = (Stage) Editusr.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/sportify/views/user/Affiche.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    @FXML
    private void srcReclamation(ActionEvent event) throws IOException {

        Stage primaryStage = (Stage) Editusr.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/sportify/views/user/Reclamation.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
