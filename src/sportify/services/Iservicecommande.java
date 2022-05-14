/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportify.services;

import java.sql.SQLException;
import java.util.List;
import static javafx.scene.input.KeyCode.T;
import sportify.models.Commande;

/**
 *
 * @author lenovo
 */
public interface Iservicecommande <T> { 
     void Add(Commande c) throws SQLException;
    void Delete(int c ) throws SQLException;
    void Update(int IdCommande,float PrixTotale,int IdClient,int Id_panier) throws SQLException;
    List<T> afficher() throws SQLException;
    
}
