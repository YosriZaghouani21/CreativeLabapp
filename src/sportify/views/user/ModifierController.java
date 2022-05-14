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
import sportify.models.user.User;
import sportify.services.user.ImpServiceUser;

/**
 * FXML Controller class
 *
 * @author zagho
 */
public class ModifierController implements Initializable {

    @FXML
    private Button modifier;

    @FXML
    private TextField id;
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;

    @FXML
    private TextField email;
    @FXML
    private TextField adresse;

    @FXML
    private TextField password;
    @FXML
    private TextField type;
    @FXML
    private TextField num;
    @FXML
    private Button Annuler;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void modifier(ActionEvent event) {
        int Id = Integer.valueOf(id.getText());
        String Nom = nom.getText();
        String Prenom = prenom.getText();
        String Email = email.getText();
        String Adresse = adresse.getText();
        String Type = type.getText();
        String Password = password.getText();
        String Num = num.getText();

        try {

            System.out.println("Modifié avec succes");
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
            Alert ModifytAlert = new Alert(Alert.AlertType.CONFIRMATION);
            ModifytAlert.setTitle("Modifier Client");
            ModifytAlert.setHeaderText(null);
            ModifytAlert.setContentText("Voulez-vous vraiment modifier ce client ? ?");
            Optional<ButtonType> optionModifyBookAlert = ModifytAlert.showAndWait();
            if (optionModifyBookAlert.get() == ButtonType.OK) {
                User r = new User(Id, Nom, Prenom, Adresse, Email, Password, Type, Num);
                ImpServiceUser ad = new ImpServiceUser();
                ad.modifier(r);
                Alert succModifyAlert = new Alert(Alert.AlertType.INFORMATION);
                succModifyAlert.setTitle("Modifier Client");
                succModifyAlert.setHeaderText("Resultats:");
                succModifyAlert.setContentText("Client Modifié !");
                succModifyAlert.showAndWait();
            } else if (optionModifyBookAlert.get() == ButtonType.CANCEL) {
            }

        } catch (Exception ex) {
            System.out.println("error : " + ex.getMessage());
        }

    }

    public void setData(int Id, String Nom, String Prenom, String Adresse, String Email, String Password, String Type, String Num) {

        id.setText("" + Id);
        nom.setText("" + Nom);
        prenom.setText("" + Prenom);
        email.setText("" + Adresse);
        adresse.setText("" + Email);
        password.setText("" + Password);
        type.setText("" + Type);
        num.setText("" + Num);
    }

    @FXML
    private void cancel(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) Annuler.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/sportify/views/user/FXMLMain.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
