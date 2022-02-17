/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportify.models.livreurs;

import java.util.Date;
import java.util.List;

/**
 *
 * @author lenovo
 */
public interface IserviceLivraison  <T> {
     void Add(Livraison l);
    void Delete(int l );
    void Update(int IdLiv,Date DateLivraison, String Methode_payement,int Id);
    List<T> GetById();
    
}
