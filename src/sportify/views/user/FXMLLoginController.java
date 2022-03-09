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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sportify.models.user.EnvoyerMail;
import sportify.models.user.Session;
import sportify.models.user.User;
import sportify.services.user.ImpServiceUser;

/**
 * FXML Controller class
 *
 * @author zagho
 */
public class FXMLLoginController implements Initializable {

    @FXML
    private Button ajouter;
    @FXML
    private PasswordField tfPassword;
    @FXML
    private Label Notification;
    @FXML
    private Button Annuler;
    @FXML
    private TextField tfNom;
    @FXML
    private Button ForgetPasswd;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void ajouter(ActionEvent event) throws IOException {

        User u = new User();
        if (tfNom.getText().toString().equals("") || tfPassword.getText().toString().equals("")) {
            Notification.setText("Wrong credentiels ! ");
            Notification.setTextFill(Color.web("#F00000"));
        } else {

            ImpServiceUser isu = new ImpServiceUser();
            String Nom = tfNom.getText();
            String Password = tfPassword.getText();
            User U = new User(Nom, Password);

            try {
                u = isu.returnuser(Nom, Password);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setContentText("User is logged successfully!");
                alert.show();

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
            System.out.println(u);
            if (("Admin".equals(u.getType()))) {
                System.out.println("Connected as Admin");
                Stage primaryStage = (Stage) ajouter.getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("/sportify/views/user/FXMLMain.fxml"));
                Scene scene = new Scene(root);
                primaryStage.setScene(scene);
                primaryStage.show();
                Session.setUserStatic(u);

            } else {
                System.out.println("Connceted as Client");
                Stage primaryStage = (Stage) ajouter.getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("/sportify/views/user/FXMLMainClient.fxml"));
                Scene scene = new Scene(root);
                primaryStage.setScene(scene);
                primaryStage.show();
                Session.setUserStatic(u);

            }

        }
    }

    @FXML
    private void Retour(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) Annuler.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/sportify/views/user/FXMLAccount.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    private void TakemeToForgetpasswd(ActionEvent event) throws IOException, Exception {
        EnvoyerMail.sendMail("yosri.zaghouani@esprit.tn","Votre code est : "+ Session.getVerificationCode() );
        ImpServiceUser su = new ImpServiceUser();
        User u1 = su.GetByName(tfNom.getText());
        Session.setUserStatic(u1);
        System.out.println(u1.toString());
        Stage primaryStage = (Stage) ForgetPasswd.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/sportify/views/user/ResetPassword.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
