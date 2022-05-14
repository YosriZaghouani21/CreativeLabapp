/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportify.services;

//import Sportifydesktop.infrastructure.DBconnector;
//import Sportifydesktop.infrastructure.IService;
import Sportifydesktop.infrastructure.DBconnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import static javafx.scene.input.KeyCode.T;
import sportify.models.Commande;
import sportify.models.Livraison;
import sportify1.Config;

/**
 *
 * @author lenovo
 */
public class livraisonservice implements IserviceLivraison<Livraison> {
 private Connection conn;
    private PreparedStatement pst;
    private Statement ste;
    
      public livraisonservice() {
        conn = DBconnector.getInstance().getCnx();
    }

   public void Add(Livraison l)  throws SQLException{
      String req = "INSERT INTO `livraison` (`DateLivraison`,`Methtode_payement`,`adresse`,`Com_adresse`,`Code_Postal`,`ville`,`IdCommande`) VALUES ('"+ l.getDateLivraison()+"','"+l.getMethode_payement()+"','"+l.getAdresse()+"','"+l.getComp_adresse()+"','"+l.getCode_postal()+"','"+l.getVille()+"','"+l.getId()+"')";
        
        try {
            ste = conn.createStatement();
            ste.executeUpdate(req);
            System.out.println("livraison ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
  public void Delete(int l ) throws SQLException {
 String req="DELETE FROM livraison WHERE `IdLiv`='"+l+"'";
        try {
            ste = conn.createStatement();
            ste.executeUpdate(req);
            System.out.println("La livraison "+l+ "est supprimer");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());    

    } }

    
    public void Update(int IdLiv,Date DateLivraison, String Methode_payement,String addresse,String Com_adresse,int codepostale,String ville, int Id) throws SQLException {
       try {    
    String req="UPDATE livraison SET DateLivraison='"+DateLivraison+"',Methtode_payement='"+Methode_payement+"',adresse='"+addresse+"',Com_adresse='"+Com_adresse+"',Code_Postal='"+codepostale+"',ville='"+ville+
                "',IdCommande='"+Id+"'WHERE IdLiv="+IdLiv;
       
            ste = conn.prepareStatement(req);
            ste.executeUpdate(req);
            System.out.println("La livraison "+IdLiv+ "est modifier");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

    } }

    @Override
      public List<Livraison> Afficher() {
 List<Livraison> liv = new ArrayList<>();
        
        String req = "SELECT * from `livraison`";
        
        try {
            pst = conn.prepareStatement(req);
            ResultSet rs= pst.executeQuery();
            
            while(rs.next()){
                Livraison l = new Livraison();
                l.setIdLiv(rs.getInt("IdLiv"));
                l.setDateLivraison(rs.getDate(2));
                l.setMethode_payement(rs.getString(3));
                l.setAdresse(rs.getString(4));
                l.setComp_adresse(rs.getString(5));
                l.setCode_postal(rs.getInt(6));
                l.setVille(rs.getString(7));
                l.setId(rs.getInt("IdCommande"));
                liv.add(l);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
     return liv ;    
  }
      public List<Livraison> AfficherTrier() {
 List<Livraison> liv = new ArrayList<>();
        
        String req = "SELECT * FROM `livraison` ORDER BY `livraison`.`DateLivraison` ASC ";
        
        try {
            pst = conn.prepareStatement(req);
            ResultSet rs= pst.executeQuery();
            
            while(rs.next()){
                Livraison l = new Livraison();
                l.setIdLiv(rs.getInt("IdLiv"));
                l.setDateLivraison(rs.getDate(2));
                l.setMethode_payement(rs.getString(3));
                l.setId(rs.getInt("IdCommande"));
                liv.add(l);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
     return liv ;    
  }
public List Recherche(Date DateLivraison ) {
List<Livraison> liv= new ArrayList<>();
        
        String req = "SELECT * from `livraison` WHERE `DateLivraison`=?";
       try {
            pst = conn.prepareStatement(req);
            pst.setDate(1, (java.sql.Date) DateLivraison);
            ResultSet rs= pst.executeQuery();
            
            while(rs.next()){
                Livraison l = new Livraison();
                l.setIdLiv(rs.getInt(1));
                l.setDateLivraison(rs.getDate(2));
                l.setMethode_payement(rs.getString(3));
                l.setId(rs.getInt(4));
                liv.add(l);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        

       return liv ;    
}
public ObservableList<Livraison> getlivraisonList() 

{
    ObservableList<Livraison> Livraisonliste=FXCollections.observableArrayList();
//Connection conn=getConnection(); 
  String req = "SELECT * from `livraison`";
        
        try {
            pst = conn.prepareStatement(req);
            ResultSet rs= pst.executeQuery();
    while(rs.next()){
                
               Livraison l = new Livraison();
               l.setIdLiv(rs.getInt("IdLiv"));
                l.setDateLivraison(rs.getDate(2));
                l.setMethode_payement(rs.getString(3));
                l.setAdresse(rs.getString(4));
                l.setComp_adresse(rs.getString(5));
                l.setCode_postal(rs.getInt(6));
                l.setVille(rs.getString(7));
                l.setId(rs.getInt(8));
               Livraisonliste.add(l);
            }
        } catch (SQLException ex) {
           ex.printStackTrace();
        }
           
  
     return Livraisonliste ;  
        }
 public List<Livraison> trierreservationdate() {
         ArrayList<Livraison> listReservaion = new ArrayList<>();
         try {
            String req = "Select * from livraison";
          PreparedStatement st =   conn.prepareStatement(req);
            ResultSet rs = st.executeQuery(req);
            Livraison re=null;
            while(rs.next()){
                 Livraison l = new Livraison();
               l.setIdLiv(rs.getInt("IdLiv"));
                l.setDateLivraison(rs.getDate(2));
                l.setMethode_payement(rs.getString(3));
                l.setAdresse(rs.getString(4));
                l.setComp_adresse(rs.getString(5));
                l.setCode_postal(rs.getInt(6));
                l.setVille(rs.getString(7));
                l.setId(rs.getInt(8));
             
                listReservaion.add(re);
            }
             Collections.sort(listReservaion, dateComparator);
             Collections.reverse(listReservaion);
             
        } catch (SQLException ex) {
             System.out.println(ex.getMessage());
        }
         return listReservaion;
    }
    
  public static Comparator<Livraison> dateComparator = new Comparator<Livraison>() {

        @Override
	public int compare(Livraison r1, Livraison r2) {
            
            
            String date1 = String.valueOf(r1.getDateLivraison());
        String date2 =  String.valueOf(r2.getDateLivraison());


           return date1.compareTo(date2);

	  
    }
    };

}


    