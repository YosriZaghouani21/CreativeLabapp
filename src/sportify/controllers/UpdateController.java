/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportify.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import java.sql.SQLException;
import java.time.LocalDate;
import javafx.scene.control.Alert;
import sportify.models.articles.Article;
import sportify.services.articles.ServiceArticle;

/**
 * FXML Controller class
 *
 * @author dali
 */
public class UpdateController implements Initializable {

    private int id;

    @FXML
    private TextField upmarque;
    @FXML
    private TextField updesc;
    @FXML
    private TextField upcateg;
    @FXML
    private TextField upimage;
    @FXML
    private TextField upPrix;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // TODO
    }

    @FXML
    private void updatearticle(ActionEvent event) {
        ServiceArticle serviceArticle = new ServiceArticle();
        Article article = new Article();
        article.setRef_articl(id);
        article.setMarque(upmarque.getText());
        article.setDescription(updesc.getText());
        article.setPrix_article(Float.parseFloat(upPrix.getText()));
        article.setId_categorie(Integer.parseInt(upcateg.getText()));
        article.setPathImg(upimage.getText());
        try {
            serviceArticle.UpdateArticle(article);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Article is update!");
            alert.show();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void setTextField(int id, String name, float prix, String desc, int idcat, String path) {

        this.id = id;
        upmarque.setText(name);
        upPrix.setText(Float.toString(prix));
        updesc.setText(desc);
        upcateg.setText(Integer.toString(idcat));
        upimage.setText(path);

    }

}
