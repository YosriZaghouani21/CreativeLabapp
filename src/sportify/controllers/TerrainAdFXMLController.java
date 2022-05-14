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
import java.sql.PreparedStatement;
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
import sportify.models.terrain.Terrain;
import sportify.services.terrain.TerrainService;




/**
 * FXML Controller class
 *
 * @author macbook
 */
public class TerrainAdFXMLController implements Initializable {

    PreparedStatement st = null;
    ResultSet rs = null;
    Connection con = null;
    @FXML
    private TextField nomTerrain;
    @FXML
    private TextField adresse;
    @FXML
    private TextField disponibilite;
    @FXML
    private TextField prixHeure;
    @FXML
    private Button add;
    @FXML
    private Button update;
    @FXML
    private TableView<Terrain> table;
    
    @FXML
    private TableColumn<Terrain, String> colnom;
    @FXML
    private TableColumn<Terrain, String> coladresse;
    @FXML
    private TableColumn<Terrain, String> coldispo;
    @FXML
    private TableColumn<Terrain, Float> colprix;

    private ObservableList<Terrain> data;
    @FXML
    private Button delete;
    @FXML
    private Button Retour;
    
    Stage dialogStage= new Stage();
    Scene scene;
    
    @FXML
    private Button Actualiser;
    @FXML
    private CheckBox check;
    
    @FXML
    private Label Adresse;
    @FXML
    private Button Excel;
    @FXML
    private Label Notification;
    @FXML
    private Button listerv;
    
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        showTerrain();
        // TODO
    }

    @FXML
    private void tablehandleButtonAction(MouseEvent event) {
        Terrain p = table.getSelectionModel().getSelectedItem();
        nomTerrain.setText(p.getNomTerrain());
        adresse.setText(p.getAdresse());
        disponibilite.setText(p.getDisponibilite());
        prixHeure.setText(String.valueOf(p.getPrixHeure()));
        add.setDisable(true);
    }

    @FXML
    private void AddTerrain(ActionEvent event) throws SQLException {
        
        if (nomTerrain.getText().toString().equals("") || adresse.getText().toString().equals("") || disponibilite.getText().toString().equals("") || prixHeure.getText().toString().equals("")) {
            Notification.setText("Remplir les champs ! ");
             Notification.setTextFill(Color.web("#F00000"));

        } else {
        
        TerrainService sp = new TerrainService();
        Terrain p = new Terrain(nomTerrain.getText(),adresse.getText(), disponibilite.getText(), Float.parseFloat(prixHeure.getText()));

        boolean b = check.isSelected();
        check.setSelected(false);
        if (b){
            sp.ajouterp(p);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Terrain ajouté avec succes!");
            alert.show();
            nomTerrain.setText("");
            adresse.setText("");
            disponibilite.setText("");
            prixHeure.setText("");
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Echec");
            alert.setContentText("Cocher je ne suis pas un robot");
            alert.show();
        }
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/sportify/views/TerrainAdFXML.fxml"));
            Scene c = new Scene(root);
            Stage stage = (Stage) nomTerrain.getScene().getWindow();
            stage.setScene(c);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
        }
    }

    @FXML
    private void UpdateTerrain(ActionEvent event) throws IOException, SQLException {
        
        if (nomTerrain.getText().toString().equals("") || adresse.getText().toString().equals("") || disponibilite.getText().toString().equals("") || prixHeure.getText().toString().equals("")) {
            Notification.setText("Wrong credentiels or email not valid ! ");
             Notification.setTextFill(Color.web("#F00000"));

        } else {
        
        TerrainService sp = new TerrainService();
        Terrain p = new Terrain(nomTerrain.getText(),adresse.getText(), disponibilite.getText(), Float.parseFloat(prixHeure.getText()));

        sp.modifierp(p);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setContentText("Terrain est modifié avec succes!");
        alert.show();
        nomTerrain.setText("");
        adresse.setText("");
        disponibilite.setText("");
        prixHeure.setText("");
        Parent root;
        root = FXMLLoader.load(getClass().getResource("/sportify/views/TerrainAdFXML.fxml"));
        Scene c = new Scene(root);
        Stage stage = (Stage) nomTerrain.getScene().getWindow();
        stage.setScene(c);

    }
    }

    @FXML
    private void DeleteTerrain(ActionEvent event) throws IOException, SQLException {
        
        TerrainService sp = new TerrainService();
        Terrain p = new Terrain(nomTerrain.getText(),adresse.getText(), disponibilite.getText(), Float.parseFloat(prixHeure.getText()));

        
            sp.deletep(p);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Terrain supprimé avec succes!");
            alert.show();
            nomTerrain.setText("");
            adresse.setText("");
            disponibilite.setText("");
            prixHeure.setText("");
            Parent root;
            root = FXMLLoader.load(getClass().getResource("/sportify/views/TerrainAdFXML.fxml"));
            Scene c = new Scene(root);
            Stage stage = (Stage) nomTerrain.getScene().getWindow();
            stage.setScene(c);

        }
    
    @FXML
    private void RetourP(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) Retour.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/sportify/views/user/FXMLMain.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }

    @FXML
    private void ActualiserP(ActionEvent event) throws IOException{
        
         Node source = (Node) event.getSource();
                            dialogStage = (Stage) source.getScene().getWindow();
                            dialogStage.close();
                            scene = new Scene(FXMLLoader.load(getClass().getResource("/sportify/views/TerrainAdFXML.fxml")));
                            dialogStage.setScene(scene);
                            dialogStage.show();
    }

    public Connection getConnection() {
        Connection conn;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sportifydatabase", "root", "");
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ObservableList<Terrain> getTerrainList() {
        ObservableList<Terrain> terrainList = FXCollections.observableArrayList();
        Connection connection = getConnection();
        String query = "SELECT * FROM terrain ";
        Statement st;
        ResultSet rs;

        try {
            st = connection.createStatement();
            rs = st.executeQuery(query);
            Terrain terrain;
            while (rs.next()) {
                terrain = new Terrain(rs.getInt("referenceTerrain"), rs.getString("nomTerrain"),rs.getString("adresse"), rs.getString("Disponibilite"), rs.getFloat("prixHeure"));
                terrainList.add(terrain);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return terrainList;
    }

    public void showTerrain() {
        
        ObservableList<Terrain> list = getTerrainList();
        colnom.setCellValueFactory(new PropertyValueFactory<Terrain, String>("nomTerrain"));
        coladresse.setCellValueFactory(new PropertyValueFactory<Terrain, String>("adresse"));
        coldispo.setCellValueFactory(new PropertyValueFactory<Terrain, String>("disponibilite"));
        colprix.setCellValueFactory(new PropertyValueFactory<Terrain, Float>("prixHeure"));
            table.setItems(list);
    }

    
    @FXML
    private void robot(ActionEvent event) {
    }

    @FXML
    private void Excel(ActionEvent event) throws SQLException {
        ExcelAp Excel=new ExcelAp();
        Excel.Excel();
        
    }

    @FXML
    private void listrv(ActionEvent event) throws IOException {
        
        Node source = (Node) event.getSource();
                            dialogStage = (Stage) source.getScene().getWindow();
                            dialogStage.close();
                            scene = new Scene(FXMLLoader.load(getClass().getResource("/sportify/views/ReservationAdFXML.fxml")));
                            dialogStage.setScene(scene);
                            dialogStage.show();
        
    }

    
    
}
