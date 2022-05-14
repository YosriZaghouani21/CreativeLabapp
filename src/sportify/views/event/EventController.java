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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sportify.models.events.Categorieevenement;
import sportify.models.events.Evenement;

import sportify.services.events.categEvCrud;
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
    @FXML
    private ComboBox<Categorieevenement> categg;
    private categEvCrud serviceCateg=new categEvCrud();
    @FXML
    private Button butEven;
    @FXML
    private Button butCatg;
    @FXML
    private Button btnOverview;
    @FXML
    private Button btnOrders;
    @FXML
    private Button btnCustomers;
    @FXML
    private Button btnSettings;
    @FXML
    private Button btnSignout;
    @FXML
    private Pane pnlCustomer;
    @FXML
    private Pane pnlOrders;
    @FXML
    private Pane pnlMenus;
    @FXML
    private Pane pnlOverview;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        for (Categorieevenement c:serviceCateg.afficher()){
            categg.getItems().add(c);
        }
        
    }

    @FXML
    private void AjouterEven(ActionEvent event) {
        
      if(testSaisie()){
        String nomEv = evnom.getText();
        String DateEv = evdate.getText();
        String localisation = evlocalisation.getText();
        String desccription = evdescription.getText();
        int idCategEv = categg.getValue().getIdCategEv();

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

        String nomReg = "^[a-zA-Z ]*";

        Pattern pat = Pattern.compile(nomReg);
        if (evnom.getText() == null) {
            return false;
        }

        if (pat.matcher(evnom.getText()).matches() == false || evnom.getText().length()==0) {
            checknom.setImage(new Image("img/ErreurcheckMark.png"));
            erreur = erreur + ("Veuillez verifier votre nom\n");
            return false;
//            

        } else {
            checknom.setImage(new Image("img/checkMark.png"));
        }
        return true;

    }

    private Boolean testDate() {

        String nomReg = "^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[13-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$";

        Pattern pat = Pattern.compile(nomReg);
        if (evdate.getText() == null) {
            return false;
        }

        if (pat.matcher(evdate.getText()).matches() == false) {
            checkdate.setImage(new Image("img/ErreurcheckMark.png"));
            erreur = erreur + ("Veuillez verifier votre nom\n");
            return false;
//            

        } else {
            checkdate.setImage(new Image("img/checkMark.png"));
        }
        return true;

    }
    
    
     private Boolean testLocalisation() {

        String nomReg = "^[a-zA-Z ]*";

        Pattern pat = Pattern.compile(nomReg);
        if (evlocalisation.getText() == null) {
            return false;
        }

        if (pat.matcher(evlocalisation.getText()).matches() == false  || evlocalisation.getText().length()==0) {
            checkloca.setImage(new Image("img/ErreurcheckMark.png"));
            erreur = erreur + ("Veuillez verifier votre nom\n");
            return false;
//            

        } else {
            checkloca.setImage(new Image("img/checkMark.png"));
        }
        return true;

    }
     
     
      private Boolean testDescription() {

        String nomReg =  "^[a-zA-Z ]*";

        Pattern pat = Pattern.compile(nomReg);
        if (evdescription.getText() == null) {
            return false;
        }

        if (pat.matcher(evdescription.getText()).matches() == false  || evdescription.getText().length()==0) {
            checkdesc.setImage(new Image("img/ErreurcheckMark.png"));
            erreur = erreur + ("Veuillez verifier votre nom\n");
            return false;
//            

        } else {
            checkdesc.setImage(new Image("img/checkMark.png"));
        }
        return true;

    }
      
      
      
      private Boolean testCategorie() {
          return categg.getValue().toString().length()>0;
      }

    @FXML
    private void startEven(ActionEvent event) throws IOException {
         Stage primaryStage = (Stage) butEven.getScene().getWindow(); 
       Parent root = FXMLLoader.load(getClass().getResource("/sportify/views/event/crudev.fxml"));
       Scene scene = new Scene (root);
       primaryStage.setScene(scene);
       primaryStage.show();
    }

    @FXML
    private void startCateg(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) butCatg.getScene().getWindow(); 
       Parent root = FXMLLoader.load(getClass().getResource("/sportify/views/events/crudcateg.fxml"));
       Scene scene = new Scene (root);
       primaryStage.setScene(scene);
       primaryStage.show();
    }

    @FXML
    private void handleClicks(ActionEvent event) {
    }

}
