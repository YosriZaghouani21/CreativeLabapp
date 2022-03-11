/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportify.services.articles;

import ClickSend.Api.SmsApi;
import ClickSend.ApiClient;
import ClickSend.ApiException;
import ClickSend.Model.SmsMessage;
import ClickSend.Model.SmsMessageCollection;
import sportify.models.articles.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import Sportifydesktop.infrastructure.DBconnector;
import java.util.Arrays;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author dali
 */
public class ServiceArticle implements ArticleInterface<Article> {

    private Connection conn;
    private PreparedStatement pst;
    private Statement ste;

    public ServiceArticle() {
        conn = DBconnector.getInstance().getCnx();
    }

  
    @Override
    public void AddArticle(Article a) throws SQLException {
        boolean test = false;
        test = VeriefAritcle(a);
        if (test == false) {
            String req = "INSERT INTO `article` (`Id_categorie`, `Description`, `Prix_article`, `Marque` , `image`) VALUES ('"
                    + a.getId_categorie() + "','" + a.getDescription() + "','" + a.getPrix_article() + "','"
                    + a.getMarque() + "','" + a.getPathImg() + "')";
            try {
                ste = conn.createStatement();
                ste.executeUpdate(req);
                System.out.println("article est ajouté");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());

            }
        } else {
            System.out.println("l'objet déja créer");
        }
    }

    @Override
    public void DeletArticle(Article a)  throws SQLException {
        System.out.println("ref article"+a.getRef_articl());
        String req = "DELETE FROM `article` WHERE `Ref_article`='" + a.getRef_articl() + "'";
        try {
            ste = conn.createStatement();
            ste.executeUpdate(req);
            System.out.println("L'article de reférence  " + a.getRef_articl() + "est supprimer");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

    }

    @Override
    public void UpdateArticle(Article a)throws SQLException {

        String req = "UPDATE `article` SET `Id_categorie`='"
                + a.getId_categorie() + "',`Description`='"
                + a.getDescription() + "', `Prix_article`='"
                + a.getPrix_article() + "',`Marque`='" + a.getMarque() + "',`image`='"+a.getPathImg()+"'"
                + " WHERE `Ref_article`='"
                + a.getRef_articl() + "'";
        try {
            ste = conn.createStatement();
            ste.executeUpdate(req);
            System.out.println("table update");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

    }

    @Override
    public List<Article> FindArticle( String Marque) {
        List<Article> ListAr = new ArrayList<>();
        String req = "SELECT * FROM `article` WHERE `Marque`='" + Marque + "'";
        try {
            pst = conn.prepareStatement(req);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Article l = new Article();
                l.setRef_articl(rs.getInt("Ref_article"));
                l.setId_categorie(rs.getInt(2));
                l.setDescription(rs.getString(3));
                l.setPrix_article(rs.getFloat(4));
                l.setMarque(rs.getString(5));
                l.setPathImg(rs.getString(6));
                ListAr.add(l);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return ListAr;
    }

    @Override
    public List<Article> display(){
        List<Article> ListAr = new ArrayList<>();

        String req = "SELECT * FROM `article`";

        try {
            pst = conn.prepareStatement(req);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
               Article l = new Article();
                l.setRef_articl(rs.getInt("Ref_article"));
                l.setId_categorie(rs.getInt(2));
                l.setDescription(rs.getString(3));
                l.setPrix_article(rs.getFloat(4));
                l.setMarque(rs.getString(5));
                l.setPathImg(rs.getString(6));
                ListAr.add(l);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

        return ListAr;
    }

    @Override
    public List<Article> TreeAticleListe() {
        List<Article> ListAr = new ArrayList<>();
        String req = "SELECT * FROM `article` ORDER BY `Marque` ASC";
        try {
            pst = conn.prepareStatement(req);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Article l = new Article();
                l.setRef_articl(rs.getInt("Ref_article"));
                l.setId_categorie(rs.getInt(2));
                l.setDescription(rs.getString(3));
                l.setPrix_article(rs.getFloat(4));
                l.setMarque(rs.getString(5));
                l.setPathImg(rs.getString(6));
                ListAr.add(l);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

        return ListAr;
    }
    public List<Article> TreeAticlePrixListe() {
        List<Article> ListAr = new ArrayList<>();
        String req = "SELECT * FROM `article` ORDER BY `Prix_article` ASC";
        try {
            pst = conn.prepareStatement(req);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Article l = new Article();
                l.setRef_articl(rs.getInt("Ref_article"));
                l.setId_categorie(rs.getInt(2));
                l.setDescription(rs.getString(3));
                l.setPrix_article(rs.getFloat(4));
                l.setMarque(rs.getString(5));
                l.setPathImg(rs.getString(6));
                ListAr.add(l);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

        return ListAr;
    }
    @Override
    public boolean VeriefAritcle(Article A) {
        boolean test = false;
        String req = "SELECT * FROM `article` WHERE `Id_categorie`='" + A.getId_categorie() + "'"
                + "AND `Description`='" + A.getDescription() + "'" + "AND `Marque`='"
                + A.getMarque() + "'";
        try {
            ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            rs.last();
            int nbrRow = rs.getRow();
            if (nbrRow != 0) {
                test = true;
            } else {
                test = false;
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return test;

    }
    public ObservableList<Article> getArticleList(){
    	ObservableList<Article> ListAr = FXCollections.observableArrayList();

    
        String req = "SELECT * from coach";
        
        try {
            pst = conn.prepareStatement(req);
            ResultSet rs= pst.executeQuery();
            
            while(rs.next()){
                Article l = new Article();
                l.setRef_articl(rs.getInt("Ref_article"));
                l.setId_categorie(rs.getInt(2));
                l.setDescription(rs.getString(3));
                l.setPrix_article(rs.getFloat(4));
                l.setMarque(rs.getString(5));
                ListAr.add(l);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        
     return ListAr ; 
  
}

    @Override
    public Article getArticleById(Article A) {
        Article l = new Article();
        String req = "SELECT * FROM `article` WHERE `Ref_article`='"+A.getRef_articl()+"'";
        try {
            pst = conn.prepareStatement(req);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                
                l.setRef_articl(rs.getInt("Ref_article"));
                l.setId_categorie(rs.getInt(2));
                l.setDescription(rs.getString(3));
                l.setPrix_article(rs.getFloat(4));
                l.setMarque(rs.getString(5));
                l.setPathImg(rs.getString(6));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return l;
    }
     public void sms()
    {
        ApiClient defaultClient = new ApiClient();
        defaultClient.setUsername("mouldi.mohamedali@esprit.tn");
        defaultClient.setPassword("C6F812AB-729B-B017-E782-77337785BEBA"); //password
        SmsApi apiInstance = new SmsApi(defaultClient);

        SmsMessage smsMessage = new SmsMessage();
        smsMessage.body("nouvouté la collection nike aire_forces est en stock" );
        smsMessage.to("+216"+97818268);
        smsMessage.source("Candidature");
        

        List<SmsMessage> smsMessageList = Arrays.asList(smsMessage);
        // SmsMessageCollection | SmsMessageCollection model
        SmsMessageCollection smsMessages = new SmsMessageCollection();
        smsMessages.messages(smsMessageList);
        try {
            String result = apiInstance.smsSendPost(smsMessages);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling SmsApi#smsSendPost");
            e.printStackTrace();
        }
    }

}
