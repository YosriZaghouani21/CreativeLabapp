/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportify;

import java.io.Console;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import sportify.model.Commande;
import sportify.model.Livraison;
import sportify.service.EnvoyerMail;
import sportify.service.commandeservice;
import sportify.service.livraisonservice;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class LivraisonController implements  Initializable {

    @FXML 
    private TableColumn<Livraison, Integer> id_liv;
   
    
    @FXML
    private TableView<Livraison> Table;

    @FXML
    private TableColumn<Livraison, String> methode_pai;
    @FXML
    private TableColumn<Livraison, Integer> id_com;
    @FXML
    private TableColumn<Livraison, Date> date_livr;
    @FXML
    private TextField id_livr;
    @FXML
    private TextField date_livraison;
    @FXML
    private ComboBox methode_payement;
    @FXML
    private TextField id_cmd;

    private String methode_payement_string;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> list = FXCollections.observableArrayList("Par Carte","Cache","Paypal");
        methode_payement.setItems(list);
       showLivraison();
       //listviewselect(); 
       setCellValue();
       

    }    
    
    @FXML
    void Select(ActionEvent event) {
      methode_payement_string = methode_payement.getSelectionModel().getSelectedItem().toString();
        
      
    }

    @FXML
    private void AddLivraison(ActionEvent event) throws Exception { 
                livraisonservice  ls= new livraisonservice();
            System.out.println(id_livr.getText());
            System.out.println(date_livraison.getText());
            System.out.println(methode_payement_string);
            System.out.println(id_cmd.getText());
        
            Livraison l =new Livraison();
         //int id=Integer.parseInt(id_livr.getText());
          Date date=Date.valueOf(date_livraison.getText()); 
          String meth= methode_payement_string; 
          int id_c= Integer.parseInt(id_cmd.getText()) ;       
          //l.setIdLiv(id);
          l.setDateLivraison(date);
          l.setMethode_payement(meth);
          l.setId(id_c);

            try {
                ls.Add(l) ;
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Livraison is added successfully!");
            EnvoyerMail.sendMail("salim.sghaier@esprit.tn","Vous avez un nouveau client: \n" +"Son identifiant est:");
            alert.show();
            id_livr.setText("");
            //methode_payement.setText("");
            date_livraison.setText("");
            id_cmd.setText("");
            showLivraison();
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void showLivraison()
    {   livraisonservice  ls= new livraisonservice();
     ObservableList<Livraison> list=ls.getlivraisonList(); 
 
     id_liv.setCellValueFactory(new PropertyValueFactory<Livraison,Integer>("idLiv"));
  date_livr.setCellValueFactory(new PropertyValueFactory<Livraison,Date>("DateLivraison"));
   methode_pai.setCellValueFactory(new PropertyValueFactory<Livraison,String>("Methode_payement"));
   id_com.setCellValueFactory(new PropertyValueFactory<Livraison,Integer>("id"));
   Table.setItems(list);
     
        
    }
    
    
public void setCellValue(){
    
    Table.setOnMouseClicked(new EventHandler<MouseEvent>(){
    public void handle(MouseEvent event)
    {
        Livraison p1=Table.getItems().get(Table.getSelectionModel().getSelectedIndex());
        id_livr.setText(String.valueOf(p1.getIdLiv()));
        date_livraison.setText(String.valueOf(p1.getDateLivraison()));
           //id_livr.setText(String.valueOf(p1.getIdLiv()));
              id_cmd.setText(String.valueOf(p1.getId()));
    }
});
}
  /* @FXML
public void listviewselect(MouseEvent event ){ 
   ObservableList<Livraison> list ; 
    list=(ObservableList<Livraison>) Table.getSelectionModel().getSelectedCells().get(0);
      id_livr.setText(String.valueOf(list.get(0).getIdLiv()));

}
    */
    @FXML
 private void Updatelivraison(ActionEvent event) {
            livraisonservice  ls= new livraisonservice();
             System.out.println(id_livr.getText());
            System.out.println(date_livraison.getText());
            System.out.println(methode_payement_string);
            System.out.println(id_cmd.getText());
        
        
            Livraison l =new Livraison();
           
       int id=Integer.parseInt(id_livr.getText());
          Date date=Date.valueOf(date_livraison.getText()); 
          String meth= methode_payement.getSelectionModel().getSelectedItem().toString(); 
          int id_c= Integer.parseInt(id_cmd.getText()) ;       
          l.setIdLiv(id);
          l.setDateLivraison(date);
          l.setMethode_payement(meth);
          l.setId(id_c);

            try {
                ls.Update(id,date,meth,id_c) ;
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Commande is updated successfully!");
            alert.show();
           id_livr.setText("");
            //methode_payement.setText("");
            date_livraison.setText("");
            id_cmd.setText("");
            showLivraison();
        }
 
   catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

 } 
    @FXML
    private void DeleteLivraison(ActionEvent event) {
            livraisonservice  ls= new livraisonservice();
            System.out.println(id_livr.getText());
           
        
            Livraison c =new Livraison();
           
            int id=Integer.parseInt(id_livr.getText()) ; 
            c.setId(id);
            try 
            {
            ls.Delete(id); 
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("livraison is deleted successfully!");
            alert.show();
            id_livr.setText("");
             date_livraison.setText("");
            id_cmd.setText("");
            showLivraison();
            
            }
        catch (SQLException ex) 
        {
            System.out.println(ex.getMessage());
        }
   
    } 
 
}
