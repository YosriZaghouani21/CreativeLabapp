/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportify.views.events;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sportify.models.events.Categorieevenement;
import sportify.services.events.categEvCrud;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class EventesviewController implements Initializable {

    @FXML
    private TextField catnom;
    @FXML
    private TextField cattype;
    @FXML
    private Button catAjout;
    @FXML
    private Button catgbut;
    @FXML
    private ImageView checkNom;
    String erreur;
    @FXML
    private ImageView checkType;
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
    }    

    @FXML
    private void AjouterCatg(ActionEvent event){
        if(testSaisie()){
         String nomCategEv = catnom.getText();
        String TypeCategEv = cattype.getText();
        
        Categorieevenement C=new Categorieevenement(nomCategEv ,TypeCategEv);
        categEvCrud CC =new categEvCrud();
        CC.ajouter(C);
        
        FXMLLoader Loader = new FXMLLoader(getClass().getResource("/sportify/views/events/Afficher.fxml"));
        Parent root;
        try {
            root = Loader.load();
            AfficherController ac = Loader.getController();
            catnom.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        }
        

        
    }
    
      @FXML
    private void retourner(ActionEvent event) throws IOException {
         Stage primaryStage = (Stage) catgbut.getScene().getWindow(); 
       Parent root = FXMLLoader.load(getClass().getResource("/sportify/views/events/crudcateg.fxml"));
       Scene scene = new Scene (root);
       primaryStage.setScene(scene);
       primaryStage.show();
    }
    private Boolean testNom() {
        
       String nomReg = "^[a-zA-Z ]*";

        Pattern pat = Pattern.compile(nomReg);
        if (catnom.getText() == null) {
            return false;
        }

        if (pat.matcher(catnom.getText()).matches() == false  || catnom.getText().length()==0) {
            checkNom.setImage(new Image("img/ErreurcheckMark.png"));
            erreur = erreur + ("Veuillez verifier votre nom\n");
            return false;
//            

        } else {
            checkNom.setImage(new Image("img/checkMark.png"));
        }
        return true;

    }
    
     private Boolean testType() {
        
         
       String nomReg = "^[a-zA-Z ]*";

        Pattern pat = Pattern.compile(nomReg);
        if (cattype.getText() == null) {
            return false;
        }

        if (pat.matcher(cattype.getText()).matches() == false  || cattype.getText().length()==0) {
            checkType.setImage(new Image("img/ErreurcheckMark.png"));
            erreur = erreur + ("Veuillez verifier votre nom\n");
            return false;
//            

        } else {
            checkType.setImage(new Image("img/checkMark.png"));
        }
        return true;

    }
    
    
    
    private Boolean testSaisie() {
        erreur = "";
        
       
        if (!testNom()) {
            erreur = erreur + ("Veuillez verifier L Nom \n");
        }
        
        if (!testType()) {
            erreur = erreur + ("Veuillez verifier le Type \n");
        }
     

        return   testNom() && testType()  ;
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

  
    
}
