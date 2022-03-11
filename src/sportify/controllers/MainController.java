/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportify.controllers;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import static java.nio.file.Files.list;
import static java.rmi.Naming.list;
import java.sql.SQLException;
import static java.util.Collections.list;
import java.util.Observable;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import sportify.models.Commande;
import sportify.models.Livraison;
import sportify.services.commandeservice;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class MainController implements Initializable {

    @FXML
    private TextField Id_commande;
    @FXML
    private TextField Prix_totale;
    @FXML
    private TextField Id_user;
    @FXML
    private TextField Id_panier;
    @FXML
    private TableView<Commande> Table;
    @FXML
    private TableColumn<Commande,Integer> Id_com;
    @FXML
    private TableColumn<Commande, Float> PrixTotale;
    @FXML
    private TableColumn<Commande, Integer> IdUser;
    @FXML
    private TableColumn<Commande, Integer> IdPanier;
    @FXML
    private Button add;
    @FXML
    private TextField fieldbtn;
    ObservableList<Commande> listC; 
    
      ObservableList<Commande> datalist; 
        private Button button ;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showCommmande();
        setCellValue();
        ChercherCoach();

        }
        

    @FXML
    private void AddCommande(ActionEvent event) {
            commandeservice  cs= new commandeservice();
                    if (((isNumeric(Id_commande.getText()) && ((Id_commande.getText().length() != 0))) && ((isNumeric(Id_panier.getText()))) && ((Id_panier.getText().length() != 0)) && ((isNumeric(Prix_totale.getText())))&&((Prix_totale.getText().length() != 0)))) {

            //System.out.println(Id_commande.getText());
            System.out.println(Prix_totale.getText());
            System.out.println(Id_user.getText());
            System.out.println(Id_panier.getText());
        
            Commande c =new Commande();
           // c.setText(Id_commande.getText());
          float f= Float.parseFloat(Prix_totale.getText()); 
          int ic=Integer.parseInt(Id_user.getText()) ; 
          int ip= Integer.parseInt(Id_panier.getText()) ;       
           c.setPrixTotale(f);
            c.setIdClient(ic);
            c.setId_panier(ip);
                        

            try {
                cs.Add(c) ;
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
                alert.setContentText("Commande is added successfully!");
            alert.show();
            Id_commande.setText("");
            Prix_totale.setText("");
            Id_user.setText("");
            Id_panier.setText("");
            showCommmande();
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } 
            }
         else{
              Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setContentText("Invalid fields!");
            alert.show();
        }
   
    } 
      @FXML
        private void UpdateCommande(ActionEvent event) {
            commandeservice  cs= new commandeservice();
                                if (((isNumeric(Id_commande.getText()) && ((Id_commande.getText().length() != 0))) && ((isNumeric(Id_panier.getText()))) && ((Id_panier.getText().length() != 0)) && ((isNumeric(Prix_totale.getText())))&&((Prix_totale.getText().length() != 0)))) {

            System.out.println(Id_commande.getText());
            System.out.println(Prix_totale.getText());
            System.out.println(Id_user.getText());
            System.out.println(Id_panier.getText());
        
            Commande c =new Commande();
           
            int id=Integer.parseInt(Id_commande.getText()) ; 
           float f= Float.parseFloat(Prix_totale.getText()); 
          int ic=Integer.parseInt(Id_user.getText()) ; 
          int ip= Integer.parseInt(Id_panier.getText()) ;       
           c.setPrixTotale(f);
            c.setIdClient(ic);
            c.setId_panier(ip);
            c.setId(id);
                        

            try {
                cs.Update(id,f,ic,ip) ;
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Commande is updated successfully!");
            alert.show();
            Id_commande.setText("");
            Prix_totale.setText("");
            Id_user.setText("");
            Id_panier.setText("");
            showCommmande();
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } }
          else{
              Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setContentText("Invalid fields!");
            alert.show();
        }
    } 
    
        @FXML
    private void Deletecommande(ActionEvent event) {
            commandeservice  cs= new commandeservice();
                                            if (((isNumeric(Id_commande.getText()) && ((Id_commande.getText().length() != 0))) && ((isNumeric(Id_panier.getText()))) && ((Id_panier.getText().length() != 0)) && ((isNumeric(Prix_totale.getText())))&&((Prix_totale.getText().length() != 0)))) {

            System.out.println(Id_commande.getText());
           
        
            Commande c =new Commande();
           
            int id=Integer.parseInt(Id_commande.getText()) ; 
               
           
            c.setId(id);
                        

            try 
            {
            cs.Delete(id); 
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Commande is deleted successfully!");
            alert.show();
            Id_commande.setText("");
            showCommmande();
            
            }
        catch (SQLException ex) 
        {
            System.out.println(ex.getMessage());
        }
             }
          else{
              Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setContentText("Invalid fields!");
            alert.show();
        }
   
    } 
    
    
    
    public void showCommmande()
    {   commandeservice  cs= new commandeservice();
     ObservableList<Commande> list=cs.getcommandeList();  
     

   Id_com.setCellValueFactory(new PropertyValueFactory<Commande,Integer>("id"));
   PrixTotale.setCellValueFactory(new PropertyValueFactory<Commande,Float>("PrixTotale"));
   IdUser.setCellValueFactory(new PropertyValueFactory<Commande,Integer>("idClient"));
   IdPanier.setCellValueFactory(new PropertyValueFactory<Commande,Integer>("id_panier"));
   Table.setItems(list);
     
        
    }
        
    @FXML
    public void setCellValue(){
    
    Table.setOnMouseClicked(new EventHandler<MouseEvent>(){
    public void handle(MouseEvent event)
    {
        Commande p1=Table.getItems().get(Table.getSelectionModel().getSelectedIndex());
        Id_commande.setText(String.valueOf(p1.getId()));
        Prix_totale.setText(String.valueOf(p1.getPrixTotale()));
           Id_user.setText(String.valueOf(p1.getIdClient()));
              Id_panier.setText(String.valueOf(p1.getId_panier()));
    }
});     
}   
    @FXML
    public void enregistrerPdf()
    {
              Commande c =new Commande();
                  commandeservice  cs= new commandeservice();
           
            int id=Integer.parseInt(Id_commande.getText()) ; 
           float f= Float.parseFloat(Prix_totale.getText()); 
          int ic=Integer.parseInt(Id_user.getText()) ; 
          int ip= Integer.parseInt(Id_panier.getText()) ;   
        try{
                                Desktop.getDesktop().open(new File("C:\\Users\\lenovo\\Desktop\\pdf_gen\\facture.pdf"));
                                   }
                             catch(IOException e){
                            System.out.println(e);
                                    }
        c.setIdClient(ic);
        c.setId(id);
        c.setId_panier(ip);
        c.setPrixTotale(f);
      cs.factureClient(c);
    }
    @FXML
    public void promotion()
    {     commandeservice cs =new commandeservice() ; 
                Commande p1=Table.getItems().get(Table.getSelectionModel().getSelectedIndex());
        Id_commande.setText(String.valueOf(p1.getId()));
        Prix_totale.setText(String.valueOf(p1.getPrixTotale()));
           Id_user.setText(String.valueOf(p1.getIdClient()));
              Id_panier.setText(String.valueOf(p1.getId_panier()));
              
                     int id=Integer.parseInt(Id_commande.getText()) ; 
           float f= Float.parseFloat(Prix_totale.getText()); 
          int ic=Integer.parseInt(Id_user.getText()) ; 
          int ip= Integer.parseInt(Id_panier.getText()) ; 
    float ff=f-(f*10/100); 
              try {
                cs.Update(id,ff,ic,ip) ;
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("promotion ajouté!");
            alert.show();
            Id_commande.setText("");
            Prix_totale.setText("");
            Id_user.setText("");
            Id_panier.setText("");
            showCommmande();
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    @FXML
   public void ChercherCoach() {
          commandeservice cs = new commandeservice();
        
       Id_com.setCellValueFactory(new PropertyValueFactory<Commande,Integer>("id"));
   PrixTotale.setCellValueFactory(new PropertyValueFactory<Commande,Float>("PrixTotale"));
   IdUser.setCellValueFactory(new PropertyValueFactory<Commande,Integer>("idClient"));
   IdPanier.setCellValueFactory(new PropertyValueFactory<Commande,Integer>("id_panier"));
   datalist=cs.getcommandeList() ; 
   
   Table.setItems(datalist);

        FilteredList<Commande> filteredList = new FilteredList<>(datalist, b -> true);
        fieldbtn.textProperty().addListener(((observableValue, oldValue, newValue) ->
        {
            filteredList.setPredicate(Commande ->
            {
                if (newValue == null || newValue.isEmpty()) {
                    return true;

                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (String.valueOf(Commande.getId()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                    


                } else if (String.valueOf(Commande.getIdClient()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;

                } else if (String.valueOf(Commande.getPrixTotale()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;

               

                }  else
                    return false;
            });
                    }));
        SortedList<Commande> sorteddata=new SortedList<>(filteredList) ;
        sorteddata.comparatorProperty().bind(Table.comparatorProperty()); 
        Table.setItems(sorteddata);
    
    } 
   public static boolean isAlpha(String s) {
        return s != null && s.matches("^[a-zA-Z]*$");
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
    
    
}

