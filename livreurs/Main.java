/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sportify.models.livreurs;
import Sportifydesktop.infrastructure.IService;
import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


import static javafx.scene.input.KeyCode.L;

/**
 *
 * @author zagho
 */
public class Main { 
    
          public static void main(String[] args) throws IOException {
              
          
         
         Date thisDate=new Date();
         SimpleDateFormat m1=new SimpleDateFormat("MM/dd/YYYY");
        livraisonservice ls = new livraisonservice(); 

        
           
              Date m2= new java.sql.Date(19,01,1999);
        Livraison L = new Livraison(6,m2,"cache",11);
        Commande C=new Commande((float) 5.5,1,1);
        commandeservice cs= new commandeservice();
      //ls.AddL(L); 
     //System.out.println(ls.GetById());
    // ls.Delete(10);
    //cs.Add(C);
    // ls.Add(L);
              System.out.println(cs.GetById());
     //ls.Update(10,m2,"par carte", 10);
                
    
    
} }
