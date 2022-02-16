/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sportify.models.user;

import java.sql.Date;
import java.util.Objects;
//import java.utils.*;

/**
 *
 * @author zagho
 */
public class Reclamation {

    private int IdReclamation;
    private String Description;
    private Date Date;
    private User Reclamateur;

    public Reclamation(int IdReclamation, String Description, Date Date, User Reclamateur) {
        this.IdReclamation = IdReclamation;
        this.Description = Description;
        this.Date = Date;
        this.Reclamateur = Reclamateur;
    }

    public Reclamation(String Description, Date Date, User Reclamateur) {

        this.Description = Description;
        this.Date = Date;
        this.Reclamateur = Reclamateur;
    }

    public Reclamation(int IdReclamation) {
        this.IdReclamation = IdReclamation;
    }

    public Reclamation() {
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

    public Date getDate() {
        return Date;
    }

    public void setDate(Date Date) {
        this.Date = Date;
    }

    public User getReclamateur() {
        return Reclamateur;
    }

    public void setReclamateur(User Reclamateur) {
        this.Reclamateur = Reclamateur;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "IdReclamation=" + IdReclamation + ", Description=" + Description + ", Date=" + Date + ", Reclamateur=" + Reclamateur + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Reclamation other = (Reclamation) obj;
        if (this.IdReclamation != other.IdReclamation) {
            return false;
        }

        if (!Objects.equals(this.Description, other.Description)) {
            return false;
        }

        if (!Objects.equals(this.Date, other.Date)) {
            return false;
        }
        if (!Objects.equals(this.Reclamateur, other.Reclamateur)) {
            return false;
        }
        return true;
    }

}
