/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportify.controllers;

import sportify.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import static sportify.controllers.LivraisonController.isAlpha;
//import static sportify.controllers.ivraisonController.isNumeric;
import sportify.models.Livraison;
import sportify.services.EnvoyerMail;
import sportify.services.livraisonservice;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class PasserlivraisonController implements Initializable {

    @FXML
    private DatePicker date_liv;
    @FXML
    private TextField adresse;
   
    @FXML
    private TextField adresse_comple;
    @FXML
    private TextField code_postale;
    @FXML
    private TextField ville;
    @FXML
    private ComboBox<String> methode_payement;
          private String methode_payement_string;
    @FXML
    private Button payer;
    @FXML
    private Button retour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          ObservableList<String> list = FXCollections.observableArrayList("carte Visa","Master Carte");
        methode_payement.setItems(list);
    }    
    void Select(ActionEvent event) {
      methode_payement_string = methode_payement.getSelectionModel().getSelectedItem().toString();

        
      
    }
    @FXML
     private void passerLivraison(ActionEvent event) throws Exception { 
                livraisonservice  ls= new livraisonservice();
      
                                            
            
           // System.out.println(id_cmd.getText());
        
            Livraison l =new Livraison();
            if ((((methode_payement.getValue()!= null))) && ((isAlpha(adresse.getText()))&& (adresse.getText().length() != 0) ) && ((isAlpha(adresse_comple.getText()))&& (adresse_comple.getText().length() != 0) )
                && (isNumeric(code_postale.getText()))&& ((isAlpha(ville.getText()))&& (ville.getText().length() != 0) )) {
         //int id=Integer.parseInt(id_livr.getText());
          Date date=Date.valueOf(date_liv.getValue()); 
       //   String meth= methode_payement_string; 
         // int id_c= Integer.parseInt(id_cmd.getText()) ;  
          int codepostale=Integer.parseInt(code_postale.getText()) ;
            String meth= methode_payement.getValue();
          
          
          //l.setIdLiv(id);
          l.setDateLivraison(date);
          l.setMethode_payement(meth);
          l.setAdresse(adresse.getText());
          l.setComp_adresse(adresse.getText());
          l.setCode_postal(codepostale);
          l.setVille(ville.getText());
          l.setId(2);

            try {
                ls.Add(l) ;
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Votre Livraison est ajouté ");
           EnvoyerMail.sendMail("salim.sghaier@esprit.tn","Vous avez un nouveau Livraison: \n" +"Son identifiant est:");
            alert.show();
            payerview();
            payer(); 
            adresse.setText("");
            //.setText("");
            date_liv.setValue(null);
          //  id_cmd.setText("");
            //showLivraison();
           
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
     }
                else{
              Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setContentText("Invalid champ!");
            alert.show();
        }}
        
    public void payerview() throws IOException{
        Stage stage =(Stage)payer.getScene().getWindow(); 
        stage.close(); 
        Stage primStage=new Stage(); 
        Parent root=FXMLLoader.load(getClass().getResource("/sportify/views/livraison/Payer.fxml")); 
        primStage.setTitle("Add coammande");
        primStage.setScene(new Scene(root));
        primStage.show();
   
    }
    
    public void payer(){
        Stage stage =(Stage)payer.getScene().getWindow(); 
        stage.close(); 
    }
    @FXML
    public void retourview() throws IOException{
        Stage stage =(Stage)retour.getScene().getWindow(); 
        stage.close(); 
        Stage primStage=new Stage(); 
        Parent root=FXMLLoader.load(getClass().getResource("/sportify/views/livraison/PasserCommande.fxml")); 
        primStage.setTitle("Add coammande");
        primStage.setScene(new Scene(root));
        primStage.show();
    }
    
    public void retour(){
        Stage stage =(Stage)retour.getScene().getWindow(); 
        stage.close(); 
    }

    @FXML
    private void Select1(ActionEvent event) {
    }
    
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
