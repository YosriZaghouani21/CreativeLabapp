/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportify.controllers;

import com.stripe.exception.StripeException;
import com.sun.javafx.image.impl.IntArgb;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import static sportify.controllers.MainController.isNumeric;
import static sportify.controllers.PayerController.showAlert;
import sportify.models.Commande;
//import sportify.models.user;
import sportify.services.commandeservice;
//import sportify.services.userservice;
import sportify1.Config; 

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class PasserCommandeController implements Initializable {

    @FXML
    private Label prix;
    @FXML
    private TextField prenom;
    @FXML
    private TextField email;
    @FXML
    private TextField numero;
   
   
    @FXML
    private TextField nom;
    @FXML
    private TextField promo;
    @FXML
    private Button promotion;
    @FXML
    private Button passer_cmd;
    @FXML
    private Button mise_a_jour;
    @FXML
    private Button retour;
    
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                //bservableList<String> list = FXCollections.observableArrayList("Par Carte","Cache","Paypal");
                    //  type_paiement.setItems(list);
        // TODO
showuser();
 showprix();

 passer_cmd.setOnAction((ActionEvent event) -> {
           
     try {
         if (controle()) {
             
             if (nom.getText().isEmpty()) {
                 nom.setText("");
             }
             if (prenom.getText().isEmpty()) {
                 prenom.setText("");
             }
             if (email.getText().isEmpty()) {
                 email.setText("");
             }
         
             
         }
     } catch (IOException ex) {
  
     }
            try {
               passercommande();
      
      
               
            
              
             
        
        } catch (SQLException ex) {    
            
         Logger.getLogger(PasserCommandeController.class.getName()).log(Level.SEVERE, null, ex);
     } catch (IOException ex) {
         Logger.getLogger(PasserCommandeController.class.getName()).log(Level.SEVERE, null, ex);
     }   
        });
        
        
   
        


  
    }    
  
     public void continuer(){
        Stage stage =(Stage)passer_cmd.getScene().getWindow(); 
        stage.close(); 
    }
    public void continuerView() throws IOException{ 
         
        
        Stage primStage=new Stage(); 
        Parent root=FXMLLoader.load(getClass().getResource("/sportify/views/livraison/passerlivraison.fxml")); 
        primStage.setTitle("Add coammande");
        primStage.setScene(new Scene(root));
        primStage.show();
    }

   /*    public void payerview() throws IOException{
       //  Stage stage =(Stage)type_paiement.getScene().getWindow(); 
        stage.close(); 
        Stage primStage=new Stage(); 
        Parent root=FXMLLoader.load(getClass().getResource("/sportify/Payer.fxml")); 
        primStage.setTitle("Add coammande");
        primStage.setScene(new Scene(root));
        primStage.show();
    }
    
    public void payer(){
        Stage stage =(Stage)type_paiement.getScene().getWindow(); 
        stage.close(); 
    }
    
    void Select(ActionEvent event) throws IOException {
      if ( type_paiement.getSelectionModel().getSelectedItem().contentEquals("Par Carte")) {
          payer();
          payerview();
      } else { 
        }
        */
public void showuser()
{ 
   // user u = new user("Salim","Sghaier","ariana","mohamedjridi99@yahoo.fr","mohamedjridi99@yahoo.fr","2430",24930942);  
//userservice us=new userservice(); 

//nom.setText(u.getNom());
//prenom.setText(u.getPrenom()); 
//email.setText(u.getEmail());
//numero.setText(String.valueOf(u.getNum()));

}
    @FXML
    public void showprix()
{
           Commande C = new Commande(38,(float) 5.5, 1, 1);
           commandeservice cs = new commandeservice(); 
     
          
      float ff=C.getPrixTotale()-(C.getPrixTotale()*10/100); 
    String p="promo";
    System.out.println(ff);
   String pp= promo.getText() ; 
    if (p.equals(pp)) {
          prix.setText(String.valueOf(ff));
}else {
             prix.setText(String.valueOf(C.getPrixTotale()));
    }
    
}
   public static boolean isAlpha(String s) {
        return s != null && s.matches("^[a-zA-Z]*$");
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
        
private boolean controle() throws IOException { 
       if (((isAlpha(nom.getText()) && ((nom.getText().length() != 0))) && ((isAlpha(prenom.getText()))) && ((prenom.getText().length() != 0)) && ((isNumeric(numero.getText())))&&((numero.getText().length() != 0)))) { 
           return true ; 

}
              else{
              Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setContentText("Invalid fields!");
            alert.show();
        }
       
    return false ; 

}  
void emailValidator(TextField tf){
        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
         tf.textProperty().addListener((observable, oldValue, newValue) -> {
Pattern pattern = Pattern.compile(regex);
        if (!pattern.matcher(email.getText()).matches()) {
           // label.setText("Veuillez insérer une adresse mail valide");
           // label.setTextFill(Color.web("#F00000"));
     Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setTitle("Error");
            alert.setContentText("Veuillez insérer une adresse mail valide fields!");
            alert.show();
        }
                 else{
         //   label.setText("");
            
        }
        });
        
    } 
  public static void showAlert(Alert.AlertType type, String title, String header, String text) throws IOException {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(text);
        alert.showAndWait();
        
                
        

    }   

public void passercommande () throws SQLException, IOException
{ 
   Commande c = new Commande (); 
   commandeservice cs = new commandeservice();  
     if (((isAlpha(nom.getText()) && ((nom.getText().length() != 0))) && ((isAlpha(prenom.getText()))) && ((prenom.getText().length() != 0)) && ((isNumeric(numero.getText())))&&((numero.getText().length() != 0))))
     {   c.setIdClient(22);
   c.setId_panier(13);
float f=Float.parseFloat(prix.getText()); 
c.setPrixTotale(f); 
      try {
       
                cs.Add(c) ;
               continuerView();
      continuer();  
       
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
      continuerView();
      continuer();
        }
     } else{
              Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setContentText("Invalid fields!");
            alert.show();
        }
}

    @FXML
    private void update_user(ActionEvent event) {
    }

    @FXML
    private void retourfunction(ActionEvent event) {
    }
}
    //@FXML
   /* public void update_user() throws SQLException
{  if (((isAlpha(nom.getText()) && ((nom.getText().length() != 0))) && ((isAlpha(prenom.getText()))) && ((prenom.getText().length() != 0)) && ((isNumeric(numero.getText())))&&((numero.getText().length() != 0))))
{
     
    user u =new user(); 
    userservice us=new userservice(); 
  u.setId(1);
   u.setEmail(email.getText());
   u.setNom(nom.getText());
   u.setPrenom(prenom.getText());
   int num=Integer.parseInt(numero.getText()) ; 
   u.setNum(num);

  
 try{
   us.modifier(u);
     nom.setText(u.getNom());
     prenom.setText(u.getPrenom());
     email.setText(u.getEmail());
     numero.setText(String.valueOf(u.getNum())); 
      emailValidator(email); 
     
}
    catch (SQLException ex) {
            System.out.println(ex.getMessage());
    } }else{
              Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setContentText("Invalid fields!");
            alert.show();
        
}
}*/

    
    


        
    
    

