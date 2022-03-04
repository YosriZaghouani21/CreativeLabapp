/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportify.service;
import Sportify.utils.DBconnector;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import sportify.model.Coach;

/**
 *
 * @author salim
 */
public class CoachService implements ServiceCoachInterface<Coach> {
    private Connection getCnx;
    private Connection conn;
    private PreparedStatement pst;
    private Statement ste;
    private ResultSet rste;
    private int nbrRow;

    public CoachService() {
        conn = DBconnector.getInstance().getCnx();
    }
    
    @Override
    public void AddCoach(Coach coach) throws SQLException{
        boolean test=true;
        test=VeriefCoach(coach);
        if(test==false){
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
        else System.out.println("coach deja existe");
            }
        
    @Override
    public void DeletCoach(int id_coach) throws SQLException {
        String req="DELETE FROM coach WHERE `id_coach`='"+id_coach+"'";
        try {
            ste = conn.createStatement();
            ste.executeUpdate(req);
            System.out.println("Le coach "+id_coach+ "est supprimer");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

    }}

    @Override
      public void modifierCoach(int id_coach,String nom,String prenom,boolean disponibilité,float PrixHeure) throws SQLException {
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
public  List<Coach> rechercheCoach(String nom){
List<Coach> coa = new ArrayList<>();
String query="SELECT * FROM coach WHERE `nom`='"+nom+"'";
        try {
            ste = conn.createStatement();
            rste =ste.executeQuery(query);
            rste.last();
            int nbrRow = rste.getRow();
            if(nbrRow!=0){ 
                       
        try {
            pst = conn.prepareStatement(query);
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
        
            System.out.println("Coach trouvé");
            
            }else{
               System.out.println("Coach non trouvé"); 
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
return coa;
}
public List<Coach> TreeCoach() {
        List<Coach> ListAr = new ArrayList<>();
        String req = "SELECT * FROM `coach` ORDER BY `nom` ASC";
        try {
            pst = conn.prepareStatement(req);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Coach c = new Coach();
                c.setId(rs.getInt("id_coach"));
                c.setNom(rs.getString(2));
                c.setPrenom(rs.getString(3));
                c.setDisponibilité(rs.getBoolean(4));
                c.setPrixHeure(rs.getFloat(5));
                ListAr.add(c);
                
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

        return ListAr;
    }

  
    public boolean VeriefCoach(Coach c) {
        boolean test = false;
        String req = "SELECT * FROM `coach` WHERE  `nom`='" + c.getNom()+ "'" + "AND `prenom`='"
                + c.getPrenom()+ "'";
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
  public ObservableList<Coach> getCoachList(){
    	ObservableList<Coach> coachList = FXCollections.observableArrayList();

    
        String req = "SELECT * from coach";
        
        try {
            pst = conn.prepareStatement(req);
            ResultSet rs= pst.executeQuery();
            
            while(rs.next()){
                Coach c = new Coach();
                c.setId(rs.getInt(1));
                c.setNom(rs.getString(2));
                c.setPrenom(rs.getString(3));
                c.setDisponibilité(rs.getBoolean(4));
                c.setPrixHeure(rs.getFloat(5));
                coachList.add(c);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        
     return coachList ; 
  
}

}

    
    
  
