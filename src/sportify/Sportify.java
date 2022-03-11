/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package sportify;

import java.sql.SQLException;
import sportify.models.coachs.Coach;
import sportify.services.coachservice.CoachService;

/**
 *
 * @author zagho
 */
public class Sportify {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
        CoachService coachService=new CoachService();
        Coach coach=new Coach("kk", "ff", true, 51.f,"efef");
        System.out.println(coach);
        coachService.AddCoach(coach);
        System.out.println(coachService.GetById());
    }
    
}
