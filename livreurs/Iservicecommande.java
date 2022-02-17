/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportify.models.livreurs;

import java.util.List;
import static javafx.scene.input.KeyCode.T;

/**
 *
 * @author lenovo
 */
public interface Iservicecommande <T> { 
     void Add(Commande c);
    void Delete(int c );
    void Update(int IdCommande,float PrixTotale,int IdClient,int Id_panier);
    List<T> GetById();
    
}
