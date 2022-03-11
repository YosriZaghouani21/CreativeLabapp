/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
import java.awt.*;
import java.awt.TrayIcon.MessageType;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import sportify.models.coachs.Coach;
import sportify.services.coachservice.CoachService;
import sportify.services.coachservice.EnvoyerMail;

/**
 * FXML Controller class
 *
 * @author dali
 */
public class FormulaireCoachController implements Initializable {

    @FXML
    private Button addbution;
    @FXML
    private TextField nomid;
    @FXML
    private TextField prenomid;
    @FXML
    private TextField disponibilitéid;
    @FXML
    private TextField prixheureid;
    @FXML
    private TextField imageid;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void Addarticle(ActionEvent event) throws Exception {
        CoachService CoachService = new CoachService();
        Coach art = new Coach();
        if (((nomid.getText().length() != 0) && (isAlpha(nomid.getText()))) && ((prenomid.getText().length() != 0) && (isAlpha(prenomid.getText())))
                && (prixheureid.getText().length() != 0 && (isNumeric(prixheureid.getText())))&&((imageid.getText().length() != 0))) {
            art.setNom(nomid.getText());

            art.setPrenom(prenomid.getText());

            art.setDisponibilité(true);
            float p = Float.parseFloat(prixheureid.getText());

            art.setPrixHeure(p);

            art.setImg(imageid.getText());
            
            
            try {
                
                    CoachService.AddCoach(art);
SystemTray tray = SystemTray.getSystemTray();

        //If the icon is a file
        Image image = Toolkit.getDefaultToolkit().createImage("icon.png");
        //Alternative (if the icon is on the classpath):
        //Image image = Toolkit.getDefaultToolkit().createImage(getClass().getResource("icon.png"));

        TrayIcon trayIcon = new TrayIcon(image, "Tray Demo");
       
        trayIcon.setImageAutoSize(true);
       
        trayIcon.setToolTip("System tray icon demo");
        tray.add(trayIcon);
        trayIcon.displayMessage("Gestion de Coach", "Coach ajouté", MessageType.INFO);
                   
                    EnvoyerMail.sendMail("salim.sghaier@esprit.tn", "Un nouveau coach a été creer: \n" + "Son nom est:" + nomid.getText() + "\n Son prenom est:" + prenomid.getText());

                    
                    nomid.setText("");
                    prenomid.setText("");
                    disponibilitéid.setText("");
                    prixheureid.setText("");
                   
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }

        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setContentText("Invalid fields!");
            alert.show();
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
}
