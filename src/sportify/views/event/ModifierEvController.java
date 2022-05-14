/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportify.views.event;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
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
public class ModifierEvController implements Initializable {

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
    private Button eveajouter;
    @FXML
    private TextField evid;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ModifierEve(ActionEvent event) {
        int idEv = Integer.valueOf(evid.getText());
        String nomEv = evnom.getText();
        String DateEv = evdate.getText();
        String localisation = evlocalisation.getText();
        String desccription = evdescription.getText();
        int idCategEv = Integer.valueOf(evcategev.getText());
        try {

            System.out.println("Modifié avec succes");
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
            Alert ModifyCattAlert = new Alert(Alert.AlertType.CONFIRMATION);
            ModifyCattAlert.setTitle("Modifier Evenement");
            ModifyCattAlert.setHeaderText(null);
            ModifyCattAlert.setContentText("Voulez-vous vraiment modifier cet Evenement ? ?");
            Optional<ButtonType> optionModifyBookAlert = ModifyCattAlert.showAndWait();
            if (optionModifyBookAlert.get() == ButtonType.OK) {
                Evenement r = new Evenement( idEv,  nomEv,  DateEv,  localisation,  desccription, idCategEv);
                eventCrud ad = new eventCrud();
                ad.modifier(r);
                Alert succModifyAlert = new Alert(Alert.AlertType.INFORMATION);
                succModifyAlert.setTitle("Modifier Evenement");
                succModifyAlert.setHeaderText("Resultats:");
                succModifyAlert.setContentText("Evenement Modifié !");
                succModifyAlert.showAndWait();
            }else if (optionModifyBookAlert.get() == ButtonType.CANCEL) {
            }
            

        } catch (Exception ex) {
            System.out.println("error : " + ex.getMessage());
        }
    }
    public void setData(int idEv ,String nomEv , String DateEv, String localisation, String desccription, int idCategEv ) {

        evid.setText("" + idEv);
        evnom.setText("" + nomEv);
        evdate.setText("" + DateEv);
        evlocalisation.setText("" + localisation);
        evdescription.setText("" + desccription);
        evcategev.setText("" + idCategEv);

    }

    
}
