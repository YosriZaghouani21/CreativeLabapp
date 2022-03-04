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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sportify.models.user.User;
import sportify.services.user.ImpServiceUser;

/**
 * FXML Controller class
 *
 * @author zagho
 */
public class UsereditController implements Initializable {

    @FXML
    private TextField id;
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField adresse;
    @FXML
    private TextField email;
    @FXML
    private TextField password;
    @FXML
    private TextField type;
    @FXML
    private TextField num;
    @FXML
    private Button Annuler;
    @FXML
    private Button Modifier;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

    @FXML
    private void ajouter(ActionEvent event) {
        //if(tfNom.getText().toString().equals("") && tfPrenom.getText().toString().equals("") && tfAdresse.getText().toString().equals("") &&  tfEmail.getText().toString().equals("") &&  tfPassword.getText().toString().equals("") && tfTelephone.getText().toString().equals("") && tfType.getText().toString().equals("") ) {
        //Notification.setText("Wrong credentiels ! ");
        // m.changeScene("/sportify/views/userFXMLLogin.fxml");
        //}
        //else {

        ImpServiceUser isu = new ImpServiceUser();

        String Nom = nom.getText();
        String Prenom = prenom.getText();
        String Adresse = adresse.getText();
        String Email = email.getText();
        String Password = password.getText();
        String Type = type.getText();
        String Num = num.getText();

        User U = new User(Nom, Prenom, Adresse, Email, Password, Num, Type);
        isu.ajouter(U);
        FXMLLoader Loader = new FXMLLoader(getClass().getResource("/sportify/views/user/Affiche.fxml"));
        Parent root;
        try {
            root = Loader.load();
            AfficheController ac = Loader.getController();
            prenom.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

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

    @FXML
    private void cancel(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) Annuler.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/sportify/views/user/FXMLMain.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    
    

}
