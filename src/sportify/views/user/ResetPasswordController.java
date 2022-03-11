/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportify.views.user;

import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sportify.models.user.Session;
import sportify.models.user.User;
import sportify.services.user.ImpServiceUser;

/**
 * FXML Controller class
 *
 * @author zagho
 */
public class ResetPasswordController implements Initializable {

    @FXML
    private TextField VerifCode;
    @FXML
    private Button changePwdButton;
    @FXML
    private PasswordField NewPassword;
    @FXML
    private PasswordField ConfirmedPassword;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private User ResetPassword(ActionEvent event) throws IOException, NoSuchAlgorithmException, NoSuchProviderException {

        ImpServiceUser su = new ImpServiceUser();
        User u = Session.getUserStatic();
        System.out.println("here    " + u.toString());
        User u1 = new User();

        if (!VerifCode.getText().equals(Session.getVerificationCode())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Verification Code is invalid!!");
            alert.show();
        } else {
            if (NewPassword.getText().equals(ConfirmedPassword.getText()) && (ConfirmedPassword.getText().length() != 0) && (NewPassword.getText().length() != 0)) {
                u1 = su.GetByName(u.getNom());

                su.modifier(u);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Upddate");
                alert.setContentText("Password is updated successfully./nRedirecting to login page.");
                alert.show();
                
         Stage primaryStage = (Stage) changePwdButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/sportify/views/user/FXMLLogin.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Enter / confirm your new password!!");
                alert.show();

            }

        }

        return u;
    }

}
