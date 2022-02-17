/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sportify.models.coachs;
import java.io.BufferedReader;
import java.io.Console;
import java.io.InputStreamReader;
import Sportifydesktop.infrastructure.DBconnector;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

/**
 *
 * @author zagho
 */
public class Main {
     public static void main(String[] args) throws IOException, SQLException {
        // TODO code application logic here
        CoachService ser= new CoachService() ;
         boolean non = false;
        Coach coach = new Coach("mohamed","jridi", non, 15.2f);
        ser.AddCoach(coach);
        ser.DeletCoach(4);
         boolean oui = true;
        ser.modifierCoach(19,"salim","sghaier",oui,11.2f);
        System.out.println(ser.GetById());
        
        Date m2 = new java.sql.Date(2022, 02, 10);
        ReservationService res= new ReservationService() ;
        ReservationCoach reservation = new ReservationCoach(23, 23, m2, 3, 20.5f);
        res.AddReservation(reservation);
        res.DeleteReservation(6);
        res.modifierReservation(19,21,45, (java.sql.Date) m2,4,20.5f);
        System.out.println(res.Afficher());
        
        
        
        
        
        
        
        
        
        
}
}
