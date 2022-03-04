/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportify.service;

import Sportify.utils.DBconnector;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sportify.model.Coach;
import sportify.model.ReservationCoach;

/**
 *
 * @author salim
 */

    public  class ReservationService implements ServiceReservationInterface<ReservationCoach> {
    private Connection conn;
    private PreparedStatement pst;
    private Statement ste;

    public ReservationService() {
        conn = DBconnector.getInstance().getCnx();
    }
    
    public void AddReservation(ReservationCoach reservation) throws SQLException{
        boolean test=true;
        test=VeriefReservation(reservation);
        if(test==false){
        String req = "INSERT INTO `reservationcoach` (`id_coach`, `Id_user`,`Date_res`, `nbrHeur`,`PrixTotal`) VALUES ('"+
             reservation.getId_coach()+"','"+ reservation.getId_user()+ "','" +
                reservation.getDate_res()+"','"+ reservation.getNbrHeur()+"','"+ reservation.getPrixTotal()+"')";
        
        try {
            ste = conn.createStatement();
            ste.executeUpdate(req);
            System.out.println("Reservation ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        }
        else System.out.println("reservation deja existe");
    }
 


    public void DeleteReservation(int RefRes)throws SQLException {
        String req="DELETE FROM reservationcoach WHERE `RefRes`='"+RefRes+"'";
        try {
            ste = conn.createStatement();
            ste.executeUpdate(req);
            System.out.println("La Reservation du cooach "+RefRes+ "est supprimer");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

    }}



      public void modifierReservation(int RefRes ,int id_coach ,int Id_user,Date Date_res,int nbrHeur,float PrixTotal)throws SQLException {
    try {    
    String req="UPDATE reservationcoach SET RefRes='"+RefRes+"',id_coach='"+id_coach+"',Id_user='"+Id_user+
                "',Date_res='"+Date_res+"',nbrHeur='"+nbrHeur+"',PrixTotal='"+PrixTotal+"'WHERE RefRes="+RefRes;
       
            ste = conn.prepareStatement(req);
            ste.executeUpdate(req);
            System.out.println("La Reservation "+RefRes+ "est modifier");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

    }
}
      public List<ReservationCoach> Afficher() {
 List<ReservationCoach> re = new ArrayList<>();
        
        String req = "SELECT * from reservationcoach";
        
        try {
            pst = conn.prepareStatement(req);
            ResultSet rs= pst.executeQuery();
            
            while(rs.next()){
                ReservationCoach r = new ReservationCoach();
                r.setRefRes(rs.getInt("RefRes"));
                r.setId_coach(rs.getInt(2));
                r.setId_user(rs.getInt(3));
                r.setDate_res(rs.getDate(4));
                r.setNbrHeur(rs.getInt(5));
                r.setPrixTotal(rs.getFloat(5));
                re.add(r);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
     return re ;    
  }
 public List<ReservationCoach> TreeReservation() {
        List<ReservationCoach> ListAr = new ArrayList<>();
        String req = "SELECT * FROM `reservationcoach` ORDER BY `Date_res` ASC";
        try {
            pst = conn.prepareStatement(req);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                ReservationCoach r = new ReservationCoach();
                r.setRefRes(rs.getInt("RefRes"));
                r.setId_coach(rs.getInt(2));
                r.setId_user(rs.getInt(3));
                r.setDate_res(rs.getDate(4));
                r.setNbrHeur(rs.getInt(5));
                r.setPrixTotal(rs.getFloat(5));
                ListAr.add(r);
                
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

        return ListAr;
    }

  
  public  List<ReservationCoach> rechercheReservation(int RefRes){
List<ReservationCoach> res = new ArrayList<>();
String query="SELECT * FROM reservationcoach WHERE `RefRes`='"+RefRes+"'";
        try {
            ste = conn.createStatement();
            ResultSet rste = ste.executeQuery(query);
            rste.last();
            int nbrRow = rste.getRow();
            if(nbrRow!=0){ 
                       
        try {
            pst = conn.prepareStatement(query);
            ResultSet rs= pst.executeQuery();
            
            while(rs.next()){
                ReservationCoach r = new ReservationCoach();
                r.setRefRes(rs.getInt("RefRes"));
                r.setId_coach(rs.getInt(2));
                r.setId_user(rs.getInt(3));
                r.setDate_res(rs.getDate(4));
                r.setNbrHeur(rs.getInt(5));
                r.setPrixTotal(rs.getFloat(5));
                res.add(r);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
            System.out.println("reseravation trouvé");
            
            }else{
               System.out.println("reservation non trouvé"); 
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
return res;
}

  
    public boolean VeriefReservation(ReservationCoach reservation) {
        boolean test = false;
        String req = "SELECT * FROM `reservationcoach` WHERE  id_coach="+reservation.getId_coach()+" and Id_user="+reservation.getId_user()+
                " and Date_res='"+reservation.getDate_res()+"' and nbrHeur="+reservation.getNbrHeur()+" and PrixTotal="+reservation.getPrixTotal()+";";
                
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
    public ObservableList<ReservationCoach> getReservation(){
    	ObservableList<ReservationCoach> ResList = FXCollections.observableArrayList();

    
      String req = "SELECT * from reservationcoach";
        
        try {
            pst = conn.prepareStatement(req);
            ResultSet rs= pst.executeQuery();
            
            while(rs.next()){
                ReservationCoach r = new ReservationCoach();
                r.setRefRes(rs.getInt("RefRes"));
                r.setId_coach(rs.getInt(2));
                r.setId_user(rs.getInt(3));
                r.setDate_res(rs.getDate(4));
                r.setNbrHeur(rs.getInt(5));
                r.setPrixTotal(rs.getFloat(5));
                ResList.add(r);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        
     return ResList ; 
  
}
    public void factureClient (ReservationCoach rv){
         
              ArrayList table= new ArrayList <>();
           String file_name="C:\\Users\\salim\\Desktop\\Nouveau dossier\\facture.pdf" ;
 
            
              try {
                 
             Document document=new Document () {};
           PdfWriter.getInstance(document, new FileOutputStream(file_name)); 
          
         
     document.open () ;

        Paragraph para=new Paragraph("Facture Client ");
        document.add (para);
      

                     //simple paragraph
 
                        
                             PdfPTable pdfPTable =new PdfPTable(5);
                              

                              PdfPCell pdfCell1 = new PdfPCell(new Phrase("Id coach ")); 
                            PdfPCell pdfCell2 = new PdfPCell(new Phrase("Id user"));
                             PdfPCell pdfCell3 = new PdfPCell(new Phrase("nobre heure"));
                              PdfPCell pdfCell4 = new PdfPCell(new Phrase("prix totale"));
                              PdfPCell pdfCell5 = new PdfPCell(new Phrase("date reservation"));
                            
                                       
                            
                        
                                      

                                       pdfPTable.addCell(pdfCell1);
                                pdfPTable.addCell(pdfCell2);
                                 pdfPTable.addCell(pdfCell3);
                                  pdfPTable.addCell(pdfCell4);
                                     
                            pdfPTable.addCell(""+rv.getId_coach()+"");
                            pdfPTable.addCell (""+rv.getId_user()+"");
                            pdfPTable.addCell(""+rv.getNbrHeur()+"");
                            pdfPTable.addCell(""+rv.getPrixTotal()+"");
                             pdfPTable.addCell(""+rv.getDate_res()+"");
                            
                          document.add(pdfPTable);
   
                        document.close();
                        document.close ();
                        System.out.println("facture enregistré");

        } catch (Exception Exception) {
         System.out.println(Exception);
 }
    }
    }

   
    
   

    

 
    

