/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import static Controller.FormulaireCoachController.isNumeric;
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
import javafx.stage.Stage;
import sportify.models.coachs.ReservationCoach;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    
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
     ReservationService rs= new ReservationService();
     if (((isNumeric(Id_coach.getText()) && ((Id_coach.getText().length() != 0))) && ((isNumeric(Id_user.getText())))&&((Id_user.getText().length() != 0))) && ((isNumeric(nbrheure.getText()))&& (Id_user.getText().length() != 0) )
                && (date.getValue()!=null)&& (Id_user.getText().length() != 0) && (isNumeric(prixtotale.getText()))&& (Id_user.getText().length() != 0)) {

        ReservationCoach r = new ReservationCoach();
        //int i=Integer.parseInt(Ref.getText());
        int n=Integer.parseInt(Id_coach.getText());
        int t=Integer.parseInt(Id_user.getText());
        int h=Integer.parseInt(nbrheure.getText());
        //r.setRefRes(i);
        r.setId_coach(n);
        r.setId_user(t);
        r.setNbrHeur(h);
        Date d=Date.valueOf(date.getValue());
        r.setDate_res(d);
        float f=Float.parseFloat(prixtotale.getText());
        r.setPrixTotal(f);
        
        try {
            rs.AddReservation(r);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Reservation is added successfully!");
            alert.show();
            Ref.setText("");
            Id_coach.setText("");
            Id_user.setText("");
            date.setValue(null);
            nbrheure.setText("");
            prixtotale.setText("");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }}
        else{
              Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setContentText("Invalid fields!");
            alert.show();
        }
    }

    private void modifierReservation(ActionEvent event) {
     ReservationService rs = new ReservationService();
        
        ReservationCoach r = new ReservationCoach();
          if (((isNumeric(Id_coach.getText()) && ((Id_coach.getText().length() != 0))) && ((isNumeric(Id_user.getText())))&&((Id_user.getText().length() != 0))) && ((isNumeric(nbrheure.getText()))&& (Id_user.getText().length() != 0) )
                && (date.getValue()!=null)&& (Id_user.getText().length() != 0) && (isNumeric(prixtotale.getText()))&& (Id_user.getText().length() != 0)) {
        int ref=Integer.parseInt(Ref.getText());      
        int n=Integer.parseInt(Id_coach.getText());
        int t=Integer.parseInt(Id_user.getText());
        int h=Integer.parseInt(nbrheure.getText());
        r.setRefRes(ref);
        r.setId_coach(n);
        r.setId_user(t);
        r.setNbrHeur(h);
        Date d=Date.valueOf(date.getValue());
        r.setDate_res(d);
        float f=Float.parseFloat(prixtotale.getText());
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
          
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }}
          else{
              Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setContentText("Invalid fields!");
            alert.show();
        }
            
   
    }

    private void DeletReservation(ActionEvent event) {
    ReservationService rs = new ReservationService();
        System.out.println(refer.getText());
         ReservationCoach r = new ReservationCoach();
        int ref=Integer.parseInt(Ref.getText()) ; 
            r.setRefRes(ref);

        try {
            rs.DeleteReservation(ref);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Reservation supprimé avec succes!");
            alert.show();
            refer.setText("");
           
            

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @FXML
    private void enregistrerPdf(ActionEvent event) {
         {
              ReservationCoach c =new ReservationCoach();
                  ReservationService  cs= new ReservationService();
           
             
        int n=Integer.parseInt(Id_coach.getText());
        int t=Integer.parseInt(Id_user.getText());
        int h=Integer.parseInt(nbrheure.getText());
         Date d=Date.valueOf(date.getValue());
          float f=Float.parseFloat(prixtotale.getText()); 
                     try{
                                Desktop.getDesktop().open(new File("C:\\Users\\salim\\Desktop\\Nouveau dossier\\facture.pdf"));
                                   }
                             catch(IOException e){
                            System.out.println(e);
                                    }

        
      cs.factureClient(c);
    }}
       }