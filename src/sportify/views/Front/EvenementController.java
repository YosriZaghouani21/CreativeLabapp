/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportify.views.Front;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import sportify.models.events.Evenement;


/**
 * FXML Controller class
 *
 * @author Asus
 */
public class EvenementController implements Initializable {

    @FXML
    private Label event_label;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void setEvent(Evenement e)
    {
        event_label.setText("Nom : "+e.getNomEv()+" \n Date Evenement :\n "+e.getDateEv()+" \n Description : "+e.getDesccription()+"\n Localisation : "+e.getLocalisation());
    }
    
}
