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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sportify.models.user.EnvoyerMail;
import sportify.models.user.Reclamation;
import sportify.models.user.User;
import sportify.services.user.ImpServiceUser;

/**
 * FXML Controller class
 *
 * @author zagho
 */
public class ReportsController implements Initializable {

    @FXML
    private Label Notification;
    @FXML
    private TextField tDate;
    @FXML
    private TextField tfDescription;
    @FXML
    private Button Reclamer;
    @FXML
    private Button Cancel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void ajouter(ActionEvent event) {

        ImpServiceUser isu = new ImpServiceUser();

        String Date = tDate.getText();
        String Description = tfDescription.getText();

        Reclamation R = new Reclamation(Date, Description, 69);

        try {
            isu.ajouter(R);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Reclamation is added successfully!");
            alert.show();
            tDate.setText("");
            tfDescription.setText("");

            Notification.setText("Veuillez insérer une adresse mail valide");
            Notification.setTextFill(Color.web("#F00000"));

            Stage primaryStage = (Stage) Reclamer.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/sportify/views/user/FXMLMainClient.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();

            //EnvoyerMail.sendMail("yosri.zaghouani@esprit.tn", "Vous avez un nouveau client: \n" + "Son Nom est:" + tfNom.getText() + "\n Son Prenom est:" + tfPrenom.getText() + "\n son email est:" + tfEmail.getText());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void annuler(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) Cancel.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/sportify/views/user/FXMLMainClient.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
