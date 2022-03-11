/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportify.models.articles;

/**
 *
 * @author dali
 */
public class Article {
    
    private int Ref_articl, Id_categorie;
    private String Description;
    private float Prix_article;
    private String Marque;
    private String pathImg;

    public Article() {
    }

    public Article(int Ref_articl, int Id_categorie, String Description, float Prix_article, String Marque,String imgpth) {
        this.Ref_articl = Ref_articl;
        this.Id_categorie = Id_categorie;
        this.Description = Description;
        this.Prix_article = Prix_article;
        this.Marque = Marque;
        this.pathImg= imgpth;
    }

 

    public int getRef_articl() {
        return Ref_articl;
    }

    public void setRef_articl(int Ref_articl) {
        this.Ref_articl = Ref_articl;
    }

    public int getId_categorie() {
        return Id_categorie;
    }

    public void setId_categorie(int Id_categorie) {
        this.Id_categorie = Id_categorie;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public float getPrix_article() {
        return Prix_article;
    }

    public void setPrix_article(float Prix_article) {
        this.Prix_article = Prix_article;
    }

    public String getMarque() {
        return Marque;
    }

    public void setMarque(String Marque) {
        this.Marque = Marque;
    }

    public void setPathImg(String pathImg) {
        this.pathImg = pathImg;
    }

    public String getPathImg() {
        return pathImg;
    }
    

    @Override
    public String toString() {
        return "Article{" + "Ref_articl=" + Ref_articl + ", Id_categorie=" +
                Id_categorie + ", Description=" + Description +
                ", Prix_article=" + Prix_article + ", Marque=" + Marque + ", image=" + pathImg + '}';
    }
    
    
    
    
    
    
    
}
