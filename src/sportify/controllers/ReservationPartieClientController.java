/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportify.controllers;

import static sportify.controllers.FormulaireCoachController.isNumeric;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sportify.models.coachs.ReservationCoach;
import sportify.models.user.Session;
import sportify.services.coachservice.EnvoyerMail;
import sportify.services.coachservice.ReservationService;

/**
 * FXML Controller class
 *
 * @author salim
 */
public class ReservationPartieClientController implements Initializable {

    private TextField Ref;
    @FXML
    private TextField Id_coach;
    @FXML
    private TextField prixtotale;
    @FXML
    private DatePicker date;
    @FXML
    private TextField nbrheure;
    @FXML
    private TextField Id_user;
    @FXML
    private Button AddReservation;
    private TableColumn<ReservationCoach, Integer> refer;
    @FXML
    private Button closeid;
    @FXML
    private Text iduserstatic;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Session.getUserStatic();
        System.out.println(Session.getUserStatic().getId() + "Client en Reservation client");

        closeid.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
                s.close();

            }

        });

    }

    @FXML
    private void AddReservation(ActionEvent event) throws Exception {
        ReservationCoach r = new ReservationCoach();

        int n = Integer.parseInt(Id_coach.getText());

        int h = Integer.parseInt(nbrheure.getText());

        r.setId_coach(n);
        r.setId(Session.getUserStatic().getId());
        r.setNbrHeur(h);

        Date d = Date.valueOf(date.getValue());
        r.setDate_res(d);
        float f = Float.parseFloat(prixtotale.getText());
        r.setPrixTotal(f);
        ReservationService rs = new ReservationService();
        if (((isNumeric(Id_coach.getText()) && ((Id_coach.getText().length() != 0))) && ((isNumeric(nbrheure.getText())) && (nbrheure.getText().length() != 0))
                && (date.getValue() != null) && (isNumeric(prixtotale.getText())) && (prixtotale.getText().length() != 0))) {

            try {
                rs.AddReservation(r);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setContentText("Reservation is added successfully!");
                alert.show();
                Id_coach.setText("");
                Id_user.setText("");
                date.setValue(null);
                nbrheure.setText("");
                prixtotale.setText("");

            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setContentText("Invalid fields!");
            alert.show();
        }
    }

}
