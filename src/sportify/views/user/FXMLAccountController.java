/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportify.views.user;

import com.sun.javaws.Main;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sportify.models.user.EnvoyerMail;
import sportify.models.user.User;
import sportify.services.user.ImpServiceUser;
import sportify1.Sportify1;

/**
 * FXML Controller class
 *
 * @author zagho
 */
public class FXMLAccountController implements Initializable {

    private TextField Nom;
    private TextField Prenom;
    private TextField Adresse;
    private TextField Email;
    private PasswordField Password;

    @FXML
    private Button Ajout;

    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfPrenom;
    @FXML
    private TextField tfAdresse;
    @FXML
    private TextField tfEmail;
    @FXML
    private PasswordField tfPassword;
    @FXML
    private TextField tfTelephone;
    @FXML
    private Button Cancel;
    @FXML
    private Label Notification;
    @FXML
    private Button Connecter;
    @FXML
    private Label Emailv;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        emailValidator(tfEmail,Emailv);
    }
    
   
      
         void emailValidator(TextField tf,Label label){
        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
         tf.textProperty().addListener((observable, oldValue, newValue) -> {
Pattern pattern = Pattern.compile(regex);
        if (!pattern.matcher(tfEmail.getText()).matches()) {
            label.setText("Veuillez insérer une adresse mail valide");
            label.setTextFill(Color.web("#F00000"));

        }
                 else{
            label.setText("");
            
        }
        });
        
    }
    

    @FXML
    private void ajouter(ActionEvent event) {
        

        if (tfNom.getText().toString().equals("") || tfPrenom.getText().toString().equals("") || tfAdresse.getText().toString().equals("") || tfEmail.getText().toString().equals("") || tfPassword.getText().toString().equals("") || tfTelephone.getText().toString().equals("")) {
            Notification.setText("Wrong credentiels or email not valid ! ");
             Notification.setTextFill(Color.web("#F00000"));

        } else {

            ImpServiceUser isu = new ImpServiceUser();

            String Nom = tfNom.getText();
            String Prenom = tfPrenom.getText();
            String Adresse = tfAdresse.getText();
            String Email = tfEmail.getText();
            String Password = tfPassword.getText();
            String Num = tfTelephone.getText();

            User U = new User(Nom, Prenom, Adresse, Email, Password, "Client", Num);

            try {
                isu.ajouter(U);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setContentText("User is added successfully!");
                alert.show();
                tfNom.setText("");
                tfPrenom.setText("");
                tfAdresse.setText("");
                tfEmail.setText("");
                tfPassword.setText("");
                tfTelephone.setText("");

                Stage primaryStage = (Stage) Ajout.getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("/sportify/views/user/FXMLLogin.fxml"));
                Scene scene = new Scene(root);
                primaryStage.setScene(scene);
                primaryStage.show();
                //EnvoyerMail.sendMail("yosri.zaghouani@esprit.tn","Vous avez un nouveau client:" );
                EnvoyerMail.sendMail("yosri.zaghouani@esprit.tn", "Vous avez un nouveau client: \n" + "Son Nom est:" + tfNom.getText() + "\n Son Prenom est:" + tfPrenom.getText() + "\n son email est:" + tfEmail.getText());

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }

    }

    @FXML
    private void annuler(ActionEvent event) {
        Stage stage = (Stage) Cancel.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void connecter(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) Ajout.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/sportify/views/user/FXMLLogin.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

       private boolean isEmailAdress(String text) {
        throw new UnsupportedOperationException("Not supported yet."); 
      }
  

 

}
