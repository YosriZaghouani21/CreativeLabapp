/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sportify.models.user;

import java.sql.Date;
import java.util.Objects;
import javafx.scene.control.TextField;
//import java.utils.*;

/**
 *
 * @author zagho
 */
public class Reclamation {

    private int IdReclamation;
    private String Description;
    private String Date;
    private int Id;

    public Reclamation(int IdReclamation, String Description, String Date, int Id) {
        this.IdReclamation = IdReclamation;
        this.Description = Description;
        this.Date = Date;
        this.Id = Id;
    }

    public Reclamation(String Description, String Date, int Id) {

        this.Description = Description;
        this.Date = Date;
        this.Id = Id;
    }

    public Reclamation(int IdReclamation) {
        this.IdReclamation = IdReclamation;
    }

    public Reclamation() {
    }

    public Reclamation(TextField IdReclamation, TextField Date, TextField Description, TextField Id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getIdReclamation() {
        return IdReclamation;
    }

    public void setIdReclamation(int IdReclamation) {
        this.IdReclamation = IdReclamation;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "IdReclamation=" + IdReclamation + ", Description=" + Description + ", Date=" + Date + ", Id=" + Id + '}';
    }

 

 

}
