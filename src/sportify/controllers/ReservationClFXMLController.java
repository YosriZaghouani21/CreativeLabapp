/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportify.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sportify.models.terrain.Reservation;
import sportify.models.terrain.Terrain;
import sportify.services.terrain.ReservationService;
import sportify.services.terrain.TerrainService;


/**
 * FXML Controller class
 *
 * @author MYLAPTOP
 */
public class ReservationClFXMLController implements Initializable {

    @FXML
    private Button Retour1;
    
    Stage dialogStage= new Stage();
    Scene scene;
    
    @FXML
    private ComboBox<String> terrainComboBox;
    TerrainService ts = new TerrainService();
    @FXML
    private TextField dateReservation;
    @FXML
    private TextField nombreHeure;
    @FXML
    private Button valider;
    @FXML
    private Label Notification;
    @FXML
    private TextField Id;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //System.out.println(ts.getAll());
        for(Terrain t : ts.getAll()){
        terrainComboBox.getItems().add(t.getNomTerrain());
        }
    }  

    
    
    @FXML
    private void RetourP(ActionEvent event) throws IOException {
        
        Node source = (Node) event.getSource();
                            dialogStage = (Stage) source.getScene().getWindow();
                            dialogStage.close();
                            scene = new Scene(FXMLLoader.load(getClass().getResource("/sportify/views/TerrainClFXML.fxml")));
                            dialogStage.setScene(scene);
                            dialogStage.show();
        
    }

    @FXML
    private void Valider(ActionEvent event) {
        if (dateReservation.getText().toString().equals("") || nombreHeure.getText().toString().equals("") ) {
            Notification.setText("Remplir les champs ! ");
             Notification.setTextFill(Color.web("#F00000"));
        }else{
        ReservationService sr = new ReservationService();
        System.out.println("combo affiche"+terrainComboBox.getValue());
        Terrain id = sr.getTerrainNam(terrainComboBox.getValue());
        System.out.println("terrain"+id);
        System.out.println("id terrain"+id);
         Reservation r = new Reservation(Integer.parseInt(Id.getText()),id.getReferenceTerrain(),dateReservation.getText(), Integer.parseInt(nombreHeure.getText()));

        try {
            sr.ajouterR(r);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Reservation ajouté avec succes!");
            alert.show();
            Id.setText("");
            terrainComboBox.setValue("");
            dateReservation.setText("");
            nombreHeure.setText("");
            
            Parent root;
            try {
                root = FXMLLoader.load(getClass().getResource("/sportify/views/TerrainClFXML.fxml"));
                Scene c = new Scene(root);
                Stage stage = (Stage) dateReservation.getScene().getWindow();
                stage.setScene(c);
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    }

    
}
