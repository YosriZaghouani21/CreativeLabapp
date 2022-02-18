/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionArticle;

/**
 *
 * @author dali
 */
public class Categorie_Article {
    
    private int Id_categorie;
    private String Nom_categorie;
    private String Type_categorie;

    public Categorie_Article() {
    }

    public Categorie_Article(int Id_categorie, String Nom_categorie, String Type_categorie) {
        this.Id_categorie = Id_categorie;
        this.Nom_categorie = Nom_categorie;
        this.Type_categorie = Type_categorie;
    }

    public Categorie_Article(String Nom_categorie, String Type_categorie) {
        this.Nom_categorie = Nom_categorie;
        this.Type_categorie = Type_categorie;
    }

    public int getId_categorie() {
        return Id_categorie;
    }

    public void setId_categorie(int Id_categorie) {
        this.Id_categorie = Id_categorie;
    }

    public String getNom_categorie() {
        return Nom_categorie;
    }

    public void setNom_categorie(String Nom_categorie) {
        this.Nom_categorie = Nom_categorie;
    }

    public String getType_categorie() {
        return Type_categorie;
    }

    public void setType_categorie(String Type_categorie) {
        this.Type_categorie = Type_categorie;
    }

    @Override
    public String toString() {
        return "Categorie_Article{" + "Id_categorie=" + Id_categorie + ", Nom_categorie=" + Nom_categorie + ", Type_categorie=" + Type_categorie + '}';
    }
    
    
}
