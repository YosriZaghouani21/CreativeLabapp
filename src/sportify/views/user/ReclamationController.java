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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sportify.models.user.Reclamation;
import sportify.services.user.ImpServiceReclamation;

/**
 * FXML Controller class
 *
 * @author zagho
 */
public class ReclamationController implements Initializable {

    @FXML
    private Button Editusr;
    @FXML
    private Button supprimer;
    @FXML
    private Button Refresh;
    @FXML
    private TableView<Reclamation> table;
    @FXML
    private Button Retour;

    public ObservableList<Reclamation> dataCat = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Reclamation, Integer> idReclamation;
    @FXML
    private TableColumn<Reclamation, String> Description;
    @FXML
    private TableColumn<Reclamation, String> Date;
    @FXML
    private TableColumn<Reclamation, Integer> Id;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadData();
    }

    @FXML
    private void supprimer(ActionEvent event) {
        if (table.getSelectionModel().getSelectedItem() != null) {
            Alert deleteCatAlert = new Alert(Alert.AlertType.CONFIRMATION);
            deleteCatAlert.setTitle("Supprimer Reclamation");
            deleteCatAlert.setHeaderText(null);
            deleteCatAlert.setContentText("Voulez-vous vraiment supprimer cette Reclamation ?");
            Optional<ButtonType> optionDeleteBookAlert = deleteCatAlert.showAndWait();
            if (optionDeleteBookAlert.get() == ButtonType.OK) {
                Reclamation A = table.getSelectionModel().getSelectedItem();
                ImpServiceReclamation pc = new ImpServiceReclamation();
                pc.supprimer(A.getId());
                dataCat.clear();
                dataCat.addAll(pc.afficher());
                //Alert Delete Blog :
                Alert succDeleteAdminAlert = new Alert(Alert.AlertType.INFORMATION);
                succDeleteAdminAlert.setTitle("Reclamation ");
                succDeleteAdminAlert.setHeaderText("Resultats:");
                succDeleteAdminAlert.setContentText("Reclamation supprimé !");

                succDeleteAdminAlert.showAndWait();
            } else if (optionDeleteBookAlert.get() == ButtonType.CANCEL) {

            }

        } else {

            Alert selectBookAlert = new Alert(Alert.AlertType.WARNING);
            selectBookAlert.setTitle("Choisir une Reclamation");
            selectBookAlert.setHeaderText(null);
            selectBookAlert.setContentText("Choisir une Reclamation avant de supprimer!");
            selectBookAlert.showAndWait();

        }

    }

    @FXML
    private void Refresh() {

        dataCat.clear();
        ImpServiceReclamation cl = new ImpServiceReclamation();
        dataCat.addAll(cl.afficher());
        cl.afficher();
        table.setItems(dataCat);
    }

    @FXML
    private void Cancel(ActionEvent event) throws IOException {

        Stage primaryStage = (Stage) Retour.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/sportify/views/user/FXMLMain.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void loadData() {

        Refresh();
        idReclamation.setCellValueFactory(new PropertyValueFactory<>("idReclamation"));
        Description.setCellValueFactory(new PropertyValueFactory<>("Description"));
        Date.setCellValueFactory(new PropertyValueFactory<>("Date"));
        Id.setCellValueFactory(new PropertyValueFactory<>("Id"));
        table.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                if (event.getClickCount() == 2) {
                    FXMLLoader Loader = new FXMLLoader();
                    Loader.setLocation(getClass().getResource("ModifierReclamation.fxml"));
                    try {
                        Loader.load();
                    } catch (IOException ex) {
                        // ex.printStackTrace();

                        System.out.println("error : " + ex.getMessage());;
                    }
                    ModifierReclamationController Mrc = Loader.getController();
                    Mrc.setData(table.getSelectionModel().getSelectedItem().getId(),
                            table.getSelectionModel().getSelectedItem().getIdReclamation(),
                            table.getSelectionModel().getSelectedItem().getDescription(),
                            table.getSelectionModel().getSelectedItem().getDate());
                           

                    Parent p = Loader.getRoot();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(p));
                    stage.show();
                }
            }

        });
    }

    @FXML
    private void Edit_User(ActionEvent event) {
    }

}
