/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportify.controllers;


import static sportify.controllers.FormulaireCoachController.isAlpha;
import static sportify.controllers.FormulaireCoachController.isNumeric;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import sportify.controllers.MarketCoachController;
import java.sql.SQLException;
import java.time.LocalDate;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import sportify.models.coachs.Coach;

import sportify.services.coachservice.CoachService;

/**
 * FXML Controller class
 *
 * @author dali
 */
public class UpdateCoachController implements Initializable {

    private int id;
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
    @FXML
    private Button update;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // TODO
    }

    @FXML
    private void updatearticle(ActionEvent event) {
       CoachService CoachService = new CoachService();
        Coach coach = new Coach();
         if (((nomid.getText().length() != 0) && (isAlpha(nomid.getText()))) && ((prenomid.getText().length() != 0) && (isAlpha(prenomid.getText())))
                && (prixheureid.getText().length() != 0 && (isNumeric(prixheureid.getText())))&&((imageid.getText().length() != 0))) {
        coach.setId(id);
        coach.setNom(nomid.getText());
        coach.setPrenom(prenomid.getText());
        coach.setPrixHeure(Float.parseFloat(prixheureid.getText()));
        coach. setDisponibilité(Boolean.parseBoolean(disponibilitéid.getText()));
        coach.setImg(imageid.getText());
         try {
            CoachService.modifierCoach(coach);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Coach is update!");
            alert.show();
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
         }
    else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Invalid fields!");
            alert.show();
        }}
        
        
        
    

    public void setTextField(int id, String nom, String prenom, boolean disponibilité,Float prixHeure, String img) {

        this.id = id;
        nomid.setText(nom);
        System.out.println(nom);
        prenomid.setText(prenom);
        prixheureid.setText(Float.toString(prixHeure));
        disponibilitéid.setText(Boolean.toString(disponibilité));
        imageid.setText(img);

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

