/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportify.views.user;

import java.io.IOException;
import javafx.scene.input.MouseEvent;
import java.net.URL;
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
import sportify.models.user.User;
import sportify.services.user.ImpServiceUser;

/**
 * FXML Controller class
 *
 * @author zagho
 */
public class AfficheController implements Initializable {

    @FXML
    private Button supprimer;
    @FXML
    private Button Refresh;
    @FXML
    private TableColumn<User, String> nom;
    @FXML
    private TableColumn<User, String> prenom;
    @FXML
    private TableColumn<User, String> adresse;
    @FXML
    private TableColumn<User, String> email;
    @FXML
    private TableColumn<User, String> password;
    @FXML
    private TableColumn<User, String> type;
    @FXML
    private TableColumn<User, String> num;
    @FXML
    private TableView<User> table;

    public ObservableList<User> dataCat = FXCollections.observableArrayList();
    @FXML
    private Button Editusr;
    @FXML
    private Button Retour;
    @FXML
    private TextField search;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadData();
        searchEvenement() ;
    }

    @FXML
    private void Refresh() {
        dataCat.clear();
        ImpServiceUser cl = new ImpServiceUser();
        dataCat.addAll(cl.afficher());
        cl.afficher();

        table.setItems(dataCat);
    }

    private void loadData() {

        Refresh();
        nom.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("Prenom"));
        adresse.setCellValueFactory(new PropertyValueFactory<>("Adresse"));
        email.setCellValueFactory(new PropertyValueFactory<>("Email"));
        password.setCellValueFactory(new PropertyValueFactory<>("Password"));
        type.setCellValueFactory(new PropertyValueFactory<>("Type"));
        num.setCellValueFactory(new PropertyValueFactory<>("Num"));
        table.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                if (event.getClickCount() == 2) {
                    FXMLLoader Loader = new FXMLLoader();
                    Loader.setLocation(getClass().getResource("Modifier.fxml"));
                    try {
                        Loader.load();
                    } catch (IOException ex) {
                        // ex.printStackTrace();

                        System.out.println("error : " + ex.getMessage());;
                    }
                    ModifierController Mcc = Loader.getController();
                    Mcc.setData(table.getSelectionModel().getSelectedItem().getId(),
                            table.getSelectionModel().getSelectedItem().getNom(),
                            table.getSelectionModel().getSelectedItem().getPrenom(),
                            table.getSelectionModel().getSelectedItem().getAdresse(),
                            table.getSelectionModel().getSelectedItem().getEmail(),
                            table.getSelectionModel().getSelectedItem().getPassword(),
                            table.getSelectionModel().getSelectedItem().getType(),
                            table.getSelectionModel().getSelectedItem().getNum());

                    Parent p = Loader.getRoot();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(p));
                    stage.show();
                }
            }

        });
    }

    @FXML
    private void supprimer(ActionEvent event) {
        if (table.getSelectionModel().getSelectedItem() != null) {
            Alert deleteCatAlert = new Alert(Alert.AlertType.CONFIRMATION);
            deleteCatAlert.setTitle("Supprimer Client");
            deleteCatAlert.setHeaderText(null);
            deleteCatAlert.setContentText("Voulez-vous vraiment supprimer ce Client ?");
            Optional<ButtonType> optionDeleteBookAlert = deleteCatAlert.showAndWait();
            if (optionDeleteBookAlert.get() == ButtonType.OK) {
                User A = table.getSelectionModel().getSelectedItem();
                ImpServiceUser pc = new ImpServiceUser();
                pc.supprimer(A.getId());
                dataCat.clear();
                dataCat.addAll(pc.afficher());
                //Alert Delete Blog :
                Alert succDeleteAdminAlert = new Alert(Alert.AlertType.INFORMATION);
                succDeleteAdminAlert.setTitle("Client ");
                succDeleteAdminAlert.setHeaderText("Resultats:");
                succDeleteAdminAlert.setContentText("Client supprimé !");

                succDeleteAdminAlert.showAndWait();
            } else if (optionDeleteBookAlert.get() == ButtonType.CANCEL) {

            }

        } else {

            Alert selectBookAlert = new Alert(Alert.AlertType.WARNING);
            selectBookAlert.setTitle("Choisir un Client");
            selectBookAlert.setHeaderText(null);
            selectBookAlert.setContentText("Choisir un Client avant de supprimer!");
            selectBookAlert.showAndWait();

        }
    }

    @FXML
    private void Edit_User(ActionEvent event) {
    }

    @FXML
    private void Cancel(ActionEvent event) throws IOException {
         Stage primaryStage = (Stage) Retour.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/sportify/views/user/FXMLMain.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    
    
    void searchEvenement() {
        User ad = new User();
         nom.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("Prenom"));
        adresse.setCellValueFactory(new PropertyValueFactory<>("Adresse"));
        email.setCellValueFactory(new PropertyValueFactory<>("Email"));
        password.setCellValueFactory(new PropertyValueFactory<>("Password"));
        type.setCellValueFactory(new PropertyValueFactory<>("Type"));
        num.setCellValueFactory(new PropertyValueFactory<>("Num"));
       table.setItems(dataCat);
        FilteredList<User> filteredData = new FilteredList<>(dataCat, b -> true);
        search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate((User adm) -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (adm.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches first name.
                } else if (adm.getPrenom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                    } else if (adm.getEmail().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                   
                } else {
                    return false; // Does not match.
                }
            });
        });
        SortedList<User> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(sortedData);
    }

}
