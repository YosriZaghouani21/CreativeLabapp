/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportify.views.user;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sportify.models.user.Reclamation;
import sportify.services.user.ImpServiceReclamation;

/**
 * FXML Controller class
 *
 * @author zagho
 */
public class ModifierReclamationController implements Initializable {

    @FXML
    private TextField IdReclamation;
    @FXML
    private TextField Date;
    @FXML
    private TextField Description;
    @FXML
    private Button modifier;
    @FXML
    private Button Annuler;
    @FXML
    private TextField Id;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void modifier(ActionEvent event) {
        
         String description = Description.getText();
         String date = Date.getText();
        int Idreclamation = Integer.valueOf(IdReclamation.getText());
        int id = Integer.valueOf(Id.getText());

        try {

            System.out.println("Modifié avec succes");
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
            Alert ModifytAlert = new Alert(Alert.AlertType.CONFIRMATION);
            ModifytAlert.setTitle("Modifier Reclamation");
            ModifytAlert.setHeaderText(null);
            ModifytAlert.setContentText("Voulez-vous vraiment modifier cette Reclamation ? ?");
            Optional<ButtonType> optionModifyBookAlert = ModifytAlert.showAndWait();
            if (optionModifyBookAlert.get() == ButtonType.OK) {
                Reclamation r = new Reclamation(IdReclamation, Date, Description, Id);
                ImpServiceReclamation ad = new ImpServiceReclamation();
                ad.modifier(r);
                Alert succModifyAlert = new Alert(Alert.AlertType.INFORMATION);
                succModifyAlert.setTitle("Modifier Reclamation");
                succModifyAlert.setHeaderText("Resultats:");
                succModifyAlert.setContentText("Reclamation Modifiée !");
                succModifyAlert.showAndWait();
            } else if (optionModifyBookAlert.get() == ButtonType.CANCEL) {
            }

        } catch (Exception ex) {
            System.out.println("error : " + ex.getMessage());
        }

    }

    @FXML
    private void cancel(ActionEvent event) throws IOException {
          Stage primaryStage = (Stage) Annuler.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/sportify/views/user/FXMLMain.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    void setData(int id, int idReclamation, String description, String date) {
        Id.setText("" + id);
        IdReclamation.setText("" + idReclamation);
        Description.setText("" + description);
        Date.setText("" + date);
    }

}
