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
    private String Sujet;
    private String Description;
    private String Traitement;
    private Date Date;
    private User Reclamateur;

    public Reclamation(int IdReclamation, String Sujet, String Description, String Traitement, Date Date, User Reclamateur) {
        this.IdReclamation = IdReclamation;
        this.Sujet = Sujet;
        this.Description = Description;
        this.Traitement = Traitement;
        this.Date = Date;
        this.Reclamateur = Reclamateur;
    }

    public Reclamation(String Sujet, String Description, String Traitement, Date Date, User Reclamateur) {
        this.Sujet = Sujet;
        this.Description = Description;
        this.Traitement = Traitement;
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

    public String getSujet() {
        return Sujet;
    }

    public void setSujet(String Sujet) {
        this.Sujet = Sujet;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getTraitement() {
        return Traitement;
    }

    public void setTraitement(String Traitement) {
        this.Traitement = Traitement;
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
        return "Reclamation{" + "IdReclamation=" + IdReclamation + ", Sujet=" + Sujet + ", Description=" + Description + ", Traitement=" + Traitement + ", Date=" + Date + ", Reclamateur=" + Reclamateur + '}';
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
        if (!Objects.equals(this.Sujet, other.Sujet)) {
            return false;
        }
        if (!Objects.equals(this.Description, other.Description)) {
            return false;
        }
        if (!Objects.equals(this.Traitement, other.Traitement)) {
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
