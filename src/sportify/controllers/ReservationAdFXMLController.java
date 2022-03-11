/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportify.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sportify.models.terrain.Reservation;
import sportify.services.terrain.ReservationService;



/**
 * FXML Controller class
 *
 * @author MYLAPTOP
 */
public class ReservationAdFXMLController implements Initializable {

    @FXML
    private TableView<Reservation> table;
    @FXML
    private TableColumn<Reservation, Integer> colid;
    @FXML
    private TableColumn<Reservation, Integer> colref;
    @FXML
    private TableColumn<Reservation, String> coldate;
    @FXML
    private TableColumn<Reservation, Integer> colnbr;
    @FXML
    private Button add;
    @FXML
    private Button update;
    @FXML
    private Button delete;
    //@FXML
    //private TextField numReservation;
    //private TextField idClient;
    @FXML
    private TextField Id;
    @FXML
    private TextField referenceTerrain;
    @FXML
    private TextField dateReservation;
    @FXML
    private TextField nombreHeure;
    
    @FXML
    private Button Retour;
    
    Stage dialogStage= new Stage();
    Scene scene;
    
    @FXML
    private CheckBox check;
    @FXML
    private Button Actualiser;
    @FXML
    private Label Notification;
    @FXML
    private Button excel;
    @FXML
    private Label Utilisateur;
    


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
                showReservation();

    }    

    @FXML
    private void tablehandleButtonAction(MouseEvent event) {
        
        Reservation r = table.getSelectionModel().getSelectedItem();
        //numReservation.setText(String.valueOf(r.getNumReservation()));
        Id.setText(String.valueOf(r.getId()));
        referenceTerrain.setText(String.valueOf(r.getReferenceTerrain()));
        dateReservation.setText(r.getDateReservation());
        nombreHeure.setText(String.valueOf(r.getNombreHeure()));
        add.setDisable(true);
    }

    @FXML
    private void AddReservation(ActionEvent event) {
        
        if (dateReservation.getText().toString().equals("") || nombreHeure.getText().toString().equals("") ) {
            Notification.setText("Remplir les champs ! ");
             Notification.setTextFill(Color.web("#F00000"));
        }else{
        
         ReservationService sr = new ReservationService();
         Reservation r = new Reservation(Integer.parseInt(Id.getText()), Integer.parseInt(referenceTerrain.getText()),dateReservation.getText(), Integer.parseInt(nombreHeure.getText()));

        try {
            boolean b = check.isSelected();
            check.setSelected(false);

            if (b){
            sr.ajouterR(r);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Reservation ajouté avec succes!");
            alert.show();
            Id.setText("");
            referenceTerrain.setText("");
            dateReservation.setText("");
            nombreHeure.setText("");
            } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Echec");
            alert.setContentText("Cocher je ne suis pas un robot");
            alert.show();
            }
            Parent root;
            try {
                root = FXMLLoader.load(getClass().getResource("/sportify/views/ReservationAdFXML.fxml"));
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

    @FXML
    private void UpdateReservation(ActionEvent event) {
        
        if (dateReservation.getText().toString().equals("") || nombreHeure.getText().toString().equals("") ) {
            Notification.setText("Remplir les champs ! ");
             Notification.setTextFill(Color.web("#F00000"));
        }else{
        
        ReservationService sr = new ReservationService();
        Reservation r = new Reservation(Integer.parseInt(Id.getText()), Integer.parseInt(referenceTerrain.getText()),dateReservation.getText(), Integer.parseInt(nombreHeure.getText()));

        try {
            sr.modifierR(r);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Reservation est modifié avec succes!");
            alert.show();
            Id.setText("");
            referenceTerrain.setText("");
            dateReservation.setText("");
            nombreHeure.setText("");
            
             Parent root;
            try {
                root = FXMLLoader.load(getClass().getResource("/sportify/views/ReservationAdFXML.fxml"));
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

    @FXML
    private void DeleteReservation(ActionEvent event) {
        
        ReservationService sr = new ReservationService();
        Reservation r = new Reservation(Integer.parseInt(Id.getText()), Integer.parseInt(referenceTerrain.getText()),dateReservation.getText(), Integer.parseInt(nombreHeure.getText()));

        try {
            sr.deleteR(r);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Reservation supprimé avec succes!");
            alert.show();
            Id.setText("");
            referenceTerrain.setText("");
            dateReservation.setText("");
            nombreHeure.setText("");
            
             Parent root;
            try {
                root = FXMLLoader.load(getClass().getResource("/sportify/views/ReservationAdFXML.fxml"));
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
    
    
    @FXML
    private void RetourP(ActionEvent event) throws IOException {
        Node source = (Node) event.getSource();
                            dialogStage = (Stage) source.getScene().getWindow();
                            dialogStage.close();
                            scene = new Scene(FXMLLoader.load(getClass().getResource("/sportify/views/TerrainAdFXML.fxml")));
                            dialogStage.setScene(scene);
                            dialogStage.show();
        
    }
    
    
    @FXML
    private void ActualiserP(ActionEvent event) throws IOException {
        
        Node source = (Node) event.getSource();
                            dialogStage = (Stage) source.getScene().getWindow();
                            dialogStage.close();
                            scene = new Scene(FXMLLoader.load(getClass().getResource("/sportify/views/ReservationAdFXML.fxml")));
                            dialogStage.setScene(scene);
                            dialogStage.show();
        
    }
    
    
    public Connection getConnection() {
    	Connection conn;
    	try {
    		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sportifydatabase","root","");
    		return conn;
    	}
    	catch (Exception e){
    		e.printStackTrace();
    		return null;
    	}
    }
    
    
    public ObservableList<Reservation> getReservationList(){
    	ObservableList<Reservation> reservationList = FXCollections.observableArrayList();
    	Connection connection = getConnection();
    	String query = "SELECT * FROM reservationterrain ";
    	Statement st;
    	ResultSet rs;
    	
    	try {
			st = connection.createStatement();
			rs = st.executeQuery(query);
			Reservation reservationterrain;
			while(rs.next()) {
				reservationterrain = new Reservation(rs.getInt("numReservation"),rs.getInt("Id"),rs.getInt("referenceTerrain"),rs.getString("dateReservation"),rs.getInt("nombreHeure"));
				reservationList.add(reservationterrain);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return reservationList;
    }
    
    
    public void showReservation() {
    	ObservableList<Reservation> list = getReservationList();
    	
    	//colres.setCellValueFactory(new PropertyValueFactory<Reservation,Integer>("numReservation"));
    	colid.setCellValueFactory(new PropertyValueFactory<Reservation,Integer>("Id"));
    	colref.setCellValueFactory(new PropertyValueFactory<Reservation,Integer>("referenceTerrain"));
    	coldate.setCellValueFactory(new PropertyValueFactory<Reservation,String>("dateReservation"));
    	colnbr.setCellValueFactory(new PropertyValueFactory<Reservation,Integer>("nombreHeure"));
    	
    	table.setItems(list);
    }
    
    
    @FXML
    private void robot(ActionEvent event) {
    }

    @FXML
    private void Excel(ActionEvent event) throws SQLException {
        
         ExcelR Excel2=new ExcelR();
        Excel2.Excel();
    }

    
    }

