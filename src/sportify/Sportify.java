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
       Categorieevenement C2=  new Categorieevenement("gymnastique","sport");
       Categorieevenement C3 =new Categorieevenement("amal","nouveau");
      C.ajouter(C2);
    //  C.ajouter(C3);
        //C.TrierParNOM();
      // System.out.println(C.afficher());
     // C.rechercher("amal");
       System.out.println("*********************recherche*********************");
        C.rechercher1("nouveau");
//       C.modifier(C1);
//       C.modifier(C2);
//       C.supprimer(7);
//       C.supprimer(8);
//       C.supprimer(9);
       
       
//       System.out.println("********************Evenment*********************");
//       eventCrud E=new eventCrud();
//       Evenement E1=new Evenement("jaw","12/06/2021","nabeul","llllll",1);
//       Evenement E2=new Evenement("amal","12/06/2021","****","llllll",1);
//      // E.ajouter(E2);
//      E.TrierParNOM();
//       System.out.println(E.afficher());
//        System.out.println("***********************recherche*************************");
//       E.rechercher("amal");
//      /* E.modifier(E1);
//       E.supprimer(10);
//       E.supprimer(7);
//       E.supprimer(8);*/
//        
//       
//       
//       
    }
    
}
