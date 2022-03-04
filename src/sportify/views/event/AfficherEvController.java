/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportify.views.event;

import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.io.IOException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sportify.models.events.Categorieevenement;
import sportify.models.events.Evenement;
import sportify.services.events.categEvCrud;
import sportify.services.events.eventCrud;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class AfficherEvController implements Initializable {

    @FXML
    private TableView<Evenement> table2;
    @FXML
    private Button back7;
    @FXML
    private TableColumn<Evenement, String> nomev;
    @FXML
    private TableColumn<Evenement, String> dateev;
    @FXML
    private TableColumn<Evenement, String> locev;
    @FXML
    private TableColumn<Evenement, String> descev;
    @FXML
    private TableColumn<Evenement, Integer> categev;
    @FXML
    private Button supprimerEvent;
    public ObservableList<Evenement> dataEvent = FXCollections.observableArrayList();
    @FXML
    private Button ref;
    @FXML
    private TextField rechercher;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadData();
        searchEvenement();
    }    

    @FXML
    private void Refresh() {
        dataEvent.clear();
        eventCrud cl = new eventCrud();
        dataEvent.addAll(cl.afficher());
        cl.afficher();

        table2.setItems(dataEvent);
    }

    private void loadData() {

        Refresh();
        nomev.setCellValueFactory(new PropertyValueFactory<>("nomEv"));
        dateev.setCellValueFactory(new PropertyValueFactory<>("DateEv"));
        locev.setCellValueFactory(new PropertyValueFactory<>("localisation"));
        descev.setCellValueFactory(new PropertyValueFactory<>("desccription"));
        categev.setCellValueFactory(new PropertyValueFactory<>("idCategEv"));

        table2.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                if (event.getClickCount() == 2) {
                    FXMLLoader Loader = new FXMLLoader();
                    Loader.setLocation(getClass().getResource("/sportify/views/event/ModifierEv.fxml"));
                    try {
                        Loader.load();
                    } catch (IOException ex) {
                        // ex.printStackTrace();

                        System.out.println("error : " + ex.getMessage());;
                    }
                    ModifierEvController Mcc = Loader.getController();
                    Mcc.setData(table2.getSelectionModel().getSelectedItem().getIdEv(),
                             table2.getSelectionModel().getSelectedItem().getNomEv(),
                             table2.getSelectionModel().getSelectedItem().getDateEv(),
                             table2.getSelectionModel().getSelectedItem().getLocalisation(),
                             table2.getSelectionModel().getSelectedItem().getDesccription(),
                             table2.getSelectionModel().getSelectedItem().getIdCategEv());

                    Parent p = Loader.getRoot();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(p));
                    stage.show();
                }
            }

        });
    }

   
    
    @FXML
    private void supprimerEvents(ActionEvent event) {
        if (table2.getSelectionModel().getSelectedItem() != null) {
            Alert deleteCatAlert = new Alert(Alert.AlertType.CONFIRMATION);
            deleteCatAlert.setTitle("Supprimer Evenement");
            deleteCatAlert.setHeaderText(null);
            deleteCatAlert.setContentText("Voulez-vous vraiment supprimer cet Evenement ?");
            Optional<ButtonType> optionDeleteBookAlert = deleteCatAlert.showAndWait();
            if (optionDeleteBookAlert.get() == ButtonType.OK) {
                Evenement A = table2.getSelectionModel().getSelectedItem();
                eventCrud pc = new eventCrud();
                pc.supprimer(A.getIdEv());
                dataEvent.clear();
                dataEvent.addAll(pc.afficher());
                //Alert Delete Blog :
                Alert succDeleteAdminAlert = new Alert(Alert.AlertType.INFORMATION);
                succDeleteAdminAlert.setTitle("Supprimer Evenement");
                succDeleteAdminAlert.setHeaderText("Resultats:");
                succDeleteAdminAlert.setContentText("Evenement supprimé !");

                succDeleteAdminAlert.showAndWait();
            } else if (optionDeleteBookAlert.get() == ButtonType.CANCEL) {

            }

        } else {

            //Alert Select BOOK :
            Alert selectBookAlert = new Alert(Alert.AlertType.WARNING);
            selectBookAlert.setTitle("Choisir une Evenement");
            selectBookAlert.setHeaderText(null);
            selectBookAlert.setContentText("Choisir un Evenement avant de supprimer!");
            selectBookAlert.showAndWait();
            //Alert Select Book !

        } 
           
        
    }

    @FXML
    private void retour7(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) back7.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("crudev.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    
     void searchEvenement() {
        Evenement ad = new Evenement();
          nomev.setCellValueFactory(new PropertyValueFactory<>("nomEv"));
        dateev.setCellValueFactory(new PropertyValueFactory<>("DateEv"));
        locev.setCellValueFactory(new PropertyValueFactory<>("localisation"));
        descev.setCellValueFactory(new PropertyValueFactory<>("desccription"));
        categev.setCellValueFactory(new PropertyValueFactory<>("idCategEv"));
         table2.setItems(dataEvent);
        FilteredList<Evenement> filteredData = new FilteredList<>(dataEvent, b -> true);
        rechercher.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate((Evenement adm) -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (adm.getNomEv().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches first name.
                } else if (adm.getDateEv().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                    } else if (adm.getLocalisation().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                   } else if (adm.getDesccription().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;  
                } else {
                    return false; // Does not match.
                }
            });
        });
        SortedList<Evenement> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table2.comparatorProperty());
        table2.setItems(sortedData);
    }
    
}
