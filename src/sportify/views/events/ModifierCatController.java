/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportify.views.events;

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
import sportify.services.events.categEvCrud;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class ModifierCatController implements Initializable {

    @FXML
    private TextField catnom;
    @FXML
    private TextField cattype;
    @FXML
    private Button catModif;
    @FXML
    private TextField catid;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ModifCatg(ActionEvent event) {

        int idCategEv = Integer.valueOf(catid.getText());
        String nomCategEv = catnom.getText();
        String TypeCategEv = cattype.getText();
        
        try {

            System.out.println("Modifié avec succes");
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
            Alert ModifyCattAlert = new Alert(Alert.AlertType.CONFIRMATION);
            ModifyCattAlert.setTitle("Modifier Categorie");
            ModifyCattAlert.setHeaderText(null);
            ModifyCattAlert.setContentText("Voulez-vous vraiment modifier cet categorie ? ?");
            Optional<ButtonType> optionModifyBookAlert = ModifyCattAlert.showAndWait();
            if (optionModifyBookAlert.get() == ButtonType.OK) {
                Categorieevenement r = new Categorieevenement(idCategEv,nomCategEv, TypeCategEv);
                categEvCrud ad = new categEvCrud();
                ad.modifier(r);
                Alert succModifyAlert = new Alert(Alert.AlertType.INFORMATION);
                succModifyAlert.setTitle("Modifier Categorie");
                succModifyAlert.setHeaderText("Resultats:");
                succModifyAlert.setContentText("Categorie Modifié !");
                succModifyAlert.showAndWait();
            }else if (optionModifyBookAlert.get() == ButtonType.CANCEL) {
            }
            

        } catch (Exception ex) {
            System.out.println("error : " + ex.getMessage());
        }
    }
    public void setData(int idCategEv ,String nomCategEv, String TypeCategEv) {

        catid.setText("" + idCategEv);
        catnom.setText("" + nomCategEv);
        cattype.setText("" + TypeCategEv);

    }

    
}
