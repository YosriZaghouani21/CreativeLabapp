/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportify.views.Front;

import Sportifydesktop.infrastructure.DBconnector;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import sportify.models.events.Evenement;
import sportify.services.events.eventCrud;
import sportify.views.event.ModifierEvController;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class AfficherFrontController implements Initializable {

    private TableView<Evenement> table2;
    private TableColumn<Evenement, String> nomev;
    private TableColumn<Evenement, String> dateev;
    private TableColumn<Evenement, String> locev;
    private TableColumn<Evenement, String> descev;
    private TableColumn<Evenement, String> categev;
    private TextField rechercher;
    public ObservableList<Evenement> dataEvent = FXCollections.observableArrayList();
    @FXML
    private GridPane gridPaneEvents;

    private PreparedStatement pst;
    private Statement ste;
    Connection conn = DBconnector.getInstance().getCnx();
    @FXML
    private ScrollPane scrollPaneEvents;
    @FXML
    private Button Retour;
    @FXML
    private Button Editusr;
    @FXML
    private Button btnReclamation;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            insertEvenements();
            // TODO
        } catch (IOException ex) {
            Logger.getLogger(AfficherFrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Evenement> getListEvents() {
        List<Evenement> Ev = new ArrayList<>();

        String req = "SELECT * from `evenement`";

        try {
            pst = conn.prepareStatement(req);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Evenement E = new Evenement();
                E.setIdEv(rs.getInt("idEv"));
                E.setNomEv(rs.getString(2));
                E.setDateEv(rs.getString(3));
                E.setLocalisation(rs.getString(4));
                E.setDesccription(rs.getString(5));
                E.setIdCategEv(rs.getInt(6));
                Ev.add(E);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return Ev;
    }

    public void insertEvenements() throws IOException {
        List<Evenement> LE = getListEvents();
        for (int i = 0; i < LE.size(); i++) {
            FXMLLoader Loader1 = new FXMLLoader();
            Loader1.setLocation(getClass().getResource("/sportify/views/Front/evenement.fxml"));
            AnchorPane anchorPane = Loader1.load();
            EvenementController eventController = Loader1.getController();
            eventController.setEvent(LE.get(i));
            gridPaneEvents.add(anchorPane, 0, i);
//                gridPaneEvents.setMinWidth(Region.USE_COMPUTED_SIZE);
//                gridPaneEvents.setPrefWidth(Region.USE_COMPUTED_SIZE);
//                gridPaneEvents.setMaxWidth(Region.USE_PREF_SIZE);
//                //set grid height
//                gridPaneEvents.setMinHeight(Region.USE_COMPUTED_SIZE);
//                gridPaneEvents.setPrefHeight(Region.USE_COMPUTED_SIZE);
//                gridPaneEvents.setMaxHeight(Region.USE_PREF_SIZE);
            GridPane.setMargin(anchorPane, new Insets(50));
        }
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
         Stage primaryStage = (Stage) Retour.getScene().getWindow(); 
       Parent root = FXMLLoader.load(getClass().getResource("/sportify/views/Front/CrudFront.fxml"));
       Scene scene = new Scene (root);
       primaryStage.setScene(scene);
       primaryStage.show();
    }

    @FXML
    private void Edit_User(ActionEvent event) {
    }

    @FXML
    private void srcReclamation(ActionEvent event) {
    }
}
