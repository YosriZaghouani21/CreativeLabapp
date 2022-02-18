/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionArticle;
import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import GestionArticle.*;
import Sportifydesktop.infrastructure.DBconnector;

public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException{
        // TODO code application logic here
        
        ServiceCategorieArt SerCat=new ServiceCategorieArt();
        Categorie_Article cat= new Categorie_Article("Vêtements", "vet");
        Categorie_Article cat1= new Categorie_Article("Proteine", "volume");
        Article article1= new Article(11, "description", 10.2f, "Adidas");
        Article article2= new Article(11, "descdription", 200.2f, "nike");
        Panier p=new Panier(16, 1);
        ServicePanier pan= new ServicePanier();
        //SerCat.AddCategorie(cat);
        //SerCat.AddCategorie(cat1);
        ServiceArticle SerArt= new ServiceArticle();
        
        //SerCat.DeletCategorie("Vêtements");
        //SerArt.AddArticle(article1);
        //SerArt.AddArticle(article2);
        //SerArt.DeletArticle(4);
        
        System.out.println(SerArt.display());
        //pan.AddPanier(p);
        //pan.DeletPanier(1);
        pan.UpdatePanier(2, 22 , 1);
        System.out.println(pan.display());
        
        
        
        

    }

}