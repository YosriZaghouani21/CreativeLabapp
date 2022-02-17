/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportify.models.coachs;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import Sportifydesktop.infrastructure.DBconnector;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author salim
 */
public class CoachService implements ServiceCoachInterface<Coach> {
    private Connection conn;
    private PreparedStatement pst;
    private Statement ste;

    public CoachService() {
        conn = DBconnector.getInstance().getCnx();
    }
    
    public void AddCoach(Coach coach){
        String req = "INSERT INTO `coach` (`nom`, `prenom`, `disponibilité`,`prixHeure`) VALUES ('"+
                coach.getNom() +"','"+ coach.getPrenom()+"','"+ coach.getDisponibilité()+"','"+
                coach.getPrixHeure()+"')";
        
        try {
            ste = conn.createStatement();
            ste.executeUpdate(req);
            System.out.println("coach ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
        
    public void DeletCoach(int id_coach) {
        String req="DELETE FROM coach WHERE `id_coach`='"+id_coach+"'";
        try {
            ste = conn.createStatement();
            ste.executeUpdate(req);
            System.out.println("Le coach "+id_coach+ "est supprimer");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

    }}

    @Override
      public void modifierCoach(int id_coach,String nom,String prenom,boolean disponibilité,float PrixHeure) {
    try {    
    String req="UPDATE coach SET id_coach='"+id_coach+"',nom='"+nom+"',prenom='"+prenom+
                "',disponibilité='"+disponibilité+"',prixHeure='"+PrixHeure+"'WHERE id_coach="+id_coach;
       
            ste = conn.prepareStatement(req);
            ste.executeUpdate(req);
            System.out.println("Le coach "+id_coach+ "est modifier");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

    }
}

   

public List<Coach> GetById() {
 List<Coach> coa = new ArrayList<>();
        
        String req = "SELECT * from coach";
        
        try {
            pst = conn.prepareStatement(req);
            ResultSet rs= pst.executeQuery();
            
            while(rs.next()){
                Coach c = new Coach();
                c.setId(rs.getInt("id_coach"));
                c.setNom(rs.getString(2));
                c.setPrenom(rs.getString(3));
                c.setDisponibilité(rs.getBoolean(4));
                c.setPrixHeure(rs.getFloat(5));
                coa.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
     return coa ;    
  }

 

}

    
    
  
