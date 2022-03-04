/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportify.service;


import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import sportify.model.Livraison;

/**
 *
 * @author lenovo
 */
public interface IserviceLivraison  <T> {
     void Add(Livraison l)throws SQLException;
    void Delete(int l ) throws SQLException;
    void Update(int IdLiv,Date DateLivraison, String Methode_payement,int Id)throws SQLException;
        List<T> Afficher();
    
}
