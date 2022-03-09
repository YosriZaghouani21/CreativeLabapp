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
import sportify.models.user.Session;
import sportify.models.user.User;
import sportify.services.user.ImpServiceReclamation;
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
    
    Reclamation rec = new Reclamation();
    private ImpServiceReclamation impr = new ImpServiceReclamation();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Session.getUserStatic();
    }
 

    @FXML
    private void ajouter(ActionEvent event) throws IOException {
            if ((tDate.getText().length() != 0) && (tfDescription.getText().length() != 0)) {
                rec.setReclamateur(Session.UserStatic);
                rec.setDate(tDate.getText());
                rec.setDescription(tfDescription.getText());
                rec.setId(Session.UserStatic.getId());
                System.out.println(Session.UserStatic.getId());
                
               
                impr.ajouter(rec);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ajout de réclamation");
                alert.setContentText("Réclamation ajoutée avec succées!");
                alert.show();
                
         Stage primaryStage = (Stage) Cancel.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/sportify/views/user/FXMLMainClient.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
                
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ajout de réclamation");
                alert.setContentText("Champs invalides.");
                alert.show();
            }
    }
        /*int Id_usr = Session.UserStatic.getId();

        ImpServiceReclamation isr = new ImpServiceReclamation();

        String Date = tDate.getText();
        String Description = tfDescription.getText();
        int Description = tfDescription.();

        Reclamation R = new Reclamation(Date, Description ,IdReclamation, Id_usr );
         if ((tDate.getText().length() != 0) && (tfDescription.getText().length() != 0)) {
             rec.setReclamateur(Session.UserStatic);
        try {
            isr.ajouter(R);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Reclamation is added successfully!");
            alert.show();
            tDate.setText("");
            tfDescription.setText("");
            

            Stage primaryStage = (Stage) Reclamer.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/sportify/views/user/FXMLMainClient.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
    }
        else {
 Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ajout de réclamation");
                alert.setContentText("Champs invalides.");
                alert.show();
}*/
    //}


    @FXML
    private void annuler(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) Cancel.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/sportify/views/user/FXMLMainClient.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    

    
    

}
