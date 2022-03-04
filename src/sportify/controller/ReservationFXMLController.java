/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportify.controller;

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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import sportify.model.Coach;
import sportify.model.ReservationCoach;
import sportify.service.CoachService;
import sportify.service.EnvoyerMail;
import sportify.service.ReservationService;

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
    private TextField date;
    @FXML
    private TextField nbrheure;
    @FXML
    private TextField Id_user;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showReservation() ;
        setCellValue();
    }    
      @FXML
    private void AddReservation(ActionEvent event) throws Exception {
     
        ReservationService rs= new ReservationService();
        System.out.println(Id_coach.getText());
        System.out.println(Id_user.getText());
        System.out.println(date.getText());
        System.out.println(nbrheure.getText());
        System.out.println(prixtotale.getText());

        ReservationCoach r = new ReservationCoach();
        //int i=Integer.parseInt(Ref.getText());
        int n=Integer.parseInt(Id_coach.getText());
        int t=Integer.parseInt(Id_user.getText());
        int h=Integer.parseInt(nbrheure.getText());
        //r.setRefRes(i);
        r.setId_coach(n);
        r.setId_user(t);
        r.setNbrHeur(h);
        Date d=Date.valueOf(date.getText());

        r.setDate_res(d);
        float f=Float.parseFloat(prixtotale.getText());
        r.setPrixTotal(f);
        
        try {
            rs.AddReservation(r);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            EnvoyerMail.sendMail("salim.sghaier@esprit.tn","Vous avez un nouveau client: \n" +"Son identifiant est:"+ Id_user.getText() + "\n Sa date de reservation est:" + date.getText() + "\n son prix est:" + prixtotale.getText());
            alert.setTitle("Success");
            alert.setContentText("Reservation is added successfully!");
            alert.show();
            Ref.setText("");
            Id_coach.setText("");
            Id_user.setText("");
            date.setText("");
            nbrheure.setText("");
            prixtotale.setText("");
            showReservation();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }

    

    @FXML
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
           showReservation();
            

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
        
   

    @FXML
    private void modifierReservation(ActionEvent event) {
        ReservationService rs = new ReservationService();
        System.out.println(Ref.getText());
        System.out.println(Id_coach.getText());
        System.out.println(Id_user.getText());
        System.out.println(date.getText());
        System.out.println(nbrheure.getText());
        System.out.println(prixtotale.getText());
        ReservationCoach r = new ReservationCoach();
        
        int ref=Integer.parseInt(Ref.getText());      
        int n=Integer.parseInt(Id_coach.getText());
        int t=Integer.parseInt(Id_user.getText());
        int h=Integer.parseInt(nbrheure.getText());
        r.setRefRes(ref);
        r.setId_coach(n);
        r.setId_user(t);
        r.setNbrHeur(h);
        Date d=Date.valueOf(date.getText());
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
            date.setText("");
            nbrheure.setText("");
            prixtotale.setText("");
            showReservation();
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
            
   
    }
      
    
        
   
    
    
    public void showReservation() {
         ReservationService  rs= new ReservationService();
    	ObservableList<ReservationCoach> list = rs.getReservation();
    	
    	refer.setCellValueFactory(new PropertyValueFactory<ReservationCoach,Integer>("RefRes"));
    	id_c.setCellValueFactory(new PropertyValueFactory<ReservationCoach,Integer>("id_coach"));
        id_cl.setCellValueFactory(new PropertyValueFactory<ReservationCoach,Integer>("id_user"));
    	date_res.setCellValueFactory(new PropertyValueFactory<ReservationCoach,Date>("Date_res"));
    	nb_heur.setCellValueFactory(new PropertyValueFactory<ReservationCoach,Integer>("nbrHeur"));
        prix_totale.setCellValueFactory(new PropertyValueFactory<ReservationCoach,Float>("PrixTotal"));
    	
    	TableReservation.setItems(list);
    } 
    @FXML
    public void setCellValue(){
    
    TableReservation.setOnMouseClicked(new EventHandler<MouseEvent>(){
    public void handle(MouseEvent event)
    {
        ReservationCoach p1=TableReservation.getItems().get(TableReservation.getSelectionModel().getSelectedIndex());
        Ref.setText(String.valueOf(p1.getRefRes()));
                Id_coach.setText(String.valueOf(p1.getId_coach()));
                        prixtotale.setText(String.valueOf(p1.getPrixTotal()));
                                nbrheure.setText(String.valueOf(p1.getNbrHeur()));
                                        Id_user.setText(String.valueOf(p1.getId_user()));
                                            date.setText(String.valueOf(p1.getDate_res()));

                                                
           
    }
});
}
    @FXML
   public void enregistrerPdf()
    {
              ReservationCoach c =new ReservationCoach();
                  ReservationService  cs= new ReservationService();
           
           int ref=Integer.parseInt(Ref.getText());      
        int n=Integer.parseInt(Id_coach.getText());
        int t=Integer.parseInt(Id_user.getText());
        int h=Integer.parseInt(nbrheure.getText());
             Date d=Date.valueOf(date.getText()); 
                     float f=Float.parseFloat(prixtotale.getText()); 
                     try{
                                Desktop.getDesktop().open(new File("C:\\Users\\salim\\Desktop\\Nouveau dossier\\facture.pdf"));
                                   }
                             catch(IOException e){
                            System.out.println(e);
                                    }

        
      cs.factureClient(c);
    }
}
