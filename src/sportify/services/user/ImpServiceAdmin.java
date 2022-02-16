/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sportify.services.user;
import Sportifydesktop.infrastructure.DBconnector;
import java.sql.Connection;
import sportify.models.user.Admin;
import java.sql.PreparedStatement;
import java.util.List;




/**
 *
 * @author zagho
 */
public class ImpServiceAdmin implements InterfaceServiceAdmin {
    private Connection dbcon;
    private PreparedStatement pst;
    
    
    public ImpServiceAdmin() {
    dbcon = DBconnector.getInstance().getCnx();
    }

    @Override
    public void Update(Admin entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Delete(Admin entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Admin Add(Admin entity) {
       String req = "INSERT INTO user(Nom, Password) VALUES (?,?)";
       try {
           pst = dbcon.prepareStatement(req);
           pst.setString(1, entity.getNomAdmin());
           pst.setString(2, entity.);
           pst.setString(3, entity.getNomAdmin());
           pst.setString(4, entity.getNomAdmin());
           pst.setString(5, entity.getNomAdmin());
           pst.setString(6, entity.getNomAdmin());
       
       
       
       
       } catch (SQLException ex) {
           System.err.println(ex.getMessage());
           return null;
       }
    }

    @Override
    public List<Admin> Getall() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Admin GetById(int ID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     
    
}
     
