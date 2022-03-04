/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportify.controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import sportify.model.Coach;
import sportify.service.CoachService;

/**
 * FXML Controller class
 *
 * @author salim
 */
public class CoachFXMLController implements Initializable {

    
    @FXML
    private TextField fieldbtn;
    @FXML
    private TextField id_coach;
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField disponibilité;
    @FXML
    private TextField prixHeure;
    @FXML
    private Button Ajouter;
    @FXML
    private Button Delete;
    @FXML
    private Button Modifier;
    @FXML
    private TableView<Coach> table_coach;
    @FXML
    private TableColumn<Coach, Integer> Id_coach;
    @FXML
    private TableColumn<Coach, String> Nom;
    @FXML
    private TableColumn<Coach, String> Prenom;
    @FXML
    private TableColumn<Coach, Boolean> Disponibilité;
    @FXML
    private TableColumn<Coach, Float> PrixHeure;
    public ObservableList<Coach> dataCoach = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showCoach();
        ChercherCoach();
    }

    @FXML
    private void AddCoach(ActionEvent event) {

        CoachService cs = new CoachService();
        System.out.println(nom.getText());
        System.out.println(prenom.getText());
        System.out.println(disponibilité.getText());
        System.out.println(prixHeure.getText());

        Coach c = new Coach();
        c.setNom(nom.getText());
        c.setPrenom(prenom.getText());
        c.setDisponibilité(true);
        float f = Float.parseFloat(prixHeure.getText());
        c.setPrixHeure(f);
        try {
            cs.AddCoach(c);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Coach is added successfully!");
            alert.show();
            id_coach.setText("");
            nom.setText("");
            prenom.setText("");
            disponibilité.setText("");
            prixHeure.setText("");
            showCoach();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void DeletCoach(ActionEvent event) {
        CoachService cs = new CoachService();
        System.out.println(id_coach.getText());
        Coach c = new Coach();
        int id = Integer.parseInt(id_coach.getText());

        c.setId(id);

        try {
            cs.DeletCoach(id);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Coach supprimé avec succes!");
            alert.show();
            id_coach.setText("");
            showCoach();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @FXML
    private void modifierCoach(ActionEvent event) {
        CoachService cs = new CoachService();
        System.out.println(nom.getText());
        System.out.println(prenom.getText());
        System.out.println(disponibilité.getText());
        System.out.println(prixHeure.getText());

        Coach c = new Coach();

        String nm = nom.getText();
        String pm = prenom.getText();
        int id = Integer.parseInt(id_coach.getText());
        boolean ic = Boolean.parseBoolean(disponibilité.getText());
        float ip = Float.parseFloat(prixHeure.getText());
        c.setNom(nm);
        c.setPrenom(pm);
        c.setDisponibilité(ic);
        c.setPrixHeure(ip);
        c.setId(id);

        try {
            cs.modifierCoach(id, nm, pm, ic, ip);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Coach is updated successfully!");
            alert.show();
            id_coach.setText("");
            nom.setText("");
            prenom.setText("");
            disponibilité.setText("");
            prixHeure.setText("");
            showCoach();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @FXML
    void ChercherCoach() {
          CoachService cs = new CoachService();
        ObservableList<Coach> list = cs.getCoachList();
         Id_coach.setCellValueFactory(new PropertyValueFactory<Coach, Integer>("id"));
        Nom.setCellValueFactory(new PropertyValueFactory<Coach, String>("nom"));
        Prenom.setCellValueFactory(new PropertyValueFactory<Coach, String>("prenom"));
        Disponibilité.setCellValueFactory(new PropertyValueFactory<Coach, Boolean>("disponibilité"));
        PrixHeure.setCellValueFactory(new PropertyValueFactory<Coach, Float>("prixHeure"));
        table_coach.setItems(list);

        FilteredList<Coach> filteredList = new FilteredList<>(list, b -> true);
        fieldbtn.textProperty().addListener(((observableValue, oldValue, newValue) ->
        {
            filteredList.setPredicate(joueur ->
            {
                if (newValue == null || newValue.isEmpty()) {
                    return true;

                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (String.valueOf(joueur.getId()).indexOf(lowerCaseFilter) != -1) {
                    return true;


                } else if (joueur.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;

                } else if (joueur.getPrenom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;

               

                }  else
                    return false;
            });
                    }));
                }

    public void showCoach() {
        CoachService cs = new CoachService();
        ObservableList<Coach> list = cs.getCoachList();

        Id_coach.setCellValueFactory(new PropertyValueFactory<Coach, Integer>("id"));
        Nom.setCellValueFactory(new PropertyValueFactory<Coach, String>("nom"));
        Prenom.setCellValueFactory(new PropertyValueFactory<Coach, String>("prenom"));
        Disponibilité.setCellValueFactory(new PropertyValueFactory<Coach, Boolean>("disponibilité"));
        PrixHeure.setCellValueFactory(new PropertyValueFactory<Coach, Float>("prixHeure"));

        table_coach.setItems(list);
    }

}
