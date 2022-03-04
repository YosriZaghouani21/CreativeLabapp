/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportify.views.event;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import sportify.models.events.Evenement;
import sportify.services.events.eventCrud;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class EventController implements Initializable {

    @FXML
    private TextField evnom;
    @FXML
    private TextField evdate;
    @FXML
    private TextField evlocalisation;
    @FXML
    private TextField evdescription;
    @FXML
    private TextField evcategev;
    @FXML
    private Button evajout;
    @FXML
    private Button evbut;
    @FXML
    private ImageView checknom;
    @FXML
    private ImageView checkdesc;
    @FXML
    private ImageView checkloca;
    @FXML
    private ImageView checkdate;
    String erreur;
    @FXML
    private ImageView checkcateg;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void AjouterEven(ActionEvent event) {
      if(testSaisie()){
        String nomEv = evnom.getText();
        String DateEv = evdate.getText();
        String localisation = evlocalisation.getText();
        String desccription = evdescription.getText();
        int idCategEv = Integer.parseInt(evcategev.getText());

        Evenement C = new Evenement(nomEv, DateEv, localisation, desccription, idCategEv);
        eventCrud CC = new eventCrud();
        CC.ajouter(C);

        FXMLLoader Loader = new FXMLLoader(getClass().getResource("AfficherEv.fxml"));
        Parent root;
        try {
            root = Loader.load();
            AfficherEvController ac = Loader.getController();
            evnom.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
      }
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) evbut.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/sportify/views/event/crudev.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    
    
     private Boolean testSaisie() {
        erreur = "";
        
       
        if (!testNom()) {
            erreur = erreur + ("Veuillez verifier votre Nom \n");
        }
        if (!testDate()) {
            erreur = erreur + ("Veuillez verifier votre surnom \n");
        }
        if (!testLocalisation()) {
            erreur = erreur + ("Veuillez verifier votre email \n");
        }
        if (!testDescription()) {
            erreur = erreur + ("Veuillez verifier votre Numero de Cin : seulement des nombres = 8 \n");
        }
        if (!testCategorie()) {
            erreur = erreur + ("Veuillez verifier votre Mot de passe \n");
        }

        return   testNom() && testDate() && testLocalisation() && testDescription() && testCategorie()  ;
    }

    private Boolean testNom() {

        String nomReg = "^[a-zA-Z]+";

        Pattern pat = Pattern.compile(nomReg);
        if (evnom.getText() == null) {
            return false;
        }

        if (pat.matcher(evnom.getText()).matches() == false) {
            checknom.setImage(new Image("Images/ErreurcheckMark.png"));
            erreur = erreur + ("Veuillez verifier votre nom\n");
            return false;
//            

        } else {
            checknom.setImage(new Image("Images/checkMark.png"));
        }
        return true;

    }

    private Boolean testDate() {

        String nomReg = "^[a-zA-Z]+";

        Pattern pat = Pattern.compile(nomReg);
        if (evdate.getText() == null) {
            return false;
        }

        if (pat.matcher(evdate.getText()).matches() == false) {
            checkdate.setImage(new Image("Images/ErreurcheckMark.png"));
            erreur = erreur + ("Veuillez verifier votre nom\n");
            return false;
//            

        } else {
            checkdate.setImage(new Image("Images/checkMark.png"));
        }
        return true;

    }
    
    
     private Boolean testLocalisation() {

        String nomReg = "^[a-zA-Z]+";

        Pattern pat = Pattern.compile(nomReg);
        if (evlocalisation.getText() == null) {
            return false;
        }

        if (pat.matcher(evlocalisation.getText()).matches() == false) {
            checkloca.setImage(new Image("Images/ErreurcheckMark.png"));
            erreur = erreur + ("Veuillez verifier votre nom\n");
            return false;
//            

        } else {
            checkloca.setImage(new Image("Images/checkMark.png"));
        }
        return true;

    }
     
     
      private Boolean testDescription() {

        String nomReg = "^[a-zA-Z]+";

        Pattern pat = Pattern.compile(nomReg);
        if (evdescription.getText() == null) {
            return false;
        }

        if (pat.matcher(evdescription.getText()).matches() == false) {
            checkdesc.setImage(new Image("Images/ErreurcheckMark.png"));
            erreur = erreur + ("Veuillez verifier votre nom\n");
            return false;
//            

        } else {
            checkdesc.setImage(new Image("Images/checkMark.png"));
        }
        return true;

    }
      
      
      
      private Boolean testCategorie() {
        String nomReg = "^[0-9]+";

        Pattern pat = Pattern.compile(nomReg);
        if (evcategev.getText() == null) {
            return false;
        }

        if (pat.matcher(evcategev.getText()).matches() == false) {
            checkcateg.setImage(new Image("Images/ErreurcheckMark.png"));
            erreur = erreur + ("Veuillez verifier votre nom\n");
            return false;
//            

        } else {
            checkcateg.setImage(new Image("Images/checkMark.png"));
        }
        return true;
      }

}
