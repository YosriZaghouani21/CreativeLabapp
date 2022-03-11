/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportify.controllers;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.model.PaymentMethod;
import com.stripe.model.Token;
import java.io.IOException;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class PayerController implements Initializable {
    @FXML
    private TextField carte;
    @FXML
    private TextField month;
    @FXML
    private TextField cvc;
    @FXML
    private TextField year;
    @FXML
    private TextField prix;
    @FXML
    private Button valider;
    @FXML
    private Button Annuler;
    
    /**
     * Initializes the controller class.
     */



    @Override
    public void initialize(URL url, ResourceBundle rb) {
    //    int aa=FXMLpopuplogController.getLog().getPrix();
    //    prix.setText(String.valueOf(aa));
        
        valider.setOnAction((ActionEvent event) -> {
           
            
            try {
               
             if (controleDeSaisi()) {
              
            if (carte.getText().isEmpty()) {
                carte.setText("");
            }
            if (month.getText().isEmpty()) {
                month.setText("");
            }
            if (year.getText().isEmpty()) {
                year.setText("");
            }
            if (cvc.getText().isEmpty()) {
                cvc.setText("");
            }   
            
               }
                payer(); 
             
        
        }   catch (StripeException ex) {
                Logger.getLogger(PayerController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(PayerController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        
       Annuler.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                     Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
        s.close();

            }

        });
        
    
    }
     private boolean controleDeSaisi() throws IOException {  

        if (carte.getText().isEmpty() || month.getText().isEmpty() || year.getText().isEmpty()
                || cvc.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Veuillez bien remplir tous les champs !");
            return false;
        } else {

            if (!Pattern.matches("[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]", carte.getText())) {
                showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Vérifiez la reference ! ");
                carte.requestFocus();
                carte.selectEnd();
                return false;
            }

           if (!Pattern.matches("[0-9]*", month.getText())) {
                showAlert(Alert.AlertType.ERROR, "Données ", "Verifier les données", "Vérifiez le Mois ! ");
                month.requestFocus();
                month.selectEnd();
                return false;
            }if (!Pattern.matches("[0-9]*", year.getText())) {
                showAlert(Alert.AlertType.ERROR, "Données ", "Verifier les données", "Vérifiez l'année ! ");
                year.requestFocus();
                year.selectEnd();
                return false;
            }if (!Pattern.matches("[0-9]*", cvc.getText())) {
                showAlert(Alert.AlertType.ERROR, "Données ", "Verifier les données", "Vérifiez le cvc ! ");
                cvc.requestFocus();
                cvc.selectEnd();
                return false;
            }
           
        }
        return true;
    }
    
    public static void showAlert(Alert.AlertType type, String title, String header, String text) throws IOException {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(text);
        alert.showAndWait();
        
                
        

    }
    public void payer() throws StripeException, IOException
    { 
        Stripe.apiKey = "sk_test_51Kb2KGCAYowVw87d1Wd5zKjoRxjGECBWmhKAth9axa1tpLsxbzuCmhA0UDYb5jLm1t8xoipwJd0HJ5TYJnsYYAgj00VwaBW82a";
        Map<String, Object> card = new HashMap<>();
        card.put("number", "4242424242424242");
        card.put("exp_month", 3);
        card.put("exp_year", 2023);
        card.put("cvc", "314");

        Map<String, Object> params = new HashMap<>();
        params.put("type", "card");
        params.put("card", card);
            PaymentMethod paymentMethod = PaymentMethod.create(params);
        Map<String, Object> params1 = new HashMap<>();
        params1.put("amount",Integer.parseInt(prix.getText())*100) ;
        params1.put("currency", "usd");
        params1.put("source", "tok_visa");
        params1.put(
                "description",
                "Paiement effectué "
        );

        Charge charge = Charge.create(params1);
   showAlert(Alert.AlertType.CONFIRMATION, "Données Valide", "Success", "Payment avec succes \n Merci pour votre Confiance!");
    Stage primaryStage = (Stage) valider.getScene().getWindow();
   
  
        
    }
   

        
    }
    
     
    

