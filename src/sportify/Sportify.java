/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package sportify;

import java.io.IOException;
import sportify.models.events.Categorieevenement;
import sportify.services.events.categEvCrud;
import sportify.models.events.Evenement;
import sportify.services.events.eventCrud;

/**
 *
 * @author zagho
 */
public class Sportify {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException{
        // TODO code application logic here
              categEvCrud C=new categEvCrud();
        System.out.println("*******************Categorie evenement*******************");
       Categorieevenement C1 =new Categorieevenement("yoga","nouveau");
       C.ajouter(C1);
       System.out.println(C.afficher());
       C.modifier(C1);
       C.supprimer(7);
       C.supprimer(8);
       C.supprimer(9);
       
       
       System.out.println("********************Evenment*********************");
       eventCrud E=new eventCrud();
       Evenement E1=new Evenement("jaw","12/06/2021","nabeul","llllll",1);
       E.ajouter(E1);
       System.out.println(E.afficher());
       E.modifier(E1);
       E.supprimer(10);
       E.supprimer(9);
       E.supprimer(8);
        
       
       
       
    }
    
}
