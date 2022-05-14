/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportify.views.events;

import javafx.scene.input.MouseEvent;
import sportify.models.events.Categorieevenement;
import sportify.services.events.categEvCrud;
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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class AfficherController implements Initializable {

    @FXML
    private TableView<Categorieevenement> table;
    @FXML
    private TableColumn<Categorieevenement, String> nomcatg;
    @FXML
    private TableColumn<Categorieevenement, String> typecatg;
    @FXML
    private Button back6;
    @FXML
    private Button supprimer;
    public ObservableList<Categorieevenement> dataCat = FXCollections.observableArrayList();
    @FXML
    private Button ref;
    @FXML
    private TextField recherhcer;
    @FXML
    private Button butEven;
    @FXML
    private Button butCatg;
    @FXML
    private Button btnOverview;
    @FXML
    private Button btnOrders;
    @FXML
    private Button btnCustomers;
    @FXML
    private Button btnSettings;
    @FXML
    private Button btnSignout;
    @FXML
    private Pane pnlCustomer;
    @FXML
    private Pane pnlOrders;
    @FXML
    private Pane pnlMenus;
    @FXML
    private Pane pnlOverview;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadData();
        searchCategorie();
    }

    @FXML
    private void Refresh() {
        dataCat.clear();
        categEvCrud cl = new categEvCrud();
        dataCat.addAll(cl.afficher());
        cl.afficher();

        table.setItems(dataCat);
    }

    private void loadData() {

        Refresh();
        nomcatg.setCellValueFactory(new PropertyValueFactory<>("NomCategEv"));
        typecatg.setCellValueFactory(new PropertyValueFactory<>("TypeCategEv"));
        table.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                if (event.getClickCount() == 2) {
                    FXMLLoader Loader = new FXMLLoader();
                    Loader.setLocation(getClass().getResource("/sportify/views/events/ModifierCat.fxml"));
                    try {
                        Loader.load();
                    } catch (IOException ex) {
                        // ex.printStackTrace();

                        System.out.println("error : " + ex.getMessage());;
                    }
                    ModifierCatController Mcc = Loader.getController();
                    Mcc.setData(table.getSelectionModel().getSelectedItem().getIdCategEv(),
                            table.getSelectionModel().getSelectedItem().getNomCategEv(),
                            table.getSelectionModel().getSelectedItem().getTypeCategEv());

                    Parent p = Loader.getRoot();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(p));
                    stage.show();
                }
            }

        });
    }

    @FXML
    private void retour6(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) back6.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("crudcateg.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    private void supprimerCat(ActionEvent event) {
        if (table.getSelectionModel().getSelectedItem() != null) {
            Alert deleteCatAlert = new Alert(Alert.AlertType.CONFIRMATION);
            deleteCatAlert.setTitle("Supprimer Categorie");
            deleteCatAlert.setHeaderText(null);
            deleteCatAlert.setContentText("Voulez-vous vraiment supprimer cet Catégorie ?");
            Optional<ButtonType> optionDeleteBookAlert = deleteCatAlert.showAndWait();
            if (optionDeleteBookAlert.get() == ButtonType.OK) {
                Categorieevenement A = table.getSelectionModel().getSelectedItem();
                categEvCrud pc = new categEvCrud();
                pc.supprimer(A.getIdCategEv());
                dataCat.clear();
                dataCat.addAll(pc.afficher());
                //Alert Delete Blog :
                Alert succDeleteAdminAlert = new Alert(Alert.AlertType.INFORMATION);
                succDeleteAdminAlert.setTitle("Supprimer Catégorie");
                succDeleteAdminAlert.setHeaderText("Resultats:");
                succDeleteAdminAlert.setContentText("Catégorie supprimé !");

                succDeleteAdminAlert.showAndWait();
            } else if (optionDeleteBookAlert.get() == ButtonType.CANCEL) {

            }

        } else {

            //Alert Select BOOK :
            Alert selectBookAlert = new Alert(Alert.AlertType.WARNING);
            selectBookAlert.setTitle("Choisir une Catégorie");
            selectBookAlert.setHeaderText(null);
            selectBookAlert.setContentText("Choisir un Catégorie avant de supprimer!");
            selectBookAlert.showAndWait();
            //Alert Select Book !

        }

    }

    void searchCategorie() {
        Categorieevenement ad = new Categorieevenement();
        nomcatg.setCellValueFactory(new PropertyValueFactory<>("NomCategEv"));
        typecatg.setCellValueFactory(new PropertyValueFactory<>("TypeCategEv"));
        table.setItems(dataCat);
        FilteredList<Categorieevenement> filteredData = new FilteredList<>(dataCat, b -> true);
        recherhcer.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate((Categorieevenement adm) -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (adm.getNomCategEv().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches first name.
                } else if (adm.getTypeCategEv().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                } else {
                    return false; // Does not match.
                }
            });
        });
        SortedList<Categorieevenement> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(sortedData);
    }

    @FXML
    private void startEven(ActionEvent event) throws IOException {
          Stage primaryStage = (Stage) butEven.getScene().getWindow(); 
       Parent root = FXMLLoader.load(getClass().getResource("/sportify/views/event/crudev.fxml"));
       Scene scene = new Scene (root);
       primaryStage.setScene(scene);
       primaryStage.show();
    }

    @FXML
    private void startCateg(ActionEvent event) throws IOException {
          Stage primaryStage = (Stage) butCatg.getScene().getWindow(); 
       Parent root = FXMLLoader.load(getClass().getResource("/sportify/views/events/crudcateg.fxml"));
       Scene scene = new Scene (root);
       primaryStage.setScene(scene);
       primaryStage.show();
    }

}
