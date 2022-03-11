/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportify.controllers;

import static sportify.controllers.FormulaireCoachController.isAlpha;
import static sportify.controllers.FormulaireCoachController.isNumeric;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
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
public class ReservationFXMLController implements Initializable {

    @FXML
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
    private Button AddReservation;
    @FXML
    private Button UpdateReservation;
    @FXML
    private Button DeleteReservation;
    @FXML
    private TableView<ReservationCoach> TableReservation;
    @FXML
    private TableColumn<ReservationCoach, Integer> id_c;
    @FXML
    private TableColumn<ReservationCoach, Integer> id_cl;
    @FXML
    private TableColumn<ReservationCoach, Date> date_res;
    @FXML
    private TableColumn<ReservationCoach, Integer> nb_heur;
    @FXML
    private TableColumn<ReservationCoach, Float> prix_totale;
    @FXML
    private TableColumn<ReservationCoach, Integer> refer;
    @FXML
    private Button closeid;
    @FXML
    private TextField Id_user;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Session.getUserStatic();
        System.out.println(Session.getUserStatic().getId() + "Client en Reservation client");

        showReservation();
        closeid.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
                s.close();

            }

        });
        setCellValue();
    }

    @FXML
    private void AddReservation(ActionEvent event) throws Exception {
        ReservationService rs = new ReservationService();
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

            if (((isNumeric(Id_coach.getText()) && ((Id_coach.getText().length() != 0)))  && ((isNumeric(nbrheure.getText())) && (nbrheure.getText().length() != 0))
                && (date.getValue() != null)  && (isNumeric(prixtotale.getText())) && (prixtotale.getText().length() != 0))) {
       
        
   

            try {
                rs.AddReservation(r);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                EnvoyerMail.sendMail("salim.sghaier@esprit.tn", "Vous avez un nouveau client: \n" + "Son identifiant est:" + Id_user.getText() + "\n Sa date de reservation est:" + date.getValue() + "\n son prix est:" + prixtotale.getText());
                alert.setTitle("Success");
                alert.setContentText("Reservation is added successfully!");
                alert.show();
                Ref.setText("");
                Id_coach.setText("");
                Id_user.setText("");
                date.setValue(null);
                nbrheure.setText("");
                prixtotale.setText("");
                showReservation();
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

    @FXML
    private void modifierReservation(ActionEvent event) {
        ReservationService rs = new ReservationService();
        ReservationCoach r = new ReservationCoach();
        if (((isNumeric(Id_coach.getText()) && ((Id_coach.getText().length() != 0)))) && ((isNumeric(Id_user.getText()) && ((Id_user.getText().length() != 0)))) &&  ((isNumeric(nbrheure.getText())) && (nbrheure.getText().length() != 0))
                && (date.getValue() != null)  && (isNumeric(prixtotale.getText())) && (prixtotale.getText().length() != 0)) {
            int ref = Integer.parseInt(Ref.getText());
            int n = Integer.parseInt(Id_coach.getText());
            int t = Integer.parseInt(Id_user.getText());
            int h = Integer.parseInt(nbrheure.getText());
            r.setRefRes(ref);
            r.setId_coach(n);
            r.setId(t);
             Date d = Date.valueOf(date.getValue());
            r.setDate_res(d);
            r.setNbrHeur(h);
           
            float f = Float.parseFloat(prixtotale.getText());
            r.setPrixTotal(f);

            try {
                rs.modifierReservation(ref, n, t, d, h, f);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setContentText("Reservation is updated successfully!");
                alert.show();
                Ref.setText("");
                Id_coach.setText("");
                Id_user.setText("");
                date.setValue(null);
                nbrheure.setText("");
                prixtotale.setText("");
                showReservation();
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

    @FXML
    private void DeletReservation(ActionEvent event) {
        ReservationService rs = new ReservationService();
        System.out.println(refer.getText());
        ReservationCoach r = new ReservationCoach();
        int ref = Integer.parseInt(Ref.getText());
        r.setRefRes(ref);

        try {
            rs.DeleteReservation(ref);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Reservation supprimé avec succes!");
            alert.show();
            refer.setText("");
            showReservation();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @FXML
    private void enregistrerPdf(ActionEvent event) {
        {
            ReservationCoach c = new ReservationCoach();
            ReservationService cs = new ReservationService();

            int n = Integer.parseInt(Id_coach.getText());
           // int t = Integer.parseInt(Id_user.getText());
            int h = Integer.parseInt(nbrheure.getText());
            Date d = Date.valueOf(date.getValue());
            float f = Float.parseFloat(prixtotale.getText());
            c.setId_coach(n);
            c.setId(Session.getUserStatic().getId());
            c.setNbrHeur(h);
            c.setDate_res(d);
            c.setPrixTotal(f);

            try {
                Desktop.getDesktop().open(new File("C:\\Users\\zagho\\Desktop\\Nouveau dossier\\facture.pdf"));
            } catch (IOException e) {
                System.out.println(e);
            }

            cs.factureClient(c);
        }
    }

    public void showReservation() {
        ReservationService rs = new ReservationService();
        ObservableList<ReservationCoach> list = rs.getReservation();

        refer.setCellValueFactory(new PropertyValueFactory<ReservationCoach, Integer>("RefRes"));
        id_c.setCellValueFactory(new PropertyValueFactory<ReservationCoach, Integer>("id_coach"));
        id_cl.setCellValueFactory(new PropertyValueFactory<ReservationCoach, Integer>("Id"));
        date_res.setCellValueFactory(new PropertyValueFactory<ReservationCoach, Date>("Date_res"));
        nb_heur.setCellValueFactory(new PropertyValueFactory<ReservationCoach, Integer>("nbrHeur"));
        prix_totale.setCellValueFactory(new PropertyValueFactory<ReservationCoach, Float>("PrixTotal"));
        TableReservation.setItems(list);
    }

    public void setCellValue() {

        TableReservation.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                ReservationCoach p1 = TableReservation.getItems().get(TableReservation.getSelectionModel().getSelectedIndex());
                Ref.setText(String.valueOf(p1.getRefRes()));
                Id_coach.setText(String.valueOf(p1.getId_coach()));
                prixtotale.setText(String.valueOf(p1.getPrixTotal()));
                nbrheure.setText(String.valueOf(p1.getNbrHeur()));
                Id_user.setText(String.valueOf(p1.getId()));
                date.setValue(p1.getDate_res().toLocalDate());

            }
        });

    }
}
