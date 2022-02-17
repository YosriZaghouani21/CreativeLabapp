/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportify.models.coachs;

import Sportifydesktop.infrastructure.DBconnector;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
    
    public void AddReservation(ReservationCoach reservation){
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
 


    public void DeleteReservation(int RefRes) {
        String req="DELETE FROM reservationcoach WHERE `RefRes`='"+RefRes+"'";
        try {
            ste = conn.createStatement();
            ste.executeUpdate(req);
            System.out.println("La Reservation du cooach "+RefRes+ "est supprimer");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

    }}



      public void modifierReservation(int RefRes ,int id_coach ,int Id_user,Date Date_res,int nbrHeur,float PrixTotal) {
    try {    
    String req="UPDATE reservationcoach SET id_coach='"+RefRes+"',id_coach='"+id_coach+"',Id_user='"+Id_user+
                "',Date_res='"+Date_res+"',nbrHeur='"+nbrHeur+"',PrixTotal='"+PrixTotal+"'WHERE id_coach="+id_coach;
       
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
 
    }

   
    
   

    

 
    

