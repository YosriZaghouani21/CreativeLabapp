/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportify.service;
import java.io.*;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import static javafx.collections.FXCollections.observableList;
import static javafx.collections.FXCollections.observableList;
import static javafx.collections.FXCollections.observableList;
import javafx.collections.ObservableList;
import sportify.model.Commande;
import sportify.utils.DBconnector;

  



/**
 *
 * @author lenovo
 */
public class commandeservice implements Iservicecommande<Commande>{

       public commandeservice() {
        conn = DBconnector.getInstance().getCnx();
    }
 private Connection conn;
    private PreparedStatement pst;
    private Statement ste;
 @Override
    public void Add(Commande c) throws SQLException{
        String req = "INSERT INTO `commande` (`PrixTotale`, `Id_user`,`Id_panier`) VALUES ('"+ c.getPrixTotale()+"','"+ c.getIdClient()+"','"+ c.getId_panier()+"')";
        
        try {
            ste = conn.createStatement();
            ste.executeUpdate(req);
            System.out.println("commande ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
 public void Delete(int c ) throws SQLException {
 String req="DELETE FROM commande WHERE `IdCommande`='"+c+"'";
        try {
            ste = conn.createStatement();
            ste.executeUpdate(req);
            System.out.println("La commande "+c+ "est supprimer");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());    

    } }  
   public void Update(int IdCommande,float PrixTotale,int IdClient,int Id_panier) throws SQLException {
       try {    
 String req="UPDATE commande SET PrixTotale='"+PrixTotale+"',Id_user='"+IdClient+
                "',Id_panier='"+Id_panier+"'WHERE IdCommande="+IdCommande;
       
            ste = conn.prepareStatement(req);
            ste.executeUpdate(req);
            System.out.println("Le commande "+IdCommande+ "est modifier");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

    } }
/*
 
   @Override
    public List<Commande> afficher() throws SQLException {
        List<Commande> com = new ArrayList<>();
        String req = "select * from commande";
        ste = conn.createStatement();
        ResultSet rst = ste.executeQuery(req);

        while (rst.next()) {
            Commande c = new Commande(rst.getInt("IdCommande"),//or rst.getInt(1)
                    c.setId(rs.getInt(1));
                c.setPrixTotale(rs.getFloat(2));
                c.setIdClient(rs.getInt(3));
                c.setId_panier(rs.getInt(4));
            com.add(c);
        }
        return com;
    }
   */
   
    @Override
 
    
    public List<Commande> afficher() {
List<Commande> com= new ArrayList<>();
        
        String req = "SELECT * FROM `commande`";
        
        try {
            pst = conn.prepareStatement(req);
            ResultSet rs= pst.executeQuery();
            
            while(rs.next()){
                
                Commande c = new Commande();
                c.setId(rs.getInt(1));
                c.setPrixTotale(rs.getFloat(2));
                c.setIdClient(rs.getInt(3));
                c.setId_panier(rs.getInt(4));
                com.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
        
     return com ;    
  }
 public List Recherche(int Idcommande ) {
List<Commande> com= new ArrayList<>();
        
        String req = "SELECT * FROM `commande` WHERE IdCommande="+Idcommande;
        
        try {
            pst = conn.prepareStatement(req);
            ResultSet rs= pst.executeQuery();
            
            while(rs.next()){
                Commande c = new Commande();
                c.setId(rs.getInt("IdCommande"));
                c.setPrixTotale(rs.getFloat(1));
                c.setIdClient(rs.getInt("Id_user"));
                c.setId_panier(rs.getInt("Id_panier"));
                com.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
        
     return com ;    
  }

  public List<Commande> AfficherTrier() {
List<Commande> com= new ArrayList<>();
        
        String req = "SELECT * FROM `commande` ORDER BY `commande`.`PrixTotale` ASC ";
        
        try {
            pst = conn.prepareStatement(req);
            ResultSet rs= pst.executeQuery();
            
            while(rs.next()){
                
                Commande c = new Commande();
                c.setId(rs.getInt(1));
                c.setPrixTotale(rs.getFloat(2));
                c.setIdClient(rs.getInt(3));
                c.setId_panier(rs.getInt(4));
                com.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
           
  
     return com ;   
 
 }  
  
   /*  public List<user> Getall() {
           List<user> users = new ArrayList<>();
           String req = "SELECT * from User";
           
           try {
             pst = conn.prepareStatement(req);
             ResultSet rs = pst.executeQuery();
             
             user u = new user();
             rs.next();
             u.setId(rs.getInt(1));
             u.setNom(rs.getString(2));
             u.setPrenom(rs.getString(3));
             u.setAdresse(rs.getString(4));
             u.setEmail(rs.getString(5));
             u.setPassword(rs.getString(6));
             u.setType(rs.getString(7));
             u.setNum(rs.getString(8));
             
            users.add(u);
             
         } catch (Exception e) {
         System.err.println(e.getMessage());
        
         }
        return users;
     
     }*/
    

public void factureClient (Commande rv){
         
              ArrayList table= new ArrayList <>();
           String file_name="C:\\Users\\lenovo\\Desktop\\pdf_gen\\facture.pdf" ;
 
            
              try {
                 
             Document document=new Document () {};
           PdfWriter.getInstance(document, new FileOutputStream(file_name)); 
          
         
     document.open () ;

        Paragraph para=new Paragraph("Facture Client ");
        document.add (para);
      

                     //simple paragraph
 
                        
                             PdfPTable pdfPTable =new PdfPTable(4);
                              

                              PdfPCell pdfCell1 = new PdfPCell(new Phrase("Id Commande ")); 
                            PdfPCell pdfCell2 = new PdfPCell(new Phrase("Id client"));
                             PdfPCell pdfCell3 = new PdfPCell(new Phrase("id Paner"));
                              PdfPCell pdfCell4 = new PdfPCell(new Phrase("Prix Totale"));
                            
                                       
                            
                        
                                      

                                       pdfPTable.addCell(pdfCell1);
                                pdfPTable.addCell(pdfCell2);
                                 pdfPTable.addCell(pdfCell3);
                                  pdfPTable.addCell(pdfCell4);
                                     
                            pdfPTable.addCell(""+rv.getId()+"");
                            pdfPTable.addCell (""+rv.getIdClient()+"");
                            pdfPTable.addCell(""+rv.getId_panier()+"");
                            pdfPTable.addCell(""+rv.getPrixTotale()+"");
                            
                          document.add(pdfPTable);
   
                        document.close();
                        document.close ();
                        System.out.println("facture enregistré");

        } catch (Exception Exception) {
         System.out.println(Exception);
 }
    }
public ObservableList<Commande> getcommandeList() 

{
    ObservableList<Commande> Commandeliste=FXCollections.observableArrayList();
//Connection conn=getConnection(); 
  String req = "SELECT * FROM `commande`";
        
        try {
            pst = conn.prepareStatement(req);
            ResultSet rs= pst.executeQuery();
    while(rs.next()){
                
                Commande c = new Commande();
                c.setId(rs.getInt(1));
                c.setPrixTotale(rs.getFloat(2));
                c.setIdClient(rs.getInt(3));
                c.setId_panier(rs.getInt(4));
                Commandeliste.add(c);
            }
        } catch (SQLException ex) {
           ex.printStackTrace();
        }
           
  
     return Commandeliste ;  
        }
}


