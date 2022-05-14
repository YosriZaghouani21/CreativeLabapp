/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportify.controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import sportify.services.articles.ServiceArticle;
import sportify.models.articles.Article;

/**
 * FXML Controller class
 *
 * @author dali
 */
public class FormulaireController implements Initializable {

    
    @FXML
    private Label a;
    @FXML
    private TextField marqueId1;
    @FXML
    private TextField prixId1;
    @FXML
    private TextField descId1;
    @FXML
    private TextField catId1;
    @FXML
    private TextField pathiamge1;
    @FXML
    private Button addbution1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void Addarticle(ActionEvent event) {
        ServiceArticle serArt = new ServiceArticle();
        Article art = new Article();
        if (((marqueId1.getText().length() != 0) && (isAlpha(marqueId1.getText())))
                && ((descId1.getText().length() != 0) && (isAlpha(descId1.getText())))
                && (prixId1.getText().length() != 0 && (isNumeric(prixId1.getText())))
                && (catId1.getText().length() != 0 && (isNumeric(catId1.getText())))
                && ((pathiamge1.getText().length() != 0))) {

            art.setMarque(marqueId1.getText());
            art.setDescription(descId1.getText());
            int id = Integer.parseInt(catId1.getText());
            art.setId_categorie(id);
            float p = Float.parseFloat(prixId1.getText());
            art.setPrix_article(p);
            art.setPathImg(pathiamge1.getText());
            try {
                serArt.AddArticle(art);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);

                alert.setTitle("Success");
                alert.setContentText("Article is added successfully!");
                alert.show();
                MarketController marketController = new MarketController();
                marqueId1.setText("");
                descId1.setText("");
                catId1.setText("");
                prixId1.setText("");
                pathiamge1.setText("");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Invalid fields!");
            alert.show();
        }
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
