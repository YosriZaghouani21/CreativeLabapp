/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sportify.models.user;

/**
 *
 * @author zagho
 */
public class Admin {
    
    public  int IdAdmin;
    public  String NomAdmin ;
    public String PasswordAdmin;

    public Admin(int IdAdmin, String NomAdmin, String PasswordAdmin) {
        this.IdAdmin = IdAdmin;
        this.NomAdmin = NomAdmin;
        this.PasswordAdmin = PasswordAdmin;
    }

    public Admin(int IdAdmin) {
        this.IdAdmin = IdAdmin;
    }

    public Admin(String NomAdmin, String PasswordAdmin) {
        this.NomAdmin = NomAdmin;
        this.PasswordAdmin = PasswordAdmin;
    }

    public int getIdAdmin() {
        return IdAdmin;
    }

    public void setIdAdmin(int IdAdmin) {
        this.IdAdmin = IdAdmin;
    }

    public String getNomAdmin() {
        return NomAdmin;
    }

    public void setNomAdmin(String NomAdmin) {
        this.NomAdmin = NomAdmin;
    }

    public String getPasswordAdmin() {
        return PasswordAdmin;
    }

    public void setPasswordAdmin(String PasswordAdmin) {
        this.PasswordAdmin = PasswordAdmin;
    }
    
    
}
