/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportify.controllers;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.io.Console;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.Instant;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import sportify.models.Commande;
import sportify.models.Livraison;
import sportify.services.EnvoyerMail;
import sportify.services.commandeservice;
import sportify.services.livraisonservice;

import java.time.LocalDateTime;

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
    private DatePicker date_livraison;
    @FXML
    private ComboBox<String>  methode_payement;
    @FXML
    private TextField id_cmd;
 
    @FXML
    private TextField adresse;
    @FXML
    private TextField adresse_c;
    @FXML
    private TextField code_postale;
    @FXML
    private TextField ville;
    @FXML
    private TableColumn<Livraison, String> adresse_comp;
    @FXML
    private TableColumn<Livraison, String> c_adresse;
    @FXML
    private TableColumn<Livraison, Integer> c_code_postale;
    @FXML
    private TableColumn<Livraison, String> c_ville;
       private String methode_payement_string; 
             ObservableList<Livraison> datalist; 
    @FXML
    private TextField fieldbtn;
 

    /** 
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> list = FXCollections.observableArrayList("Par Carte Stripe ","Cache");
        methode_payement.setItems(list);
       showLivraison();
       //listviewselect(); 
       setCellValue();
       ChercherCoach();
       

    }    
    
   
 @FXML
    void Select(ActionEvent event) {
      methode_payement_string = methode_payement.getSelectionModel().getSelectedItem().toString();
        
      
    }
    @FXML
    private void AddLivraison(ActionEvent event) throws Exception { 
                livraisonservice  ls= new livraisonservice();
         
            
     
        
            Livraison l =new Livraison();
            if ((((methode_payement.getValue()!= null))) && ((isAlpha(adresse.getText()))&& (adresse.getText().length() != 0) ) && ((isAlpha(adresse_c.getText()))&& (adresse_c.getText().length() != 0) )
                && ((isNumeric(code_postale.getText())))&& ((isAlpha(ville.getText()))&& (ville.getText().length() != 0) )&& ((isNumeric(id_cmd.getText()) && ((id_cmd.getText().length()!= 0))))) {
         //int id=Integer.parseInt(id_livr.getText());
          Date date=Date.valueOf(date_livraison.getValue()); 
          String meth= methode_payement.getValue(); 
          int id_c= Integer.parseInt(id_cmd.getText()) ;  
          int codepostale=Integer.parseInt(code_postale.getText()) ; 
          
          
          //l.setIdLiv(id);
          l.setDateLivraison(date);
          l.setMethode_payement(meth);
          l.setAdresse(adresse.getText());
          l.setComp_adresse(adresse_c.getText());
          l.setCode_postal(codepostale);
          l.setVille(ville.getText());
          l.setId(id_c);

            try {
                ls.Add(l) ;
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Livraison is added successfully!");
           // EnvoyerMail.sendMail("salim.sghaier@esprit.tn","Vous avez un nouveau client: \n" +"Son identifiant est:");
            alert.show();
            id_livr.setText("");
            //methode_payement.setText("");
            date_livraison.setValue(null);
            id_cmd.setText("");
            showLivraison();
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
            }
         else{
              Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setContentText("Invalid champ!");
            alert.show();
        }
    }

    public void showLivraison()
    {   livraisonservice  ls= new livraisonservice();
     ObservableList<Livraison> list=ls.getlivraisonList(); 
 
     id_liv.setCellValueFactory(new PropertyValueFactory<Livraison,Integer>("idLiv"));
  date_livr.setCellValueFactory(new PropertyValueFactory<Livraison,Date>("DateLivraison"));
   methode_pai.setCellValueFactory(new PropertyValueFactory<Livraison,String>("Methode_payement"));
   id_com.setCellValueFactory(new PropertyValueFactory<Livraison,Integer>("id"));
   c_adresse.setCellValueFactory(new PropertyValueFactory<Livraison,String>("adresse"));
      adresse_comp.setCellValueFactory(new PropertyValueFactory<Livraison,String>("comp_adresse"));
         c_code_postale.setCellValueFactory(new PropertyValueFactory<Livraison,Integer>("Code_postal"));
            c_ville.setCellValueFactory(new PropertyValueFactory<Livraison,String>("Ville"));
   
   Table.setItems(list);
     
        
    }
    
    
public void setCellValue(){
    
    Table.setOnMouseClicked(new EventHandler<MouseEvent>(){
    public void handle(MouseEvent event)
    { 
       
        Livraison p1=Table.getItems().get(Table.getSelectionModel().getSelectedIndex());
        id_livr.setText(String.valueOf(p1.getIdLiv()));
     
     //date_livraison.setValue(p1.getDateLivraison()); 
           methode_payement.setValue(String.valueOf(p1.getMethode_payement()));
               methode_payement.setValue(String.valueOf(p1.getMethode_payement()));
                   adresse.setText(String.valueOf(p1.getAdresse()));
                       code_postale.setText(String.valueOf(p1.getCode_postal()));
                           ville.setText(String.valueOf(p1.getVille()));
                           adresse_c.setText(String.valueOf(p1.getComp_adresse()));
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
 private void Updatelivraison(ActionEvent event){
            livraisonservice  ls= new livraisonservice();
                        if ((((methode_payement.getValue()!= null))) && ((isAlpha(adresse.getText()))&& (adresse.getText().length() != 0) ) && ((isAlpha(adresse_c.getText()))&& (adresse_c.getText().length() != 0) )
                && ((isNumeric(code_postale.getText())))&& ((isAlpha(ville.getText()))&& (ville.getText().length() != 0) )&& ((isNumeric(id_cmd.getText()) && ((id_cmd.getText().length()!= 0))))) {
             System.out.println(id_livr.getText());
            System.out.println(date_livraison.getValue());
            System.out.println(methode_payement_string);
              System.out.println(adresse.getText());
                            System.out.println(adresse_c.getText());
                                    System.out.println(code_postale.getText());
                                            System.out.println(ville.getText());
            System.out.println(id_cmd.getText());
        
        
            Livraison l =new Livraison();
           
       int id=Integer.parseInt(id_livr.getText());
          Date date=Date.valueOf(date_livraison.getValue()); 
          String meth= methode_payement.getSelectionModel().getSelectedItem().toString(); 
          int id_c= Integer.parseInt(id_cmd.getText()) ;    
                    int codepostale=Integer.parseInt(code_postale.getText()) ; 
          l.setIdLiv(id);
          l.setDateLivraison(date);
          l.setMethode_payement(meth);
          l.setId(id_c);
            l.setAdresse(adresse.getText());
          l.setComp_adresse(adresse_c.getText());
          l.setCode_postal(codepostale);
          l.setVille(ville.getText());
          

            try {
               ls.Update(id,date,meth,adresse.getText(),adresse_c.getText(),codepostale,ville.getText(),id_c) ;
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Commande is updated successfully!");
            alert.show();
           id_livr.setText("");
            methode_payement.setValue(methode_pai.getText());
            date_livraison.setValue(null);
            id_cmd.setText("");
            showLivraison();
        }
 
   catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
             }
         else{
              Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setContentText("Invalid champ!");
            alert.show();
        }

 } 
    @FXML
    private void DeleteLivraison(ActionEvent event) {
            livraisonservice  ls= new livraisonservice();
       
                        if ((((methode_payement.getValue()!= null))) && ((isAlpha(adresse.getText()))&& (adresse.getText().length() != 0) ) && ((isAlpha(adresse_c.getText()))&& (adresse_c.getText().length() != 0) )
                && ((isNumeric(code_postale.getText())) )&& ((isAlpha(ville.getText()))&& (ville.getText().length() != 0) )&& ((isNumeric(id_cmd.getText()) && ((id_cmd.getText().length()!= 0))))) {
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
             date_livraison.setValue(null);
            id_cmd.setText("");
            showLivraison();
            
            }
        catch (SQLException ex) 
        {
            System.out.println(ex.getMessage());
        } 
                        }   else{
              Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setContentText("Invalid champ!");
            alert.show();
        }
   
    } 
    /*@FXML
 private void trier(ActionEvent event) {
          ObservableList<Livraison> recList = FXCollections.observableArrayList();
           id_liv.setCellValueFactory(new PropertyValueFactory<Livraison,Integer>("idLiv"));
  date_livr.setCellValueFactory(new PropertyValueFactory<Livraison,Date>("DateLivraison"));
   methode_pai.setCellValueFactory(new PropertyValueFactory<Livraison,String>("Methode_payement"));
   id_com.setCellValueFactory(new PropertyValueFactory<Livraison,Integer>("id"));
   c_adresse.setCellValueFactory(new PropertyValueFactory<Livraison,String>("adresse"));
      adresse_comp.setCellValueFactory(new PropertyValueFactory<Livraison,String>("comp_adresse"));
         c_code_postale.setCellValueFactory(new PropertyValueFactory<Livraison,Integer>("Code_postal"));
            c_ville.setCellValueFactory(new PropertyValueFactory<Livraison,String>("Ville"));
   
        livraisonservice rt = new livraisonservice();
        List old = rt.trierreservationdate();
        recList.addAll(old);
        Table.setItems(recList);
        Table.refresh();    
 }

 */

    @FXML
    private void trier(ActionEvent event) {
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
    @FXML
       public void ChercherCoach() {
          livraisonservice cs = new livraisonservice();
        
 
     id_liv.setCellValueFactory(new PropertyValueFactory<Livraison,Integer>("idLiv"));
  date_livr.setCellValueFactory(new PropertyValueFactory<Livraison,Date>("DateLivraison"));
   methode_pai.setCellValueFactory(new PropertyValueFactory<Livraison,String>("Methode_payement"));
   id_com.setCellValueFactory(new PropertyValueFactory<Livraison,Integer>("id"));
   c_adresse.setCellValueFactory(new PropertyValueFactory<Livraison,String>("adresse"));
      adresse_comp.setCellValueFactory(new PropertyValueFactory<Livraison,String>("comp_adresse"));
         c_code_postale.setCellValueFactory(new PropertyValueFactory<Livraison,Integer>("Code_postal"));
            c_ville.setCellValueFactory(new PropertyValueFactory<Livraison,String>("Ville"));
    datalist=cs.getlivraisonList() ; 
   Table.setItems(datalist);

        FilteredList<Livraison> filteredList = new FilteredList<>(datalist, b -> true);
        fieldbtn.textProperty().addListener(((observableValue, oldValue, newValue) ->
        {
            filteredList.setPredicate(Livraison ->
            {
                if (newValue == null || newValue.isEmpty()) {
                    return true;

                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (String.valueOf(Livraison.getIdLiv()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                    


                } else if (String.valueOf(Livraison.getAdresse()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;

                } else if (String.valueOf(Livraison.getCode_postal()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;

               

                }  else if (String.valueOf(Livraison.getDateLivraison()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;

                    
               

                }  else if (String.valueOf(Livraison.getVille()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;

                    
               

                }else if (String.valueOf(Livraison.getComp_adresse()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;

                    
               

                }else if (String.valueOf(Livraison.getId()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;

                    
               

                }
                    return false;
            });
                    }));
        SortedList<Livraison> sorteddata=new SortedList<>(filteredList) ;
        sorteddata.comparatorProperty().bind(Table.comparatorProperty()); 
        Table.setItems(sorteddata);





}
}